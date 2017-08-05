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
    vm.collectionTitle = "Electric";
    vm.showRoomInput = false;
    // vm.apartments = getApartments();
    getApartments();
    // set the default active tab
    vm.isElectricCollection = true;
    
    /** set the tab pages ***/
    vm.setActivePage = function(tab) {
      vm.isPropertyCollection = false;
      vm.isElectricCollection= false;
      vm.isWaterCollection = false;
      switch (tab) {
      case 'property':
        vm.isPropertyCollection = true;
        break;
      case 'water':
        vm.isWaterCollection = true;
        break;
      default:
        vm.isElectricCollection = true;
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
      getBilling(room);
    }
   
    vm.submit = function() {
      submit();
    }

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
    
    function getBilling() {
      return 
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
        rooms : vm.rooms,
        totalAmount:0,
        totalOverdue:0,
        readingDate:vm.readingDate,
        accountNumber: vm.meterNo,
        electricProvider: vm.electricProvider
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
 
      }
      
    }
    
  }; //collection controller
  
  CollectionController.$inject = injectParams;
  angular.module('rental').controller('CollectionController', CollectionController);
  
})();
