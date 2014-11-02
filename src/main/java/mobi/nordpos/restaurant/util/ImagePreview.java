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
package mobi.nordpos.restaurant.util;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ImagePreview {

    public static byte[] createThumbnail(byte[] image, int size) throws IOException {
        return createThumbnail(new ByteArrayInputStream(image), size);
    }

    public static byte[] createThumbnail(InputStream in, int size) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage thumbnail = ImageIO.read(in);
        int width = thumbnail.getWidth();
        int height = thumbnail.getHeight();
        if (width > size || height > size) {
            if (width > height) {
                thumbnail = Scalr.resize(thumbnail, Scalr.Mode.FIT_TO_HEIGHT, size);
                thumbnail = Scalr.crop(thumbnail, thumbnail.getWidth() / 2 - size / 2, 0, size, size);
            } else {
                thumbnail = Scalr.resize(thumbnail, Scalr.Mode.FIT_TO_WIDTH, size);
                thumbnail = Scalr.crop(thumbnail, 0, thumbnail.getWidth() / 2 - size / 2, size, size);
            }
        }
        try {
            ImageIO.write(thumbnail, "jpeg", baos);
            baos.flush();
            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }
}
