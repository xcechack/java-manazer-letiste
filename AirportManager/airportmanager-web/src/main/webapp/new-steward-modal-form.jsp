<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<div id="new_steward_modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <s:form beanclass="cz.muni.fi.pa165.airportmanager.StewardsActionBean" method="POST">  
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel"><f:message key="Stewards.addnew"/></h3>
        </div>
        <div class="modal-body">
                <p>
                    <label><f:message key="Stewards.name"/></label>
                    <input type="text" placeholder="John" name="new_name">
                    <label><f:message key="Stewards.surname"/></label>
                    <input type="text" placeholder="Doe" name="new_surname">
                    <label><f:message key="Stewards.birthnumber"/></label>
                    <input type="text" placeholder="870104/4433" name="new_birthnumber">
                    <label><f:message key="Stewards.sex"/></label>
                    <select name="new_sex">
                            <option value="${Sex.male}"><f:message key="Sex.male"/></option>
                            <option value="${Sex.female}"><f:message key="Sex.female"/></option>
                    </select>
                </p>
        </div>
        <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true"><f:message key="Global.close"/></button>
                <s:submit class="btn btn-primary" name="save"><f:message key="Stewards.addnew"/></s:submit>
        </div>
    </s:form>
</div>