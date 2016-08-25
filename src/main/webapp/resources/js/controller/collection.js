(function() {

    var injectParams = [ '$filter', 'TransactionService', 'adminService',
            'modalService' ];

    var CollectionController = function ($filter, transactionService,
            adminService, modalService) {
        var vm = this;
        vm.services = [adminService,TransactionService, modalService];
        vm._selected;
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
            get
        }
        vm.popup = function(model) {
            showModal(model);
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
        function getRoomsByRenter() {
            return transactionService.getRooms().then(function(response) {
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
            vm.model.aptId = vm.model.apartment.id;
            vm.model.roomId = vm.model.room.id;

            vm.model.startDate = vm.startDate.toISOString().substring(0, 10);
            if (vm.endDate) {
                vm.model.endDate = vm.endDate.toISOString().substring(0, 10);
            }
            if (vm.collectionDate) {
                vm.model.collectionDate = vm.collectionDate.toISOString()
                        .substring(0, 10);
            }
            vm.ts.saveTx(vm.model).then(function(response) {
                console.log("status return :" + response);
                vm.popup(response);
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
