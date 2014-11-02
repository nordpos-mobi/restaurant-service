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
package mobi.nordpos.restaurant.ext;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.localization.DefaultLocalePicker;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class MyLocalePicker extends DefaultLocalePicker {

    public static final String LOCALE = "locale";

    @Override
    public Locale pickLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // Look in the request.
        String locale = request.getParameter(LOCALE);
        if (locale != null) {
            session.setAttribute(LOCALE, locale);
        } // Not found in the request? Look in the session.
        else {
            locale = (String) session.getAttribute(LOCALE);
        }
        // Use the locale if found.
        if (locale != null) {
            return new Locale(locale);
        }
        // Otherwise, use the default.
        return super.pickLocale(request);
    }
}
