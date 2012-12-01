<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<div id="edit_destination_modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <s:form beanclass="cz.muni.fi.pa165.airportmanager.DestinationsActionBean" method="POST">  
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel"><f:message key="Destinations.edit"/></h3>
        </div>
        <div class="modal-body">
            <p>
                <label><f:message key="Destinations.city"/></label>
                <input type="text" placeholder="Prague" name="edit_dest_city" required>
                <label><f:message key="Destinations.country"/></label>
                <input type="text" placeholder="Czech republic" name="edit_dest_country" required>
            </p>
        </div>
        <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true"><f:message key="Global.close"/></button>
                <input type="hidden" name="destination_id" value="null"/>
                <s:submit class="btn btn-primary" name="edit"><f:message key="Destinations.edit"/></s:submit>
        </div>
    </s:form>
</div>