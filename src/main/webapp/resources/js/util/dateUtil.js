(function () {
 /* Generic Services */      
 var DateFactory = function () {
     return {
         format: function(dateValue, format) {
             
             if(!format) {
                 format = "MMddYYYY";
             }
             var dDate = new Date(dateValue);
             return (dDate.getMonth() + 1) + "/" + dDate.getDate() + "/" + dDate.getFullYear();
         }
     };
 };
 
 angular.module('rental').factory('dateFactory', DateFactory);

})();