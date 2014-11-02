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

public class StringUtils {

    private static final char[] hexchars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private StringUtils() {
    }

    public static String byte2hex(byte[] binput) {

        StringBuilder sb = new StringBuilder(binput.length * 2);
        for (int i = 0; i < binput.length; i++) {
            int high = ((binput[i] & 0xF0) >> 4);
            int low = (binput[i] & 0x0F);
            sb.append(hexchars[high]);
            sb.append(hexchars[low]);
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String sinput) {
        int length = sinput.length();

        if ((length & 0x01) != 0) {
            throw new IllegalArgumentException("odd number of characters.");
        }

        byte[] out = new byte[length >> 1];

        for (int i = 0, j = 0; j < length; i++) {
            int f = Character.digit(sinput.charAt(j++), 16) << 4;
            f = f | Character.digit(sinput.charAt(j++), 16);
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }
}
