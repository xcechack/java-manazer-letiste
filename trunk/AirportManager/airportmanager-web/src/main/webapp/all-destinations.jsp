<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/template.jsp" pageTitle="Destinations" activeDestinations="class=&quot;active&quot;">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.DestinationsActionBean" var="destinationsABean"/>
        
        <div class="hero-unit">
            <h1><i class="icon-globe"></i><f:message key="Destinations.header"/></h1>
        </div>
        
        <sec:authorize access="hasRole('USER')">
            <!-- Formular pridavania -->
            <%@include file="new-destination-modal-form.jsp"%>
            <!-- Formular editovania -->
            <%@include file="edit-destination-modal-form.jsp"%>
            <!-- TLACITKO PRIDANIA LETU: -->
            <p>
                <a href="#new_destination_modal" role="button" class="btn" data-toggle="modal"><i class="icon-plus"></i> <f:message key="Destinations.addnew"/></a>
            </p>
        </sec:authorize>
            
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
                            <th><span><f:message key="Destinations.city"/></span></th>
                            <th><span><f:message key="Destinations.country"/></span></th>
                            <sec:authorize access="hasRole('USER')">
                                <th><f:message key="Global.tools"/></th>
                            </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${destinationsABean.destinations}" var="destination" >
                            <tr class="destination" data-internal-id="${destination.id}">
                                <td>${destination.id}</td>
                                <td class="city" data-dest-city="${destination.city}">${destination.city}</td>
                                <td class="country" data-dest-country="${destination.country}">${destination.country}</td>
                                <sec:authorize access="hasRole('USER')">
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                Tools
                                                <span class="caret"></span>
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="#" class="show_edit_destination_dialog" data-internal-id="${destination.id}"><i class="icon-edit"></i> <f:message key="Destinations.edit"/></a></li>
                                                <li class="divider"></li>
                                                <li>
                                                    <s:link class="toolboxDelete" rel="${destination.id}" beanclass="cz.muni.fi.pa165.airportmanager.DestinationsActionBean" event="ajaxDelete" tabindex="-1" href="#">
                                                        <s:param name="destination.id" value="${destination.id}"/>
                                                        <i class="icon-remove"></i> <f:message key="Destinations.delete"/>
                                                    </s:link>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                               </sec:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table>
        </div>
    </s:layout-component>
</s:layout-render>