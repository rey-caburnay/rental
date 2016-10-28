(function() {
    'use strict';

    var injectParams = [ '$scope','$filter', 'transactionService', 'adminService',
            'modalService' ];

    var CollectionController = function ($scope, $filter, transactionService,
            adminService, modalService) {
        var vm = this,
        collection = {}, //model to map to services
        paymentTypes = ["cash", "check", "online"],
        COLLECTION_DAYS = 30;
        
        vm.services = [adminService,transactionService, modalService];
        vm.popup = modalService;
        vm._selected;
        vm.model = {}; //model for ui;
        vm.model.total = 0;
        vm.model.paymentType = paymentTypes[0];
        vm.label = {
            deposit: "Deposit",
            total: "Total"
        }
        vm.payment = {
            cash : true,
            credit : false,
            check : false,
            paypal : false,
            visa : false,
            master : false
        };
        
        vm.model.rooms = [];
        vm.renters = getRenters();

        vm.initModel = function () {
            vm.model.total = 0;
            vm.model.balance = 0;
            vm.model.deposit = 0;
            vm.model.amount = 0;
            vm.model.cash = {
                    amtpaid : '',
                    cashchanged : '',
                    cashreceived : ''
            }
        }
        vm.initModel();
        vm.submit = function () {
            submit();
        }
        vm.modelOptions = {
            debounce : {
                "default" : 500,
                blur : 250
            },
            getterSetter : true
        };
        vm.setRenter = function (renter) {
            vm.initModel();
            vm.model.mobileno = renter.mobileNo;
            vm.model.telno = renter.telno;
            vm.model.lastName = renter.lastName;
            vm.model.firstName = renter.firstName;
            vm.model.address = renter.address;
            vm.model.transactions = renter.transactions;
            computeRooms(renter.transactions);
//            getRoomsByRenter(renter.id);
        }
//        vm.popup = function (model) {
//            showModal(model);
//        }
        vm.computeAmount = function () {
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
        vm.setRoomInfo = function (room) {
            var dueDate = new Date(room.dueDate),
            newCollectionDate = new Date();
            //map values to service
            collection.txId = room.txId;
            collection.renterId = room.id;
            collection.roomId = roomId;
            collection.aptId = room.aptId;
            //map values to ui
            vm.model.amount = room.amount;
            vm.model.paymentType = room.paymentType;
            vm.model.deposit = room.deposit;
            vm.model.balance = room.balance;
            vm.model.amtInWords = room.amount;
            vm.model.apartment = room.aptName;
            newCollectionDate.setDate(dueDate.getDate() + COLLECTION_DAYS); 
            vm.model.collectionDate = new Date(newCollectionDate);
            vm.model.total =  vm.model.balance - vm.model.deposit - vm.model.amount;
        }

        vm.paymentType = function () {
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
        vm.removeItem = function (index) {
            if (index > -1) {
                vm.model.transactions.splice(index, 1);
            }
            computeRooms(vm.model.transactions);
        }
        vm.submit = function () {
          
            submit();
        }
        $scope.$watchCollection('vm.model.cash',
        function (newValue) {
            vm.model.cash.baldep = 0;
            vm.model.cash.cashreceivederror = false;
            if (vm.model.cash.amtpaid > vm.model.cash.cashreceived) {
                vm.model.cash.cashreceivederror = true;
                vm.model.cashrecivedNote  = 'Cash Received is smaller than the Amount paid';    
            }
            vm.model.cash.cashchanged = vm.model.cash.cashreceived - vm.model.cash.amtpaid;
            if(vm.model.cash.cashchanged < 0) {
                vm.model.cash.cashchanged = 0;
            }
            if (vm.model.cash.amtpaid > vm.model.total && !vm.model.cash.cashreceivederror) {
                vm.model.cash.depoist = vm.model.amtpaid - vm.model.total;
                vm.model.cash.baldep = vm.model.cash.deposit;
            } else if (vm.model.cash.amtpaid < vm.model.total && !vm.model.cash.cashreceivederror) {
                vm.model.cash.balance = vm.model.total - vm.model.cash.amtpaid;
                vm.model.cash.baldep = vm.model.cash.amtpaid - vm.model.total;
            }
            
        })
        
        /* functions to communicate with server */
        function computeRooms(transactions) {
            vm.initModel();
            for (var i = 0; i < transactions.length; i++) {
                vm.model.total = (vm.model.total || 0) + (transactions[i].room.rate || 0);
                vm.model.deposit = (vm.model.deposit || 0) + (transactions[i].deposit || 0);
                vm.model.balance = (vm.model.balance || 0) + (transactions[i].balance || 0);
            }
            
            vm.model.total = vm.model.total - vm.model.deposit;
            vm.model.deposit = vm.model.deposit;
            if (vm.model.deposit < 1 && vm.model.balance > 1) {
                vm.model.deposit = vm.model.balance;
            } 
        }
        function getRenters() {
            var renter = '';
            return adminService.getRenters().then(function(response) {
                if(response.result.length > 0) {
                    for(var i = 0; i < response.result.length; i++) {
                        renter = response.result[i];
                        response.result[i].name = (renter.lastName || '') + " " +
                            (renter.firstName || '') + " " + (renter.initial || '')
                    }
                }

                return response.result;
            })
        }
        function getRoomsByRenter(renterId) {
            return transactionService.getRoomsByRenter(renterId)
                .then(function(response) {
                    vm.model.rooms = response.result;
                    if(vm.model.rooms && vm.model.rooms.length > 0) {
                        vm.model.room = vm.model.rooms[0];
                        vm.setRoomInfo(vm.model.room);
                    }
                    return  vm.model.rooms;
                });
        }
//        function showModal(result) {
//            var msg = 'Successfully Registered';
//            if (result.responseStatus != 'OK') {
//                msg = 'Failed to Register';
//            }
//            var options = {
//                header : msg,
//                service : result.result
//            };
//            modalService.show(options);
//        }
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
        function submit() {
            collection.txDate = ''; // server will handle this
            collection.status = ''; // server will handle this
            collection.cash = {};
            collection.credit = {};
            if(vm.model.cash.cashreceivederror) {
                vm.popup.showError("Cash Recevied is lesser than the actual amount paid by the customers, " +
                		"Please adjust to continue the transaction.")
                return;
            } 
            if (vm.payment.cash) {
                collection.cash.cashReceived = vm.model.cash.cashreceived || 0;
                collection.cash.amountPaid = vm.model.cash.amtpaid || 0;
                collection.cash.change = vm.model.cash.change || 0;
                collection.cash.balance = vm.model.cash.balance || 0;
                collection.cash.deposit = vm.model.cash.deposit || 0;
            } else  if (vm.payment.credit) {
                //TODO
            } else if (vm.payment.check) {
                //TODO
            } else  if (vm.payment.paypal) {
                //TODO
            }
            collection.transactions = vm.model.transactions;
            collection.userId = 1;
            transactionService.saveCollection(collection)
                .then(function(response){
                    console.log("status return :" + response);
                    vm.popup(response);
             });
        }

    };

    CollectionController.$inject = injectParams;
    angular.module('rental').controller('CollectionController', CollectionController);

})();
