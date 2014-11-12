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
            <link rel="stylesheet" href="<c:url value='/css/jquery.mobile-1.4.5.min.css'/>" />
            <link rel="stylesheet" href="<c:url value='/css/jquery.mobile.icons-1.4.5.min.css'/>" />
            <link rel="stylesheet" href="<c:url value='/css/icon-pack-custom.css'/>" />
            <link rel="icon" type="image/png" href="<c:url value='/icon.png' />" />
            <script src="<c:url value='/js/jquery-2.1.1.min.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/js/jquery.mobile-1.4.5.min.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/js/jquery.flot.min.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/js/jquery.flot.pie.min.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/js/jquery-ean13.min.js'/>" type="text/javascript"></script>
        </head>
        <body>
            <div data-role="page" id="${pageid}">
                <div data-role="header" role="banner">
                    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <stripes:layout-component name="button.return"/>
                        <c:if test="${not empty place}">
                            <sdynattr:link href="/OrderPlace.action"
                                           class="ui-btn ui-corner-all ui-icon-bullets ui-btn-icon-left">
                                <stripes:param name="place.id" value="${place.id}"/>
                                <c:out value="${place.name}" />
                            </sdynattr:link>
                        </c:if>
                    </div>
                    <h2>
                        <stripes:layout-component name="header.title"/>
                    </h2>
                    <div class="ui-btn-right" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <stripes:layout-component name="button.action"/>
                        <c:if test="${not empty user}">
                            <sdynattr:link href="/UserView.action"
                                           class="ui-btn ui-corner-all ui-btn-icon-left ui-icon-user">
                                <span>
                                    <c:out value="${user.name}"/>
                                </span>
                            </sdynattr:link>
                        </c:if>
                        <fmt:message var="otherLocale" key="layout.otherLocale"/>
                        <sdynattr:link href="${actionBean.lastUrl}"
                                       class="ui-btn ui-corner-all"
                                       role="button">
                            <stripes:param name="locale" value="${otherLocale}"/>
                            <fmt:message key="layout.currentLanguage"/>
                        </sdynattr:link>
                    </div>
                </div>
                <div data-role="content" style="padding: 15px" align="center">
                    <stripes:errors />
                    <stripes:messages />
                    <stripes:layout-component name="content"/>
                    <div class="ui-body ui-body-d">
                        <a href="${actionBean.application.projectURL}">
                            <img src="<c:url value='/image/poweredby-${actionBean.application.id}.png' />" alt="${actionBean.application.toString()}"/>
                        </a>
                    </div>
                </div>
                <div data-role="footer" class="ui-bar">
                    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal" data-mini="true">                        
                        <a href="mailto:<fmt:message key='mailto.address' />" class="ui-btn ui-corner-all ui-icon-mail ui-btn-icon-notext"><fmt:message key='mailto.address' /></a>
                        <a href="tel:<fmt:message key='tel.number' />" class="ui-btn ui-corner-all ui-icon-phone ui-btn-icon-notext"><fmt:message key='tel.number' /></a>
                    </div>

                    <h2>
                        <span><fmt:message key="label.Copyright" /></span>
                    </h2>

                    <div class="ui-btn-right" data-role="controlgroup" data-type="horizontal" data-mini="true">
                        <a href="https://github.com/nordpos-mobi" data-role="button" data-icon="github" data-iconpos="notext">nordpos-mobi</a>
                    </div>                    
                </div>
            </div>
        </body>
    </html>
</stripes:layout-definition>

