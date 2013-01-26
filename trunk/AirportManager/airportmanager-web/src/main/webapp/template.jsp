
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:layout-definition>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Airportmanager | <c:out value="${pageTitle}" /></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

        <script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar">
                        	
                        </span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    
                    <a class="brand" href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/logo.png" alt="logo" />Airport Manager</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                        	
                            <li ${activeHome}><a href="${pageContext.request.contextPath}/index.jsp"><f:message key="Template.home"/></a></li>
                            <li ${activeFlights}><s:link href="${pageContext.request.contextPath}/flights/all"><f:message key="Template.flights"/></s:link></li>
                            <li ${activeDestinations}><a href="${pageContext.request.contextPath}/destinations/all"><f:message key="Template.destinations"/></a></li>
                            <li ${activeStewards}><a href="${pageContext.request.contextPath}/stewards/all"><f:message key="Template.stewards"/></a></li>
                            <li ${activePlanes}><a href="${pageContext.request.contextPath}/planes/all"><f:message key="Template.planes"/></a></li>
                            <li ${activeAbout}><a href="${pageContext.request.contextPath}/about.jsp"><f:message key="Template.about"/></a></li>
                            <sec:authorize access="isAuthenticated()">
                                 <li><a href="${pageContext.request.contextPath}/users.jsp"><f:message key="Users.users"/></a></li>
                                  <li><a href="<c:url value="j_spring_security_logout" />"><f:message key="Template.Logout"/></a></li>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <li><a href="<c:url value="j_spring_security_login" />"><f:message key="Template.Login"/></a></li>
                            </sec:authorize>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <div class="container">

            <s:layout-component name="content"/>

            <footer>
                <p>&copy; 2012</p>
            </footer>

        </div> <!-- /container -->

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.8.2.min.js"><\/script>')</script>

        <script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>
</s:layout-definition>