<%--
    Document   : product_list
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product Category View"
                       pageid="ProductCategoryView">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Presentation.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext">            
            <stripes:label name="label.home" />
        </sdynattr:link>    
        <sdynattr:link href="/CategoryList.action"
                       class="ui-btn ui-corner-all ui-icon-bars ui-btn-icon-left"
                       data-prefetch="true">
            <stripes:label name="label.categories" />
        </sdynattr:link>         
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <c:out value="${actionBean.category.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">
        <sdynattr:link href="/ProductCreate.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-action ui-btn-icon-left">            
            <stripes:param name="product.productCategory.id" value="${actionBean.category.id}"/>
            <stripes:label name="label.create" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <stripes:errors/>
        <stripes:messages/>
        <ul data-role="listview" 
            data-filter="true" 
            data-filter-placeholder="${actionBean.getLocalizationKey("label.Product.search")}"
            data-inset="true">
            <c:forEach items="${actionBean.productList}" var="product">                
                <li>
                    <sdynattr:link href="/ProductView.action"
                                   data-transition="slide">
                        <stripes:param name="product.code" value="${product.code}"/>
                        <c:out value="${product.name}"/>
                        <p class="ui-li-aside">
                            <strong>
                                <fmt:formatNumber value="${product.priceSell}"
                                                  type="CURRENCY"
                                                  pattern="#0.00 ¤"                                                  
                                                  maxFractionDigits="2" 
                                                  minFractionDigits="2"/>
                            </strong>
                        </p>
                    </sdynattr:link>
                </li>
            </c:forEach>
        </ul>        
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
