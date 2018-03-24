(function() {
    'use strict';

    var injectParams = [ '$rootScope', '$scope', '$filter','AuthSharedService',
            'adminService', 'dateFactory','modalService', '$location' ];

    var LoginController = function($rootScope, $scope, $filter, AuthSharedService,
            adminService, dateFactory, modalService, $location) {
      $scope.rememberMe = true;
      $rootScope.authenticationError = false;
      var vm = this;
      vm.user = {email: '', password:'', rememberMe:false};
      
      vm.login = function () {
        var response = AuthSharedService.login(vm.user);
        console.log(response);
      }

    };
    

    LoginController.$inject = injectParams;
    angular.module('rental').controller('LoginController', LoginController);

})();
