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

import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import mobi.nordpos.restaurant.ext.Public;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.util.ImagePreview;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@Public
public class OrderProductImageActionBean extends OrderBaseActionBean {

    private int thumbnailSize = 256;

    public StreamingResolution preview() {
        return new StreamingResolution("image/jpeg") {
            @Override
            public void stream(HttpServletResponse response) throws Exception {
                if (getProduct().getImage() != null) {
                    response.getOutputStream().write(ImagePreview.createThumbnail(getProduct().getImage(), thumbnailSize));
                    response.flushBuffer();
                }
            }
        }.setFilename("product-".concat(getProduct().getCode()).concat(".jpeg"));
    }

    @ValidationMethod(on = "preview")
    public void validateProductIdIsAvalaible(ValidationErrors errors) {
        try {
            Product product = readProduct(getProduct().getId());
            if (product != null) {
                setProduct(product);
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

}
