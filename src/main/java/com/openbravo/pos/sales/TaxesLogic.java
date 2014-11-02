//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2008-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.nordpos.restaurant.model.Tax;

/**
 *
 * @author adrianromero
 */
public class TaxesLogic {

    private List<Tax> taxList;

    public TaxesLogic(List<Tax> taxList) {
        this.taxList = taxList;

        Map<String, TaxesLogicElement> taxTrees = new HashMap<String, TaxesLogicElement>();
        List<Tax> taxListOrdered = new ArrayList<Tax>();

        taxListOrdered.addAll(taxList);

        Collections.sort(taxListOrdered, new Comparator<Tax>() {
            @Override
            public int compare(Tax o1, Tax o2) {
                if (o2.getApplicationRateOrder() >= o1.getApplicationRateOrder()) {
                    return -1;
                } else if (o1.getApplicationRateOrder() == o2.getApplicationRateOrder()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        HashMap<String, TaxesLogicElement> taxOrphans = new HashMap<String, TaxesLogicElement>();

        for (Tax t : taxListOrdered) {

            TaxesLogicElement te = new TaxesLogicElement(t);
            TaxesLogicElement teParent = taxTrees.get(t.getParentId());

            if (teParent == null) {
                teParent = taxOrphans.get(t.getParentId());
                if (teParent == null) {
                    teParent = new TaxesLogicElement(null);
                    taxOrphans.put(t.getParentId(), teParent);
                }
            }

            teParent.getSons().add(te);

            teParent = taxOrphans.get(t.getId());
            if (teParent != null) {
                te.getSons().addAll(teParent.getSons());
                taxOrphans.remove(t.getId());
            }

            taxTrees.put(t.getId(), te);
        }
    }

    public BigDecimal getTaxRate(String taxCategoryId, Date date) {

        if (taxCategoryId == null) {
            return BigDecimal.ZERO;
        } else {
            Tax tax = getTax(taxCategoryId, date);
            if (tax == null) {
                return BigDecimal.ZERO;
            } else {
                return tax.getRate();
            }
        }
    }

    public Tax getTax(String taxCategoryId, Date date) {

        Tax candidateTax = null;
        Tax defaultTax = null;

        for (Tax tax : taxList) {
            if (tax.getParentId() == null && tax.getCategoryId().equals(taxCategoryId) && tax.getValidFrom().compareTo(date) <= 0) {

                if (candidateTax == null || tax.getValidFrom().compareTo(candidateTax.getValidFrom()) > 0) {
                    candidateTax = tax;
                }

                if (defaultTax == null || tax.getValidFrom().compareTo(defaultTax.getValidFrom()) > 0) {
                    defaultTax = tax;
                }
            }
        }

        return candidateTax == null ? defaultTax : candidateTax;
    }
}