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
package mobi.nordpos.catalog.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.UUID;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@DatabaseTable(tableName = "PRODUCTS")
public class Product {

    public static final String ID = "ID";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String PRICEBUY = "PRICEBUY";
    public static final String PRICESELL = "PRICESELL";
    public static final String CATEGORY = "CATEGORY";

    @DatabaseField(id = true, columnName = ID)
    private UUID id;

    @DatabaseField(columnName = CODE, unique = true, canBeNull = false)
    private String code;

    @DatabaseField(columnName = NAME, unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = PRICEBUY, canBeNull = false)
    private Double pricebuy;

    @DatabaseField(columnName = PRICESELL, canBeNull = false)
    private Double pricesell;

    @DatabaseField(foreign = true, columnName = CATEGORY, canBeNull = false)
    private ProductCategory productCategory;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceBuy() {
        return pricebuy;
    }

    public void setPriceBuy(Double pricebuy) {
        this.pricebuy = pricebuy;
    }

    public Double getPriceSell() {
        return pricesell;
    }

    public void setPriceSell(Double pricesell) {
        this.pricesell = pricesell;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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
        return name.equals(((Product) other).name);
    }

}
