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
import java.util.List;
import mobi.nordpos.restaurant.dao.ormlite.FloorPersist;
import mobi.nordpos.restaurant.model.Floor;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class FloorBaseActionBean extends BaseActionBean {

    private Floor floor;

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    protected List<Floor> readFloorList() throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            FloorPersist floorDao = new FloorPersist(connection);
            return floorDao.getList();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
