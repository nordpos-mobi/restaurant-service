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
import java.sql.SQLException;
import mobi.nordpos.restaurant.dao.ormlite.UserPersist;
import mobi.nordpos.restaurant.model.User;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class UserBaseActionBean extends BaseActionBean {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected User readUser(String name) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            UserPersist userDao = new UserPersist(connection);
            return userDao.read(name);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected User createUser(User user) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            UserPersist userDao = new UserPersist(connection);
            return userDao.createIfNotExists(user);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected Boolean updateUser(User user) throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            UserPersist userDao = new UserPersist(connection);
            return userDao.update(user) > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
