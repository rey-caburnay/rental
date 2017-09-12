(function() {
  'use strict';
  
  var injectParams = [ '$scope', '$filter', 'transactionService',
      'adminService', 'dateFactory', 'modalService', '$location' ];
  var MECO_PROVIDER = 'meco';
  var VECO_PROVIDER = 'veco';
  
  var CollectionController = function($scope, $filter, transactionService,
      adminService, dateFactory, modalService, $location) {
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
    vm.label = {
      deposit : "Expenses",
      total : "Total"
    }
    vm.cash = {
        amountPaid : '',
        cashChange : '',
        cashReceived : ''
    }
    vm.payment = {
      cash : true,
      credit : false,
      check : false,
      paypal : false,
      visa : false,
      master : false
    };
    vm.collectionTitle = "Electric";
    vm.showRoomInput = false;
    // vm.apartments = getApartments();
    getApartments();
    // set the default active tab
    vm.isElectricCollection = true;
    vm.collectionType = 'electric';
    
    /** set the tab pages ***/
    vm.setActivePage = function(tab) {
      vm.isPropertyCollection = false;
      vm.isElectricCollection= false;
      vm.isWaterCollection = false;
      switch (tab) {
      case 'property':
        vm.isPropertyCollection = true;
        vm.collectionType = 'property';
        break;
      case 'water':
        vm.isWaterCollection = true;
        vm.collectionType = 'water';
        break;
      default:
        vm.isElectricCollection = true;
        vm.collectionType = 'electric';
        break;
      
      }
    };
    
    vm.modelOptions = {
      debounce : {
        "default" : 500,
        blur : 250
      },
      getterSetter : true
    };
    
    vm.getRooms = function (apt) {
      getRooms(apt);
    };
    
    vm.getBilling = function (room) {
      getBilling(room.aptId, room.id, vm.collectionType);
    }
   
    vm.submit = function() {
      submit();
    }

    $scope
        .$watch(
            function(scope) {
              return {
                cash : scope.vm.cash,
//                items : scope.vm.transactions
              };
            },
            function(newValue, oldValue) {
              vm.cash.baldep = 0;
              vm.cash.balance = 0;
              vm.cash.deposit = 0;
              vm.cash.cashreceivederror = false;
            if (vm.cash.amountPaid > (vm.cash.cashReceived || 0)) {
                vm.cash.cashreceivederror = true;
                vm.model.cashrecivedNote = 'Cash Received is smaller than the Amount paid';
            }
            vm.cash.cashChange = vm.cash.cashReceived
                    - vm.cash.amountPaid;
            if (vm.cash.cashChange < 0) {
                vm.cash.cashChange = 0;
            }
    
            if (vm.cash.amountPaid < vm.total) {
                vm.cash.balance =  vm.total - vm.cash.amountPaid;
//                vm.cash.balance = vm.cash.balance.toFixed(2);
            }
            if (vm.cash.amountPaid > vm.total) {
                vm.cash.deposit =  vm.cash.amountPaid - vm.total;
//                vm.cash.deposit = vm.cash.deposit.toFixed(2);
            }
            if (vm.cash.deposit < 1) {
              vm.cash.deposit = 0;
            }
            
        }, true)


    /**
     * ************************* functions that need to interact with services
     * *****************
     */
    
    /**
     * fetch all the available apartments
     */
    function getApartments() {
      return adminService.getApartments().then(function(response) {
        processResponse(response);
      });
    }
    /**
     * get rooms by apartment ID
     */
    function getRooms(aptId) {
        return adminService.getRooms(aptId).then(
                function(response) {
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
    
    function getBilling(aptId, roomId, type) {
      return adminService.getBilling(aptId, roomId, type).then(function (response) {
        processResponse(response);
      });
    }
    function hasError() {
      return !vm.cash.cashReceived || !vm.cash.amountPaid || (vm.cash.amountPaid < 0);
//      return false;
    }
    /**
     * submit the form
     */
    function submit() {
      
      if (hasError()) {
        vm.popup.showError("Oops! there is something wrong with the input data.<br>Please check....");
        
        return;
      }
      
      var tx = {
        userId : 1,
        aptId : vm.model.apartment.id,
        roomId : vm.model.room.roomId,
        cash: vm.cash,
        billing: vm.billing,
       
      }
      transactionService
          .electricCollection(tx)
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
                  vm.popup.show(options, function() {
                    $location.path('/home');
                  });
                }
                
              });
    }
    
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
      case 'getApartments':
        vm.apartments = data.result;
        break;
      case 'getRooms':
        vm.rooms= data.result;
        break;
      case 'getBilling':
        vm.billing = data.model;
        if (data.model) {
//          vm.billing = data.model;
          vm.total = vm.billing.amount + vm.billing.overdue;
        }
        break;
      }
      
    }
    
  }; //collection controller
  
  CollectionController.$inject = injectParams;
  angular.module('rental').controller('CollectionController', CollectionController);
  
})();
