<%--
    Document   : category_list
    Author     : Andrey Svininykh (svininykh@gmail.com)
    Copyright  : Nord Trading Network
    License    : Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
--%>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
                       title="Product Category"
                       pageid="ProductCategory">

    <stripes:layout-component name="button.return">
        <sdynattr:link href="/Presentation.action"
                       class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext">            
            <stripes:label name="label.home" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="header.title">
        <stripes:label name="label.ProductCategory"/>
    </stripes:layout-component>

    <stripes:layout-component name="button.action">
        <sdynattr:link href="/CategoryCreate.action"                       
                       class="ui-btn ui-shadow ui-corner-all ui-icon-action ui-btn-icon-left">
            <stripes:param name="category"/>
            <stripes:param name="category.code" value="${actionBean.categoryList.size() + 1}"/>
            <stripes:label name="label.create" />
        </sdynattr:link>          
    </stripes:layout-component>

    <stripes:layout-component name="content">
        <stripes:messages />
        <stripes:errors />
        <ul data-role="listview" data-filter="true"
            data-filter-placeholder="${actionBean.getLocalizationKey("label.ProductCategory.search")}"
            data-inset="true" data-split-icon="edit" data-split-theme="a">
            <c:forEach items="${actionBean.categoryList}" var="category">                
                <li>
                    <sdynattr:link href="/Product.action"
                                   event="list"
                                   data-transition="slide">
                        <stripes:param name="categoryId" value="${category.id}"/>
                        <c:out value="${category.name}"/>
                        <span class="ui-li-count"><c:out value="${category.productList.size()}"/></span>
                    </sdynattr:link>
                    <sdynattr:link href="/CategoryChange.action"
                                   data-transition="slide">
                        <stripes:param name="category"/>
                        <stripes:param name="categoryId" value="${category.id}"/>
                        <stripes:param name="category.code" value="${category.code}"/>
                        <stripes:label name="label.edit" />
                    </sdynattr:link>
                </li>
            </c:forEach>
        </ul>
    </stripes:layout-component>

    <stripes:layout-component name="footer">

    </stripes:layout-component>
</stripes:layout-render>
