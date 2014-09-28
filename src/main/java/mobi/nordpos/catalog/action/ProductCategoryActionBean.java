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
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductCategoryActionBean extends BaseActionBean {

    private static final String CATEGORY_LIST = "/WEB-INF/jsp/category_list.jsp";
    private static final String CATEGORY_CHANGE = "/WEB-INF/jsp/category_change.jsp";

    ConnectionSource connection;

    private String name;
    private String code;

    @DefaultHandler
    public Resolution list() throws SQLException {
        return new ForwardResolution(CATEGORY_LIST);
    }

    public Resolution change() throws SQLException {
        return new ForwardResolution(CATEGORY_CHANGE);
    }

    public Resolution add() throws SQLException {
        ProductCategory category = new ProductCategory();
        category.setId(UUID.randomUUID());
        category.setName(name);
        category.setCode(code);
        try {
            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            productCategoryDao.createIfNotExists(category);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return new ForwardResolution(CATEGORY_LIST);
    }

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

    public List<ProductCategory> getCategoryList() throws SQLException {
        try {
            this.connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            return productCategoryDao.getList();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
