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
package mobi.nordpos.catalog.action;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import mobi.nordpos.catalog.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.catalog.dao.ormlite.ProductPersist;
import mobi.nordpos.catalog.model.Product;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductActionBean extends BaseActionBean {

    private static final String PRODUCT_LIST = "/WEB-INF/jsp/product_list.jsp";
    private static final String PRODUCT_CREATE = "/WEB-INF/jsp/product_create.jsp";
//    private static final String PRODUCT_EDIT = "/WEB-INF/jsp/product_edit.jsp";

    private Product product = new Product();
    private ProductCategory category = new ProductCategory();
    private String categoryId;

    @DefaultHandler
    public Resolution list() throws SQLException {
        category = getProductCategory();
        return new ForwardResolution(PRODUCT_LIST);
    }

    public Resolution create() throws SQLException {
        category = getProductCategory();
        return new ForwardResolution(PRODUCT_CREATE);
    }

    public Resolution add() throws SQLException {
        product.setId(UUID.randomUUID());
        product.setProductCategory(getProductCategory());
        product.setPriceBuy(0.0);
        try {
            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            getContext().getMessages().add(
                    new SimpleMessage(getLocalizationKey("label.message.Product.added"),
                            productDao.createIfNotExists(product).getName(), product.getProductCategory().getName())
            );
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return new ForwardResolution(ProductActionBean.class, "list");
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<Product> getProductList() throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.getList(categoryId);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ProductCategory getProductCategory() throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            return productCategoryDao.read(UUID.fromString(categoryId));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
