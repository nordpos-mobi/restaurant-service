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

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@DatabaseTable(tableName = "FLOORS")
public class Floor {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String IMAGE = "IMAGE";

    @DatabaseField(id = true, columnName = ID)
    private String id;

    @DatabaseField(columnName = NAME, unique = true, canBeNull = false)
    private String name;

    @ForeignCollectionField(orderAscending = true, orderColumnName = Place.NAME)
    private ForeignCollection<Place> placeCollection;

    @DatabaseField(persisted = false)
    private List<Place> placeList;

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

    public ForeignCollection<Place> getPlaceCollection() {
        return this.placeCollection;
    }

    public List<Place> getPlaceList() {
        if (placeList == null) {
            return Arrays.asList(this.getPlaceCollection().toArray(new Place[this.getPlaceCollection().size()]));
        } else {
            return placeList;
        }
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }
    
    public int getPlaceListSize() {
        return this.getPlaceCollection().size();
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
        return name.equals(((Floor) other).name);
    }

}
