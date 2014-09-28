/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.dao;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import mobi.nordpos.catalog.model.ProductCategory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public interface ProductCategoryDAO extends Dao<ProductCategory, UUID> {

    public ProductCategory read(UUID id) throws SQLException;

    public ProductCategory read(String code) throws SQLException;

}
