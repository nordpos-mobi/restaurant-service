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
package mobi.nordpos.restaurant.action;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.UUID;
import mobi.nordpos.restaurant.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.restaurant.ext.MobileActionBeanContext;
import mobi.nordpos.restaurant.model.ProductCategory;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.StripesFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class BaseActionBean implements ActionBean {

    private MobileActionBeanContext context;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    ConnectionSource connection;

    @Override
    public MobileActionBeanContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.context = (MobileActionBeanContext) actionBeanContext;
    }

    public String getDataBaseURL() {
        return getContext().getServletContext().getInitParameter("db.URL");
    }

    public String getDataBaseUser() {
        return getContext().getServletContext().getInitParameter("db.user");
    }

    public String getDataBasePassword() {
        return getContext().getServletContext().getInitParameter("db.password");
    }    
    
    public String getLocalizationKey(String key) {
        return StripesFilter.getConfiguration().getLocalizationBundleFactory()
                .getFormFieldBundle(getContext().getLocale()).getString(key);
    }   
    
    protected ProductCategory readProductCategory(UUID uuid) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            return productCategoryDao.queryForId(uuid);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
