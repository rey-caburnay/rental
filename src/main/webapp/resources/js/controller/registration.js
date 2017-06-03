(function() {

    var injectParams = [ '$filter', 'transactionService', 'adminService',
            'modalService' ];

    var RegistrationController = function($filter, transactionService,
            adminService, modalService) {
        var vm = this;
        vm.ts = transactionService;
        vm.admin = adminService;
        vm.renter;
        vm.startDate;
        vm.endDate;
        vm.collectionDate;
        vm.user = {
            id : null,
            username : '',
            address : '',
            email : ''
        };
       
        filltestData();

        vm.init = function() {
            vm.renter = {
                lastName: 'caburnay',
                firstName:'rey',
                initial: 'c',
                address: 'cebu city',
                mobileNo:'01293213',
                telno:'123123123',
                paymentType:'cash',
                amount:5000,
                idPresented:'gssw #131232',
                email:'test@email.com',
                emergencyContact:'emergency',
            }
        }
        vm.submit = function() {
            submit();
        }

        vm.cancel = function() {
           vm.init();
        }
        vm.popup = function(model) {
            showModal(model);
        }
                /**
         * submit data to server
         */
        function submit() {
            //vm.popup(vm.renter);
             vm.ts.register(vm.renter).then(function(response){
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
            modalService.showAndRedirect(msg, "home");
        }
        
        function filltestData() {
            // test data
            vm.renter = {
                lastname : 'caburnay',
                firstname : 'rey',
                renterMI : 'c',
                address : 'cebu city',
                mobileno : '01293213',
                note : '',
                idpresented : 'Drivers Licence # G019-023123132'
            }

        }

    };

    RegistrationController.$inject = injectParams;
    angular.module('rental').controller('RegistrationController',
            RegistrationController);

})();
