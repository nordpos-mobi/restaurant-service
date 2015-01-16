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

import mobi.nordpos.dao.model.Place;
import mobi.nordpos.dao.model.Product;
import mobi.nordpos.dao.factory.PlacePersist;
import mobi.nordpos.dao.factory.ProductPersist;
import mobi.nordpos.dao.factory.SharedTicketPersist;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public abstract class OrderBaseActionBean extends BaseActionBean {

    public final PlacePersist placePersist;
    public final ProductPersist productPersist;
    public final SharedTicketPersist sharedTicketPersist;
    private Product product;

    public OrderBaseActionBean() {
        productPersist = new ProductPersist();
        placePersist = new PlacePersist();
        sharedTicketPersist = new SharedTicketPersist();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Place place;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

}
