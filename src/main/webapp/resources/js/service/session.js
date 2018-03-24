(function() {
  var injectParams = [ '$http', '$rootScope', '$window' ];
  var Session = function($http, $rootScope, $window) {
    this.create = function(data) {
      this.id = data.id;
      this.login = data.login;
      this.firstName = data.firstName;
      this.lastName = data.familyName;
      this.email = data.email;
      this.userRoles = [];
      angular.forEach(data.authorities, function(value, key) {
        this.push(value.name);
      }, this.userRoles);
    };
    this.invalidate = function() {
      this.id = null;
      this.login = null;
      this.firstName = null;
      this.lastName = null;
      this.email = null;
      this.userRoles = null;
    };
    return this;
  }
  Session.$inject = injectParams;
  angular.module('rental').service('Session', Session);
}());
