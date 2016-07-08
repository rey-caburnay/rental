'use strict';

var App = angular.module('rental',['ngRoute']);


/**
 * Configure the Routes
 */

App.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "home", controller: "UserController"})
    // Pages
    .when("/about", {templateUrl: "resources/ui/content/about.html", controller: "UserController"})
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



