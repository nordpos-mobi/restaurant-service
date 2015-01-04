/**
 * Copyright (c) 2012-2015 Nord Trading Network.
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

import javax.servlet.http.HttpServletResponse;
import mobi.nordpos.restaurant.util.ImagePreview;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class UserImageActionBean extends UserBaseActionBean{

    private int thumbnailSize = 256;

    public StreamingResolution preview() {        
        return new StreamingResolution("image/jpeg") {
            @Override
            public void stream(HttpServletResponse response) throws Exception {
                if (getContext().getUser().getImage() != null) {
                    response.getOutputStream().write(ImagePreview.createThumbnail(getContext().getUser().getImage(), thumbnailSize));
                    response.flushBuffer();
                }
            }
        }.setFilename("user-".concat(getContext().getUser().getId().toString()).concat(".jpeg"));
    }

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

}
