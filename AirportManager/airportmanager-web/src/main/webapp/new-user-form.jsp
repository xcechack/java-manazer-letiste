<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<div>
    <s:form beanclass="cz.muni.fi.pa165.airportmanager.UsersActionBean" method="POST">  
        <div class="modal-body">
            <p>
                <label><f:message key="Users.name"/></label>
                <input type="text" placeholder="John" name="new_user_name" required>
                <label><f:message key="Users.password"/></label>
                <input type="password" name="new_user_password" required>
            </p>
        </div>
        <div class="modal-footer">
                <s:submit class="btn btn-primary" name="save"><f:message key="Users.addnew"/></s:submit>
        </div>
    </s:form>
</div>