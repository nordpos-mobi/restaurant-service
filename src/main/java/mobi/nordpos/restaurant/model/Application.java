/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@DatabaseTable(tableName = "APPLICATIONS")
public class Application {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String VERSION = "VERSION";

    @DatabaseField(id = true, columnName = ID)
    private String id;

    @DatabaseField(columnName = NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = VERSION, canBeNull = false)
    private String version;

    Application() {
    }

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getId() {
        return id;
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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Application) other).name) && version.equals(((Application) other).version);
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, version);
    }
}
