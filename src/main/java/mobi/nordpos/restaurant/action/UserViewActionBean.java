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

import java.io.IOException;
import java.sql.SQLException;
import mobi.nordpos.restaurant.util.ImagePreview;
import mobi.nordpos.dao.model.User;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class UserViewActionBean extends UserBaseActionBean {

    private static final String USER_VIEW = "/WEB-INF/jsp/user_view.jsp";

    private FileBean imageFile;

    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(USER_VIEW);
    }

    public Resolution update() {
        User user = getContext().getUser();
        try {
            userPersist.init(getDataBaseConnection());
            if (userPersist.change(user)) {
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("message.User.updated"),
                                user.getName()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(WelcomeActionBean.class);
    }

    @ValidationMethod(on = "update")
    public void validateUserImageUpload(ValidationErrors errors) {
        if (imageFile != null) {
            if (imageFile.getContentType().startsWith("image")) {
                try {
                    getContext().getUser().setImage(ImagePreview.createThumbnail(imageFile.getInputStream(), 256));
                } catch (IOException ex) {
                    errors.add("user.image", new SimpleError(
                            getLocalizationKey("error.FileNotUpload"), imageFile.getFileName()));
                }
            } else {
                errors.add("user.image", new SimpleError(
                        getLocalizationKey("error.FileNotImage"), imageFile.getFileName()));
            }
        }
    }

    public FileBean getImageFile() {
        return imageFile;
    }

    public void setImageFile(FileBean imageFile) {
        this.imageFile = imageFile;
    }
}
