<%--
    Document   : user_reg
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="User Registration"
                       pageid="UserRegistration">

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>                 
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <fmt:message key="label.UserRegistration" />
    </stripes:layout-component>

    <stripes:layout-component name="buttons_right">
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <stripes:form action="/UserRegistration.action">
            <div>
                <stripes:hidden name="user.role.id" value="3"/>
                <stripes:hidden name="user.visible" value="false"/>
            </div>
            <ul data-role="listview" data-inset="true">                
                <li class="ui-field-contain">
                    <stripes:label name="label.User.name" for="userName" />
                    <input name="user.name" id="userName" type="text"
                           placeholder="<fmt:message key='label.UserName.enter' />"
                           data-clear-btn="true">
                </li>
                <li class="ui-field-contain">
                    <stripes:label name="label.User.password" for="userPassword" />
                    <input name="user.password" id="userPassword" type="password"
                           placeholder="<fmt:message key='label.UserPassword.enter' />"
                           data-clear-btn="true">
                </li>
                <li class="ui-field-contain">
                    <stripes:label name="label.User.confirmPassword" for="userConfirmPassword" />
                    <input name="confirmPassword" id="userConfirmPassword" type="password"
                           placeholder="<fmt:message key='label.UserPassword.confirm' />"
                           data-clear-btn="true">
                </li>
                <li class="ui-body ui-body-b">
                    <fieldset class="ui-grid-a">
                        <div class="ui-block-a">
                            <sdynattr:reset name="clear" data-theme="b"/>
                        </div>
                        <div class="ui-block-b">
                            <sdynattr:submit name="accept" data-theme="a"/>
                        </div>
                    </fieldset>
                </li>
            </ul>        
        </stripes:form>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
