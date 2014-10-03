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
import java.util.List;
import java.util.UUID;
import mobi.nordpos.catalog.dao.*;
import mobi.nordpos.catalog.model.ProductCategory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductCategoryPersist extends BaseDaoImpl<ProductCategory, UUID> implements ProductCategoryDAO {

    Dao<ProductCategory, UUID> productCategoryDao;

    public ProductCategoryPersist(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, ProductCategory.class);
    }

    @Override
    public List<ProductCategory> getList() throws SQLException {
        productCategoryDao = DaoManager.createDao(connectionSource, ProductCategory.class);
        QueryBuilder qb = productCategoryDao.queryBuilder().orderBy(ProductCategory.NAME, true);
        qb.where().isNotNull(ProductCategory.ID);
        return qb.query();
    }
    
    @Override
    public ProductCategory read(String code) throws SQLException {
        productCategoryDao = DaoManager.createDao(connectionSource, ProductCategory.class);
        QueryBuilder qb = productCategoryDao.queryBuilder();
        qb.where().like(ProductCategory.CODE, code);        
        return (ProductCategory) qb.queryForFirst();
    }    

}
