package com.itheima.testnewproject.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * <pre>
 *     author : baoX
 *     time   : 2017/12/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NumUtil {
    /*
    * 元转万元
    * */
    public static String yuan2wan(int num) {
        BigDecimal bigDecimal = new BigDecimal(num);
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
        DecimalFormat formater;

        if (num < 10) {
            formater = new DecimalFormat("0.0000");
        } else if (num >= 10 && num < 100) {
            formater = new DecimalFormat("0.000");
        } else {
            formater = new DecimalFormat("0.00");
        }
        //不四舍五入
        formater.setRoundingMode(RoundingMode.FLOOR);
        String formatNum = formater.format(decimal);
        String[] split = formatNum.split("\\.");
        if ("00".equals(split[1])) {
            return split[0] + "万";
        }
        return formatNum + "万";
    }


    public static String yuan2wanNoUnit(int num) {
        BigDecimal bigDecimal = new BigDecimal(num);
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
        DecimalFormat formater;

        if (num < 10) {
            formater = new DecimalFormat("0.0000");
        } else if (num >= 10 && num < 100) {
            formater = new DecimalFormat("0.000");
        } else {
            formater = new DecimalFormat("0.00");
        }
        //不四舍五入
        formater.setRoundingMode(RoundingMode.FLOOR);
        String formatNum = formater.format(decimal);
        String[] split = formatNum.split("\\.");
        if ("00".equals(split[1])) {
            return split[0];
        }
        return formatNum;
    }

    public static String save2Decimal(float num, float divide) {
        BigDecimal bd = new BigDecimal(num);
        BigDecimal decimal = bd.divide(new BigDecimal(divide));
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimal);
    }
}
