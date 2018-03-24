(function() {
    'use strict';

    var injectParams = [ 'AuthSharedService'];

    var LogoutController = function( AuthSharedService) {
      AuthSharedService.logout();
    };
    
    LogoutController.$inject = injectParams;
    angular.module('rental').controller('LogoutController', LogoutController);

})();
