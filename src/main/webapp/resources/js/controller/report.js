(function() {
  'use strict';
  
  var injectParams = [ '$scope', '$filter', 'transactionService',
      'adminService', 'dateFactory', 'modalService', '$location', 'DTOptionsBuilder' ];
  var MECO_PROVIDER = 'meco';
  var VECO_PROVIDER = 'veco';
  
  var ReportController = function($scope, $filter, transactionService,
      adminService, dateFactory, modalService, $location, DTOptionsBuilder) {
    var vm = this, collection = {}, // model to map to services
    paymentTypes = [ "cash", "check", "online" ], COLLECTION_DAYS = 30;
    vm.dtOptions = DTOptionsBuilder.newOptions();
//    .withOptions('autoWidth', fnThatReturnsAPromise);


    vm.popup = modalService;
    vm._selected;
    vm.renters = []; // model for ui;
    vm.reportTitle = "Tenants";
    vm.currentPage = "tenant";
//    vm.init();
    getApartments();
    getRenters();
    // set the default active tab
//    vm.isElectricCollection = false;
    vm.isTenant = true;
    
    /** set the tab pages ***/
    vm.setActivePage = function(tab) {
      vm.isApartment = false;
      vm.isElectric= false;
      vm.isWater = false;
      vm.isTenant = false;
      console.log(tab);
      switch (tab) {
      case 'tenant':
        vm.isTenant = true;
        if(vm.currentPage != "tenant") {
          m.currentPage = "tenant"
        };
//        getApartments();
//        getRenters();
      case 'property':
        vm.isApartment= true;
        if(vm.currentPage != 'property') {
          vm.currentPage = "property";
          vm.getRooms(null);
        }
     
        break;
      case 'electric':
        vm.isElectric = true;
        if(vm.currentPage != 'electric') {
          vm.currentPage = "electric";
          vm.getRoomElectric(null);
        }
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
    
        
    vm.getName = function (renter) {
      var name = "";
      name = renter.lastName + ", " + renter.firstName;
      if (renter.initial) {
        name = name + " " + renter.initial;
      }
      return name;
    };
    
    vm.getRooms = function (aptId) {
      getRooms(aptId);
    };
    vm.getRenters = function (aptId) {
      getRenters(aptId);
    };
    vm.getRoomElectric = function (aptId) {
      getRoomsElectric(aptId);
    };
    vm.submit = function() {
      submit();
    };



    /**
     * ************************* functions that need to interact with services
     * *****************
     */
    function getRenters(aptId) {
      adminService.getRentersReport(aptId).then(function (result){ 
        processResponse(result);
      });
    }
    
    function getRooms(aptId) {
      
        adminService.getRoomsReport(aptId).then(function (result){ 
          processResponse(result);
        });
      
    }
    function getApartments() {
      adminService.getApartments().then(function (result) {
        processResponse(result);
      });
    }
    
    function getRoomsElectric(aptId) {
      adminService.getElectricReport(aptId).then(function (result) {
        processResponse(result);
      });
    }
    
    function fnThatReturnsAPromise() {
      var defer = $q.defer();
      defer.resolve(false);
      return defer.promise;
  }
    
    /** test centralize response function * */
    function processResponse(response) {
      if(!response) {
        return false;
      }
      console.log(response);
      var data = response.data;
      switch (response.method) {
      case 'getRentersReport':
        vm.renters = data.result;
        if(!vm.renters) {
          getRenters();
        }
        break;
      case 'getApartments':
        vm.apartments = data.result;
        if(!vm.apartments) {
          getApartments();
        }
        break;
      case 'getRoomsReport':
        vm.rooms = data.result;
        break;
      case 'getElectricReport':
        vm.electricBills = data.result;
      default:
      }
      
    }
    
  }; //collection controller
  
  ReportController.$inject = injectParams;
  angular.module('rental').controller('ReportController', ReportController);
  
})();
