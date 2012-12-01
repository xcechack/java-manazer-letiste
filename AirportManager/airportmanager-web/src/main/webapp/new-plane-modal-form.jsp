<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<div id="new_plane_modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <s:form beanclass="cz.muni.fi.pa165.airportmanager.PlanesActionBean" method="POST">  
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel"><f:message key="Planes.addnew"/></h3>
        </div>
        <div class="modal-body">
                <p>
                        <label><f:message key="Planes.producer"/></label>
                        <input type="text" placeholder="Airbus" name="new_plane_producer" required>
                        <label><f:message key="Planes.type"/></label>
                        <input type="text" placeholder="A319" name="new_plane_type" required>
                        <label><f:message key="Planes.seats"/></label>
                        <input type="text" placeholder="210" name="new_plane_seats">
                        <label><f:message key="Planes.stewards"/></label>
                        <input type="text" placeholder="6" name="new_plane_stewards">
                </p>
        </div>
        <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true"><f:message key="Global.close"/></button>
                <s:submit class="btn btn-primary" name="save"><f:message key="Planes.addnew"/></s:submit>
        </div>
    </s:form>
</div>