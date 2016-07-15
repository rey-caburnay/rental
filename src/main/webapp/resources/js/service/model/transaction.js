(function(){
	
	var Transaction = function (){
		 this.id = '',
		 this.aptId = '',
		 this.roomId = '',
		 this.renterId = '',
		 this.renterLastName = '',
		 this.renterFirstName = '',
		 this.renterMI = '',
		 this.dueDate = '',
		 this.txDate = '',
		 this.startDate = '',
		 this.endDate = '',
		 this.deposit = '',
		 this.balance = '',
		 this.amount = '',
		 this.txType = '',
		 this.provider = '',
		 this.status = '',
		 this.userId = '';
		 
		 return this;
	}

	Transaction.$inject = [];
	angular.module('rental').factory('Transaction', Transaction);
}())