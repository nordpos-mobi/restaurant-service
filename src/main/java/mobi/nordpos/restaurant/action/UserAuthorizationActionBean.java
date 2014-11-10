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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import mobi.nordpos.restaurant.ext.Public;
import mobi.nordpos.restaurant.model.User;
import com.openbravo.pos.util.Hashcypher;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationError;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 *
 * An example of an ActionBean that uses validation annotations on fields
 * instead of on methods. Logs the user in using a conventional
 * username/password combo and validates the password in the action method.
 *
 * @author Tim Fennell
 */
@Public
public class UserAuthorizationActionBean extends UserBaseActionBean {

    private static final String LOGIN = "/WEB-INF/jsp/user_login.jsp";

    private String targetUrl;

    /**
     * The URL the user was trying to access (null if the login page was
     * accessed directly).
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * The URL the user was trying to access (null if the login page was
     * accessed directly).
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(LOGIN);
    }

    public Resolution login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            User loginUser = readUser(getUser().getName());

            if (loginUser == null) {
                ValidationError error = new LocalizableError("error.User.usernameDoesNotExist", getUser().getName());
                getContext().getValidationErrors().add("loginName", error);
                return getContext().getSourcePageResolution();
            } else if (!Hashcypher.authenticate(getUser().getPassword(), loginUser.getPassword())) {
                ValidationError error = new LocalizableError("error.User.incorrectPassword");
                getContext().getValidationErrors().add("loginPassword", error);
                return getContext().getSourcePageResolution();
            } else {
                getContext().setUser(loginUser);
                getContext().getMessages().add(
                    new SimpleMessage(getLocalizationKey("message.User.loged"), loginUser.getName())
            );
                if (this.targetUrl != null) {
                    return new RedirectResolution(this.targetUrl);
                } else {
                    return new RedirectResolution(WelcomeActionBean.class);
                }
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
    }

    public Resolution logout() throws SQLException {
        getContext().logout();
        return new RedirectResolution(WelcomeActionBean.class);
    }

    @ValidateNestedProperties({
        @Validate(on={"login"},
                field = "name",
                required = true,
                minlength = 5,
                maxlength = 20),
        @Validate(on={"login"},
                field = "password",
                required = true,
                minlength = 5,
                maxlength = 20)})
    @Override
    public void setUser(User user) {
        super.setUser(user);
    }

}
