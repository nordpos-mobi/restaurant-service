<%--
    Document   : product_order
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product View"
                       pageid="ProductView">

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>
        <sdynattr:link href="/CategoryProductList.action"
                       class="ui-btn ui-corner-all ui-icon-bars ui-btn-icon-left">
            <c:out value="${actionBean.product.productCategory.name}"/>
        </sdynattr:link>
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <c:out value="${actionBean.product.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="buttons_right">       
    </stripes:layout-component>

    <stripes:layout-component name="content">

        <div class="ui-body ui-body-a ui-corner-all">
            <div class="ui-grid-a ui-responsive">  
                <div class="ui-block-a">
                    <div class="ui-body ui-body-d">
                        <img src="${pageContext.servletContext.contextPath}/OrderProductImage.action?preview&product.id=${actionBean.product.id}"
                             alt="${actionBean.product.name}" />
                    </div>                    
                </div>                    
                <div class="ui-block-b">
                    <div class="ui-grid-a ui-responsive">
                        <div class="ui-block-a">
                            <div class="ui-body ui-body-d">
                                <fmt:message key="label.Product.name" />
                            </div>
                        </div>
                        <div class="ui-block-b">
                            <div class="ui-body ui-body-d">
                                <c:out value="${actionBean.product.name}"/>
                            </div>                    
                        </div>
                        <div class="ui-block-a">
                            <div class="ui-body ui-body-d">
                                <fmt:message key="label.Product.taxPriceSell" />
                            </div>                    
                        </div>                    
                        <div class="ui-block-b">
                            <div class="ui-body ui-body-d">
                                <fmt:formatNumber value="${actionBean.product.taxPriceSell}"
                                                  type="CURRENCY"
                                                  pattern="#0.00 ¤"                                                  
                                                  maxFractionDigits="2" 
                                                  minFractionDigits="2"/>
                            </div>                    
                        </div>
                    </div>
                    <div class="ui-grid-solo">
                        <div class="ui-block-a">
                            <sdynattr:form action="/OrderProduct.action">
                                <div>                
                                    <stripes:hidden name="product.code" value="${actionBean.product.code}"/>
                                    <c:if test="${not empty place}">
                                        <stripes:hidden name="place.id" value="${place.id}"/>
                                    </c:if>
                                </div>
                                <ul data-role="listview" data-inset="false">  
                                    <li>
                                        <h1><fmt:message key="label.ProductOrder"/></h1>
                                    </li>
                                    <c:if test="${empty place}">
                                        <li class="ui-field-contain">
                                            <stripes:label name="label.ProductOrder.place" for="productOrderPlace" class="select"/>
                                            <sdynattr:select name="place.id" id="productOrderPlace" data-mini="true">
                                                <c:forEach items="${actionBean.placeList}" var="place">
                                                    <stripes:option value="${place.id}">
                                                        <c:out value="${place.name}"/>
                                                    </stripes:option>
                                                </c:forEach>
                                            </sdynattr:select>
                                        </li>
                                    </c:if>
                                    <li class="ui-field-contain">
                                        <stripes:label name="label.ProductOrder.unit" for="productOrderUnit"/>
                                        <input name="orderUnit" id="productOrderUnit" value="1" min="1" max="<fmt:formatNumber value="${actionBean.orderUnitMax}"
                                                          type="NUMBER"
                                                          pattern="#0"                                                  
                                                          maxFractionDigits="0" 
                                                          minFractionDigits="0"/>" step="1" data-highlight="true" type="range">                                        
                                    </li>
                                    <li class="ui-body ui-body-b">
                                        <fieldset class="ui-grid-a">
                                            <div class="ui-block-a">
                                                <sdynattr:reset name="clear" data-theme="b"/>
                                            </div>
                                            <div class="ui-block-b">
                                                <sdynattr:submit name="add" data-theme="a"/>
                                            </div>
                                        </fieldset>
                                    </li>
                                </ul>
                            </sdynattr:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
