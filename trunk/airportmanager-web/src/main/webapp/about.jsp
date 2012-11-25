<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/template.jsp" pageTitle="About" activeAbout="class=&quot;active&quot;">
    <s:layout-component name="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
            <h1><i class="icon-info-sign"></i><f:message key="About.header"/></h1>
            <p><f:message key="About.headling"/></p>
        </div>

        <!-- Example row of columns -->
        <div class="row">
           <div class="page-header">
                                <h1><i class="icon-plane"></i>Airportmanager</h1>
                            </div>
           <p><f:message key="About.info"/></p>
        </div>
        <div class="row">
           <div class="page-header">
                                <h1><span class="show_tooltip" title="Sorted alphabetically"><f:message key="About.authors"/></span></h1>
                       </div>
                       <div class="well">
                   <h3>Marek Hornak</h3>
                   <p></p>
           </div>

           <div class="well">
                    <h3>Jaroslav Nespesny</h3>
                    <p></p>
           </div>
           <div class="well">
                    <h3>Lukas Neudert</h3>
                    <p></p>
           </div>

           <div class="well">
                    <h3>Vasek Janosek</h3>
                    <p></p>
           </div>
        </div>
        <hr>
    </s:layout-component>
</s:layout-render>