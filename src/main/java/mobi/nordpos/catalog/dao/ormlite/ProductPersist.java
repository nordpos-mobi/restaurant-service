/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
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
    public Product read(UUID productId) throws SQLException {
        productDao = DaoManager.createDao(connectionSource, Product.class);
        return productDao.queryForId(productId);
    }
}
