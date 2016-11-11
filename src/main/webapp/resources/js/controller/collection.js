(function() {
    'use strict';

    var injectParams = [ '$scope','$filter', 'transactionService', 'adminService',
            'modalService', '$location'];

    var CollectionController = function ($scope, $filter, transactionService,
            adminService, modalService, $location) {
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
            deposit: "Expenses",
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
                    cashchange : '',
                    cashReceived : ''
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
            vm.model.renterId = renter.id;
            vm.model.mobileno = renter.mobileNo;
            vm.model.telno = renter.telno;
            vm.model.lastname = renter.lastName;
            vm.model.firstname = renter.firstName;
            vm.model.address = renter.address;
            vm.model.transactions = renter.transactions;
        }
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
        
        vm.toggleFullPaid = function ($index) {
            validateCash();
        }
        
        $scope.$watch(function(scope) {
            return {cash: scope.vm.model.cash, items: scope.vm.model.transactions};
        },
        function (newValue, oldValue) {
            vm.model.cash.baldep = 0;
            vm.model.cash.cashreceivederror = false;
            vm.model.fullPaidError = false;
            if (vm.model.cash.amountPaid > (vm.model.cash.cashReceived || 0)) {
                vm.model.cash.cashreceivederror = true;
                vm.model.cashrecivedNote  = 'Cash Received is smaller than the Amount paid';    
            }
            vm.model.cash.cashChange = vm.model.cash.cashReceived - vm.model.cash.amountPaid;
            if(vm.model.cash.cashChange < 0) {
                vm.model.cash.cashChange = 0;
            }
            validateCash();
        }, true)
        
        function validateCash() {
            var total = 0,
            hasPaid = false;
            vm.model.fullPaidError = false;
            vm.model.cash.amountPaid = 0;
            vm.model.cash.balance = 0;
            vm.model.cash.deposit = 0;
            if(vm.model.transactions) {
                //search if there is input in amount paid
                for(var i = 0; i < vm.model.transactions.length; i++) {
                    //search full paid items
                    console.log("amount paid :" + vm.model.transactions[i].amountPaid)
                    if(vm.model.transactions[i].amountPaid && vm.model.transactions[i].amountPaid > 0) {
                        hasPaid = true;
                        vm.model.cash.amountPaid = vm.model.cash.amountPaid + vm.model.transactions[i].amountPaid;
                    }
                    if(vm.model.transactions[i].amountPaid && vm.model.transactions[i].amountPaid >= vm.model.transactions[i].amount) {
                        vm.model.transactions[i].isFullPaid = true;
                        vm.model.cash.deposit +=  vm.model.transactions[i].amountPaid - vm.model.transactions[i].amount;
                        vm.model.transactions[i].deposit = vm.model.transactions[i].amountPaid - vm.model.transactions[i].amount;
                        vm.model.transactions[i].balance = 0;
                        
                    } else {
                        vm.model.transactions[i].isFullPaid = false;
                        vm.model.cash.balance += vm.model.transactions[i].amount - vm.model.transactions[i].amountPaid;
                        vm.model.transactions[i].balance = vm.model.transactions[i].amount - vm.model.transactions[i].amountPaid;
                        vm.model.transactions[i].deposit = 0;
                    }
                    console.log("amount paid :" + vm.model.transactions[i].amountPaid + "deposit: " +  vm.model.transactions[i].balance + "balance :" + vm.model.transactions[i].balance)
                }
                //possible partial payments
                if (!hasPaid) {
                    vm.model.fullPaidError = true;
                }
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
            if(vm.model.cash.cashreceivederror || vm.model.fullPaidError) {
                vm.popup.showError("Please fix/adjust inputs to continue the transaction.")
                return;
            } 
            vm.model.userId = 1;
            transactionService.saveCollection(vm.model)
                .then(function(response){
                    console.log("status return :" + response);
                    if (response.responseStatus === "ERROR") {
                        vm.popup.showError("There is something wrong in processing your request: " + response.errorMsg);
                    } else {
                        var options = {
                            title: "Thank You",
                            text: "Transaction successfully processed",
                            type: "success",
                        }
                        vm.popup.show(options, function () {$location.path('/home');});
                    }
                    
             });
        }

    };

    CollectionController.$inject = injectParams;
    angular.module('rental').controller('CollectionController', CollectionController);

})();
