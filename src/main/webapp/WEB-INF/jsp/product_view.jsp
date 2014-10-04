<%--
    Document   : category_create
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product View"
                       pageid="ProductView">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Presentation.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <stripes:label name="label.home" />
        </sdynattr:link>                  
        <sdynattr:link href="/CategoryProductList.action"
                       class="ui-btn ui-corner-all ui-icon-bars ui-btn-icon-left">
            <stripes:param name="category.id" value="${actionBean.product.productCategory.id}"/>
            <c:out value="${actionBean.product.productCategory.name}"/>
        </sdynattr:link>                   
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <c:out value="${actionBean.product.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <stripes:errors />
        <stripes:messages />
        <div data-role="collapsible" data-collapsed="false">
            <h4><stripes:label name="label.Product"/></h4>
            <div class="ui-grid-a ui-responsive">                
                <div class="ui-block-a">
                    <div class="ui-body ui-body-d"><stripes:label name="label.Product.code" /></div>                    
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-d"><c:out value="${actionBean.product.code}"/></div>                    
                </div>
                <div class="ui-block-a">
                    <div class="ui-body ui-body-d"><stripes:label name="label.Product.priceBuy" /></div>                    
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-d">
                        <fmt:formatNumber value="${actionBean.product.priceBuy}"
                                          type="CURRENCY"
                                          pattern="#0.00 ¤"                                                  
                                          maxFractionDigits="2" 
                                          minFractionDigits="2"/>
                    </div>                    
                </div>                
                <div class="ui-block-a">
                    <div class="ui-body ui-body-d"><stripes:label name="label.Product.priceSell" /></div>                    
                </div>
                <div class="ui-block-b">
                    <div class="ui-body ui-body-d">
                        <fmt:formatNumber value="${actionBean.product.priceSell}"
                                          type="CURRENCY"
                                          pattern="#0.00 ¤"                                                  
                                          maxFractionDigits="2" 
                                          minFractionDigits="2"/>
                    </div>                    
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
