/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.dao;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import mobi.nordpos.catalog.model.ProductCategory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public interface ProductCategoryDAO extends Dao<ProductCategory, String> {

    public ProductCategory read(String productCategoryId) throws SQLException;
    public List<ProductCategory> getList() throws SQLException;
    
}
