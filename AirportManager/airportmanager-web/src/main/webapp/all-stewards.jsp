<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/template.jsp" pageTitle="Planes" activeStewards="class=&quot;active&quot;">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.StewardsActionBean" var="stewardsABean"/>
        
        <sec:authorize access="hasRole('USER')">
            <!-- Formular pridavania lietadiel -->
            <%@include file="new-steward-modal-form.jsp"%>

            <%@include file="edit-steward-modal-form.jsp"%>
        </sec:authorize>
            
        <!-- NADPIS: -->
        <div class="hero-unit">
                <h1><i class="icon-user"></i><f:message key="Stewards.header"/></h1>
            </div>
            
        <sec:authorize access="hasRole('USER')">
            <!-- TLACITKO PRIDANIA LETU: -->
           <p>
               <a href="#new_steward_modal" role="button" class="btn" data-toggle="modal"><i class="icon-plus"></i> <f:message key="Stewards.addnew"/></a>
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
                                <th><span><f:message key="Stewards.name"/></span></th>
                                <th><span><f:message key="Stewards.surname"/></span></th>
                                <th><span><f:message key="Stewards.birthnumber"/></span></th>
                                <th><span><f:message key="Stewards.sex"/></span></th>
                                <sec:authorize access="hasRole('USER')"> <th><f:message key="Global.tools"/></th> </sec:authorize>
                        </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stewardsABean.stewardess}" var="steward" >
                        <tr class="steward" data-internal-id="${steward.id}">
                            <td>${steward.id}</td>
                            <td class="name" data-name="${steward.name}">${steward.name}</td>
                            <td class="surname" data-surname="${steward.surname}">${steward.surname}</td>
                            <td class="birthnumber" data-birthnumber="${steward.birthNumber}">${steward.birthNumber}</td>
                            <td class="sex" data-sex="${steward.sex}"/><f:message key="Sex.${steward.sex}"/></td>
                            <sec:authorize access="hasRole('USER')">
                                <td>
                                    <div class="btn-group">
                                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                Tools
                                                <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a class="show_edit_steward_dialog" tabindex="-1" href="#" data-internal-id="${steward.id}"><i class="icon-edit"></i> <f:message key="Stewards.edit"/></a></li>
                                            <li class="divider"></li>
                                            <s:link class="toolboxDelete" rel="${steward.id}" beanclass="cz.muni.fi.pa165.airportmanager.StewardsActionBean" event="ajaxDelete" tabindex="-1" href="#">
                                                <s:param name="steward.id" value="${steward.id}"/>
                                                <i class="icon-remove"></i> <f:message key="Stewards.delete"/>
                                            </s:link>
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