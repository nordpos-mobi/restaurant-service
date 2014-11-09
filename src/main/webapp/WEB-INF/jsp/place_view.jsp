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
        <table data-role="table" 
               id="ticket-table" 
               data-mode="columntoggle" 
               class="ui-body-d ui-shadow table-stripe ui-responsive"
               data-column-btn-theme="b" 
               data-column-btn-text="..." 
               data-column-popup-theme="a"
               cellspacing="0" cellpadding="0">
            <thead>
                <tr class="ui-bar-b">
                    <th data-priority="persist" style="width: 10%"><fmt:message key="label.line.number" /></th>
                    <th data-priority="persist" style="width: 35%"><fmt:message key="label.line.name" /></th>                    
                    <th data-priority="persist" style="width: 5%"><fmt:message key="label.line.unit" /></th>
                    <th data-priority="3" style="width: 10%"><fmt:message key="label.line.value" /></th>
                    <th data-priority="2" style="width: 10%"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.place.ticket.content.lines}" var="line">
                    <tr>
                        <th style="text-align: center;">${line.getM_iLine() + 1}</th>
                        <td>${line.attributes.getProperty("product.name")}</td>                        
                        <td style="text-align: center;">${line.multiply}</td>
                        <td style="text-align: right;">${line.value}</td>
                        <td style="text-align: center;">
                            <a href="#" ><fmt:message key="label.line.remove" /></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr class="ui-bar-a">
                    <th><fmt:message key="label.line.total" /></th>
                    <td/>
                    <td/>
                    <td/>
                    <td/>
                </tr>
            </tfoot>
        </table>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
