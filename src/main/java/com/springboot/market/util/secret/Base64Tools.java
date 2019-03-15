package com.springboot.market.util.secret;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Tools {

    /**
     * 将json对象转化成字符串进行编码
     *
     * @param obj
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String getEncoderString(JSONObject obj) throws UnsupportedEncodingException {
        return getEncoderString(obj.toJSONString());
    }

    /**
     * 对字符串编码
     *
     * @param str 原字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getEncoderString(String str) throws UnsupportedEncodingException {
        final Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = str.getBytes("UTF-8");
        return encoder.encodeToString(textByte); // 编码
    }

    /**
     * 对字符串解码
     *
     * @param str base64编码后的字符串
     * @return 解码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String getDecoderString(String str) throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str), "UTF-8");// 解码
    }

}
