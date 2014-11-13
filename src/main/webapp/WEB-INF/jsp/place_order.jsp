<%--
    Document   : place_order
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Place Order"
                       pageid="PlaceOrder">

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>
        <sdynattr:link href="/FloorList.action"
                       class="ui-btn ui-corner-all ui-icon-grid ui-btn-icon-left">
            <fmt:message key="label.places" />
        </sdynattr:link>
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <c:out value="${actionBean.place.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="buttons_right">
        <c:if test="${not empty actionBean.place.ticket}">
            <a href="#delete_place" 
               data-rel="popup" 
               data-position-to="window" 
               data-transition="pop" 
               class="ui-btn ui-corner-all ui-icon-delete ui-btn-icon-left ui-btn-b ui-shadow">
                <fmt:message key="label.delete" />
            </a>
            <div data-role="popup" 
                 id="delete_place" 
                 data-overlay-theme="b" data-theme="b" 
                 data-dismissible="false" style="max-width:400px;">
                <div data-role="header" data-theme="a">
                    <h1><fmt:message key="label.dialog.delete" /></h1>
                </div>
                <div role="main" class="ui-content">
                    <h3 class="ui-title">
                        <c:out value="${actionBean.place.name}"/>
                    </h3>
                    <p><fmt:message key="label.ask.delete" /></p>
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
                            <stripes:form action="/OrderPlace.action?delete">
                                <div>
                                    <stripes:hidden name="place.id" value="${actionBean.place.id}"/>
                                </div>
                                <sdynattr:submit name="yes" data-theme="a" data-icon="check"/>                    
                            </stripes:form>
                        </div>
                    </fieldset>
                </div>
            </div>
        </c:if>
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
                    <th data-priority="persist" style="width: 50%"><fmt:message key="label.line.name" /></th>                    
                    <th data-priority="persist" style="width: 10%"><fmt:message key="label.line.unit" /></th>
                    <th data-priority="3" style="width: 15%"><fmt:message key="label.line.value" /></th>
                    <th data-priority="2" style="width: 15%"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.place.ticket.content.lines}" var="line">
                    <tr>
                        <th style="text-align: center;"><c:out value="${line.getM_iLine() + 1}" /></th>
                        <td><c:out value="${line.attributes.getProperty('product.name')}" /></td>
                        <td style="text-align: center;"><fmt:formatNumber value="${line.multiply}"
                                          type="NUMBER"                                   
                                          maxFractionDigits="3"/></td>
                        <td style="text-align: right;"><fmt:formatNumber value="${line.value}"
                                          type="CURRENCY"
                                          pattern="#0.00 ¤"                                                  
                                          maxFractionDigits="2" 
                                          minFractionDigits="2"/></td>
                        <td style="text-align: center;">
                            <a href="#remove_line_<c:out value='${line.getM_iLine() + 1}' />"
                               data-rel="popup" 
                               data-position-to="window" 
                               data-transition="pop">
                                <fmt:message key="label.line.remove" />
                            </a>
                            <div data-role="popup" 
                                 id="remove_line_<c:out value='${line.getM_iLine() + 1}' />" 
                                 data-overlay-theme="b" data-theme="b" 
                                 data-dismissible="false" style="max-width:400px;">
                                <div data-role="header" data-theme="a">
                                    <h1><fmt:message key="label.line.remove" /></h1>
                                </div>
                                <div role="main" class="ui-content">
                                    <h3 class="ui-title">
                                        <c:out value="${line.attributes.getProperty('product.name')}" />
                                    </h3>
                                    <p><fmt:message key="label.ask.remove" /></p>
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
                                            <stripes:form action="/OrderPlace.action?remove">
                                                <div>
                                                    <stripes:hidden name="place.id" value="${actionBean.place.id}"/>
                                                    <stripes:hidden name="removeLineNumber" value="${line.getM_iLine()}"/>
                                                </div>
                                                <sdynattr:submit name="yes" data-theme="a" data-icon="check"/>                    
                                            </stripes:form>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>        


                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr class="ui-bar-a">
                    <th><fmt:message key="label.line.total" /></th>
                    <td/>
                    <td style="text-align: center;"><fmt:formatNumber value="${actionBean.place.ticket.totalUnit}"
                                      type="NUMBER"                                   
                                      maxFractionDigits="3"/>
                    </td>
                    <td style="text-align: right;"><fmt:formatNumber value="${actionBean.place.ticket.totalValue}"
                                      type="CURRENCY"
                                      pattern="#0.00 ¤"                                                  
                                      maxFractionDigits="2" 
                                      minFractionDigits="2"/>
                    </td>
                    <td/>
                </tr>
            </tfoot>
        </table>

        <div class="ui-body">
            <sdynattr:link href="/OrderPlace.action"
                           event="add"
                           class="ui-btn ui-shadow ui-corner-all"
                           role="button">
                <stripes:param name="place.id" value="${actionBean.place.id}"/>
                <fmt:message key="label.line.add" />
            </sdynattr:link>  
        </div>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
