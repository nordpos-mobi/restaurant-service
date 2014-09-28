<%--
    Document   : category_change
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product Category Change"
                       pageid="ProductCategoryChange">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Presentation.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <stripes:label name="label.home" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <stripes:label name="label.ProductCategoryChange"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <stripes:errors />
        <stripes:form action="/ProductCategory.action?add">
            <ul data-role="listview" data-inset="true">                
                <li class="ui-field-contain">
                    <label for="name"><stripes:label name="label.ProductCategory.name" /></label>
                    <input name="productCategory.name" id="name" placeholder="${actionBean.getLocalizationKey("label.ProductCategory.name")}" value="" type="text">
                </li>
                <li class="ui-field-contain">
                    <label for="code"><stripes:label name="label.ProductCategory.code" /></label>
                    <input name="productCategory.code" id="code" placeholder="${actionBean.getLocalizationKey("label.ProductCategory.code")}" value="" type="text">
                </li>
                <li class="ui-body ui-body-b">
                    <fieldset class="ui-grid-a">
                        <div class="ui-block-a">
                            <sdynattr:submit name="add" data-theme="a"/>
                        </div>
                        <div class="ui-block-b">
                            <sdynattr:reset name="clear" data-theme="b"/>
                        </div>
                    </fieldset>
                </li>
            </ul>
        </stripes:form>


    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
