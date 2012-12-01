<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/template.jsp" pageTitle="Flights" activeHome="" activeFlights="class=&quot;active&quot;" activeDestinations="" activeStewards="" activePlanes="" activeAbout="">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" var="flightsABean"/>
        
        <!-- Formular pridavania letov -->
        <%@include file="new-flight-modal-form.jsp"%>
        
        <!-- Formular editovania letov -->
        <%@include file="edit-flight-modal-form.jsp"%>
        
        <!-- NADPIS: -->
        <div class="hero-unit">
            <h1><i class="icon-briefcase"></i><f:message key="Flights.header"/></h1>
        </div>
         <!-- TLACITKO PRIDANIA LETU: -->
        <p>
            <a href="#new_flight_modal" role="button" class="btn" data-toggle="modal"><i class="icon-plus"></i> <f:message key="Flights.addnew"/></a>
        </p>
        <div class="alert alert-error alert-block alertOuterContent">
            <button id="alert_close" type="button" class="close">×</button>
            <div class="alertInnerContent">
                
            </div>
        </div>  
        
        <div class="row">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th><f:message key="Global.internal_id"/></th>
                        <th><span class="show_tooltip"><f:message key="Flights.identifier"/></span></th>
                        <th><span class="show_tooltip"><f:message key="Flights.departure"/> <i class="icon-arrow-down"></i></span></th>
                        <th><span class="show_tooltip"> <f:message key="Flights.arrival"/></span></th>
                        <th><span class="show_tooltip"> <f:message key="Flights.plane"/></span></th>
                        <th><span class="show_tooltip"> <f:message key="Flights.departure_dest"/></span></th>
                        <th><span class="show_tooltip"> <f:message key="Flights.arrival_dest"/></span></th>
                        <th><f:message key="Global.tools"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${flightsABean.flights}" var="flight" >
                        <tr class="flight" data-internal-id="${flight.id}">
                            <td>${flight.id}</td>
                            <td class="flight_identifier" data-flight-identifier="${flight.flightIdentifier}">
                                <s:link rel="${flight.id}" beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" event="show" tabindex="-1" href="#">
                                    <s:param name="flight.id" value="${flight.id}"/>
                                    <c:out value="${flight.flightIdentifier}"/>
                                </s:link>
                            </td>
                            <td class="departure" data-departure-day="<f:formatDate value="${flight.timeStart}" pattern="dd" />" data-departure-month="<f:formatDate value="${flight.timeStart}" pattern="MM" />" data-departure-year="<f:formatDate value="${flight.timeStart}" pattern="yyyy" />" data-departure-hour="<f:formatDate value="${flight.timeStart}" pattern="HH" />" data-departure-minute="<f:formatDate value="${flight.timeStart}" pattern="mm" />"><f:formatDate value="${flight.timeStart}" pattern="dd.MM.yyyy HH:mm" /></td>
                            <td class="arrival" data-arrival-day="<f:formatDate value="${flight.timeArrival}" pattern="dd" />" data-arrival-month="<f:formatDate value="${flight.timeArrival}" pattern="MM" />" data-arrival-year="<f:formatDate value="${flight.timeArrival}" pattern="yyyy" />" data-arrival-hour="<f:formatDate value="${flight.timeArrival}" pattern="HH" />" data-arrival-minute="<f:formatDate value="${flight.timeArrival}" pattern="mm" />"><f:formatDate value="${flight.timeArrival}" pattern="dd.MM.yyyy HH:mm" /></td>
                            <td class="plane" data-plane-id="${flight.plane.id}">${flight.plane.producer} ${flight.plane.type}</td>
                            <td class="departure_dest" data-departure-dest-city="${flight.destinationStart.city}" data-departure-dest-country="${flight.destinationStart.country}"><c:out value="${flight.destinationStart.city}"/> (<c:out value="${flight.destinationStart.country}"/>)</td>
                            <td class="arrival_dest" data-arrival-dest-city="${flight.destinationArrival.city}" data-arrival-dest-country="${flight.destinationArrival.country}"><c:out value="${flight.destinationArrival.city}"/> (<c:out value="${flight.destinationArrival.country}"/>)</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                            Tools
                                            <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a tabindex="-1" href="#" class="show_edit_flight_dialog" id="${flight.id}"><i class="icon-edit"></i> <f:message key="Flights.edit"/></a></li>
                                        <li class="divider"></li>
                                        <li>
                                            <s:link class="toolboxDelete" rel="${flight.id}" beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" event="ajaxDelete" tabindex="-1" href="#">
                                                <s:param name="flight.id" value="${flight.id}"/>
                                                <i class="icon-remove"></i> <f:message key="Flights.delete"/>
                                            </s:link>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </s:layout-component>
</s:layout-render>