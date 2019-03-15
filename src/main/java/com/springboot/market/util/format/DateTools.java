package com.springboot.market.util.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的时间戳(13位)
     * @param formatStr
     * @return
     */
    public static String transformTimestampToDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd";
        }
        if (seconds.length() == 10) {
            seconds += "000";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的时间戳字符串(13位)
     * @param format  时间格式字符串
     * @return
     */
    public static String transformTimestampToDate(Long seconds, String format) {
        if (seconds == null) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd";
        }
        if (String.valueOf(seconds).length() == 10) {
            seconds *= 1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(seconds));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String transformDateToTimestamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String gettimeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 获取当前时间 年-月-日 时:分:秒
     *
     * @return dateString
     */
    public static String currentDate() {
        return currentDate(new Date());
    }

    /**
     * 当前/指定 年-月-日 时:分:秒
     *
     * @param nowdate
     * @return
     */
    public static String currentDate(Date nowdate) {
        return currentDate(nowdate, "yyyy-MM-dd HH:mm:ss");
    }

    // 当前/指定时间 年-月
    public static String currentDate(Date nowdate, String format) {
        if (nowdate == null) {
            nowdate = new Date();
        }
        DateFormat format2 = new SimpleDateFormat(format);
        String dateString = null;
        dateString = format2.format(nowdate);
        return dateString;
    }
}