(function() {
  'use strict';
  
  var injectParams = [ '$scope', '$filter', 'transactionService',
      'adminService', 'dateFactory', 'modalService', '$location' ];
  var MECO_PROVIDER = 'meco';
  var VECO_PROVIDER = 'veco';
  var BillingController = function($scope, $filter, transactionService,
      adminService, dateFactory, modalService, pdfService, $location) {
    var vm = this, collection = {}, // model to map to services
    paymentTypes = [ "cash", "check", "online" ], COLLECTION_DAYS = 30;
    
    vm.popup = modalService;
    vm._selected;
    vm.model = {}; // model for ui;
    vm.model.total = 0;
    vm.totalOverdue = 0;
    vm.model.existingBalance = 0;
    vm.electricProvider = {};
    vm.model.paymentType = paymentTypes[0];
    vm.pdf = "";
    vm.label = {
      deposit : "Expenses",
      total : "Total"
    }
    vm.bill = {};
    vm.bill.tax = {};
    vm.payment = {
      cash : true,
      credit : false,
      check : false,
      paypal : false,
      visa : false,
      master : false
    };
    vm.billingTitle = "Electric Billing";
    vm.showRoomInput = false;
    // vm.apartments = getApartments();
    getApartments();
    vm.errors = {
      diffReading : false,
      currentReading : false
    };
    // set the default active tab
    vm.isElectricBilling = true;
    
    /** set the tab pages ** */
    vm.setActivePage = function(tab) {
      vm.isPropertyBilling = false;
      vm.isElectricBilling = false;
      vm.isWaterBilling = false;
      switch (tab) {
      case 'property':
        vm.isPropertyBilling = true;
        break;
      case 'water':
        vm.isWaterBilling = true;
        break;
      default:
        vm.isElectricBilling = true;
        break;
      
      }
    };
    
    /** retrieve the billings of the apartment * */
    vm.getBillings = function(apt) {
      var type = 'electric';
      vm.meterNo = apt.electricAccount;
      getBillings(apt.id, type);
    };
    
    vm.modelOptions = {
      debounce : {
        "default" : 500,
        blur : 250
      },
      getterSetter : true
    };
    
    vm.removeItem = function(index) {
      if (vm.rooms && vm.rooms.length > 0) {
        if (index > -1) {
          vm.rooms.splice(index, 1);
        }
      }
    };
    
    vm.computeBilling = function() {
      if (vm.rooms && vm.rooms.length > 0) {
        vm.totalOverdue = 0;
        vm.bill.currentBill = 0;
        vm.totalAmount = 0;
        for (var i = 0; i < vm.rooms.length; i++) {
          var room = vm.rooms[i];
          vm.electricProvider = room.electricProvider;
          var electricParam = room.electricProvider;
          room.diffReading = room.currentReading - room.previousReading;
          // rate
          vm.bill.generationCharge = room.diffReading
              * electricParam.generationCharge;
          
          vm.bill.transmissionCharge = room.diffReading
              * electricParam.transmissionCharge;
          vm.bill.systemLossCharge = room.diffReading
              * electricParam.systemLoss;
          vm.bill.subTotal1 = vm.bill.transmissionCharge
              + vm.bill.generationCharge + vm.bill.systemLossCharge;
          // Distribution revenues
          vm.bill.distributionCharge = room.diffReading
              * electricParam.distributionCharge;
          vm.bill.supplyCharge = room.diffReading * electricParam.supplyCharge;
          vm.bill.meteringCharge = room.diffReading
              * electricParam.meteringSystem;
          vm.bill.customerCharge = electricParam.retailCustomer;
          vm.bill.subTotal2 = vm.bill.distributionCharge + vm.bill.supplyCharge
              + vm.bill.meteringCharge + vm.bill.customerCharge;
          // others
          vm.bill.subsidyOnLifelineCharge = room.diffReading
              * electricParam.lifeLineSubsidy;
          // only available for senior citizens
          vm.bill.seniorCitizenSubsidyCharge = room.diffReading
              * electricParam.seniorCitizenSubsidy;
          // surcharge * overdue
          vm.bill.surcharge = room.overdue * electricParam.surcharge;
          vm.bill.subTotal3 = vm.bill.subsidyOnLifelineCharge
              + vm.bill.seniorCitizenSubsidyCharge + vm.bill.surcharge;
          
          // government charges
          vm.bill.tax.localFranchise = electricParam.localFranchiseTax
              * (vm.bill.subTotal1 + vm.bill.subTotal2 + vm.bill.subTotal3);
          vm.bill.tax.generation = room.diffReading
              * electricParam.generationTax;
          vm.bill.tax.transmission = room.diffReading
              * electricParam.transmissionCharge;
          vm.bill.tax.systemLoss = room.diffReading
              * electricParam.systemLossTax;
          vm.bill.tax.distribution = electricParam.distributionTax
              * vm.bill.subTotal2;
          vm.bill.tax.others = electricParam.othersTax * vm.bill.subTotal3;
          vm.bill.tax.missionaryElectrification = room.diffReading
              * electricParam.missionaryElectrification;
          vm.bill.tax.environmental = room.diffReading
              * electricParam.environmentalCharge;
          vm.bill.tax.npc = room.diffReading * electricParam.npc;
          vm.bill.tax.fitAllRenewable = room.diffReading
              * electricParam.fitAllRenewable;
          vm.bill.subTotal4 = vm.bill.tax.localFranchise
              + vm.bill.tax.generation + vm.bill.tax.systemLoss
              + vm.bill.tax.distribution + vm.bill.tax.others
          vm.bill.tax.environmental + vm.bill.tax.npc
              + vm.bill.tax.fitAllRenewable;
          vm.bill.grossAmount = vm.bill.subTotal1 + vm.bill.subTotal2
              + vm.bill.subTotal3 + vm.bill.subTotal4 + room.overdue;
          vm.bill.currentAmount = vm.bill.subTotal1 + vm.bill.subTotal2
              + vm.bill.subTotal3 + vm.bill.subTotal4;
          
          vm.totalOverdue += room.overdue;
          room.amount = vm.bill.currentAmount;
          room.grossAmount = parseFloat(vm.bill.grossAmount.toFixed(2));
          room.bill = vm.bill;
          vm.rooms[i] = room;
          vm.totalAmount += vm.bill.grossAmount;
          
        }
        // fixed all bill properties to 2 decimal
        for ( var property in vm.bill) {
          if (property == 'tax') {
            for ( var prop in vm.bill.tax) {
              vm.bill.tax[prop] = parseFloat(vm.bill.tax[prop].toFixed(2));
            }
          } else {
            vm.bill[property] = parseFloat(vm.bill[property].toFixed(2));
          }
        }
        vm.totalOverdue = parseFloat(vm.totalOverdue.toFixed(2));
        vm.totalAmount = parseFloat(vm.totalAmount.toFixed(2));
      }
    };
    
    $scope.$watch(function(scope) {
      return {
        rooms : scope.vm.rooms
      };
    }, function(newValue, oldValue) {
      vm.errors.currentReading = false;
      if (vm.rooms && vm.rooms.length > 0) {
        for (var i = 0; i < vm.rooms.length; i++) {
          if (vm.rooms[i].currentReading < 1
              || vm.rooms[i].currentReading == "") {
            vm.errors.currentReading = true;
          }
          vm.rooms[i].diffReading = (vm.rooms[i].currentReading || 0)
              - (vm.rooms[i].previousReading || 0);
          if (vm.rooms[i].diffReading < 1) {
            vm.rooms[i].diffReading = 0;
          }
        }
        vm.computeBilling();
      }
    }, true)

    vm.submit = function() {
      submit();
    }

    /**
     * ************************* functions that need to interact with services
     * *****************
     */
    
    /**
     * get rooms by apartment ID
     */
    function getBillings(aptId, type) {
      return adminService.getBillings(aptId, type).then(function(response) {
        processResponse(response);
      });
    }
    /**
     * fetch all the available apartments
     */
    function getApartments() {
      return adminService.getApartments().then(function(response) {
        processResponse(response);
      });
    }
    
    function getPdf(model) {
      return transactionService.getPdf(model).then(function(response) {
        processResponse(response);
      });
    }
    
    /**
     * fetch the list of available apartments by apartment Id
     */
    function getApartment() {
      return adminService.getApartment(aptId).then(function(response) {
        processResponse(response);
      });
    }
    function hasError() {
      var hasError = false;
      if (!vm.readingDate) {
        return true;
      }
      for ( var prop in vm.errors) {
        if (vm.errors[prop] == true) {
          hasError = true;
          break;
        }
      }
      return hasError;
    }
    /**
     * submit the form
     */
    function submit() {
      
      if (hasError()) {
        vm.popup
            .showError("Oops! there is something wrong with the input data.<br>Please check....");
        
        return;
      }
      
      var tx = {
        userId : 1,
        aptId : vm.model.apartment.id,
        rooms : vm.rooms,
        totalAmount : 0,
        totalOverdue : 0,
        readingDate : vm.readingDate,
        accountNumber : vm.meterNo,
        electricProvider : vm.electricProvider
      }
      transactionService
          .generateBillings(tx)
          .then(
              function(response) {
                console.log("status return :" + response);
                if (response.responseStatus === "ERROR") {
                  vm.popup
                      .showError("There is something wrong in processing your request: "
                          + response.errorMsg);
                } else {
                  var options = {
                    title : "Thank You",
                    text : "Transaction successfully processed",
                    type : "success",
                  }
                  tx.billingNo = response.data.model.billingNo;
                  vm.popup.show(options, function() {
                    // $location.path('/home');
                    getPdf(tx);
                  });
                }
                
              });
    }
    
    function getPdf(form) {
      transactionService.getPdf(form).then(function(response) {
        processResponse(response);
      });
    }
    var saveByteArray = (function() {
      var a = document.createElement("a");
      document.body.appendChild(a);
      a.style = "display: none";
      return function(data, name) {
        var blob = new Blob([ data ], {
          type : 'application/pdf'
        }), url = window.URL.createObjectURL(blob);
        a.href = url;
        a.download = name;
        a.click();
        window.URL.revokeObjectURL(url);
      };
    }());
    
    /** test centralize response function * */
    function processResponse(response) {
      console.log(response);
      var data = response.data;
      switch (response.method) {
      case 'getBillings':
        vm.rooms = [];
        vm.rooms = data.result;
        vm.computeBilling();
        break;
      case 'getApartment':
        vm.rooms = [];
        var rooms = data.result;
        for (var i = 0; i < rooms.length; i++) {
          var room = {
            id : rooms[i].id,
            label : ordinal(rooms[i].floor) + ' Floor - Room #'
                + rooms[i].roomNo
          }
          vm.rooms.push(room);
        }
        break;
      case 'getApartments':
        vm.apartments = data.result;
        break;
      case 'getPdf':
        saveByteArray(data, 'sample.pdf');
        // PDF.js
        // var data = {data:result};
        // pdfService.view(data);
        // var file = new Blob([data], {type: 'application/pdf'});
        // vm.pdf = file;
        // var fileURL = URL.createObjectURL(file);
        // window.open(fileURL);
        break;
      }
      
    }
    
  };
  
  BillingController.$inject = injectParams;
  angular.module('rental').controller('BillingController', BillingController);
  
})();
