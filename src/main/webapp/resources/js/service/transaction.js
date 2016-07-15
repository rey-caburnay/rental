/**
 * service module for transaction
 */
(function () {

//    var injectParams = ['config', 'customersService', 'customersBreezeService'];
	var injectParams = ['$http'];

    var TransactionService = function ($http) {
    	this.http = $http;
    	return this;
    };
    TransactionService.prototype = {
		saveTx: function (model) {
			return this.http.post('http://localhost:8080/rentService/tx/savetx', model    					)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching users');
							return $q.reject(errResponse);
						}
				);
			
		}
 
    }

    TransactionService.$inject = injectParams;

    angular.module('rental').service('TransactionService', TransactionService);

})();

/*
 
'use strict';
App.factory('TransactionService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllUsers: function() {
					return $http.get('http://localhost:8080/Spring4MVCAngularJSExample/user/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createUser: function(user){
					return $http.post('http://localhost:8080/Spring4MVCAngularJSExample/user/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, id){
					return $http.put('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(id){
					return $http.delete('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
*/