/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import mobi.nordpos.catalog.dao.*;
import mobi.nordpos.catalog.model.ProductCategory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductCategoryPersist extends BaseDaoImpl<ProductCategory, String> implements ProductCategoryDAO {

    Dao<ProductCategory, String> productCategoryDao;

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
    public ProductCategory read(String productCategoryId) throws SQLException {
        productCategoryDao = DaoManager.createDao(connectionSource, ProductCategory.class);
        return productCategoryDao.queryForId(productCategoryId);
    }
}
