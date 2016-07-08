<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    </style>
<!--      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
     <link href="<c:url value='/resources/core/css/app.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/resources/core/css/bootstrap.min.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="rental" class="ng-cloak">
    <!--[if lt IE 7]>
    <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
        your browser</a> to improve your experience.</p>
    <![endif]-->

    <!-- Our Website Content Goes Here -->
    <div ng-include="resources/ui/header.html"></div>
    <div ng-view></div>
    <div ng-include="resources/ui/footer.html"></div>

    <!-- Vendor: Javascripts -->
    <script src="<c:url value='/resources/core/js/external/jquery.min.js'/>"></script>
    <script src="<c:url value='/resources/core/js/external//bootstrap.min.js'/>"></script>

    <!-- Vendor: Angular, followed by our custom Javascripts -->
    <script src="<c:url value='/resources/core/js/external/angular.min.js'/>"></script>
    <script src="<c:url value='/resources/core/js/external/angular.route.min.js'/>"></script>

    <!-- Our Website Javascripts -->
    <script src="<c:url value='/resources/core/js/external/angular.1.4.4.js' />"></script>
    <script src="<c:url value='/resources/core/js/app.js' />"></script>
    <script src="<c:url value='/resources/core/js/service/user_service.js' />"></script>
    <script src="<c:url value='/resources/js/controller/user_controller.js' />"></script>
  </body>
</html>