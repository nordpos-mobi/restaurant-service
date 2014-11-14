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

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext">            
            <fmt:message key="label.home" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <fmt:message key="label.RestaurantFloor"/>
    </stripes:layout-component>

    <stripes:layout-component name="buttons_right">          
    </stripes:layout-component>

    <%-- Main content of the page implementation to the template for view --%>
    <stripes:layout-component name="content">
        <%-- Listview include search by the name of elements --%>
        <ul data-role="listview" data-inset="true" data-divider-theme="a" data-filter="true"
            data-filter-placeholder="<fmt:message key='label.FloorPlace.search' />">
            <%-- Create dynamic listview. --%>
            <c:forEach items="${actionBean.floorList}" var="floor">                
                <li data-role="list-divider">
                    <%-- Name of the list item --%>
                    <h2><c:out value="${floor.name}"/></h2>
                    <span class="ui-li-count"><c:out value="${floor.placeList.size()}" /></span>
                </li>
                <c:forEach items="${floor.placeList}" var="place">
                    <c:if test="${not empty place.ticket}">
                        <c:set var="has_ticket" scope="page" value="tag"/>
                    </c:if>
                    <c:if test="${empty place.ticket}">
                        <c:set var="has_ticket" scope="page" value="false"/>
                    </c:if>
                    <li data-icon="<c:out value='${has_ticket}'/>">
                        <sdynattr:link href="/OrderPlace.action"
                                       data-transition="slide">
                            <stripes:param name="place.id" value="${place.id}"/>      
                            <c:out value="${place.name}"/>
                            <p class="ui-li-aside">                                
                                <strong>
                                    <fmt:formatNumber value="${place.ticket.totalValue}"
                                                      type="CURRENCY"
                                                      pattern="#0.00 ¤"                                                  
                                                      maxFractionDigits="2" 
                                                      minFractionDigits="2"/>
                                </strong>
                            </p>
                        </sdynattr:link>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
