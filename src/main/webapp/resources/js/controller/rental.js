(function() {
    'use strict';

    var injectParams = [ '$scope', '$filter', 'transactionService',
            'adminService', 'dateFactory','modalService', '$location' ];

    var RentController = function($scope, $filter, transactionService,
            adminService, dateFactory, modalService, $location) {
        var vm = this, collection = {}, // model to map to services
        paymentTypes = [ "cash", "check", "online" ], COLLECTION_DAYS = 30;

        vm.services = [ adminService, transactionService, modalService ];
        vm.popup = modalService;
        vm._selected;
        vm.model = {}; // model for ui;
        vm.model.total = 0;
        vm.model.existingBalance = 0;
        vm.model.paymentType = paymentTypes[0];
        vm.label = {
            deposit : "Expenses",
            total : "Total"
        }
        vm.payment = {
            cash : true,
            credit : false,
            check : false,
            paypal : false,
            visa : false,
            master : false
        };

        vm.showRoomInput = false;
        vm.model.rooms = [];
//        vm.apartments = getApartments();
        getApartments();
        getRenters();
        vm.renters = [];
//        showLoading(vm.renters.length || 0)
         vm.initModel = function() {
            vm.model.total = 0;
            vm.model.balance = 0;
            vm.model.deposit = 0;
            vm.model.amount = 0;
            vm.model.cash = {
                amountPaid : '',
                cashChange : '',
                cashReceived : ''
            }
        }
        
        vm.initModel();
        vm.getRenters = function () {
        	 getRenters();
        }
        vm.getRooms = function(aptId) {
            getRooms(aptId);
        };

        vm.modelOptions = {
            debounce : {
                "default" : 500,
                blur : 250
            },
            getterSetter : true
        };
        
        /**
         * flag to show the room entry
         */
        vm.showAptEntry = function() {
            vm.showRoomInput = true;
        }

        vm.setRenter = function(renter) {
            vm.renter = renter;
            vm.computeTotal();
        }
        
        vm.computeAmount = function() {
            var diff = vm.model.amount - vm.model.roomRate;
            vm.model.cashReceived = vm.model.amount;
            if (diff < 0) {
                vm.model.balance = Math.abs(diff);
                vm.model.change = 0;
            } else {
                vm.model.deposit = diff;
                vm.model.change = diff;
            }
        }
        vm.setRoomInfo = function(room) {
            var dueDate = new Date(room.dueDate), newCollectionDate = new Date();
            // map values to service
            collection.txId = room.txId;
            collection.renterId = room.id;
            collection.roomId = roomId;
            collection.aptId = room.aptId;
            // map values to ui
            vm.model.amount = room.amount;
            vm.model.paymentType = room.paymentType;
            vm.model.deposit = room.deposit;
            vm.model.balance = room.balance;
            vm.model.amtInWords = room.amount;
            vm.model.apartment = room.aptName;
            newCollectionDate.setDate(dueDate.getDate() + COLLECTION_DAYS);
            vm.model.collectionDate = new Date(newCollectionDate);
//            vm.model.total = vm.model.balance - vm.model.deposit
//                    - vm.model.amount;
        }

        vm.paymentType = function() {
            vm.payment.cash = false;
            vm.payment.credit = false;
            vm.payment.check = false;
            vm.payment.paypal = false;
            vm.payment.visa = false;
            vm.payment.master = false;
            switch (vm.model.paymentType) {
            case "cash":
                vm.payment.cash = true;
                break;
            case "visa":
                vm.payment.visa = true;
                vm.payment.credit = true;
                break;
            case "master":
                vm.payment.master = true;
                vm.payment.credit = true;
                break;
            case "check":
                vm.payment.check = true;
                break;
            case "paypal":
                vm.payment.paypal = true;
                break;
            default:
                vm.payment.cash = true;
            }
        }
        vm.removeItem = function(index) {
            if (vm.model.tenants && vm.model.tenants.length > 0 ) {
               	if(index > -1) {
            		vm.model.tenants.splice(index, 1);
            	}
            }
             vm.computeTotal();
        }
        /**
         * assign room to customer
         */
        vm.acquire = function () {
        	if(!vm.startDate) {
        		vm.popup.showError("Please input startDate");
        		return;
        	}
            var index = 0, tx = {};
            if (vm.model.tenants) {
                index = vm.model.tenants.length;    
            }
            tx.aptId = vm.model.apartment.id
            tx.id = 0;
            tx.roomId = vm.model.room.id;
            tx.firstName = vm.renter.firstName;
            tx.initial = vm.renter.initial;
            tx.lastName = vm.renter.lastName;
            tx.startDate = dateFactory.format(vm.startDate);
            tx.txDate = dateFactory.format(vm.startDate);
            //+ 30 days from start date.
            vm.endDate = dateFactory.add(vm.startDate);
            tx.dueDate = dateFactory.format(vm.endDate);
            tx.amount = vm.model.room.rate;
            tx.balance = 0;
            tx.deposit = 0;
            vm.model.rate = vm.model.room.rate;
            vm.model.tenants[index] = tx;
            vm.computeTotal();
        }
        /** 
         * display the rate of the room when 
         * room combo box is change 
         */
        vm.getRate = function (room) {
            vm.model.rate = room.rate;
        }
        
        vm.getTenant = function (room) {
        	vm.showRoomInput = false;
        	vm.model.rate = room.rate;
        	getTenantsByAptRoom(room.aptId, room.id);
//        	vm.popup.spinner(vm.model.response || 0);
        	vm.computeTotal();
        }
        vm.submit = function() {
            submit();
        }

        $scope.$watch(
            function(scope) {
                return {
                    cash : scope.vm.model.cash,
                    items : scope.vm.model.transactions
                };
            },
            function(newValue, oldValue) {
                vm.model.cash.baldep = 0;
                vm.model.cash.balance = 0;
                vm.model.cash.deposit = 0;
                vm.model.cash.cashreceivederror = false;
                vm.model.fullPaidError = false;
                if (vm.model.cash.amountPaid > (vm.model.cash.cashReceived || 0)) {
                    vm.model.cash.cashreceivederror = true;
                    vm.model.cashrecivedNote = 'Cash Received is smaller than the Amount paid';
                }
                vm.model.cash.cashChange = vm.model.cash.cashReceived
                        - vm.model.cash.amountPaid;
                if (vm.model.cash.cashChange < 0) {
                    vm.model.cash.cashChange = 0;
                }
                if (vm.model.cash.amountPaid < vm.model.total) {
                    vm.model.cash.balance =  vm.model.total - vm.model.cash.amountPaid;
                }
                if (vm.model.cash.amountPaid > vm.model.total) {
                    vm.model.cash.deposit =  vm.model.cash.amountPaid - vm.model.total;
                }
                
            }, true)
            
        vm.computeTotal = function () {
            var total = 0;
            vm.model.cash.amountPaid = 0;
            vm.model.cash.balance = 0;
            vm.model.cash.deposit = 0;
            vm.model.total = 0;
            if(vm.showRoomInput) {
            	if (vm.model.tenants && vm.model.tenants.length > 0) {
            		var sharedTotal = vm.model.total / vm.model.tenants
                    for (var i = 0; i < vm.model.tenants.length; i++) {
                        vm.model.tenants[i].amount = sharedTotal;
                    }
                }
            }
            if (vm.model.tenants) {
                vm.model.total = vm.model.total + vm.model.existingBalance;
                for (var i = 0; i < vm.model.tenants.length; i++) {
                    vm.model.total = vm.model.total + vm.model.tenants[i].amount;
                }
            }
        }


/*************************** functions that need to interact with services ******************/
       
        /**
         * get all the available renters
         */    
 
        function getRenters() {
            var renter = '';
            return adminService
                .getRenters()
                .then(
                    function(data) {
                    	processResponse(data);
                   })
        }
        
        /**
         * get rooms by apartment ID
         */
        function getRooms(aptId) {
            return adminService.getRooms(aptId).then(
                    function(response) {
                    	processResponse(response);
                    });
        }
        
        /**
         * get Rooms by renter id
         */
        function getRoomsByRenter(renterId) {
            return transactionService.getRoomsByRenter(renterId).then(
                    function(response) {
                    	processResponse(response);
                    });
        }
        /**
         * get the tenants of the room
         */
        function getTenantsByAptRoom(aptId, roomId) {
	    	 return adminService
	         .getTenants(aptId, roomId)
	         .then(
	             function(response) {
	            	 processResponse(response);
	            	
	             })
        }
        /**
         * fetch all the available apartments
         */
        function getApartments() {
            return adminService.getApartments().then(function(response) {
                processResponse(response);
            });
        }
        
        /**
         * fetch the list of available apartments
         * by apartment Id
         */
        function getApartment() {
            return adminService.getApartment(aptId).then(
                    function(response) {
                    	processResponse(response);
                    });
        }

        /**
         * submit the form
         */
        function submit() {
            if (!vm.model.apartment || !vm.model.room) {
           	 	vm.popup.showError("Select an Apartment and the room.");
                return;
            }
            if (!vm.model.tenants || vm.model.tenants.length < 1) {
                vm.popup.showError("Please assign a tenant(s) in the room.");
                return;
            }
            if ((vm.model.cash.cashReceived &&
            		vm.model.cash.cashReceived < 1) 
            		|| vm.model.cash.cashReceived == "") {
            	 vm.popup.showError("Please input the Amount of Cash Received.");
                 return;
            }
            if ((vm.model.cash.amountPaid &&
                    vm.model.cash.amountPaid < 1) 
                    || vm.model.cash.amountPaid == "") {
                 vm.popup.showError("Please input the Amount Paid.");
                 return;
            }

            var tx = {
                renterId: vm.renter.id,
                transactions: vm.model.transactions,
                paymentType: vm.model.paymentType,
                cash: vm.model.cash,
                credit: vm.model.credit,
                paypal: vm.model.paypal,
                recievedBy: vm.model.receivedBy,
                note: vm.model.note,
                userId: 1
            } 
            transactionService
                    .saveCollection(tx)
                    .then(
                        function(response) {
                            console.log("status return :" + response);
                            if (response.responseStatus === "ERROR") {
                                vm.popup
                                        .showError("There is something wrong in processing your request: "
                                                + response.errorMsg);
                            } else {
                                var options = {
                                    title : "Thank You",
                                    text : "Transaction successfully processed",
                                    type : "success",
                                }
                                vm.popup.show(options, function() {
                                    $location.path('/home');
                                });
                            }

                        });
        }
        
        
        /** test centralize response function **/
        function processResponse(response) {
        	swal.closeModal();
        	var data = response.data;
        	switch(response.method) {
        	case 'getRenters':
        		 var renter = {};
        		 if (data.result && data.result.length > 0) {
                     for (var i = 0; i < data.result.length; i++) {
                         renter = data.result[i];
                         data.result[i].name = (renter.lastName || '')
                                 + " "
                                 + (renter.firstName || '')
                                 + " " + (renter.initial || '')
                     }
                 }
                 vm.renters = data.result
        		break;
        	case 'getRooms':
        		  vm.rooms=[];
                  for (var i = 0; i < data.result.length; i++) {
                      vm.rooms.push(data.result[i]);
                  }
                  vm.rooms;
                  break;
        	case 'getApartment':
        		 vm.rooms = [];
                 var rooms = data.result;
                 for (var i = 0; i < rooms.length; i++) {
                     var room = {
                         id : rooms[i].id,
                         label : ordinal(rooms[i].floor)
                                 + ' Floor - Room #' + rooms[i].roomNo
                     }
                     vm.rooms.push(room);
                 }
                 break;
        	case 'getRoomsByRenter':
        		  vm.model.rooms = data.result;
                  if (vm.model.rooms && vm.model.rooms.length > 0) {
                      vm.model.room = vm.model.rooms[0];
                      vm.setRoomInfo(vm.model.room);
                  }
                  vm.model.rooms;
                  break;
        	case 'getTenants':
        		vm.model.response = 1;
                vm.model.tenants = data.result;
        		break;
        	case 'getApartments':
        		vm.apartments = data.result;
        		break;
        	}
        	
         
        }

    };
    

    RentController.$inject = injectParams;
    angular.module('rental').controller('RentController', RentController);

})();
