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

import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mobi.nordpos.restaurant.model.Place;
import mobi.nordpos.restaurant.model.SharedTicket;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class OrderPlaceActionBean extends OrderBaseActionBean {

    private static final String PLACE_VIEW = "/WEB-INF/jsp/place_order.jsp";

    @Validate(on = "remove", required = true)
    Integer removeLineNumber;

    @DefaultHandler
    public Resolution view() {
        getContext().setPlace(null);
        return new ForwardResolution(PLACE_VIEW);
    }

    public Resolution add() {
        getContext().setPlace(getPlace());
        return new ForwardResolution(CategoryProductListActionBean.class);
    }

    public Resolution remove() {
        TicketLineInfo removeLine = new TicketLineInfo();
        SharedTicket sharedTicket = getPlace().getTicket();
        TicketInfo ticket = sharedTicket.getContent();
        List<TicketLineInfo> lines = ticket.getLines();
        ticket.setM_aLines(new ArrayList<TicketLineInfo>());
        for (TicketLineInfo line : lines) {
            if (line.getM_iLine() != removeLineNumber) {
                ticket.addLine(line);
            } else {
                removeLine = line;
            }
        }
        sharedTicket.setContent(ticket);
        try {
            if (updateTicket(sharedTicket)) {
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("message.PlaceTicketLine.removed"),
                                sharedTicket.getName(), removeLine.getAttributes().getProperty("product.name"), removeLine.getMultiply(), getPlace().getName()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(OrderPlaceActionBean.class, "view");
    }

    public Resolution delete() throws SQLException {
        Place place = getPlace();
        try {
            if (deleteTicket(place.getId())) {
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("message.Order.deleted"),
                                place.getName()));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution(FloorListActionBean.class);
    }

    public Integer getRemoveLineNumber() {
        return removeLineNumber;
    }

    public void setRemoveLineNumber(Integer removeLineNumber) {
        this.removeLineNumber = removeLineNumber;
    }

    @ValidationMethod
    public void validatePlaceIsAvalaible(ValidationErrors errors) {
        try {
            Place place = readPlace(getPlace().getId());
            SharedTicket ticket = readTicket(place.getId());
            
            if (ticket != null) {
                BigDecimal value = BigDecimal.ZERO;
                BigDecimal unit = BigDecimal.ZERO;
                for (TicketLineInfo line : ticket.getContent().getM_aLines()) {
                    value = value.add(BigDecimal.valueOf(line.getValue()));
                    unit = unit.add(BigDecimal.valueOf(line.getMultiply()));
                }
                ticket.setTotalValue(value);
                ticket.setTotalUnit(unit);
            }
            place.setTicket(ticket);
            setPlace(place);
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @ValidationMethod(on = "remove")
    public void validateSharedTicketLineIsRemove(ValidationErrors errors) {
        SharedTicket ticket = getPlace().getTicket();
        if (ticket != null) {
            for (TicketLineInfo line : ticket.getContent().getM_aLines()) {
                if (removeLineNumber == line.getM_iLine()) {                    
                    ticket.setTotalValue(ticket.getTotalValue().subtract(BigDecimal.valueOf(line.getValue())));
                    ticket.setTotalUnit(ticket.getTotalUnit().subtract(BigDecimal.valueOf(line.getMultiply())));
                }
            }
            getPlace().setTicket(ticket);
        }
    }
}
