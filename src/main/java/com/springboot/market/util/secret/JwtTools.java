package com.springboot.market.util.secret;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

/**
 * JWT格式token生成类
 * 1，token签发功能
 * 2，更新token有效时间
 * 3，检查token是否失效
 */
public class JwtTools {

    // SECRET KEY
    private static String SECRET_KEY = "ndE2jdZNFixH9G6Aidsfyf7lYT3PxW";
    private static int DEFAULT_EXP = 600000;// token默认有效时间（毫秒单位）

    /**
     * 获取sha256秘钥
     *
     * @return
     */
    public static String getSecretKey() {
        return SECRET_KEY;
    }

    /**
     * 获取Token有效时间
     *
     * @return
     */
    public static int getEXP() {
        return DEFAULT_EXP;
    }


    /**
     * 获取JWT格式的token
     *
     * @param header  未加密的字符串
     * @param payload 未加密的字符串
     * @param key     秘钥
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getJwtToken(JSONObject header, JSONObject payload, String key)
            throws UnsupportedEncodingException {
        // 经过base64算法加密后的token头
        String base64Header = Base64Tools.getEncoderString(header);
        // 经过base64算法加密后的token负载部分
        String base64Payload = Base64Tools.getEncoderString(payload);
        // 经过sha256加密后的验证部分
        String sha256Signature = SHA256Tools.getSHA256String(base64Header + "." + base64Payload, key);
        // 返回JWT token字符串
        StringBuffer stringBuffer = new StringBuffer().append(base64Header)
                .append(".")
                .append(base64Payload)
                .append(".")
                .append(sha256Signature);
        return stringBuffer.toString();
    }

    /**
     * 验证token是否有效
     *
     * @param tokenString token字符串
     * @return false：失效，true：未失效
     * @throws UnsupportedEncodingException
     */
    public static boolean checkToken(String tokenString) throws UnsupportedEncodingException {
        // 判断字符串是否为空
        if (tokenString.isEmpty()) {
            return false;
        }
        // 将字符串转换成字符数组，判断token格式是否正确
        String[] strList = getTokenList(tokenString);
        if (strList.length != 3) {
            return false;
        }
        // 判断token是否超时
        JSONObject obj = JSONObject.parseObject(Base64Tools.getDecoderString(strList[1]));
        if (obj.getLong("exp") > System.currentTimeMillis()) {
            return false;
        }
        // 将头和负载部分重新加密判断是否被篡改
        String localToken = strList[0] + "." + strList[1] + "."
                + SHA256Tools.getSHA256String(strList[0] + "." + strList[1], getSecretKey());
        if (localToken.equals(tokenString)) {
            return true;
        }
        return false;
    }

    /**
     * 更新token中的有效时间
     *
     * @param tokenString Jwt token字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String updateDateToken(String tokenString) throws UnsupportedEncodingException {
        // 获取token头json object
        JSONObject headerObj = getHeaderObj(tokenString);
        // 获取负载部分的json object
        JSONObject payloadObj = getPayloadObj(tokenString);
        payloadObj.put("exp", System.currentTimeMillis() + DEFAULT_EXP);
        return getJwtToken(headerObj, payloadObj, SECRET_KEY);
    }

    /**
     * 获取默认的头对象
     *
     * @return
     */
    public static JSONObject getDefaultHeader() {
        JSONObject obj = new JSONObject();
        obj.put("typ", "JWT");
        obj.put("alg", "SHA256");
        return obj;
    }


    /**
     * 获取默认负载对象
     *
     * @return
     */
    public static JSONObject getDefaultPayload() {
        long currentDate = System.currentTimeMillis();
        JSONObject obj = new JSONObject();
        obj.put("iss", "admin");// 签发人
        obj.put("sub", "def");// 主题
        obj.put("aud", "all");// 受众
        obj.put("iat", currentDate);// 签发时间(默认当前时间戳)
        obj.put("exp", currentDate + getEXP());// 过期时间默认十分钟
        return obj;
    }

    /**
     * 将token截取分段
     *
     * @param tokenString token字符串
     * @return
     */
    private static String[] getTokenList(String tokenString) {
        return tokenString.split("\\."); // 截取字符数组
    }

    /**
     * 获取token字符串中的头字符串（未解密）
     *
     * @param tokenString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getHeaderString(String tokenString) throws UnsupportedEncodingException {
        return getTokenList(tokenString)[0];
    }

    /**
     * 获取JSONObject格式的token头
     *
     * @param tokenString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static JSONObject getHeaderObj(String tokenString) throws UnsupportedEncodingException {
        return JSONObject.parseObject(Base64Tools.getDecoderString(getHeaderString(tokenString)));
    }

    /**
     * 获取token负载部分String（未解密）
     *
     * @param tokenString
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getPayloadString(String tokenString) throws UnsupportedEncodingException {
        return getTokenList(tokenString)[1];
    }

    /**
     * 获取token负载部分JSON对象
     *
     * @param tokenString
     * @return
     * @throws UnsupportedEncodingException
     */
    private static JSONObject getPayloadObj(String tokenString) throws UnsupportedEncodingException {
        return JSONObject.parseObject(Base64Tools.getDecoderString(getTokenList(tokenString)[1]));
    }
}