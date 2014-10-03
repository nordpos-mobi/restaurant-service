<%--
    Document   : category_edit
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product Category Edit"
                       pageid="ProductCategoryEdit">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Presentation.action"
                       class="ui-btn ui-corner-all ui-icon-home ui-btn-icon-notext">
            <stripes:label name="label.home" />
        </sdynattr:link>          
        <sdynattr:link href="/ProductCategory.action"
                       class="ui-btn ui-corner-all ui-icon-bars ui-btn-icon-left"
                       data-prefetch="true">
            <stripes:label name="label.categories" />
        </sdynattr:link>           
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <stripes:label name="label.ProductCategoryEdit" />
    </stripes:layout-component>

    <stripes:layout-component name="button.action">

        <a href="#deleteCategoryDialog" 
           data-rel="popup" 
           data-position-to="window" 
           data-transition="pop" 
           class="ui-btn ui-corner-all ui-icon-delete ui-btn-icon-left ui-btn-b ui-shadow">
            <stripes:label name="label.delete" />
        </a>
        <div data-role="popup" 
             id="deleteCategoryDialog" 
             data-overlay-theme="b" data-theme="b" 
             data-dismissible="false" style="max-width:400px;">
            <div data-role="header" data-theme="a">
                <h1><stripes:label name="label.dialog.ProductCategoryDelete" /></h1>
            </div>
            <div role="main" class="ui-content">
                <h3 class="ui-title">
                    <c:out value="${actionBean.category.name}"/>
                </h3>
                <p><stripes:label name="label.ask.ProductCategoryDelete" /></p>
                <stripes:form action="/ProductCategory.action?delete">
                    <sdynattr:submit name="yes" data-theme="a" data-icon="check"/>
                </stripes:form>
                <a href="#" 
                   class="ui-btn ui-corner-all ui-icon-forbidden ui-btn-icon-left ui-btn-b ui-btn-inline ui-shadow" 
                   data-rel="back" 
                   data-transition="flow">
                    <stripes:label name="no" />
                </a>                
            </div>
        </div>        

    </stripes:layout-component>


    <stripes:layout-component name="content">
        <stripes:errors />
        <stripes:form action="/ProductCategory.action?update">
            <div>
                <stripes:hidden name="category"/>
            </div>
            <ul data-role="listview" data-inset="true">                
                <li class="ui-field-contain">
                    <label for="categoryName"><stripes:label name="label.ProductCategory.name" /></label>
                    <input name="category.name" id="categoryName" type="text"
                           placeholder="${actionBean.getLocalizationKey("label.ProductCategory.name")}" 
                           value="${actionBean.category.name}"
                           data-clear-btn="true">
                </li>
                <li class="ui-field-contain">
                    <label for="categoryCode"><stripes:label name="label.ProductCategory.code" /></label>
                    <input name="category.code" id="categoryCode" type="text"
                           placeholder="${actionBean.getLocalizationKey("label.ProductCategory.code")}" 
                           value="${actionBean.category.code}"
                           data-clear-btn="true">
                </li>
                <li class="ui-body ui-body-b">
                    <fieldset class="ui-grid-a">
                        <div class="ui-block-a">
                            <sdynattr:submit name="update" data-theme="a"/>
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
