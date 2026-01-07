package com.ncode.epp.common;

import java.io.IOException;

public class Base64Service {

    public static String encode(byte[] dataToEncode) {
        return Base64New.encode(dataToEncode);
    }

    public static byte[] decode(String dataToDecode) throws IOException {
        while (dataToDecode.length() < 4) {
            dataToDecode += "=";
        }
        return Base64New.decode(dataToDecode);
    }

    public static String convertToString(byte[] buf, int start) {
        int len = buf.length;
        StringBuffer convertData = new StringBuffer();
        String returnString = null;
        for (int i = start; i < len; i++) {
            String HEX = "0123456789abcdef";
            convertData.append(" ");
            convertData.append(HEX.charAt(buf[i] >>> 4 & 0x0F));
            convertData.append(HEX.charAt(buf[i] & 0x0F));
        }
        returnString = new String(convertData);
        return returnString;
    }

}
