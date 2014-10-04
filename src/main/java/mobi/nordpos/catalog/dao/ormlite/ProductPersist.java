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
package mobi.nordpos.catalog.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.UUID;
import mobi.nordpos.catalog.dao.*;
import mobi.nordpos.catalog.model.Product;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductPersist extends BaseDaoImpl<Product, UUID> implements ProductDAO {

    Dao<Product, UUID> productDao;

    public ProductPersist(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Product.class);
    }

    @Override
    public Product read(String code) throws SQLException {
        productDao = DaoManager.createDao(connectionSource, Product.class);
        QueryBuilder qb = productDao.queryBuilder();
        qb.where().like(Product.CODE, code);
        return (Product) qb.queryForFirst();
    }

}
