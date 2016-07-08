'use strict';

var App = angular.module('rental',[]);


/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/home", {templateUrl: "/home", controller: "PageCtrl"})
    // Pages
    .when("/about", {templateUrl: "/about", controller: "PageCtrl"})
    .when("/faq", {templateUrl: "/faq", controller: "PageCtrl"})
//    .when("/pricing", {templateUrl: "/pricing", controller: "PageCtrl"})
//    .when("/services", {templateUrl: "/services", controller: "PageCtrl"})
//    .when("/contact", {templateUrl: "/contact", controller: "PageCtrl"})
    // Blog
    .when("/blog", {templateUrl: "/blog", controller: "BlogCtrl"})
//    .when("/blog/post", {templateUrl: "/content/blog_item", controller: "BlogCtrl"})
    // else 404
//    .otherwise("/404", {templateUrl: "/content/404", controller: "PageCtrl"});
}]);



