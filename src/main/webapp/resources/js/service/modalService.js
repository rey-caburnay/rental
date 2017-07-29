/**
 * Modal service using the sweetalert2 library
 */

(function() {
	'use strict'
	var injectParams = ['$uibModal','$location','$window'];
	var ModalService = function ($uibModal,$location, $window) {
	    var options = {};

	    //options.title = 'Are you sure?',
        //options.text = "You won't be able to revert this!",
        //options.type  = 'warning',
        //options.showCancelButton = true,
        //options.confirmButtonColor = '#3085d6',
        //options.cancelButtonColor = '#d33',
        //options.confirmButtonText = 'Yes, delete it!'
          
        return {
//			show: function (model,size) {
//				var options = {};
//				options.animation = true;
//				options.templateUrl =  'resources/ui/modal.html';
//				options.controller = 'ModalInstanceCtrl as vm';
//				options.size = size || 'lg';
//				options.resolve =   {model: model}
//				 var modalInstance = $uibModal.open(options);
//
//				    modalInstance.result.then(function (selectedItem) {
//				      $location.path("/"+selectedItem);
//				    }, function () {
//				      console.log('Modal dismissed at: ' + new Date());
//				    });	
//			}
		    showAndRedirect: function (message, location) {
		      //  options.templateUrl = "resources/ui/" + location +".html";
		        options.title = message;
//		        options.onClose = function () {
//		            $location.path("/"+location);
//		        }
		        swal(options).then(function () {
//		            $location.path("/"+location);
		            $window.location.href = '#/' + location;
		        })
		        
		    },
		    show: function (message,func) {
		        swal(message,func);
		    },
		    showSuccess: function (message) {
                swal(message,"", "success");
            },
		    showError: function (message) {
		        swal(message,"", "error");
		    },
		    showInfo: function (message) {
		    	swal(message,"","info");
		    },
            spinner: function (timeout) {
//            	swal({
//              		  text: 'Processing',
//              		  imageUrl: '/rentService/resources/images/spinners.gif',
////            		  imageUrl: '../../images/spinners.gif',
//            		  imageWidth: 400,
//            		  imageHeight: 200,
//            		  animation: true,
//            		  showCloseButton: false,
//            		  showCancelButton: false,
//            		});
            	swal({
            		  title: 'Loading',
            		  text: 'Please wait while fetching your data..',
            		  imageUrl: '/rentService/resources/images/spinners.gif',
            		  timer: 10000,
            		  showCancelButton: false,
            		  showConfirmButton: false,
            		  background: '#344557',
            		  allowOutsideClick: false,
            		  allowEscapeKey: false,
            		  allowEnterKey: false
            		}).then(
            		  function () {
            			  if (timeout > 0) {
            				  swal.close();
            			  }
            		  },
            		  // handling the promise rejection
            		  function (dismiss) {
            		    if (timeout > 0) {
            		      //console.log('I was closed by the timer')
            		    }
            		  }
            		)
            },
		    close: function () {
		    	if (swal.isVisible()) {
		    		swal.close();
		    	}
		    }
//		    show: function (message) {
//		        swal(message, "You clicked the button!", "success");
//		    },
//		    show: function (message) {
//		        swal({title: "Are you sure?",   
//		             text: "You will not be able to recover this imaginary file!",   
//		             type: "warning",   
//		             showCancelButton: true,   
//		             confirmButtonColor: "#DD6B55",   
//		             confirmButtonText: "Yes, delete it!",   
//		             closeOnConfirm: false 
//		             }, 
//		             function() {
//		                 swal("Deleted!", "Your imaginary file has been deleted.", "success"); 
//		             }); 
//		    }
		    
		    
		}
	} 
	ModalService.$inject = injectParams;
	angular.module('rental').factory('modalService', ModalService);
})();

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.
//(function() {
//	
//	var ModalInstanceCtrl = function ($uibModalInstance, model) {
//		var vm = this;
//		vm.model = model;
//		vm.header = model.header;
//		vm.ok = function () {
//			  $uibModalInstance.close('');
//		};
//
//		vm.cancel = function () {
//		  $uibModalInstance.dismiss('cancel');
//		};
//	}
//	ModalInstanceCtrl.$inject = ['$uibModalInstance', 'model'];
//	angular.module('rental').controller('ModalInstanceCtrl', ModalInstanceCtrl);
//})();

