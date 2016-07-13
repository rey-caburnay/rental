(function () {

    var injectParams = ['$filter', 'TransactionService', 'Transaction'];

    var TransactionController = function ($filter,  TransactionService, Transaction) {
    	var self = this;
    	self.ts = TransactionService;
    	self.txModel = Transaction;
        self.user={id:null, username:'', address:'', email:''};
        self.renter = {
        	id:null,
        	lastname:'test',
        	firstname:'',
        	mi:'', 
        	address:'', 
        	mobileno:'', 
        	telno:'', 
        	idpresented:''
        };
        self.apartment;
        self.roomId;
        self.room = {type:''};
        self.trans = {
        	type:'', 
        	deposit:1000, 
        	advance:500, 
        	startDate:'06/22/2016', 
        	endDate:'06/22/2017',
        	amount:'',
        	amtInWords:'',
        	collectionDate:'',
        	status:''};
        self.util = {
        	elecCurntRead:'',
        	watrCurntRead:''
        }
        self.receivedBy;
        self.note;
        self.customers = [];
        self.filteredCustomers;
        self.filteredCount;
        self.rentType = [
        	{name:"innType", label: "Inn Type"},
            {name:"aptType", label: "Apartment"},
            {name:"aptType", label: "Store"},      
        ]
        self.apartments = [
                     	{id:'', name:"Buhisan"},
                        {id:'', name:"Lapulapu"},
                        {id:'', name:'Shrine'},      
                     ]
        self.paymentType;

};
        
      
    
    TransactionController.prototype = {
    		init:function () {

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
    			
    		}
    }

    TransactionController.$inject = injectParams;
    App.controller('TransactionController', TransactionController);

})();

