(function() {
  var injectParams = [ '$http', '$rootScope', '$window', '$resource', 'authService'
      , 'Session','modalService' ];
  
  var AuthSharedService = function($http, $rootScope, $window, $resource,  authService,
      Session, modalService) {
    return {
      login : function(user) {
        var config = {
          ignoreAuthModule : 'ignoreAuthModule',
          headers : {
            'Content-Type' : 'application/x-www-form-urlencoded'
          }
        };
        $http.post('authenticate', $.param({
          username : user.email,
          password : user.password,
          "remember-me" : user.rememberMe
        }), config).success(function(data, status, headers, config) {
          authService.loginConfirmed(data);
        }).error(function(data, status, headers, config) {
          $rootScope.authenticationError = true;
          Session.invalidate();
          modalService.showError("Oops! It'seems you input a bad credentials..");
        });
      },
      getAccount : function() {
        $rootScope.loadingAccount = true;
        $http.get('security/account').then(function(response) {
          authService.loginConfirmed(response.data);
        });
      },
      isAuthorized : function(authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
          if (authorizedRoles == '*') {
            return true;
          }
          authorizedRoles = [ authorizedRoles ];
        }
        var isAuthorized = false;
        angular.forEach(authorizedRoles, function(authorizedRole) {
          var authorized = (!!Session.login && Session.userRoles
              .indexOf(authorizedRole) !== -1);
          if (authorized || authorizedRole == '*') {
            isAuthorized = true;
          }
        });
        return isAuthorized;
      },
      logout : function() {
        $rootScope.authenticationError = false;
        $rootScope.authenticated = false;
        $rootScope.account = null;
        $http.get('logout');
        Session.invalidate();
        authService.loginCancelled();
      }
    };
  }
  AuthSharedService.$inject = injectParams;
  angular.module('rental').factory('AuthSharedService', AuthSharedService);
}());

// (function () {
//
// var injectParams = ['$http', '$rootScope', '$window'];
//
// var authFactory = function ($http, $rootScope, $window) {
// var serviceBase = '/mst',
// factory = {
// loginPath: '/login',
// user: {
// isAuthenticated: false,
// roles: null
// }
// };
//
// factory.login = function (email, password) {
// return $http.post(serviceBase + '/login', { userLogin: { username: email,
// password: password } }).then(
// function (results) {
// var loggedIn = results.data.status;;
// changeAuth(loggedIn);
// return loggedIn;
// });
// };
//
// factory.logout = function () {
// return $http.post(serviceBase + 'logout').then(
// function (results) {
// var loggedIn = !results.data.status;
// changeAuth(loggedIn);
// return loggedIn;
// });
// };
//
// factory.redirectToLogin = function () {
// $rootScope.$broadcast('redirectToLogin', null);
// };
//
// function changeAuth(loggedIn) {
// factory.user.isAuthenticated = loggedIn;
// $rootScope.$broadcast('loginStatusChanged', loggedIn);
// }
//
// return factory;
// };
//
// authFactory.$inject = injectParams;
//
// angular.module('rental').factory('authService', authFactory);
//
// }());
//
