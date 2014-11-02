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
 *
 * Copyright (C) 2009 Ben Gunter
 *
 * This source code contains part of code derived from the SecurityInterceptor
 * written by Ben Gunter for Stripes Examples Webapp. The original source code
 * is available from
 *
 * https://github.com/StripesFramework/stripes/
 *
 * Licensed under the Apache License, Version 2.0
 *
 */
package mobi.nordpos.restaurant.ext;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import mobi.nordpos.restaurant.action.UserAuthorizationActionBean;
import net.sourceforge.stripes.util.HttpUtil;
import net.sourceforge.stripes.util.Log;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 * 
 * After the {@link LifecycleStage#ActionBeanResolution} stage, this interceptor
 * checks the resolved {@link ActionBean} class for a {@link Public} annotation.
 * If none is present, then the client is redirected to the login page.
 *
 * @author Ben Gunter
 *
 */
@Intercepts(LifecycleStage.ActionBeanResolution)
public class SecurityInterceptor implements Interceptor {

    private final Log log = Log.getInstance(SecurityInterceptor.class);

    @Override
    public Resolution intercept(ExecutionContext context) throws Exception {
        HttpServletRequest request = context.getActionBeanContext().getRequest();
        String url = HttpUtil.getRequestedPath(request);
        if (request.getQueryString() != null) {
            url = url + '?' + request.getQueryString();
        }
        log.debug("Intercepting request: ", url);

        Resolution resolution = context.proceed();

        // A null resolution here indicates a normal flow to the next stage
        boolean authed = ((MobileActionBeanContext) context.getActionBeanContext()).getUser() != null;
        if (!authed && resolution == null) {
            ActionBean bean = context.getActionBean();
            if (bean != null && !bean.getClass().isAnnotationPresent(Public.class)) {
                log.warn("Thwarted attempted to access ", bean.getClass().getSimpleName());
                return new RedirectResolution(UserAuthorizationActionBean.class).addParameter("targetUrl", url);
            }
        }

        log.debug("Allowing public access to ", context.getActionBean().getClass().getSimpleName());
        return resolution;
    }
}
