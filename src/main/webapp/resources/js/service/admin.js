/**
 * service module for administrative actions
 */
(function() {
  
  // var injectParams = ['config', 'customersService',
  // 'customersBreezeService'];
  var injectParams = [ '$http', '$q' ];
  var REQUEST_GET = 'get', REQUEST_POST = 'post';
  var AdminService = function($http, $q) {
    var defer = $q.defer();
    return {
      getApartments : function() {
        var url = 'mst/apartments';
        return request(REQUEST_GET, url, null,'getApartments');
      },
      getRooms : function(aptId) {
        var url = 'mst/rooms/' + aptId;
        return request(REQUEST_GET, url, null,'getRooms');
      },
      getRenters : function() {
        var url = 'mst/renters';
        return request(REQUEST_GET, url, null,'getRenters');
      },
      getTenants : function(aptId, roomId) {
        var url = 'mst/tenants/' + aptId + '/' + roomId;
        return request(REQUEST_GET, url, null,'getTenants');
      },
      getBillings : function(aptId, type) {
        var url = 'bill/';
        switch (type) {
        case 'water':
          url = 'bill/water/';
          break;
        case 'property':
          url = 'bill/property/';
          break;
        default:
          url = 'bill/electric/';
        }
         return request(REQUEST_GET, url+aptId, null,'getBillings');

      }
    }; // end of return statement
    /**
     * 
     */
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

  /*
   * AdminService.prototype = { getApartments: function () { return
   * this.http.get('mst/apartments') .then( function(response){
   * this.q.resolve(response.data); }, function(errResponse){
   * console.error('Error while fetching users'); this.q.reject(errResponse); } );
   * return this.q.promise; }, getRooms: function (aptId) { return
   * this.http.get('mst/rooms/'+aptId) .then( function(response){ return
   * response.data; }, function(errResponse){ console.error('Error while
   * fetching users'); return this.q.reject(errResponse); } ); }, }
   */
  AdminService.$inject = injectParams;
  
  angular.module('rental').factory('adminService', AdminService);
  
})();
