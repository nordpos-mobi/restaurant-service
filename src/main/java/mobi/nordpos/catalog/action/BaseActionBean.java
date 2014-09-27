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

import mobi.nordpos.catalog.ext.MobileActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class BaseActionBean implements ActionBean {

    private MobileActionBeanContext context;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    Boolean hasHomePage = false;

    @Override
    public MobileActionBeanContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.context = (MobileActionBeanContext) actionBeanContext;
    }

    public Boolean getHomePageFlag() {
        return hasHomePage;
    }

    public void setHomePageFlag(Boolean hasHomePage) {
        this.hasHomePage = hasHomePage;
    }

}
