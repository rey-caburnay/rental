(function() {
  'use strict';

  angular.module('rental', ['ngRoute', 'ui.bootstrap', 'ngSanitize',
      'ngResource', 'pdfjsViewer', 'datatables', 'http-auth-interceptor']);

  angular.module('rental').constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
  });
  /**
   * Configure the Routes
   */

  angular.module('rental').config(['$routeProvider', '$httpProvider',

  function($routeProvider, $httpProvider, USER_ROLES) {
    var viewBase = 'resources/ui/content/';
    $routeProvider
    // Home
    .when("/home", {
      templateUrl: viewBase + "home.html",
      controller: "",
      controllerAs: "vm",
      loginRequired: false,
    }).when("/reg", {
      templateUrl: viewBase + "registration.html",
      controller: "RegistrationController",
      controllerAs: "vm"
    }).when("/rent", {
      templateUrl: viewBase + "rent.html",
      controller: "RentController",
      controllerAs: "vm"
    })
    // Pages
    .when("/collection", {
      templateUrl: viewBase + "/collection/collection.html",
      controller: "CollectionController",
      controllerAs: "vm"
    }).when("/login", {
      templateUrl: viewBase + "login.html",
      controller: "LoginController",
      controllerAs: "vm",
    // access: {
    // loginRequired: false,
    // authorizedRoles: [USER_ROLES.all]
    // }
    }).when("/bill", {
      templateUrl: viewBase + "/billing/billing.html",
      controller: "BillingController",
      controllerAs: "vm"
    }).when("/reports", {
      templateUrl: viewBase + "/report/report.html",
      controller: "ReportController",
      controllerAs: "vm"
    })
    .when("/logout",{
      controller: "LogoutController",
      controllerAs: "vm",
    })
    // .when("/pricing", {templateUrl: "/pricing", controller:
    // "PageCtrl"})
    // .when("/services", {templateUrl: "/services", controller:
    // "PageCtrl"})
    // .when("/contact", {templateUrl: "/contact", controller:
    // "PageCtrl"})
    // Blog
    // .when("/blog", {templateUrl: "/blog", controller:
    // "BlogCtrl"})
    // .when("/blog/post", {templateUrl: "/content/blog_item",
    // controller: "BlogCtrl"})
    // else 404
    .otherwise({
      redirectTo: '/login'
    });
    var spinnerFunction = function spinnerFunction(data, headersGetter) {
      swal({
        title: 'Loading',
        text: 'Please wait while fetching your data..',
        imageUrl: '/rentService/resources/images/spinners.gif',
        showCancelButton: false,
        showConfirmButton: false,
        background: '#344557',
        allowOutsideClick: true,
        allowEscapeKey: false,
        allowEnterKey: false
      }).then(function() {
        return data;
      },
      // handling the promise rejection
      function(dismiss) {
        return data;
      });
      return data;
    };
    // $httpProvider.defaults.transformRequest.push(spinnerFunction);
    $httpProvider.interceptors.push('customInterceptor');

  }]);

  angular.module('rental').run(
          [
              '$rootScope',
              '$location',
              '$http',
              'AuthSharedService',
              'Session',
               'USER_ROLES',
              '$q',
              '$timeout',
   

              function($rootScope, $location, $http, AuthSharedService,
                      Session, USER_ROLES, $q, $timeout) {

                $rootScope.$on('$routeChangeStart', function(event, next) {

                  if (next.originalPath === "/login"
                          && $rootScope.authenticated) {
                    event.preventDefault();
                  } else if(next.originalPath === "/logout") {
                    AuthSharedService.logout();
                    $location.path("/login").replace();
                  } else if (!$rootScope.authenticated) {
                    $location.path("/login").replace();
                  } 
                });

                $rootScope.$on('$routeChangeSuccess', function(scope, next,
                        current) {
                  $rootScope.$evalAsync(function() {
//                    $.material.init();
                  });
                });

                // Call when the the client is confirmed
                $rootScope.$on('event:auth-loginConfirmed', function(event,
                        data) {
                  console.log('login confirmed start ' + data);
                  $rootScope.loadingAccount = false;
                  var nextLocation = ($rootScope.requestedUrl
                          ? $rootScope.requestedUrl : "/home");
                  var delay = ($location.path() === "/loading" ? 1500 : 0);

                  $timeout(function() {
                    Session.create(data);
                    $rootScope.account = Session;
                    $rootScope.authenticated = true;
                    $location.path(nextLocation).replace();
                  }, delay);

                });

                // Call when the 401 response is returned by the server
                $rootScope
                        .$on('event:auth-loginRequired',
                                function(event, data) {
                                  if ($rootScope.loadingAccount
                                          && data.status !== 401) {
                                    $rootScope.requestedUrl = $location.path()
                                    $location.path('/loading');
                                  } else {
                                    Session.invalidate();
                                    $rootScope.authenticated = false;
                                    $rootScope.loadingAccount = false;
                                    $location.path('/login');
                                  }
                                });

                // Call when the 403 response is returned by the server
                $rootScope.$on('event:auth-forbidden', function(rejection) {
                  $rootScope.$evalAsync(function() {
                    $location.path('/error/403').replace();
                  });
                });

                // Call when the user logs out
                $rootScope.$on('event:auth-loginCancelled', function() {
                  $location.path('/login').replace();
                });

                // Get already authenticated user account
                // AuthSharedService.getAccount();
                // Client-side security. Server-side framework MUST add it's
                // own security as well since client-based security is easily
                // hacked
//                $rootScope.$on("$routeChangeStart", function(event, next,
//                        current) {
//                  if (next && next.$$route && next.$$route.secure) {
//                    if (!authService.user.isAuthenticated) {
//                      $rootScope.$evalAsync(function() {
//                        authService.redirectToLogin();
//                      });
//                    }
//                  }
//                });
//                $rootScope.navbarCollapsed = true;
                $rootScope.navbarToggle = function() {
                  console.log("trigger toggle");
                  $rootScope.navbarCollapsed = false;
                  $('button.navbar-toggle').click();
                }
                //
              }

          ]);

})();

