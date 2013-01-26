<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/template.jsp" pageTitle="Planes" activePlanes="class=&quot;active&quot;">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.PlanesActionBean" var="planesABean"/>
        
        <sec:authorize access="hasRole('USER')">
            <!-- Formular pridavania lietadiel -->
            <%@include file="new-plane-modal-form.jsp"%>

            <%@include file="edit-plane-modal-form.jsp"%>
        </sec:authorize>
            
        <!-- NADPIS: -->
        <div class="hero-unit">
            <h1><i class="icon-plane"></i><f:message key="Planes.header"/></h1>
        </div>
        
        <sec:authorize access="hasRole('USER')">
            <!-- TLACITKO PRIDANIA LETU: -->
           <p>
               <a href="#new_plane_modal" role="button" class="btn" data-toggle="modal"><i class="icon-plus"></i> <f:message key="Planes.addnew"/></a>
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
                                <th><span><f:message key="Planes.producer"/></span></th>
                                <th><span><f:message key="Planes.type"/></span></th>
                                <th><span><f:message key="Planes.seats"/></span></th>
                                <th><span><f:message key="Planes.stewards"/></span></th>
                                <sec:authorize access="hasRole('USER')"> <th><f:message key="Global.tools"/></th> </sec:authorize>
                        </tr>
                </thead>
                <tbody>
                    <c:forEach items="${planesABean.planes}" var="plane" >
                        <tr class="plane" data-internal-id="${plane.id}">
                            <td>${plane.id}</td>
                            <td class="producer" data-producer="${plane.producer}">${plane.producer}</td>
                            <td class="type" data-type="${plane.type}">${plane.type}</td>
                            <td class="seats" data-seats="${plane.numberSeats}">${plane.numberSeats}</td>
                            <td class="stewardess" data-stewardess="${plane.maxStewardessNumber}">${plane.maxStewardessNumber}</td>
                            <sec:authorize access="hasRole('USER')">
                                <td>
                                    <div class="btn-group">
                                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                Tools
                                                <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a class="show_edit_plane_dialog" data-internal-id="${plane.id}" tabindex="-1" href="#"><i class="icon-edit"></i> <f:message key="Planes.edit"/></a></li>
                                            <li class="divider"></li>
                                            <li>
                                                <s:link class="toolboxDelete" rel="${plane.id}" beanclass="cz.muni.fi.pa165.airportmanager.PlanesActionBean" event="ajaxDelete" tabindex="-1" href="#">
                                                    <s:param name="plane.id" value="${plane.id}"/>
                                                    <i class="icon-remove"></i> <f:message key="Planes.delete"/>
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