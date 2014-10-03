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
package mobi.nordpos.catalog.action;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.UUID;
import mobi.nordpos.catalog.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class CategoryChangeActionBean extends CategoryBaseActionBean {

    private static final String CATEGORY_EDIT = "/WEB-INF/jsp/category_edit.jsp";

    private String categoryId;

    @DefaultHandler
    public Resolution form() throws SQLException {
        return new ForwardResolution(CATEGORY_EDIT);
    }

    public Resolution update() throws SQLException {
        ProductCategory category = getCategory();
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            productCategoryDao.update(category);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return new ForwardResolution(CategoryListActionBean.class, "list");
    }

    public Resolution delete() throws SQLException {
        try {
            connection = new JdbcConnectionSource(getDataBaseURL(), getDataBaseUser(), getDataBasePassword());
            ProductCategoryPersist productCategoryDao = new ProductCategoryPersist(connection);
            productCategoryDao.deleteById(getCategory().getId());
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError("{2} {3}", ex.getErrorCode(), ex.getMessage()));
            return new ForwardResolution(CategoryListActionBean.class, "list");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return new ForwardResolution(CategoryListActionBean.class, "list");
    }

    @ValidationMethod(on = "delete")
    public void validateProductListIsEmpty(ValidationErrors errors) throws SQLException {
        ProductCategory category = getCategory();
        if (!category.getProductCollection().isEmpty()) {
            errors.addGlobalError(new SimpleError(
                    getLocalizationKey("label.error.ProductCategory.IncludeProducts"), category.getName(), category.getProductCollection().size()
            ));
        }
    }

    @ValidationMethod(on = "update")
    public void validateCategoryCodeIsUnique(ValidationErrors errors) {
        String code = getCategory().getCode();
        try {
            if (readProductCategory(code) != null) {
                errors.addGlobalError(new SimpleError(
                        getLocalizationKey("label.error.ProductCategory.AlreadyExists"), code
                ));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @ValidationMethod
    public void validateCategoryListIsAvalaible(ValidationErrors errors) {
        try {
            setCategory(readProductCategory(UUID.fromString(getCategoryId())));
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @Override
    public void setCategory(ProductCategory category) {
        super.setCategory(category);
    }

    @Validate(required = true)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
