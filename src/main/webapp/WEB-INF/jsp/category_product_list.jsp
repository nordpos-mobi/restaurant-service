<%--
    Document   : category_product_list
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product Category"
                       pageid="ProductCategory">

    <stripes:layout-component name="buttons_left">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext">            
            <fmt:message key="label.home" />
        </sdynattr:link>       
    </stripes:layout-component>

    <stripes:layout-component name="title">
        <fmt:message key="label.RestaurantMenu"/>
    </stripes:layout-component>

    <stripes:layout-component name="buttons_right">          
    </stripes:layout-component>

    <%-- Main content of the page implementation to the template for view --%>
    <stripes:layout-component name="content">
        <%-- Listview include search by the name of elements --%>
        <ul data-role="listview" data-inset="true" data-divider-theme="a" data-filter="true"
            data-filter-placeholder="<fmt:message key='label.Product.search' />">
            <%-- Create dynamic listview. --%>
            <c:forEach items="${actionBean.categoryList}" var="category">                
                <li data-role="list-divider">
                    <%-- Name of the list item --%>
                    <h2><c:out value="${category.name}"/></h2>
                </li>     
                <c:forEach items="${category.productList}" var="product">
                    <li>
                        <sdynattr:link href="/OrderProduct.action"
                                       data-transition="slide">
                            <stripes:param name="product.code" value="${product.code}"/>
                            <img src="${pageContext.servletContext.contextPath}/OrderProductImage.action?preview&product.id=${product.id}&thumbnailSize=80"
                                 alt="${product.name}"/>
                            <c:out value="${product.name}"/>
                            <p><c:out value="${product.code}"/></p>
                            <span  class="ui-li-count">
                                <fmt:formatNumber value="${product.taxPriceSell}"
                                                  type="CURRENCY"
                                                  pattern="#0.00 ¤"                                                  
                                                  maxFractionDigits="2" 
                                                  minFractionDigits="2"/>
                            </span>
                        </sdynattr:link>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
