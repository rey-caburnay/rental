(function() {

	var injectParams = [ '$filter', 'TransactionService', 'adminService','modalService'];

	var TransactionController = function($filter, TransactionService,
			adminService,  modalService) {
		var vm = this;
		vm.ts = TransactionService;
		vm.admin = adminService;
		vm.model;
		vm.user = {
			id : null,
			username : '',
			address : '',
			email : ''
		};
		vm.rooms = [];
		vm.rentType = [ {
			name : "innType",
			label : "Inn Type"
		}, {
			name : "aptType",
			label : "Apartment"
		}, {
			name : "aptType",
			label : "Store"
		}, ]
		vm.apartments = [];

		getApartments();
		 showModal();

		vm.getRooms = function(aptId) {
			getRooms(aptId);
		};
		vm.submit = function () {
			submit();
		}
		//test data
		vm.model = {
				lastname: 'caburnay',
				firstname:'rey',
				renterMI: 'c',
				address: 'cebu city',
				mobileno:'01293213',
				txType:{
					name : "aptType",
					label : "Apartment"
				},
				paymentType:'cash',
				amount:5000,
				apartment:{id:1, name:'Buhisan'},
				room:{id:1, label:'1st Floor Room # 1'}
				
				
		}
		function getApartments() {
			return adminService.getApartments().then(function(response) {
				vm.apartments = response.result;
				return vm.apartments;
			});
		}
		/**
		 * 
		 */
		function getRooms(aptId) {
			return adminService.getRooms(aptId).then(
					function(response) {
						vm.rooms=[];
						var rooms = response.result;
						for (var i = 0; i < rooms.length; i++) {
							var room = {
								id : rooms[i].id,
								label : ordinal(rooms[i].floor)
										+ ' Floor - Room #' + rooms[i].roomNo
							}
							vm.rooms.push(room);
						}
						return vm.rooms;
					});
		}
		/**
		 * 
		 */
		function ordinal(number) {
			// Ensure that the passed in data is a number
			if (isNaN(number) || number < 1) {

				// If the data is not a number or is less than one (thus not having a cardinal value) return it unmodified.
				return number;

			} else {

				// If the data we are applying the filter to is a number, perform the actions to check it's ordinal suffix and apply it.

				var lastDigit = number % 10;

				if (lastDigit === 1) {
					return number + 'st'
				} else if (lastDigit === 2) {
					return number + 'nd'
				} else if (lastDigit === 3) {
					return number + 'rd'
				} else if (lastDigit > 3) {
					return number + 'th'
				}
			}
		}
		
		function submit() {
			vm.model.aptId = vm.model.apartment.id;
			vm.model.roomId = vm.model.room.id;
			
			vm.model.startDate = vm.model.startDate.toISOString().substring(0, 10);
			if (vm.model.endDate) {
				vm.model.endDate = vm.model.endDate.toISOString().substring(0, 10);
			}
			if (vm.model.collectionDate) {
				vm.model.collectionDate = vm.model.collectionDate.toISOString().substring(0, 10);
			}
			 vm.ts.saveTx(vm.model).then(function(response){
				 console.log("status return :" + response);
				
			 });
			 
		}
		function showModal() {
//			 var modalInstance = $uibModal.open({
//			      animation: true,
//			      templateUrl: 'resources/ui/modal.html',
//			      controller: 'ModalInstanceCtrl',
//			      size: 'sm',
//			      resolve: {
//			        items: function () {
//			          return ['item1', 'item2', 'item3'];
//			        }
//			      }
//			    });
//
//			    modalInstance.result.then(function (selectedItem) {
//			      vm.model.lastname = selectedItem;
//			    }, function () {
//			      console.log('Modal dismissed at: ' + new Date());
//			    });	
			modalService.show();
	        
		}
		
	};

	TransactionController.$inject = injectParams;
	angular.module('rental').controller('TransactionController',
			TransactionController);

})();
