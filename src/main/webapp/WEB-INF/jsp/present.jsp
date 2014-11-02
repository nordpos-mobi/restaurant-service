<%--
    Document   : present
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Welcome"
                       pageid="Welcome">

    <stripes:layout-component name="header.title">
        <fmt:message key="label.welcome" />
    </stripes:layout-component>

    <stripes:layout-component name="button.action">
        <sdynattr:link href="/Welcome.action"
                       event="info"
                       class="ui-btn ui-corner-all ui-icon-info ui-btn-icon-left"
                       role="button">
            <fmt:message key="label.info" />
        </sdynattr:link>
    </stripes:layout-component> 

    <stripes:layout-component name="content">
        <div class="ui-body">            
            <a href="http://www.nordpos.mobi">
                <img src="<c:url value='/image/logo.png' />" alt="NORD POS mobi"/>
            </a>            
            <p><fmt:message key="label.present" /></p>
            <sdynattr:link href="/CategoryList.action"
                           class="ui-btn ui-shadow ui-corner-all"
                           role="button">
                <fmt:message key="label.Catalog" />
            </sdynattr:link>            
        </div>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
