package com.itheima.testnewproject.utils;


import com.itheima.testnewproject.common.Constants;
import com.itheima.testnewproject.common.Property;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SOFFICE on 2015/11/14.
 */
public class MessageDigestUtil {

    /***
     * SHA1加密
     *
     * @param
     * @return
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {

        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * SHA1加密
     *
     * @param
     * @return
     */
    public static String sign2(Map<String, String> paramValues, List<String> ignoreParamNames, String secret, String method) {
        paramValues.put("appKey", Property.APPKEY);
        paramValues.put("format", Property.FORMAT);//
        paramValues.put("method", method);
        paramValues.put("v", Property.V);
        paramValues.put("loginType", "4");
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(Property.APPSECRET);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(Property.APPSECRET);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(Constants.UTF8));
        } catch (Exception gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String getDefaultSign(String method) {
        Map<String, String> paramValues = new HashMap<String, String>();
        paramValues.put("appKey", Property.APPKEY);
        paramValues.put("format", Property.FORMAT);//
        paramValues.put("method", method);
        paramValues.put("v", Property.V);
        paramValues.put("loginType", "4");
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());

            Collections.sort(paramNames);

            sb.append(Property.APPSECRET);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(Property.APPSECRET);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUpdateSign(String method, String version) {
        Map<String, String> paramValues = new HashMap<String, String>();
        paramValues.put("appKey", Property.APPKEY);
        paramValues.put("format", Property.FORMAT);//
        paramValues.put("method", method);
        paramValues.put("v", Property.V);
        paramValues.put("loginType", "4");
        paramValues.put("version", version);
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());

            Collections.sort(paramNames);

            sb.append(Property.APPSECRET);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(Property.APPSECRET);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
