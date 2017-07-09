(function (){
'use strict';

angular.module('rental',['ngRoute','ui.bootstrap','ngSanitize','ngResource']);


/**
 * Configure the Routes
 */

angular.module('rental').config(['$routeProvider','$httpProvider', function ($routeProvider,$httpProvider) {
  var viewBase = 'resources/ui/content/';
  $routeProvider
    // Home
    .when("/home", {
    	templateUrl: viewBase + "home.html",
    	controller: "TransactionController",
    	controllerAs:"vm"
    })
    .when("/reg", {
    	templateUrl: viewBase + "registration.html",
    	controller: "RegistrationController",
    	controllerAs: "vm"
    })
     .when("/rent", {
    	templateUrl: viewBase + "rent.html",
    	controller: "RentController",
    	controllerAs: "vm"
    })
    // Pages
    .when("/collection", {templateUrl: viewBase + "collection.html",
        controller: "CollectionController",
        controllerAs: "vm"
     })
    .when("/login", {templateUrl: viewBase + "login.html", 
        controller: "LoginController",
        controllerAs: "vm"
    })
    
//    .when("/pricing", {templateUrl: "/pricing", controller: "PageCtrl"})
//    .when("/services", {templateUrl: "/services", controller: "PageCtrl"})
//    .when("/contact", {templateUrl: "/contact", controller: "PageCtrl"})
    // Blog
    //.when("/blog", {templateUrl: "/blog", controller: "BlogCtrl"})
//    .when("/blog/post", {templateUrl: "/content/blog_item", controller: "BlogCtrl"})
    // else 404
    .otherwise({ redirectTo: '/login' });
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
		}).then(
      		  function () {
      			 return data;
    		  },
    		  // handling the promise rejection
    		  function (dismiss) {
    			return data;
    		  }
    		);
	    return data;
	  };
//	  $httpProvider.defaults.transformRequest.push(spinnerFunction);
	  $httpProvider.interceptors.push('customInterceptor');
}]);


angular.module('rental').run(['$rootScope', '$location',
    function ($rootScope, $location, authService) {

        //Client-side security. Server-side framework MUST add it's
        //own security as well since client-based security is easily hacked
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (next && next.$$route && next.$$route.secure) {
                if (!authService.user.isAuthenticated) {
                    $rootScope.$evalAsync(function () {
                        authService.redirectToLogin();
                    });
                }
            }
        });

}]);

})();

