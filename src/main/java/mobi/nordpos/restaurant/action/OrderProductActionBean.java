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

import com.openbravo.pos.ticket.ProductInfo;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import mobi.nordpos.restaurant.ext.Public;
import mobi.nordpos.restaurant.model.Place;
import mobi.nordpos.restaurant.model.Product;
import mobi.nordpos.restaurant.model.SharedTicket;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
@Public
public class OrderProductActionBean extends OrderBaseActionBean {

    private static final String PRODUCT_ORDER = "/WEB-INF/jsp/product_order.jsp";

    List<Place> placeList;
    @Validate(on = "add", required = true)
    BigDecimal orderUnit;

    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(PRODUCT_ORDER);
    }

    public Resolution add() {
        return new ForwardResolution(CategoryProductListActionBean.class);
    }

    @ValidateNestedProperties({
        @Validate(field = "code",
                required = true,
                trim = true)
    })
    @Override
    public void setProduct(Product product) {
        super.setProduct(product);
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    @ValidateNestedProperties({
        @Validate(on = "add",
                field = "id",
                required = true,
                trim = true)
    })
    @Override
    public void setPlace(Place place) {
        super.setPlace(place);
    }

    public BigDecimal getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(BigDecimal orderUnit) {
        this.orderUnit = orderUnit;
    }

    @ValidationMethod
    public void validatePlaceListIsAvalaible(ValidationErrors errors) {
        try {
            setPlaceList(readPlaceList());
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @ValidationMethod
    public void validateProductCodeIsAvalaible(ValidationErrors errors) {
        try {
            Product product = readProduct(Product.CODE, getProduct().getCode());
            if (product != null) {
                product.setTax(readTax(product.getTaxCategory().getId()));
                setProduct(product);
            } else {
                errors.add("product.code", new SimpleError(
                        getLocalizationKey("error.CatalogNotInclude")));
            }
        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

    @ValidationMethod(on = "add", priority = 1)
    public void tryTicketSave(ValidationErrors errors) {
        try {
            TicketInfo ticket;
            Place place = readPlace(getPlace().getId());
            SharedTicket sharedTicket = readTicket(place.getId());

            Product product = getProduct();
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(product.getId());
            productInfo.setPriceSell(product.getPriceSell().doubleValue());
            productInfo.setName(product.getName());
            productInfo.setTaxcat(product.getTaxCategory().getId());
            productInfo.setCategoryId(product.getProductCategory().getId());
            productInfo.setCom(product.getCom());

            TaxInfo taxInfo = new TaxInfo();
            taxInfo.setId(product.getTax().getId());
            taxInfo.setRate(product.getTax().getRate().doubleValue());
            taxInfo.setTaxcategoryid(product.getTaxCategory().getId());

            TicketLineInfo ticketLine = new TicketLineInfo(productInfo, product.getPriceSell().doubleValue(), taxInfo);
            ticketLine.setMultiply(orderUnit.doubleValue());

            if (sharedTicket == null) {
                ticket = new TicketInfo();
                ticket.setTickettype(TicketInfo.RECEIPT_NORMAL);
                ticket.setM_dDate(new Date());
                ticket.addLine(ticketLine);
                sharedTicket = new SharedTicket();
                sharedTicket.setId(place.getId());
                sharedTicket.setName(ticket.getName());
                sharedTicket.setContent(ticket);
                getContext().getMessages().add(
                        new SimpleMessage(getLocalizationKey("message.OrderTicketLine.added"),
                                createTicket(sharedTicket).getName(), getProduct().getName(), getOrderUnit(), place.getName())
                );
            } else {
                ticket = sharedTicket.getContent();
                ticket.addLine(ticketLine);
                sharedTicket.setContent(ticket);
                if (updateTicket(sharedTicket)) {
                    getContext().getMessages().add(
                            new SimpleMessage(getLocalizationKey("message.OrderTicketLine.added"),
                                    sharedTicket.getName(), getProduct().getName(), getOrderUnit(), place.getName()));
                }
            }

        } catch (SQLException ex) {
            getContext().getValidationErrors().addGlobalError(
                    new SimpleError(ex.getMessage()));
        }
    }

}
