(function() {
  'use strict';
  
  var injectParams = [ '$scope', '$filter','$location'];

  
  var NavController = function($scope, $filter,
      $location) {

  
   $scope.navbarCollapsed=true;
   this.navbarCollapsed = true;
   $scope.toggle = function () {
     console.log("trigger toggle");
     return false;
   }
    
  }; 
  
  NavController.$inject = injectParams;
  angular.module('rental').controller('NavController', NavController);
  
})();
