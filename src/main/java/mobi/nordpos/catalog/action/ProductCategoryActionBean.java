/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.catalog.action;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import mobi.nordpos.catalog.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ProductCategoryActionBean extends BaseActionBean {

    private static final String CATEGORY_LIST = "/WEB-INF/jsp/category_list.jsp";
    private static final String CATEGORY_CHANGE = "/WEB-INF/jsp/category_change.jsp";

    private String categoryId;
    private String productId;

    private List<ProductCategory> categoryList;

    public Resolution list() throws SQLException {
        return new ForwardResolution(CATEGORY_LIST);
    }
    
    public Resolution add() throws SQLException {
        return new ForwardResolution(CATEGORY_CHANGE);
    }    

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<ProductCategory> getCategoryList() throws SQLException {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(getDataBaseURL());
            ((JdbcConnectionSource) connectionSource).setUsername(getDataBaseUser());
            ((JdbcConnectionSource) connectionSource).setPassword(getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connectionSource);
            return productCategoryDao.queryForAll();
        } finally {
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }
}
