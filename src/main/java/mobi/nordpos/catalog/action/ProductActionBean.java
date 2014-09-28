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
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import mobi.nordpos.catalog.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.catalog.dao.ormlite.ProductPersist;
import mobi.nordpos.catalog.model.Product;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductActionBean extends BaseActionBean {

    private static final String PRODUCT_LIST = "/WEB-INF/jsp/product_list.jsp";
    private static final String PRODUCT_CREATE = "/WEB-INF/jsp/product_create.jsp";
//    private static final String PRODUCT_EDIT = "/WEB-INF/jsp/product_edit.jsp";

    ConnectionSource connection;

    private String name;
    private String code;
    private String categoryId;    

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution(PRODUCT_LIST);
    }

    public Resolution create() {
        return new ForwardResolution(PRODUCT_CREATE);
    }
    
//    public Resolution edit() {
//        return new ForwardResolution(PRODUCT_EDIT);
//    }    

//    public Resolution add() throws SQLException {
//        Product product = new Product();
//        product.setId(UUID.randomUUID());
//        product.setName(name);
//        product.setCode(code);
//        try {
//            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
//            ProductPersist productDao = new ProductPersist(connection);
//            productDao.createIfNotExists(product);
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//        return new ForwardResolution(PRODUCT_LIST);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    

    public List<Product> getProductList() throws SQLException {
        try {
            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
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
            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            return productCategoryDao.read(UUID.fromString(categoryId));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }    

}
