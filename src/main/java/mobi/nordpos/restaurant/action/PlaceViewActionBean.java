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

import com.openbravo.pos.ticket.TicketLineInfo;
import java.math.BigDecimal;
import java.sql.SQLException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class PlaceViewActionBean extends PlaceBaseActionBean {

    private static final String PLACE_VIEW = "/WEB-INF/jsp/place_view.jsp";

    BigDecimal totalValue;
    BigDecimal totalUnit;

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(PLACE_VIEW);
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public BigDecimal getTotalUnit() {
        return totalUnit;
    }

    @ValidationMethod
    public void validatePlaceIsAvalaible(ValidationErrors errors) {
        try {
            setPlace(readPlace(getPlace().getId()));
            totalValue = BigDecimal.ZERO;
            totalUnit = BigDecimal.ZERO;
            for (TicketLineInfo line : getPlace().getTicket().getContent().getM_aLines()) {
                totalValue = totalValue.add(BigDecimal.valueOf(line.getValue()));
                totalUnit = totalUnit.add(BigDecimal.valueOf(line.getMultiply()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }
}
