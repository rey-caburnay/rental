(function() {

    var injectParams = [ '$filter', 'transactionService', 'adminService',
            'modalService' ];

    var CollectionController = function ($filter, transactionService,
            adminService, modalService) {
        var vm = this;
        vm.services = [adminService,transactionService, modalService];
        vm._selected;
        vm.model = {}; //model for ui;
        vm.model.rooms = [];
        vm.renters = getRenters();

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
        vm.setRenter = function (renter) {
            vm.model.mobileno = renter.mobileNo;
            vm.model.telno = renter.telno;
            getRoomsByRenter(renter.id);
        }
        vm.popup = function (model) {
            showModal(model);
        }
        vm.setRoomInfo = function (room) {

            vm.model.amount = room.amount;
            vm.model.paymentType = room.paymentType;
            vm.model.deposit = room.deposit;
            vm.model.balance = room.balance;
            vm.model.amtInWords = room.amount;
            vm.model.apartment = room.aptName;
            vm.collectionDate = room.dueDate;
            vm.model.total =  vm.model.balance - vm.model.deposit - vm.model.amount;
        }
        function getRenters() {
            var reter = '';
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
        function showModal(result) {
            var msg = 'Successfully Registered';
            if (result.responseStatus != 'OK') {
                msg = 'Failed to Register';
            }
            var options = {
                header : msg,
                service : result.result
            };
            modalService.show(options);
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

    };

    CollectionController.$inject = injectParams;
    angular.module('rental').controller('CollectionController', CollectionController);

})();
