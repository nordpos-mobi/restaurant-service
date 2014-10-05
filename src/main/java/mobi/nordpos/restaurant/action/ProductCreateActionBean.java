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
import mobi.nordpos.restaurant.ext.UUIDTypeConverter;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductCreateActionBean extends ProductBaseActionBean {

    private static final String PRODUCT_CREATE = "/WEB-INF/jsp/product_create.jsp";

    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(PRODUCT_CREATE);
    }

    public Resolution add() {
        Product product = getProduct();
        try {
            getContext().getMessages().add(
                    new SimpleMessage(getLocalizationKey("label.message.Product.added"),
                            createProduct(product).getName(), product.getProductCategory().getName())
            );
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError("{2} {3}", ex.getErrorCode(), ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(CategoryProductListActionBean.class)
                .addParameter("category.id", product.getProductCategory().getId());
    }

    @ValidateNestedProperties({        
        @Validate(on = {"add"},
                field = "name",
                required = true,
                trim = true,
                maxlength = 255),
        @Validate(on = {"add"},
                field = "code",
                required = true,
                trim = true,
                maxlength = 12),
        @Validate(on = {"add"},
                field = "priceSell",
                required = true),
        @Validate(on = {"add"},
                field = "priceBuy",
                required = true),
        @Validate(field = "productCategory.id",
                required = true,
                converter = UUIDTypeConverter.class)
    })
    @Override
    public void setProduct(Product product) {
        super.setProduct(product);
    }

    @ValidationMethod
    public void validateProductCategoryIdIsAvalaible(ValidationErrors errors) {
        try {
            ProductCategory category = readProductCategory(getProduct().getProductCategory().getId());
            if (category != null) {
                getProduct().setProductCategory(category);
            } else {
                errors.add("product.category.id", new SimpleError(
                        getLocalizationKey("label.error.CatalogNotInclude")));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

}
