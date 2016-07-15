(function() {
	'use strict'
	var injectParams = ['$uibModal'];
	var ModalService = function ($uibModal) {
		return {
			show: function (options) {
				if (!options) {
					options = {
						animation: true,
				        templateUrl: 'resources/ui/modal.html',
				         controller:'ModalInstanceCtrl as vm',
				         size: 'lg',
				         resolve: {
				        model: function () {
				        	return ['item1','item2','item3'];
				        }
				        
				      }
					};
				}
				 var modalInstance = $uibModal.open(options);

				    modalInstance.result.then(function (selectedItem) {
				      return selectedItem;
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
		vm.header = "Im a modal fucker";
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

