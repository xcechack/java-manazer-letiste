<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/template.jsp" pageTitle="Flight" activeFlights="class=&quot;active&quot;">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" var="flightsABean"/>
        
        <div class="hero-unit">
            <h1><i class="icon-globe"></i><f:message key="Flight.header"/> #${flightsABean.flight.id}</h1>
        </div>
            
        <div class="alert alert-error alert-block alertOuterContent">
            <button id="alert_close" type="button" class="close">×</button>
            <div class="alertInnerContent">
                
            </div>
        </div> 
        
        <div class="row">
            <table class="table table-striped table-hover table-bordered">
                    <tbody>
                        <tr><td><f:message key="Global.internal_id"/></td><td>${flightsABean.flight.id}</td></tr>
                        <tr><td><span class="show_tooltip"><f:message key="Flights.identifier"/></span></td><td>${flightsABean.flight.flightIdentifier}</td></tr>
                        <tr><td><span class="show_tooltip"><f:message key="Flights.departure"/></i></span></td><td><f:formatDate value="${flightsABean.flight.timeStart}" pattern="dd.MM.yyyy HH:mm" /></td></tr>
                        <tr><td><span class="show_tooltip"> <f:message key="Flights.arrival"/></span></td><td><f:formatDate value="${flightsABean.flight.timeArrival}" pattern="dd.MM.yyyy HH:mm" /></td></tr>
                        <tr><td><span class="show_tooltip"> <f:message key="Flights.plane"/></span></td><td>${flightsABean.flight.plane.producer} ${flightsABean.flight.plane.type}<td></td></tr>
                        <tr><td><span class="show_tooltip"> <f:message key="Flights.departure_dest"/></span></td><td>${flightsABean.flight.destinationStart.country} ${flightsABean.flight.destinationStart.country}</td></tr>
                        <tr><td><span class="show_tooltip"> <f:message key="Flights.arrival_dest"/></span></td><td>${flightsABean.flight.destinationArrival.country} ${flightsABean.flight.destinationArrival.city}</td></tr>
                        <tr><td><span class="show_tooltip"> <f:message key="Stewards.header"/></span></td>
                            <td>
                                <ul class="nav nav-list">
                                    <c:forEach items="${flightsABean.flight.stewardess}" var="steward" >
                                        <li><i class="icon-user"></i>${steward.name} ${steward.surname}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
            </table>
        </div>
    </s:layout-component>
</s:layout-render>