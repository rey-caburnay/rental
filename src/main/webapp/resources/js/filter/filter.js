(function(){
// Setup the filter
angular.module('rental').filter('ordinal', function() {
  // Create the return function
  // set the required parameter name to **number**
  return function(number) {

    // Ensure that the passed in data is a number
    if(isNaN(number) || number < 1) {

      // If the data is not a number or is less than one (thus not having a cardinal value) return it unmodified.
      return number;

    } else {

      // If the data we are applying the filter to is a number, perform the actions to check it's ordinal suffix and apply it.

      var lastDigit = number % 10;

      if(lastDigit === 1) {
        return number + 'st'
      } else if(lastDigit === 2) {
        return number + 'nd'
      } else if (lastDigit === 3) {
        return number + 'rd'
      } else if (lastDigit > 3) {
        return number + 'th'
      }

    }
  }
});

//Setup the filter
angular.module('rental').filter('customOrdinal', function() {
  // Create the return function
  // set the required parameter name to sample data 1-1
  //transform to 1st Floor - Room 1
  return function(data) {
    // Ensure that the passed in data is a number
    if(!data) {
      return data;
    } else {
    	
        var lastDigit = data.floor % 10;
        var a = data.floor;
        var flor;

      if(lastDigit === 1) {
    	 flor = a + 'st'
      } else if(lastDigit === 2) {
    	 flor = a + 'nd'
      } else if (lastDigit === 3) {
    	 flor = a + 'rd'
      } else if (lastDigit > 3) {
    	 flor = a + 'th'
      }
      flor = flor + ' Floor';
      var room = 'Room - '+ data.roomNo;
      return flor + room;

    }
  }
});
})();