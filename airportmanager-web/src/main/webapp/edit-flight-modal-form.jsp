<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.PlanesActionBean" var="planesABean"/>
<s:useActionBean beanclass="cz.muni.fi.pa165.airportmanager.DestinationsActionBean" var="destinationsABean"/>
    
<div id="edit_flight_modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <s:form beanclass="cz.muni.fi.pa165.airportmanager.FlightsActionBean" method="POST">   
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel"><f:message key="Flights.edit"/> <span id="edit_id_span"></span></h3>
        </div>
        <div class="modal-body">
                <p>
                        <label><f:message key="Flights.identifier"/></label>
                        <input type="text" placeholder="XY001" name="edit_flight_identifier" required/>
                        <label><f:message key="Flights.departure"/></label>
                        <select class="select_day" name="edit_dep_day">
                                <option value="01">1</option>
                                <option value="02">2</option>
                                <option value="03">3</option>
                                <option value="04">4</option>
                                <option value="05">5</option>
                                <option value="06">6</option>
                                <option value="07">7</option>
                                <option value="08">8</option>
                                <option value="09">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                        </select>
                        <select class="select_month" name="edit_dep_month">
                                <option value="01"><f:message key="Flights.jan"/></option>
                                <option value="02"><f:message key="Flights.feb"/></option>
                                <option value="03"><f:message key="Flights.mar"/></option>
                                <option value="04"><f:message key="Flights.apr"/></option>
                                <option value="05"><f:message key="Flights.may"/></option>
                                <option value="06"><f:message key="Flights.jun"/></option>
                                <option value="07"><f:message key="Flights.jul"/></option>
                                <option value="08"><f:message key="Flights.aug"/></option>
                                <option value="09"><f:message key="Flights.sep"/></option>
                                <option value="10"><f:message key="Flights.oct"/></option>
                                <option value="11"><f:message key="Flights.nov"/></option>
                                <option value="12"><f:message key="Flights.dec"/></option>
                        </select>
                        <select class="select_year" name="edit_dep_year">
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                        </select>,
                        <select class="select_hour" name="edit_dep_hour">
                                <option value="00">00</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                        </select>:
                        <select class="select_minute" name="edit_dep_minute">
                                <option value="00">00</option>
                                <option value="05">05</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                                <option value="30">30</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="45">45</option>
                                <option value="50">50</option>
                                <option value="55">55</option>
                        </select>
                        <label><f:message key="Flights.arrival"/></label>
                        <select class="select_day" name="edit_arr_day">
                                <option value="01">1</option>
                                <option value="02">2</option>
                                <option value="03">3</option>
                                <option value="04">4</option>
                                <option value="05">5</option>
                                <option value="06">6</option>
                                <option value="07">7</option>
                                <option value="08">8</option>
                                <option value="09">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                        </select>
                        <select class="select_month" name="edit_arr_month">
                                <option value="01"><f:message key="Flights.jan"/></option>
                                <option value="02"><f:message key="Flights.feb"/></option>
                                <option value="03"><f:message key="Flights.mar"/></option>
                                <option value="04"><f:message key="Flights.apr"/></option>
                                <option value="05"><f:message key="Flights.may"/></option>
                                <option value="06"><f:message key="Flights.jun"/></option>
                                <option value="07"><f:message key="Flights.jul"/></option>
                                <option value="08"><f:message key="Flights.aug"/></option>
                                <option value="09"><f:message key="Flights.sep"/></option>
                                <option value="10"><f:message key="Flights.oct"/></option>
                                <option value="11"><f:message key="Flights.nov"/></option>
                                <option value="12"><f:message key="Flights.dec"/></option>
                        </select>
                        <select class="select_year" name="edit_arr_year">
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                        </select>,
                        <select class="select_hour" name="edit_arr_hour">
                                <option value="00">00</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                        </select>:
                        <select class="select_minute" name="edit_arr_minute">
                                <option value="00">00</option>
                                <option value="05">05</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                                <option value="30">30</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="45">45</option>
                                <option value="50">50</option>
                                <option value="55">55</option>
                        </select>
                        <label><f:message key="Flights.plane"/></label>
                        <select name="edit_plane">
                            <c:forEach items="${planesABean.planes}" var="plane" >
                                <option value="${plane.id}">${plane.producer}, ${plane.type}</option>
                            </c:forEach>                             
                        </select>
                        <label><f:message key="Flights.departure_dest"/></label>
                        <input type="text" title="" class="typeahead" name="edit_dep_city" placeholder="Moscow" data-source="[<c:forEach items="${destinationsABean.destinations}" var="dest">&quot;${dest.city}&quot;,</c:forEach>&quot;&amp;&quot;]" data-items="4" data-provide="typeahead"/>
                        <input type="text" title="" class="typeahead" name="edit_dep_country" data-source="[<c:forEach items="${destinationsABean.destinations}" var="dest">&quot;${dest.country}&quot;,</c:forEach>&quot;&amp;&quot;]" placeholder="Russia" data-items="4" data-provide="typeahead"/>
                        <label><f:message key="Flights.arrival_dest"/></label>
                        <input type="text" title="" class="typeahead" name="edit_arr_city" placeholder="Moscow" data-source="[<c:forEach items="${destinationsABean.destinations}" var="dest">&quot;${dest.city}&quot;,</c:forEach>&quot;&amp;&quot;]" data-items="4" data-provide="typeahead"/>
                        <input type="text" title="" class="typeahead" name="edit_arr_country" data-source="[<c:forEach items="${destinationsABean.destinations}" var="dest">&quot;${dest.country}&quot;,</c:forEach>&quot;&amp;&quot;]" placeholder="Russia" data-items="4" data-provide="typeahead"/>
                </p>
        </div>
        <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true"><f:message key="Global.close"/></button>
                <input type="hidden" name="flight_id" value="null"/>
                <s:submit class="btn btn-primary" name="edit"><f:message key="Flights.save_flight"/>t</s:submit>
        </div>
    </s:form>
</div>