<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/template.jsp" pageTitle="Home" activeHome="class=&quot;active&quot;">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" var="flightsABean"/>

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1><i class="icon-home"></i><f:message key="Index.welcome"/></h1>
                <p><f:message key="Index.headline"/></p>
                <p><a class="btn btn-primary btn-large" href="about.jsp"><f:message key="Index.learn"/> &raquo;</a></p>
            </div>
            
            <%@include file="new-plane-modal-form.jsp"%>
            <%@include file="new-destination-modal-form.jsp"%>
            <%@include file="new-flight-modal-form.jsp"%>
            <%@include file="new-steward-modal-form.jsp"%>
            
            <!-- Example row of columns -->
            <div class="row">
                <div class="span9">
                    <h2><f:message key="Index.recent"/></h2>
                    <table class="table table-striped table-hover table-bordered">
                            <thead>
                                    <tr>
                                            <th><span class="show_tooltip"><f:message key="Flights.identifier"/></span></th>
                                            <th><span class="show_tooltip"><f:message key="Flights.departure"/></span></th>
                                            <th><span class="show_tooltip"><f:message key="Flights.arrival"/></span></th>
                                            <th><span class="show_tooltip"><f:message key="Flights.arrival_dest"/></span></th>
                                    </tr>
                            </thead>
                            <tbody>
                                    <c:forEach items="${flightsABean.flights}" var="flight" begin="0" end="10">    
                                        <tr>
                                            <td>${flight.flightIdentifier}</td>
                                            <td><f:formatDate value="${flight.timeStart}" pattern="dd.MM.yyyy HH:mm" /></td>
                                            <td><f:formatDate value="${flight.timeArrival}" pattern="dd.MM.yyyy HH:mm" /></td>
                                            <td>${flight.destinationArrival.city}(${flight.destinationArrival.country})</td>
                                        </tr>
                                    </c:forEach>
                            </tbody>
                    </table>
                    <p><a class="btn" href="${pageContext.request.contextPath}/flights/all"><f:message key="Index.view_more"/> &raquo;</a></p>
                </div>
                <div class="span3">
                    <h2><f:message key="Global.tools"/></h2>
                    <p><a href="#new_flight_modal" role="button" class="btn btn-primary btn-success" data-toggle="modal"><i class="icon-briefcase"></i> <f:message key="Flights.addnew"/></a></p>
                    <p><a href="#new_destination_modal" role="button" class="btn btn-primary btn-success" data-toggle="modal"><i class="icon-globe"></i> <f:message key="Destinations.addnew"/></a></p>
                    <p><a href="#new_steward_modal" role="button" class="btn btn-primary btn-success" data-toggle="modal"><i class="icon-user"></i> <f:message key="Stewards.addnew"/></a></p>
                    <p><a href="#new_plane_modal" role="button" class="btn btn-primary btn-success" data-toggle="modal"><i class="icon-plane"></i> <f:message key="Planes.addnew"/></a></p>
                </div>
            </div>
            
            <hr>
    </s:layout-component>
</s:layout-render>