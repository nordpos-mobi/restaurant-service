<%--
    Document   : login
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network.
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Home"
                       pageid="home">

    <stripes:layout-component name="button.return">

    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        NORD POS mobi
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <div class="ui-body">            
            <a href="http://www.nordpos.mobi">
                <img src="<c:url value='/image/logo.png' />" alt="NORD POS mobi"/>
            </a>            
                <h2><stripes:label name="label.present" /></h2>
        </div>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
