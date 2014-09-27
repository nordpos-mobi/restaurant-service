/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.dao;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.UUID;
import mobi.nordpos.catalog.model.Product;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public interface ProductDAO extends Dao<Product, UUID> {

    public Product read(UUID productId) throws SQLException;

}
