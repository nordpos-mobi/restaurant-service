<%--
    Document   : layout_main
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<stripes:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <title>${title} - NORD POS mobi</title>
            <link rel="stylesheet" href="<c:url value='/css/jquery.mobile-1.4.4.min.css'/>" />
            <link rel="stylesheet" href="<c:url value='/css/stripes.mobile.css'/>" />
            <link rel="icon" type="image/png" href="<c:url value='/icon.png' />" />
            <script src="<c:url value='/js/jquery-2.1.1.min.js'/>"></script>
            <script src="<c:url value='/js/jquery.mobile-1.4.4.min.js'/>"></script>
            <script src="<c:url value='/js/jquery.flot.min.js'/>" type="text/javascript" ></script>
            <script src="<c:url value='/js/jquery.flot.pie.min.js'/>" type="text/javascript" ></script>
        </head>
        <body>
            <div data-role="page" id="${pageid}">
                <div data-role="header" role="banner">                  
                    <h2>
                        <stripes:layout-component name="header.title"/>
                    </h2>
                    <div class="ui-btn-right" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <sdynattr:link href="/Information.action"
                                       class="ui-btn ui-corner-all ui-icon-info ui-btn-icon-notext"
                                       role="button">
                            <stripes:label name="label.info" />
                        </sdynattr:link>
                    </div>
                </div>
                <div data-role="content" style="padding: 15px" align="center">
                    <stripes:layout-component name="content"/>
                </div>
                <div data-role="footer" class="ui-bar">
                    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <a href="tel:+7-777-544-06-60" class="ui-btn ui-corner-all ui-icon-phone ui-btn-icon-notext">+7-777-544-06-60</a>
                    </div>

                    <h2>
                        <span>&copy;2012-2014 Nord Trading Network. <a href="http://www.apache.org/licenses/LICENSE-2.0.html">ALv2</a></span>
                    </h2>

                    <div class="ui-btn-right" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <a href="mailto:svininykh@gmail.com" class="ui-btn ui-corner-all ui-icon-mail ui-btn-icon-notext">svininykh@gmail.com</a>
                    </div>                    
                </div>
            </div>
        </body>
    </html>
</stripes:layout-definition>

