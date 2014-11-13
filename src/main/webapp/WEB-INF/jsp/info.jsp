<%--
    Document   : info
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Information"
                       pageid="information">

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>        
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <fmt:message key="label.information" />
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-collapsed="true">
            <h4><fmt:message key="label.servlet.environments" /></h4>
            <div class="ui-grid-a ui-responsive">
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.info.java.version" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.javaVersion}" />
                    </div>
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.info.os" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.operationSystem}" />
                    </div>
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.info.server" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.serverInfo}" />
                    </div>
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.info.language" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.language}" />
                    </div>
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.info.country" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-d">
                        <c:out value="${actionBean.country}" />
                    </div>
                </div>
            </div>
        </div>

        <div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-collapsed="false">
            <h4><fmt:message key="label.application.parameters" /></h4>
            <div class="ui-grid-a ui-responsive">
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.application.version" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.application.toString()}" />
                    </div>
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-c">
                        <fmt:message key="label.orderUnit.max" />
                    </div>
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-c">
                        <c:out value="${actionBean.orderUnitMax}" />
                    </div>
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
