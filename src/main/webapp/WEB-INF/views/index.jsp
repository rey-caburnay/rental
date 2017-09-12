<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="rental">
  <head>
  
    </style>
<!--      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
     <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/nav.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/main.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/sweetalert.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/simple-tabs.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/simpleform.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/custom-simple-form.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/responstable.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>

  </head>
  <body  class="ng-cloak">
    <!--[if lt IE 7]>
    <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
        your browser</a> to improve your experience.</p>
    <![endif]-->

    <!-- Our Website Content Goes Here -->
     <div class="container">
	    <div ng-include="'<c:url value='resources/ui/header.html'/>'"></div>
	  	<div ng-view class="slide-animation"></div>
	 <script type="text/ng-template" id="myModalContent.html">
        <div class="modal-header">
            <h3 class="modal-title">I'm a modal!</h3>
        </div>
        <div class="modal-body">
            <ul>
                <li ng-repeat="item in items">
                    <a href="#" ng-click="$event.preventDefault(); selected.item = item">{{ item }}</a>
                </li>
            </ul>
            Selected: <b>{{ selected.item }}</b>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
            <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
        </div>
    </script>

	</div>

    <!-- Vendor: Javascripts -->
     <script src="<c:url value='/resources/js/external/jquery.js'/>"></script>
    <script src="<c:url value='/resources/js/external/bootstrap.min.js'/>"></script>


<!--      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->



    <!-- Our Website Javascripts -->
    <script src="<c:url value='/resources/js/external/angular.min.js' />"></script>
    <script src="<c:url value='/resources/js/external/angular-sanitize.js' />"></script>
    <script src="<c:url value='/resources/js/external/angular-route.js' />"></script>
        <script src="<c:url value='/resources/js/external/angular-resource.min.js' />"></script>
    <script src="<c:url value='/resources/js/external/ui-bootstrap-1.3.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/external/sweetalert.min.js'/>"></script>
    <script src="<c:url value='/resources/js/external/angular-pdfjs-viewer.js'/>"></script>
   	 <script src="<c:url value='/resources/js/app.js' />"></script>
    <!-- utils -->
    <script src="<c:url value='/resources/js/util/dateUtil.js' />"></script>
    <script src="<c:url value='/resources/js/util/util.js' />"></script>
    <!-- filters -->
    <script src="<c:url value='/resources/js/filter/filter.js' />"></script>
    <!-- services -->
    <script src="<c:url value='/resources/js/service/authService.js' />"></script>
    <script src="<c:url value='/resources/js/service/transaction.js' />"></script>
     <script src="<c:url value='/resources/js/service/admin.js' />"></script>
     <script src="<c:url value='/resources/js/service/modalService.js' />"></script>
     <script src="<c:url value='/resources/js/service/interceptor.js' />"></script>
     <script src="<c:url value='/resources/js/service/pdfService.js' />"></script>
    <!--  service model -->
    <script src="<c:url value='/resources/js/service/model/transaction.js' />"></script>
    <!-- controllers -->
    <script src="<c:url value='/resources/js/controller/user_controller.js' />"></script>
    <script src="<c:url value='/resources/js/controller/transaction.js' />"></script>
    <script src="<c:url value='/resources/js/controller/registration.js' />"></script>
    <script src="<c:url value='/resources/js/controller/rental.js' />"></script>
    <script src="<c:url value='/resources/js/controller/collection.js' />"></script>
    <script src="<c:url value='/resources/js/controller/billing.js' />"></script>
    <script src="<c:url value='/resources/js/controller/report.js' />"></script>
   
  </body>
</html>