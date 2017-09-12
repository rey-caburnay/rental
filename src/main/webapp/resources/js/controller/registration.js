(function() {
  
  var injectParams = [ '$filter', 'transactionService', 'adminService',
      'modalService' ];
  
  var RegistrationController = function($filter, transactionService,
      adminService, modalService) {
    var vm = this;
    vm.ts = transactionService;
    vm.admin = adminService;
    vm.renter;
    vm.startDate;
    vm.endDate;
    vm.collectionDate;
    vm.user = {
      id : null,
      username : '',
      address : '',
      email : ''
    };
    
    getApartments();
    
    vm.init = function() {
      vm.renter = {
        lastName : 'caburnay',
        firstName : 'rey',
        initial : 'c',
        address : 'cebu city',
        mobileNo : '01293213',
        telno : '123123123',
        paymentType : 'cash',
        amount : 5000,
        idPresented : 'gssw #131232',
        email : 'test@email.com',
        emergencyContact : 'emergency',
      }
    }
    vm.submit = function() {
      submit();
    }

    vm.cancel = function() {
      vm.init();
    }
    vm.popup = function(model) {
      showModal(model);
    }
    vm.isValid = function () {
      return isValid();
    }
    vm.getRooms = function (apt) {
      getRooms(apt);
    };
    
    
    function isValid() {
      hasError = false;
      if(!vm.renter) {
        vm.init();
      }
      return !(vm.renter.lastName == "" ||
          vm.renter.firstName == ""||
          vm.renter.mobileNo == "");
    }
    /**
     * submit data to server
     */
    function submit() {
      // vm.popup(vm.renter);
      if(isValid()) {
        vm.renter.aptId = vm.apartment.id;
        vm.renter.roomId = vm.room.id;
        vm.ts.register(vm.renter).then(function(response) {
          console.log("status return :" + response);
          vm.popup(response.data);
        });
      } else {
        vm.popup("Please Input required data... ");
      }
      
      
    }
    function showModal(result) {
      var msg = 'Successfully Registered';
      if (result.responseStatus != 'OK') {
        msg = 'Failed to Register';
        return false;
      }
      var options = {
        header : msg,
        service : result.result
      };
      modalService.showAndRedirect(msg, "home");
    }
    
    function getApartments() {
      return adminService.getVacantApartments().then(function(response) {
        processResponse(response);
      });
    }
    
    /**
     * get rooms by apartment ID
     */
    function getRooms(apt) {
        return adminService.getVacantRooms(apt.id).then(
                function(response) {
                  processResponse(response);
                });
    }
    
    function processResponse(response) {
      console.log(response);
      var data = response.data;
      switch (response.method) {
      case 'getVacantApartments':
        vm.apartments = data.result;
        break;
      case 'getVacantRooms':
        vm.rooms= data.result;
        break;
      }
       
      }
      
  };
  
  RegistrationController.$inject = injectParams;
  angular.module('rental').controller('RegistrationController',
      RegistrationController);
  
})();
