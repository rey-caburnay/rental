(function() {

    var injectParams = [ '$filter', 'TransactionService', 'adminService',
            'modalService' ];

    var CollectionController = function ($filter, TransactionService,
            adminService, modalService) {
        var vm = this;
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
        vm.popup = function(model) {
            showModal(model);
        }
        // test data
        vm.model = {
            name : 'rey bryan caburnay',
            address : 'cebu city',
            mobileno : '01293213',

            paymentType : 'cash',
            amount : 5000,
            apartment : {
                id : 1,
                name : 'Buhisan'
            },
            room : {
                id : 1,
                label : '1st Floor Room # 1'
            }

        }
        vm.ngModelOptionsSelected = function(value) {
            if (arguments.length) {
                vm._selected = value;
                vm.model.mobileno = vm.selected.mobileno;
            } else {
                return vm._selected;
            }
        }
        function getRenters() {
            return adminService.getRenters().then(function(response) {
                return response.data;
            })
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
         *
         */
        function ordinal(number) {
            // Ensure that the passed in data is a number
            if (isNaN(number) || number < 1) {

                // If the data is not a number or is less than one (thus not
                // having a cardinal value) return it unmodified.
                return number;

            } else {

                // If the data we are applying the filter to is a number,
                // perform the actions to check it's ordinal suffix and apply
                // it.

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

    };

    CollectionController.$inject = injectParams;
    angular.module('rental').controller('CollectionController', CollectionController);

})();
