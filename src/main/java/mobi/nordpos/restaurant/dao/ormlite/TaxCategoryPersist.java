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
package mobi.nordpos.restaurant.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import mobi.nordpos.restaurant.model.TaxCategory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class TaxCategoryPersist extends BaseDaoImpl<TaxCategory, String> {

    Dao<TaxCategory, String> taxCategoryDao;

    public TaxCategoryPersist(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, TaxCategory.class);
    }

}
