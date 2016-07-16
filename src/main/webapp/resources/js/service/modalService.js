(function() {
	'use strict'
	var injectParams = ['$uibModal','$location'];
	var ModalService = function ($uibModal,$location) {
		return {
			show: function (model,size) {
				var options = {};
				options.animation = true;
				options.templateUrl =  'resources/ui/modal.html';
				options.controller = 'ModalInstanceCtrl as vm';
				options.size = size || 'lg';
				options.resolve =   {model: model}
				 var modalInstance = $uibModal.open(options);

				    modalInstance.result.then(function (selectedItem) {
				      $location.path("/"+selectedItem);
				    }, function () {
				      console.log('Modal dismissed at: ' + new Date());
				    });	
			}
		}
	} 
	ModalService.$inject = injectParams;
	angular.module('rental').factory('modalService', ModalService);
})();

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.
(function() {
	
	var ModalInstanceCtrl = function ($uibModalInstance, model) {
		var vm = this;
		vm.model = model;
		vm.header = model.header;
		vm.ok = function () {
			  $uibModalInstance.close('');
		};

		vm.cancel = function () {
		  $uibModalInstance.dismiss('cancel');
		};
	}
	ModalInstanceCtrl.$inject = ['$uibModalInstance', 'model'];
	angular.module('rental').controller('ModalInstanceCtrl',ModalInstanceCtrl);
})();

