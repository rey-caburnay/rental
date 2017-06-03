/**
 * service module for transaction
 */
(function () {

//    var injectParams = ['config', 'customersService', 'customersBreezeService'];
	var injectParams = ['$q','$http'];

    var TransactionService = function ($q, $http) {
        var http = $http,
    	defer = $q.defer();

        this.register = function (model) {
            return http.post('tx/register', model)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching users');
                        return defer.reject(errResponse);
                    }
                );
        },
        this.saveTx = function (model) {
            return http.post('tx/savetx', model)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching users');
                        return defer.reject(errResponse);
                    }
                );
        },
        this.getRoomsByRenter = function (renterId) {
            var ts = this;
            return http.get('tx/getrooms/'+renterId)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching rooms');
                        return defer.reject(errResponse);
                    }
                );
        }
        this.saveCollection = function(model) {
            return http.post('tx/collections', model)
                .then(function(response) {
                    return response.data;
                }, 
                function(errResponse) {
                    console.error('Error while fetching users');
                    return errResponse.data;
                }
            );
        }
    	return this;
    };

    TransactionService.$inject = injectParams;
    angular.module('rental').service('transactionService', TransactionService);

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