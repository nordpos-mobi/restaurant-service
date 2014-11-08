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

import mobi.nordpos.restaurant.ext.Public;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@Public
public class WelcomeActionBean extends BaseActionBean {

    private static final String PRESENT = "/WEB-INF/jsp/present.jsp";
    private static final String INFO = "/WEB-INF/jsp/info.jsp";

    @DefaultHandler
    public Resolution title() {
        return new ForwardResolution(PRESENT);
    }

    public Resolution info() {
        return new ForwardResolution(INFO);
    }

    public String getCountry() {
        return getContext().getLocale().getDisplayCountry();
    }

    public String getLanguage() {
        return getContext().getLocale().getDisplayLanguage();
    }

    public String getServerInfo() {
        return getContext().getServletContext().getServerInfo();
    }

    public String getJavaVersion() {
        return System.getProperty("java.vendor") + " " + System.getProperty("java.version");
    }

    public String getOperationSystem() {
        return System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
    }

}
