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
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;
import mobi.nordpos.restaurant.dao.ormlite.PlacePersist;
import mobi.nordpos.restaurant.dao.ormlite.ProductPersist;
import mobi.nordpos.restaurant.dao.ormlite.SharedTicketPersist;
import mobi.nordpos.restaurant.model.Place;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.model.SharedTicket;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class OrderBaseActionBean extends BaseActionBean {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Place place;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    protected Product readProduct(String id) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            return productDao.queryForId(id);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Product readProduct(String table, String value) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductPersist productDao = new ProductPersist(connection);
            QueryBuilder qb = productDao.queryBuilder();
            qb.where().like(table, value);
            return (Product) qb.queryForFirst();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Place readPlace(String id) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            PlacePersist placeDao = new PlacePersist(connection);
            return placeDao.queryForId(id);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected List<Place> readPlaceList() throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            PlacePersist placeDao = new PlacePersist(connection);
            return placeDao.getList();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected SharedTicket createTicket(SharedTicket ticket) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            SharedTicketPersist sharedTicketDao = new SharedTicketPersist(connection);
            return sharedTicketDao.createIfNotExists(ticket);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Boolean updateTicket(SharedTicket ticket) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            SharedTicketPersist sharedTicketDao = new SharedTicketPersist(connection);
            return sharedTicketDao.update(ticket) > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Boolean deleteTicket(String id) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            SharedTicketPersist sharedTicketDao = new SharedTicketPersist(connection);
            return sharedTicketDao.deleteById(id) > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
