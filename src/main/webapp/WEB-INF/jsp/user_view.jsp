<%--
    Document   : user_view
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="User"
                       pageid="User">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>        
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <fmt:message key="label.UserLogIn" />
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <a href="#popupDialog" 
           data-rel="popup" 
           data-position-to="window" 
           data-transition="pop" 
           class="ui-btn-center ui-btn ui-btn-b ui-shadow ui-corner-all">
            <fmt:message key="label.UserLogOut" />
        </a>
        <div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
            <div data-role="header" data-theme="a">
                <h1><fmt:message key="label.dialog.UserLogOut" /></h1>
            </div>
            <div role="main" class="ui-content">
                <h3 class="ui-title"><fmt:message key="label.ask.UserLogOut" /></h3>
                <fieldset class="ui-grid-a">
                    <div class="ui-block-a">
                        <a href="#" 
                           class="ui-btn ui-corner-all ui-icon-forbidden ui-btn-icon-left ui-btn-b ui-shadow" 
                           data-rel="back" 
                           data-transition="flow">
                            <fmt:message key="no" />
                        </a>
                    </div>
                    <div class="ui-block-b">
                        <sdynattr:link href="/UserAuthorization.action"
                                       event="logout"
                                       class="ui-btn ui-corner-all ui-icon-check ui-btn-icon-left ui-btn-a ui-shadow">
                            <fmt:message key="yes" />
                        </sdynattr:link>
                    </div>
                </fieldset>
            </div>
        </div>                
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
