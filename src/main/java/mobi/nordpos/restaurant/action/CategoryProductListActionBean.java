/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package mobi.nordpos.restaurant.action;

import java.sql.SQLException;
import java.util.List;
import mobi.nordpos.restaurant.ext.UUIDTypeConverter;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class CategoryProductListActionBean extends CategoryBaseActionBean {

    private static final String PRODUCT_LIST = "/WEB-INF/jsp/product_list.jsp";

    private List<Product> productList;

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(PRODUCT_LIST);
    }
           
    @ValidateNestedProperties({
        @Validate(field = "id",
                required = true,
                converter = UUIDTypeConverter.class)
    })     
    @Override
    public void setCategory(ProductCategory category) {
        super.setCategory(category);
    }
    
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @ValidationMethod
    public void validateCategoryIdIsAvalaible(ValidationErrors errors) {
        try {
            ProductCategory category = readProductCategory(getCategory().getId());
            if (category != null) {
                setCategory(category);
                setProductList(category.getProductList());
            } else {
                errors.add("category.id", new SimpleError(
                        getLocalizationKey("label.error.CatalogNotInclude")));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }    
}
