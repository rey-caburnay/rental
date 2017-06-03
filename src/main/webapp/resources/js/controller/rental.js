(function() {
    'use strict';

    var injectParams = [ '$scope', '$filter', 'transactionService',
            'adminService', 'modalService', '$location' ];

    var RentController = function($scope, $filter, transactionService,
            adminService, modalService, $location) {
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
        vm.renters = getRenters();

        vm.initModel = function() {
            vm.model.total = 0;
            vm.model.balance = 0;
            vm.model.deposit = 0;
            vm.model.amount = 0;
            vm.model.cash = {
                amtpaid : '',
                cashchange : '',
                cashReceived : ''
            }
        }
        vm.initModel();
        vm.getRooms = function(aptId) {
            getRooms(aptId);
        };
        vm.submit = function() {
            submit();
        }
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
            vm.initModel();
            vm.model.renterId = renter.id;
            vm.model.mobileno = renter.mobileNo;
            vm.model.telno = renter.telno;
            vm.model.lastname = renter.lastName;
            vm.model.firstname = renter.firstName;
            vm.model.address = renter.address;
            vm.model.transactions = renter.transactions;
            if (vm.model.transactions) {
                vm.showRoomInput = false;
            }
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
            if (index > -1) {
                vm.model.transactions.splice(index, 1);
            }
            vm.computeTotal();
        }
        /**
         * assign room to customer
         */
        vm.acquire = function () {
            var length = 0, tx = {};
            if (vm.model.transactions) {
                length = vm.model.transactions.length;    
            }
            tx.aptName = vm.model.apartment.aptName;
            tx.room = vm.model.room;
            tx.dueDate = vm.endDate;
            tx.amount = vm.model.room.rate;
            vm.model.rate = vm.model.room.rate;
            vm.model.transactions[length + 1] = tx;
            vm.computeTotal();
        }
        /** 
         * display the rate of the room when 
         * room combo box is change 
         */
        vm.getRate = function (room) {
            vm.model.rate = room.rate;
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
            if (vm.model.transactions) {
                vm.model.total = vm.model.total + vm.model.existingBalance;
                for (var i = 0; i < vm.model.transactions.length; i++) {
                    vm.model.total = vm.model.total + vm.model.transactions[i].amount;
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
                    function(response) {
                        if (response.result.length > 0) {
                            for (var i = 0; i < response.result.length; i++) {
                                renter = response.result[i];
                                response.result[i].name = (renter.lastName || '')
                                        + " "
                                        + (renter.firstName || '')
                                        + " " + (renter.initial || '')
                            }
                        }
                        return response.result;
                    })
        }
        
        /**
         * get rooms by apartment ID
         */
        function getRooms(aptId) {
            return adminService.getRooms(aptId).then(
                    function(response) {
                        vm.rooms=[];
                        for (var i = 0; i < response.result.length; i++) {
                            vm.rooms.push(response.result[i]);
                        }
                        return vm.rooms;
                    });
        }
        
        /**
         * get Rooms by renter id
         */
        function getRoomsByRenter(renterId) {
            return transactionService.getRoomsByRenter(renterId).then(
                    function(response) {
                        vm.model.rooms = response.result;
                        if (vm.model.rooms && vm.model.rooms.length > 0) {
                            vm.model.room = vm.model.rooms[0];
                            vm.setRoomInfo(vm.model.room);
                        }
                        return vm.model.rooms;
                    });
        }
        
        /**
         * fetch all the available apartments
         */
        function getApartments() {
            return adminService.getApartments().then(function(response) {
                vm.apartments = response.result;
//                return response.result;
            });
        }
        
        /**
         * fetch the list of available apartments
         * by apartment Id
         */
        function getApartment() {
            return adminService.getApartment(aptId).then(
                    function(response) {
                        vm.rooms = [];
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
         * submit the form
         */
        function submit() {
            if (vm.model.cash.cashreceivederror || vm.model.fullPaidError) {
                vm.popup
                        .showError("Please fix/adjust inputs to continue the transaction.")
                return;
            }
            vm.model.userId = 1;
            transactionService
                    .saveCollection(vm.model)
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

    };

    RentController.$inject = injectParams;
    angular.module('rental').controller('RentController', RentController);

})();
