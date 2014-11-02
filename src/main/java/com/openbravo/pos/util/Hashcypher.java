//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
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
package com.openbravo.pos.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashcypher {

    /**
     * Creates a new instance of Hashcypher
     */
    public Hashcypher() {
    }

    public static boolean authenticate(String sPassword, String sHashPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (sHashPassword == null || sHashPassword.equals("") || sHashPassword.startsWith("empty:")) {
            return sPassword == null || sPassword.equals("");
        } else if (sHashPassword.startsWith("sha1:")) {
            return sHashPassword.equals(hashString(sPassword));
        } else if (sHashPassword.startsWith("plain:")) {
            return sHashPassword.equals("plain:" + sPassword);
        } else {
            return sHashPassword.equals(sPassword);
        }
    }

    public static String hashString(String sPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (sPassword == null || sPassword.equals("")) {
            return "empty:";
        } else {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sPassword.getBytes("UTF-8"));
            byte[] res = md.digest();
            return "sha1:" + StringUtils.byte2hex(res);
        }
    }

}
