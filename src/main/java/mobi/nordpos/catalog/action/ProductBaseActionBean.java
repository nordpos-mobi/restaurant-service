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
import java.util.UUID;
import mobi.nordpos.catalog.dao.ormlite.ProductPersist;
import mobi.nordpos.catalog.model.Product;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class ProductBaseActionBean extends BaseActionBean {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    protected Product readProduct(UUID uuid) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.queryForId(uuid);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    protected Product readProduct(String code) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.read(code);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }    

    protected Product createProduct(Product product) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.createIfNotExists(product);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Boolean updateProduct(Product product) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.update(product) > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Boolean deleteProduct(UUID id) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.deleteById(id) > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
