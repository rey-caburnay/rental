(function() {
	// Setup the filter
	angular.module('rental').filter('ordinal', function() {
		// Create the return function
		// set the required parameter name to **number**
		return function(number) {

			// Ensure that the passed in data is a number
			if (isNaN(number) || number < 1) {

				// If the data is not a number or is less than one (thus not having a
        // cardinal value) return it unmodified.
				return number;

			} else {

				// If the data we are applying the filter to is a number, perform the
        // actions to check it's ordinal suffix and apply it.

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
	});

	// Setup the filter
	angular.module('rental').filter('customOrdinal', function() {
		// Create the return function
		// set the required parameter name to sample data 1-1
		// transform to 1st Floor - Room 1
		return function(data) {
			// Ensure that the passed in data is a number
			if (!data) {
				return data;
			} else {

				var lastDigit = data.floor % 10;
				var a = data.floor;
				var flor;

				if (lastDigit === 1) {
					flor = a + 'st'
				} else if (lastDigit === 2) {
					flor = a + 'nd'
				} else if (lastDigit === 3) {
					flor = a + 'rd'
				} else if (lastDigit > 3) {
					flor = a + 'th'
				}
				flor = flor + ' Floor';
				var room = 'Room - ' + data.roomNo;
				return flor + room;

			}
		}
	});

	// number to words
	angular.module('rental').filter('amtToWords', function() {
		var th = [ '', 'thousand', 'million', 'billion', 'trillion' ];
		var dg = [ 'zero', 'one', 'two', 'three', 'four', 'five', 'six',
				'seven', 'eight', 'nine' ];
		var tn = [ 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen',
				'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen' ];
		var tw = [ 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy',
				'eighty', 'ninety' ];
		function isInteger(x) {
			return x % 1 === 0;
		}

		function toWords(s) {
			s = s.toString();
			s = s.replace(/[\, ]/g, '');
			if (s != parseFloat(s))
				return 'not a number';
			var x = s.indexOf('.');
			if (x == -1)
				x = s.length;
			if (x > 15)
				return 'too big';
			var n = s.split('');
			var str = '';
			var sk = 0;
			for (var i = 0; i < x; i++) {
				if ((x - i) % 3 == 2) {
					if (n[i] == '1') {
						str += tn[Number(n[i + 1])] + ' ';
						i++;
						sk = 1;
					} else if (n[i] != 0) {
						str += tw[n[i] - 2] + ' ';
						sk = 1;
					}
				} else if (n[i] != 0) {
					str += dg[n[i]] + ' ';
					if ((x - i) % 3 == 0)
						str += 'hundred ';
					sk = 1;
				}

				if ((x - i) % 3 == 1) {
					if (sk)
						str += th[(x - i - 1) / 3] + ' ';
					sk = 0;
				}
			}
			if (x != s.length) {
				var y = s.length;
				str += 'point ';
				for (var i = x + 1; i < y; i++)
					str += dg[n[i]] + ' ';
			}
			return str.replace(/\s+/g, ' ');
		}

		return function(value) {
			if (value && isInteger(value))
				return toWords(value);

			return value;
		};

	});
	
	// filter for date format MMddYYYY
	angular.module('rental').filter('MMddYYYY', function() {
		// Create the return function
		// set the required parameter name to **number**
		return function(date) {
		  if(!date) {
		    return "";
		  }
			var newDate = new Date();
			if (date && date instanceof Date) {
				newDate = date;
			}
			if(date && !(date instanceof Date)) {
				newDate = new Date(date);
			}
			return (newDate.getMonth() + 1) + "/" + newDate.getDate() + "/" + newDate.getFullYear();
		}
	});
	// filter for date format yyyy-MM-dd
	angular.module('rental').filter('yyyyMMdd', function() {
		// Create the return function
		// set the required parameter name to **number**
	  var newDate = new Date();
		return function(date) {
			if(date && date instanceof Date) {
				newDate = new Date(date);
			} 
			return (newDate.getFullYear() + "-" + newDate.getMonth() + 1) + "-" + newDate.getDate();
		}
	});

	angular.module('rental').filter('cusCurrency',
    [ '$filter', '$locale',
	  function(filter, locale) {
	    var currencyFilter = filter('currency');
	    var formats = locale.NUMBER_FORMATS;
	    return function(amount, currencySymbol) {
	    
	      if (!currencySymbol) {
	        currencySymbol = '';
	      }
	      return currencyFilter(amount,currencySymbol);
//	      var sep = value.indexOf(formats.DECIMAL_SEP);
//	    if(amount >= 0) { 
//	      return value.substring(0, sep);
//	    }
//	      return value.substring(0, sep) + ')';
	    };
	 }]);
})();