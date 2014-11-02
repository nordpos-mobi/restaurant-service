<%--
    Document   : category_list
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Floors"
                       pageid="Floors">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext">            
            <fmt:message key="label.home" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <fmt:message key="label.Floors"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">          
    </stripes:layout-component>

    <%-- Main content of the page implementation to the template for view --%>
    <stripes:layout-component name="content">
        <%-- Listview include search by the name of elements --%>
        <ul data-role="listview" data-inset="true" data-divider-theme="a" data-filter="true"
            data-filter-placeholder="<fmt:message key='label.ProductCategory.search' />">
            <%-- Create dynamic listview. --%>
            <c:forEach items="${actionBean.floorList}" var="floor">                
                <li data-role="list-divider">
                    <%-- Name of the list item --%>
                    <h2><c:out value="${floor.name}"/></h2>                    
                </li>
                <c:forEach items="${floor.placeList}" var="place">
                    <li data-icon="false">
                        <a href="#"><c:out value="${place.name}"/></a>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
