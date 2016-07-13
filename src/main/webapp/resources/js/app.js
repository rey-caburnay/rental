
'use strict';

var App = angular.module('rental',['ngRoute']);


/**
 * Configure the Routes
 */

App.config(['$routeProvider', function ($routeProvider) {
  var viewBase = 'resources/ui/content/';
  $routeProvider
    // Home
    .when("/", {
    	templateUrl: viewBase + "transaction.html", 
    	controller: "TransactionController",
    	controllerAs:"tx"})
    // Pages
    .when("/about", {templateUrl: viewBase + "about.html", controller: "UserController"})
    //.when("#/faq", {templateUrl: "/faq", controller: "UserController"})
//    .when("/pricing", {templateUrl: "/pricing", controller: "PageCtrl"})
//    .when("/services", {templateUrl: "/services", controller: "PageCtrl"})
//    .when("/contact", {templateUrl: "/contact", controller: "PageCtrl"})
    // Blog
    //.when("/blog", {templateUrl: "/blog", controller: "BlogCtrl"})
//    .when("/blog/post", {templateUrl: "/content/blog_item", controller: "BlogCtrl"})
    // else 404
//    .otherwise("/404", {templateUrl: "/content/404", controller: "PageCtrl"});
}]);


App.run(['$rootScope', '$location',
    function ($rootScope, $location, authService) {
        
        //Client-side security. Server-side framework MUST add it's 
        //own security as well since client-based security is easily hacked
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
//            if (next && next.$$route && next.$$route.secure) {
//                if (!authService.user.isAuthenticated) {
//                    $rootScope.$evalAsync(function () {
//                        authService.redirectToLogin();
//                    });
//                }
//            }
        });

}]);



