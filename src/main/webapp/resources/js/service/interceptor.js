/**
 * Custom Interceptor for Ajax Request
 */

(function() {
	'use strict'
	var injectParams = ['$q','$window'];
	var CustomInterceptor = function ($q, $window) {
	    var options = {};
      function showSpinner() {
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
      }   
      return {
    	  request: function (config) {
    		  if (config.url.indexOf('typeahead') < 1) {
    			  showSpinner();
    		  }
    		  return config;
    	  }

      }   
	} 
	CustomInterceptor.$inject = injectParams;
	angular.module('rental').factory('customInterceptor', CustomInterceptor);
})();
//angular.module('rental').factory('myHttpInterceptor', function ($q, $window) {
//  return function (promise) {
//    return promise.then(function (response) {
//    	swal.closeModal();
//      return response;
//    }, function (response) {
//    	swal.closeModal();
//      return $q.reject(response);
//    });
//  };
//});