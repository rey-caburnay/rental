(function() {

	var injectParams = [ '$filter', 'TransactionService', 'adminService',
			'Transaction' ];

	var TransactionController = function($filter, TransactionService,
			adminService, Transaction) {
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
//			vm.model.aptId = vm.apartment;
//			vm.txModel.roomId =  vm.room.id
//			vm.txModel.renterId = vm.renter.id;
//			 vm.txModel.renterLastName = vm.renter.lastname;
//			 vm.txModel.renterFirstName = vm.renter.firstname;
//			 vm.txModel.renterMI = vm.renter.mi;
//			 vm.txModel.dueDate = vm.trans.collectionDate;
//			 vm.txModel.txDate = new Date();
//			 vm.txModel.startDate = vm.trans.startDate;
//			 vm.txModel.endDate = vm.trans.endDate;
//			 vm.txModel.deposit = vm.trans.deposit;
//			 vm.txModel.balance = vm.balance;
//			 vm.txModel.amount = vm.amount;
//			 vm.txModel.txType = vm.trans.type;
//			 vm.txModel.provider = '';
//			 vm.txModel.status = vm.trans.status;
//			 vm.txModel.userId = '';
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
			 console.log("status return :" + status);
		}
		
	};
	/*
	 TransactionController.prototype = {
	
	 init:function () {

	 },
	
	 getRooms: function () {
	 this.rooms = this.getRooms1();
	 },
	 getCustomers: function () {
	 //    			dataService.getCustomers(vm.currentPage - 1, vm.pageSize)
	 //                 .then(function (data) {
	 //                     vm.totalRecords = data.totalRecords;
	 //                     vm.customers = data.results;
	 //                     filterCustomersProducts('');
	 //                 }, function (error) {
	 $window.alert('hello');
	 //                 });
	 },
	 test: function () {
	 alert('hello');
	 },
	
	 submit: function () {
	 this.txModel.aptId = this.apartment;
	 this.txModel.roomId =  this.roomId;
	 this.txModel.renterId = this.renter.id;
	 this.txModel.renterLastName = this.renter.lastname;
	 this.txModel.renterFirstName = this.renter.firstname;
	 this.txModel.renterMI = this.renter.mi;
	 this.txModel.dueDate = this.trans.collectionDate;
	 this.txModel.txDate = new Date();
	 this.txModel.startDate = this.trans.startDate;
	 this.txModel.endDate = this.trans.endDate;
	 this.txModel.deposit = this.trans.deposit;
	 this.txModel.balance = this.balance;
	 this.txModel.amount = this.amount;
	 this.txModel.txType = this.trans.type;
	 this.txModel.provider = '';
	 this.txModel.status = this.trans.status;
	 this.txModel.userId = '';
	 status = this.ts.saveTx(this.txModel);
	 console.log("status return :" + status);
	 },
	
	 cancel: function () {
	
	 },
	 getRooms1: function () {
	 this.admin.getRooms(this.apartment.id).then(function (response){
	 return response.result;
	
	 });
	 }
	
	
	 }
	 */

	TransactionController.$inject = injectParams;
	angular.module('rental').controller('TransactionController',
			TransactionController);

})();
