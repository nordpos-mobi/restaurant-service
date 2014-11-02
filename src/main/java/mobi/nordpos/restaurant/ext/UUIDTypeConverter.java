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

import java.util.Collection;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class UUIDTypeConverter implements TypeConverter<UUID> {

    private static final SimpleError UUID_NOT_VALID_ERROR = new SimpleError(
            "The UUID {1} is not valid.");
    private static final Pattern PATTERN = Pattern.compile(
            "[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}");

    @Override
    public UUID convert(String string,
            Class<? extends UUID> type,
            Collection<ValidationError> errors) {
        UUID uuid;
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            try {
                uuid = UUID.fromString(string);
                if (uuid == null) {
                    errors.add(UUID_NOT_VALID_ERROR);
                }
                return uuid;
            } catch (Exception exc) {
                errors.add(UUID_NOT_VALID_ERROR);
                return null;
            }
        } else {
            errors.add(UUID_NOT_VALID_ERROR);
            return null;
        }
    }

    @Override
    public void setLocale(Locale locale) {
    }
}
