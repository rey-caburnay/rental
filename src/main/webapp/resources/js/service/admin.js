/**
 * service module for administrative actions
 */
(function () {

//    var injectParams = ['config', 'customersService', 'customersBreezeService'];
	var injectParams = ['$http','$q'];

    var AdminService = function ($http,$q) {
    	var defer = $q.defer(),
    	REQUEST_GET = 'get',
    	REQUEST_POST = 'post';
    	return {
    		getApartments: function () {
    			return $http.get('mst/apartments')
    			.then(
    					function(response){
    						return {method:'getApartments', data:response.data}
    					}, 
    					function(errResponse){
    						console.error('Error while fetching users');
    						defer.reject(errResponse);
    					});
    			
    		},

    		getRooms: function (aptId) {
    			return $http.get('mst/rooms/'+aptId)
    			.then(
    					function(response){
    						return {method:'getRooms', data:response.data}
    					}, 
    					function(errResponse){
    						console.error('Error while fetching users');
    						defer.reject(errResponse);
    					}
    			);
        	},
    	   getRenters: function () {
//    		   request(REQUEST_GET,'mst/renters',null);
               return $http.get('mst/renters')
               .then(
                       function(response){
                    	   
                           return {method:'getRenters', data:response.data};
                       }, 
                       function(errResponse){
                           console.error('Error while fetching renters');
                           defer.reject(errResponse);
                       }
               );
               
           },
           getTenants: function (aptId, roomId) {
               return $http.get('mst/tenants/'+ aptId + '/' + roomId)
               .then(
                       function(response){
                    	   return {method:'getTenants', data:response.data}
                       }, 
                       function(errResponse){
                           console.error('Error while fetching renters');
                           defer.reject(errResponse);
                       }
               );
           }
    	};
    	/**
    	 * 
    	 */
    	function request(type, url, parameters) {
    		if (type == REQUEST_GET) {
    			$http.get(url)
    			.then(
    					function(response){
    		
    						defer.resolve(response.data);
    					}, 
    					function(errResponse){

    						console.error('Error while fetching users');
    						defer.reject(errResponse);
    					}
    			);
    		} else {
    			$http.post(url,parameters)
    			.then(
    					function(response){

    						defer.resolve(response.data);
    					}, 
    					function(errResponse){
   
    						console.error('Error while fetching users');
    						defer.reject(errResponse);
    					}
    			);
    		}
    		return defer.promise;

    	}

    	

    };
    /*
    AdminService.prototype = {
    	getApartments: function () {
    		return this.http.get('mst/apartments')
			.then(
					function(response){
						this.q.resolve(response.data);
					}, 
					function(errResponse){
						console.error('Error while fetching users');
						this.q.reject(errResponse);
					}
			);
    		return this.q.promise;

    	},
    	getRooms: function (aptId) {
    		return this.http.get('mst/rooms/'+aptId)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching users');
						return this.q.reject(errResponse);
					}
			);
    	},
    }
*/
    AdminService.$inject = injectParams;

    angular.module('rental').factory('adminService', AdminService);

})();

