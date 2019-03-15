package com.springboot.market.util.format;

public class StringTools {

    /**
     * 判断传入的字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}