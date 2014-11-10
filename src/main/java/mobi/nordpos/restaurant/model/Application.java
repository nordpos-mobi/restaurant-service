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
@DatabaseTable(tableName = "APPLICATIONS")
public class Application {

    public enum ApplicationProjectURL {

        NORDPOS("http://github.com/nordpos/nordpos"),
        OPENBRAVOPOS("http://sourceforge.net/projects/openbravopos/"),
        UNICENTAOPOS("http://sourceforge.net/projects/unicentaopos/"),
        WANDAPOS("http://sourceforge.net/projects/wandaposdapos/");

        private final String url;

        private ApplicationProjectURL(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return url;
        }
    }

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String VERSION = "VERSION";

    @DatabaseField(id = true, columnName = ID)
    private String id;

    @DatabaseField(columnName = NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = VERSION, canBeNull = false)
    private String version;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProjectURL() {
        return ApplicationProjectURL.valueOf(id.toUpperCase()).toString();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return id.equals(((Application) other).id) && version.equals(((Application) other).version);
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, version);
    }
}
