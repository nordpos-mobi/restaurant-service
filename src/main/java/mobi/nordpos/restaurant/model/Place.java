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
package mobi.nordpos.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@DatabaseTable(tableName = "PLACES")
public class Place {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String FLOOR = "FLOOR";

    @DatabaseField(id = true, columnName = ID)
    private String id;

    @DatabaseField(columnName = NAME, unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = X, unique = true, canBeNull = false)
    private Integer x;

    @DatabaseField(columnName = Y, unique = true, canBeNull = false)
    private Integer y;

    @DatabaseField(foreign = true,
            columnName = FLOOR,
            foreignColumnName = Floor.ID,
            foreignAutoRefresh = true,
            canBeNull = false)
    private Floor floor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }    
   
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Place) other).name);
    }

}
