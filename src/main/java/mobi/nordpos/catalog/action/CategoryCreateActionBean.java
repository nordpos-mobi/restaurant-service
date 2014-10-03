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

import java.sql.SQLException;
import java.util.UUID;
import mobi.nordpos.catalog.model.ProductCategory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
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
public class CategoryCreateActionBean extends CategoryBaseActionBean {

    private static final String CATEGORY_CREATE = "/WEB-INF/jsp/category_create.jsp";

    @DefaultHandler
    @DontValidate
    public Resolution form() {
        return new ForwardResolution(CATEGORY_CREATE);
    }

    public Resolution add() {
        ProductCategory category = getCategory();
        category.setId(UUID.randomUUID());
        try {
            getContext().getMessages().add(
                    new SimpleMessage(getLocalizationKey("label.message.ProductCategory.added"),
                            createProductCategory(category).getName())
            );
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError("{2} {3}", ex.getErrorCode(), ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(CategoryListActionBean.class);
    }

    @ValidateNestedProperties({
        @Validate(field = "name",
                required = true,
                trim = true,
                maxlength = 255)
    })
    @Override
    public void setCategory(ProductCategory category) {
        super.setCategory(category);
    }

    @ValidationMethod
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
}
