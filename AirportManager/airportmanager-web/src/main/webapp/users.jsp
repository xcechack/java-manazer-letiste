<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/template.jsp" pageTitle="Users" activeHome="" activeFlights="" activeDestinations="" activeStewards="" activePlanes="" activeAbout="">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.UsersActionBean" var="usersABean"/>
        
        <!-- NADPIS: -->
        <div class="hero-unit">
            <h1><i class="icon-user"></i><f:message key="Users.header"/></h1>
        </div>
         <!-- Formular pridania uzivatela: -->
        <p>
            <%@include file="new-user-form.jsp"%>
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
                        <th><span class="show_tooltip"><f:message key="Users.name"/> <i class="icon-arrow-down"></i></span></th>
                        <th><span class="show_tooltip"> <f:message key="Users.password"/>(hash)</span></th>
                        <th><f:message key="Global.tools"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usersABean.users}" var="user" >
                        <tr class="user" data-internal-id="${user.id}">
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                            <f:message key="Global.tools"/>
                                            <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <s:link class="toolboxDelete" rel="${user.id}" beanclass="cz.muni.fi.pa165.airportmanager.UsersActionBean" event="ajaxDelete" tabindex="-1" href="#">
                                                <s:param name="user.id" value="${user.id}"/>
                                                <i class="icon-remove"></i> <f:message key="Users.delete"/>
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