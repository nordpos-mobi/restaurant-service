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
import mobi.nordpos.restaurant.ext.Public;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@Public
public class CategoryProductListActionBean extends CategoryProductBaseActionBean {

    private static final String CATEGORY_PRODUCT_LIST = "/WEB-INF/jsp/category_product_list.jsp";

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(CATEGORY_PRODUCT_LIST);
    }

    @ValidationMethod
    public void validateCategoryListIsAvalaible(ValidationErrors errors) {
        try {
            List<ProductCategory> categories = readCategoryList();
            for (int i = 0; i < categories.size(); i++) {
                ProductCategory category = categories.get(i);
                List<Product> products = category.getProductList();
                for (int j = 0; j < products.size(); j++) {
                    Product product = products.get(j);
                    product.setTax(readTax(product.getTaxCategory().getId()));
                    products.set(j, product);
                }
                category.setProductList(products);
                categories.set(i, category);
            }
            setCategoryList(categories);
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

}
