/*
 * @Author: iszhangheng
 * @Date: 2018-12-27 16:05:16
 * @LastEditors: iszhangheng
 * @LastEditTime: 2019-02-14 10:08:53
 * @Description: file content
 */
package com.springboot.market.util.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberTools {
    /**
     * 判断是否是数字类型
     *
     * @param obj
     * @return
     */
    public static boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        }
        return false;
    }

    /**
     * 计算百分比
     *
     * @return result 格式: '0.00%'
     * @throws Exception
     * @params x:分子 ， total: 分母
     */
    public static String getPercent(int x, int total) throws Exception {
        if (total == 0) {
            throw new Exception("算数除零异常！");
        }
        return new DecimalFormat("0.00%").format((x * 1.0) / total);
    }

    /**
     * 判断 local < tag
     *
     * @param local 参数一
     * @param tag   参数二
     * @return
     * @throws Exception
     */
    public static boolean lt(Object local, Object tag) throws Exception {
        if (local instanceof Integer && tag instanceof Integer) {
            return (int) local < (int) tag ? true : false;
        } else {
            throw new Exception("参数异常！");
        }
    }

    /**
     * 判断 local < tag
     *
     * @param local 参数一
     * @param tag   参数二
     * @return
     */
    public static boolean lt(int local, int tag) {
        if (local < tag) {
            return true;
        }
        return false;
    }

    /**
     * 判断 local < tag
     *
     * @param local 参数一
     * @param tag   参数二
     * @return
     */
    public static boolean lt(String local, int tag) {
        if (local == null || local == "") {
            return false;
        }
        if (Integer.parseInt(local) < tag) {
            return true;
        }
        return false;
    }

    /**
     * 判断 local > tag
     *
     * @param local
     * @param tag
     * @return
     */
    public static boolean gt(int local, int tag) {
        if (local > tag) {
            return true;
        }
        return false;
    }

    /**
     * 判断 local > tag
     *
     * @param local
     * @param tag
     * @return
     */
    public static boolean gt(String local, int tag) {
        if (local == null || local == "") {
            return false;
        }
        if (Integer.parseInt(local) > tag) {
            return true;
        }
        return false;
    }

    /**
     * 判断 local >= tag
     *
     * @param local
     * @param tag
     * @return
     */
    public static boolean gtEqual(int local, int tag) {
        if (local >= tag) {
            return true;
        }
        return false;
    }

    /**
     * 判断 local >= tag
     *
     * @param local
     * @param tag
     * @return
     */
    public static boolean gtEqual(String local, int tag) {
        if (local == null || local == "") {
            return false;
        }
        if (Integer.parseInt(local) >= tag) {
            return true;
        }
        return false;
    }

    /**
     * 向下取整
     *
     * @param n
     * @return
     */
    public static int floor(double n) {
        return (int) Math.floor(n);
    }

    /**
     * 向上取整
     *
     * @param n
     * @return
     */
    public static int ceil(double n) {
        return (int) Math.ceil(n);
    }

    /**
     * 四舍五入取整
     *
     * @param n
     * @return
     */
    public static int round(double n) {
        return (int) Math.round(n);
    }

    /**
     * 保留scale位小数
     *
     * @param e
     * @param scale
     * @return
     */
    public static double scaleFloor(double e, int scale) {
        return new BigDecimal(e).setScale(2, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 保留scale位小数
     *
     * @param e
     * @param scale
     * @return
     */
    public static String scaleFloorStr(double e, int scale) {
        return scaleFloorStr(e, scale) + "";
    }

    /**
     * 求log(x)(y)，利用换底公式
     *
     * @param x 底
     * @param y
     * @return
     */
    public static double log(double x, double y) {
        return Math.log(y) / Math.log(x);
    }

    /**
     * 去掉小数点后无效的0
     *
     * @param e
     * @return
     */
    public static String subZeroAndDot(double e) {
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(e);
    }

    /**
     * 去掉小数点后无效的0
     *
     * @param e
     * @return
     */
    public static String subZeroAndDot(String e) {
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(e);
    }

    /**
     * 将double格式化为指定小数位的String，不足小数位用0补全
     *
     * @param v
     * @param scale
     * @return
     */
    public static String roundByScale(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }
        String formatStr = "0.";
        for (int i = 0; i < scale; i++) {
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }
}