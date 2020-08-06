package com.hwc.aidlbottom.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hwc
 */
public class StringUtil {

    /**
     * 将字符串转为阿拉伯数字
     *
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        int value;
        try {
            value = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

    /**
     * 将字符串转为阿拉伯数字
     *
     * @param str
     * @return
     */
    public static double parseDouble(String str) {
        double value;
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

    /**
     * UrlEncode， UTF-8 编码
     *
     * @param str 原始字符串
     * @return
     */
    public static String urlEncode(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取前面匹配，后面匹配的字符串
     *
     * @param str      传递的字符串
     * @param startStr 起始字符串
     * @param endStr   结束字符串
     * @return
     */
    public static String getSubUtilSimple(String str, String startStr, String endStr) {
        StringBuffer sb = new StringBuffer();
        sb.append(startStr).append("(.*?)").append(endStr);
        Pattern pattern = Pattern.compile(sb.toString());
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }


    /**
     * 是否包含阿拉伯数字
     *
     * @param result
     * @return
     */
    public static boolean isContainArabic(String result) {
        if (TextUtils.isEmpty(result)) {
            return false;
        } else {
            String regEx = "\\d+";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(result);
            return m.find();
        }
    }


    /**
     * byte转16进字符串
     *
     * @param b
     * @param size
     * @return
     */
    public static String printHexString(byte[] b, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("0x");
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                sb.append('0').append(hex);
            } else {
                sb.append(hex.toLowerCase());
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 过滤特殊符号
     *
     * @param str
     * @return
     */
    public static String filterChinese(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        return matcher.replaceAll("").trim();
    }


    /**
     * 获取中间字符串数据
     *
     * @return
     */
    public static String findCentreStr(String result, String startStr, String endStr) {
        Pattern p = Pattern.compile(startStr + "(\\w+)" + endStr);
        Matcher m = p.matcher(result);
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }

}
