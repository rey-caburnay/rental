/**
 * service module for transaction
 */
(function() {
  
  // var injectParams = ['config', 'customersService',
  // 'customersBreezeService'];
  var injectParams = [ '$q', '$http' ];
  var REQUEST_GET = 'get', REQUEST_POST = 'post';
  var TransactionService = function($q, $http) {
    var http = $http, defer = $q.defer();
    
    this.register = function(model) {
      var url = 'tx/register';
      return request(REQUEST_POST, url, model, 'register');
    }
    this.saveTx = function(model) {
      var url = 'tx/savetx';
      return request(REQUEST_POST, url, model, 'saveTx');
    }
    this.getRoomsByRenter = function(renterId) {
      var url = 'tx/getrooms/' + renterId;
      return request(REQUEST_GET, url, null, 'getRoomsByRenter');
    }
    this.saveCollection = function(model) {
      var url = 'tx/collections';
      return request(REQUEST_POST, url, model, 'saveCollection');
    }
    this.electricCollection = function(model) {
      var url = 'collection/electric';
      return request(REQUEST_POST, url, model, 'electricCollection');
    }
    this.generateBillings = function(model) {
      var url = 'bill/generate';
      return request(REQUEST_POST, url, model, 'generateBillings');
    }

    this.getPdf = function(form) {
      var url = 'bill/pdf';
      return $http.post(url, form, {responseType:'arraybuffer'}).then(function(response) {
        
        return {
          method : 'getPdf',
          data : response.data
        }

      }, function(errResponse) {
        
        console.error('Error while fetching users');
        defer.reject(errResponse);
      });

    }

    return this;
    
    function request(type, url, parameters, context) {
      if (type == REQUEST_GET) {
        return $http.get(url).then(function(response) {
          
          return {
            method : context,
            data : response.data
          }
        }, function(errResponse) {
          
          console.error('Error while fetching users');
          defer.reject(errResponse);
        });
      } else {
        return $http.post(url, parameters).then(function(response) {
          
          return {
            method : context,
            data : response.data
          }

        }, function(errResponse) {
          
          console.error('Error while fetching users');
          defer.reject(errResponse);
        });
      }
    }
  };
  
  TransactionService.$inject = injectParams;
  angular.module('rental').service('transactionService', TransactionService);
  
})();

/*
 * 
 * 'use strict'; App.factory('TransactionService', ['$http', '$q',
 * function($http, $q){
 * 
 * return {
 * 
 * fetchAllUsers: function() { return
 * $http.get('http://localhost:8080/Spring4MVCAngularJSExample/user/') .then(
 * function(response){ return response.data; }, function(errResponse){
 * console.error('Error while fetching users'); return $q.reject(errResponse); } ); },
 * 
 * createUser: function(user){ return
 * $http.post('http://localhost:8080/Spring4MVCAngularJSExample/user/', user)
 * .then( function(response){ return response.data; }, function(errResponse){
 * console.error('Error while creating user'); return $q.reject(errResponse); } ); },
 * 
 * updateUser: function(user, id){ return
 * $http.put('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id, user)
 * .then( function(response){ return response.data; }, function(errResponse){
 * console.error('Error while updating user'); return $q.reject(errResponse); } ); },
 * 
 * deleteUser: function(id){ return
 * $http.delete('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id)
 * .then( function(response){ return response.data; }, function(errResponse){
 * console.error('Error while deleting user'); return $q.reject(errResponse); } ); } };
 * 
 * }]);
 */