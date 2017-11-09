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
      getVacantApartments : function () {
        var url = 'mst/vacantapartments';
        return request(REQUEST_GET, url, null,'getVacantApartments');
      },
      getRooms : function(aptId, type) {
        var url = 'mst/rooms/' + aptId;
        switch (type) {
        case 'collection_property':
          url = 'collection/rooms/' + aptId;
        default:
          
        }
        return request(REQUEST_GET, url, null,'getRooms');
      },
      
      getRoomsReport : function(aptId) {
        var url = 'report/rooms';
        if (aptId) {
          url = 'report/rooms/' + aptId;
        }
        return request(REQUEST_GET, url, null,'getRoomsReport');
      },
      getElectricReport : function (aptId) {
        var url = 'report/electric/' + aptId;
//        if (aptId) {
//          url = 'report/electric/' + aptId;
//        }
        return request(REQUEST_GET, url, null,'getElectricReport');
      },
      getVacantRooms : function(aptId) {
        var url = 'mst/vacantrooms/' + aptId;
        return request(REQUEST_GET, url, null,'getVacantRooms');
      },
      getRenters : function() {
        var url = 'mst/renters';
        return request(REQUEST_GET, url, null,'getRenters');
      },
      getRentersReport : function(aptId) {
        var url = 'report/tenant';
        if (aptId) {
          url = 'report/tenant/' + aptId;
        }
        return request(REQUEST_GET, url, null,'getRentersReport');
      },

      getTenants : function(aptId, roomId) {
        var url = 'mst/tenants/' + aptId + '/' + roomId;
        return request(REQUEST_GET, url, null,'getTenants');
      },
      getBillings : function(aptId, roomId, type) {
        var url = 'bill/';
        var roomParam = "";
        var aptParam = "";
        if (aptId) {
          aptParam = aptId;
        }
        if(roomId) {
          roomParam = "/" + roomId;
        }
        switch (type) {
        case 'water':
          url = 'bill/water/' + aptParam + roomParam ;
          break;
        case 'property':
          url = 'bill/room/'  + aptParam + roomParam;
          break;
        default:
          url = 'bill/electric/'  + aptParam + roomParam;
        }
         return request(REQUEST_GET, url, null,'getBillings' + type);

      },

      getRoomBilling : function(aptId) {
        var url = 'bill/room/';
        return request(REQUEST_GET, url + params, null,'getBilling');
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
          
          console.error('Error while fetching' + context);
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
