<%--
    Document   : place_view
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Place View"
                       pageid="PlaceView">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>                 
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <c:out value="${actionBean.place.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">       
    </stripes:layout-component>

    <stripes:layout-component name="content">

    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
