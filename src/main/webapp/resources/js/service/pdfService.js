/**
 * Modal service using the sweetalert2 library
 */

(function() {
  'use strict'
  var injectParams = [ '$uibModal', '$location', '$window' ];
  var PdfService = function($uibModal, $location, $window) {
    PDFJS.workerSrc = '/resources/js/external/pdf.worker.js';
    
    return {
      view : function(data) {
        
        // Using DocumentInitParameters object to load binary data.
        var loadingTask = PDFJS.getDocument({
          data : pdfData
        });
        loadingTask.promise.then(function(pdf) {
          console.log('PDF loaded');
          
          // Fetch the first page
          var pageNumber = 1;
          pdf.getPage(pageNumber).then(function(page) {
            console.log('Page loaded');
            
            var scale = 1.5;
            var viewport = page.getViewport(scale);
            
            // Prepare canvas using PDF page dimensions
            var canvas = document.getElementById('the-canvas');
            var context = canvas.getContext('2d');
            canvas.height = viewport.height;
            canvas.width = viewport.width;
            
            // Render PDF page into canvas context
            var renderContext = {
              canvasContext : context,
              viewport : viewport
            };
            var renderTask = page.render(renderContext);
            renderTask.then(function() {
              console.log('Page rendered');
            });
          });
        }, function(reason) {
          // PDF loading error
          console.error(reason);
        });
      }
    }
  }
  PdfService.$inject = injectParams;
  angular.module('rental').factory('pdfService', PdfService);
})();
