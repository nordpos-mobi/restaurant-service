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
import java.math.BigDecimal;

import java.util.Date;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@DatabaseTable(tableName = "TAXES")
public class Tax {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String VALIDFROM = "VALIDFROM";
    public static final String CATEGORY = "CATEGORY";
    public static final String CUSTCATEGORY = "CUSTCATEGORY";
    public static final String PARENTID = "PARENTID";
    public static final String RATE = "RATE";
    public static final String RATECASCADE = "RATECASCADE";
    public static final String RATEORDER = "RATEORDER";

    @DatabaseField(id = true, columnName = ID)
    private String id;

    @DatabaseField(columnName = NAME, unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = VALIDFROM, canBeNull = false)
    private Date validFrom;
    
    @DatabaseField(columnName = CATEGORY, canBeNull = false)
    private String categoryId;
    
    @DatabaseField(columnName = CUSTCATEGORY)
    private String custcategoryId;
    
    @DatabaseField(columnName = PARENTID)
    private String parentId;
    
    @DatabaseField(columnName = RATE, canBeNull = false)
    private BigDecimal rate;
    
    @DatabaseField(columnName = RATECASCADE, canBeNull = false)
    private Boolean rateCascade;
    
    @DatabaseField(columnName = RATEORDER, canBeNull = false)
    private Integer rateOrder;

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

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCustCategoryId() {
        return custcategoryId;
    }

    public void setCustCategoryId(String custcategoryId) {
        this.custcategoryId = custcategoryId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getRateCascade() {
        return rateCascade;
    }

    public void setRateCascade(Boolean rateCascade) {
        this.rateCascade = rateCascade;
    }

    public Integer getRateOrder() {
        return rateOrder;
    }

    public void setRateOrder(Integer rateOrder) {
        this.rateOrder = rateOrder;
    }

    public Integer getApplicationRateOrder() {
        return rateOrder == null ? Integer.MAX_VALUE : rateOrder;
    }
}
