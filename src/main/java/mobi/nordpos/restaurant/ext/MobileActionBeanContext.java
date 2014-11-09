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

import mobi.nordpos.restaurant.model.Place;
import mobi.nordpos.restaurant.model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class MobileActionBeanContext extends ActionBeanContext {

    /**
     * Gets the currently logged in user, or null if no-one is logged in.
     */
    public User getUser() {
        return (User) getRequest().getSession().getAttribute("user");
    }

    /**
     * Sets the currently logged in user.
     */
    public void setUser(User currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);
    }
    
    public Place getPlace() {
        return (Place) getRequest().getSession().getAttribute("place");
    }

    public void setPlace(Place place) {
        getRequest().getSession().setAttribute("place", place);
    }    

    /**
     * Logs the user out by invalidating the session.
     */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}
