<%--
    Document   : product_view
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product View"
                       pageid="ProductView">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Welcome.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <fmt:message key="label.home" />
        </sdynattr:link>
        <sdynattr:link href="/CategoryProductList.action"
                       class="ui-btn ui-corner-all ui-icon-bars ui-btn-icon-left">
            <c:out value="${actionBean.product.productCategory.name}"/>
        </sdynattr:link>                   
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <c:out value="${actionBean.product.name}"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">       
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <div class="ui-body ui-body-a ui-corner-all">
            <div class="ui-grid-a ui-responsive">  
                <div class="ui-block-a">
                    <div class="ui-body ui-body-d">
                        <img src="${pageContext.servletContext.contextPath}/ProductImage.action?preview&product.id=${actionBean.product.id}"
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
                                <fmt:message key="label.Product.priceSell" />
                            </div>                    
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
                        <div class="ui-block-a">
                            <div class="ui-body ui-body-d">
                                <fmt:message key="label.Product.taxCategory" />
                            </div>                    
                        </div>                    
                        <div class="ui-block-b">
                            <div class="ui-body ui-body-d">
                                <c:out value="${actionBean.product.taxCategory.name}"/>
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
                        <div class="ui-block">
                            <div class="ui-body ui-body-d">
                                <canvas id="ean" width="256" height="128"></canvas>  
                                <script type="text/javascript">$("#ean").EAN13("${actionBean.product.code}");</script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
