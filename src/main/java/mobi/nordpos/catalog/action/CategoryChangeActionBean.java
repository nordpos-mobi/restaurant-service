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
import mobi.nordpos.catalog.dao.ormlite.ProductCategoryPersist;
import mobi.nordpos.catalog.ext.UUIDTypeConverter;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class CategoryChangeActionBean extends CategoryBaseActionBean {

    private static final String CATEGORY_EDIT = "/WEB-INF/jsp/category_edit.jsp";

    private String codeCurrent;

    @DefaultHandler
    public Resolution form() throws SQLException {
        return new ForwardResolution(CATEGORY_EDIT);
    }

    public Resolution update() {
        ProductCategory category = getCategory();
        try {
            if (updateProductCategory(category)) {
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("label.message.ProductCategory.updated"),
                                category.getName()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError("{2} {3}", ex.getErrorCode(), ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(CategoryListActionBean.class);
    }

    public Resolution delete() throws SQLException {
        ProductCategory category = getCategory();
        try {
            if (deleteProductCategory(category.getId())) {
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("label.message.ProductCategory.deleted"),
                                category.getName()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError("{2} {3}", ex.getErrorCode(), ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(CategoryListActionBean.class);
    }

    @ValidationMethod(on = "delete")
    public void validateProductListIsEmpty(ValidationErrors errors) throws SQLException {
        setCategory(readProductCategory(getCategory().getId()));        
        if (!getCategory().getProductCollection().isEmpty()) {
            errors.addGlobalError(new SimpleError(
                    getLocalizationKey("label.error.ProductCategory.IncludeProducts"), getCategory().getName(), getCategory().getProductCollection().size()
            ));
        }
    }

    @ValidationMethod(on = "update")
    public void validateCategoryCodeIsUnique(ValidationErrors errors) {
        String codeUpdate = getCategory().getCode();
        if (codeUpdate != null && !codeUpdate.isEmpty() && !codeUpdate.equals(getCodeCurrent())) {
            try {
                if (readProductCategory(codeUpdate) != null) {
                    errors.addGlobalError(new SimpleError(
                            getLocalizationKey("label.error.ProductCategory.AlreadyExists"), codeUpdate
                    ));
                }
            } catch (SQLException ex) {
                getContext().getValidationErrors().addGlobalError(
                        new SimpleError(ex.getMessage()));
            }
        }
    }

    @ValidationMethod(on = "form")
    public void validateCategoryListIsAvalaible(ValidationErrors errors) {
        try {
            setCategory(readProductCategory(getCategory().getId()));
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @ValidateNestedProperties({
        @Validate(on = {"form", "update", "delete"},
                field = "id",
                required = true,
                converter = UUIDTypeConverter.class),
        @Validate(on = {"update"},
                field = "name",
                required = true,
                trim = true,
                maxlength = 255),
        @Validate(on = {"update"},
                field = "code",
                trim = true,
                maxlength = 4)
    })
    @Override
    public void setCategory(ProductCategory category) {
        super.setCategory(category);
    }

    public String getCodeCurrent() {
        return codeCurrent;
    }

    public void setCodeCurrent(String codeCurrent) {
        this.codeCurrent = codeCurrent;
    }
}
