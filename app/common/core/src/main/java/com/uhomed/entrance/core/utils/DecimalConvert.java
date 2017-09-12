/**
 * @date 2014年6月28日 上午11:20:09
 * @author lim
 * @version V1.0 
 */
package com.uhomed.entrance.core.utils;

import java.util.StringTokenizer;

/**
 * 十进制转换
 * @date 2014年6月28日 上午11:20:09
 * @author lim
 * @version V1.0 
 */
public class DecimalConvert {

    /**
     * 任意进制到十进制的转换
     * @param strInt  需要转换的字符串
     * @param JZ   需要转换到的进制数
     * @return
     */
    public static String ten2AnySystem(String strInt, int hex) { 
    	//因为由任意进制到十进制的转换结果为"整数部分.小数部份"
        //也就是分成整数和小数部份分别转换
        //此函数完成的功能是十进制的整数部分到任意进制的转换 
        String stringForInt = null;
        try {
            //取出整数部份 
            stringForInt = returnWantPart(strInt, "left");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long intStr = Long.parseLong(stringForInt);
        long[] result = new long[50]; //用50个数组来保存相除的余数
        long T;
        String resultStr = "";
        int i = 0; //数组的下标
        while (intStr != 0) {
            result[i] = intStr % hex; //取出余数
            intStr = intStr - result[i]; //现数为减去余数的数
            intStr = intStr / hex;
            i++; //数组下标增加一
        }
        //将数组全部反序排列,并返回结果
        for (int j1 = 0, j2 = i - 1; j1 < (i / 2); j1++, j2--) {
            T = result[j1];
            result[j1] = result[j2];
            result[j2] = T;
        }
        for (int j = 0; j < i; j++) {
            resultStr = resultStr + intToLetter(result[j]);
        }
        return resultStr;

    }
    
    
    /**
     * 将取得的数字转换为相应的字母
     * @param num  需要转换的数字
     * @return
     */
    public static String intToLetter(long num) {
        String result = "";
        String S = "";
        if (0 <= num && num <= 9)
            result = String.valueOf(num);
        else if (num > 9) {
            S = String.valueOf(num); //将长整型转换为字符串型
            switch (Integer.parseInt(S)) //将字符串型转换为整型
            {
                case 10:
                    result = "A";
                    break;
                case 11:
                    result = "B";
                    break;
                case 12:
                    result = "C";
                    break;
                case 13:
                    result = "D";
                    break;
                case 14:
                    result = "E";
                    break;
                case 15:
                    result = "F";
                    break;
                case 16:
                    result = "G";
                    break;
                case 17:
                    result = "H";
                    break;
                case 18:
                    result = "I";
                    break;
                case 19:
                    result = "J";
                    break;
                case 20:
                    result = "K";
                    break;
                case 21:
                    result = "L";
                    break;
                case 22:
                    result = "M";
                    break;
                case 23:
                    result = "N";
                    break;
                case 24:
                    result = "O";
                    break;
                case 25:
                    result = "P";
                    break;
                case 26:
                    result = "Q";
                    break;
                case 27:
                    result = "R";
                    break;
                case 28:
                    result = "S";
                    break;
                case 29:
                    result = "T";
                    break;
                case 30:
                    result = "U";
                    break;
                case 31:
                    result = "V";
                    break;
                case 32:
                    result = "W";
                    break;
                case 33:
                    result = "X";
                    break;
                case 34:
                    result = "Y";
                    break;
                case 35:
                    result = "Z";
                    break;
                case 36:
                    result = "a";
                    break;
                case 37:
                    result = "b";
                    break;
                case 38:
                    result = "c";
                    break;
                case 39:
                    result = "d";
                    break;
                case 40:
                    result = "e";
                    break;
                case 41:
                    result = "f";
                    break;
                case 42:
                    result = "g";
                    break;
                case 43:
                    result = "h";
                    break;
                case 44:
                    result = "i";
                    break;
                case 45:
                    result = "j";
                    break;
                case 46:
                    result = "k";
                    break;
                case 47:
                    result = "l";
                    break;
                case 48:
                    result = "m";
                    break;
                case 49:
                    result = "n";
                    break;
                case 50:
                    result = "o";
                    break;
                case 51:
                    result = "p";
                    break;
                case 52:
                    result = "q";
                    break;
                case 53:
                    result = "r";
                    break;
                case 54:
                    result = "s";
                    break;
                case 55:
                    result = "t";
                    break;
                case 56:
                    result = "u";
                    break;
                case 57:
                    result = "v";
                    break;
                case 58:
                    result = "w";
                    break;
                case 59:
                    result = "x";
                    break;
                case 60:
                    result = "y";
                    break;
                case 61:
                    result = "z";
            }
        }
        return result;
    }

    /**
     * 返回一个浮点数的整数或者是小数部份
     * @param doubleNum 需要转换的数字
     * @param whichPart left：整数部分  right：小数部分
     * @return
     * @throws Exception
     */
    public static String returnWantPart(String doubleNum, String whichPart) throws Exception {
        String strNum = doubleNum;
        String leftStr;
        String rightStr;
        //如果输入的是一个浮点数
        if (strNum.indexOf(".") != -1) {
            StringTokenizer fenxi = new StringTokenizer(strNum, ".");
            leftStr = fenxi.nextToken();
            rightStr = fenxi.nextToken();
        } else { //如果输入的是一个整数
            leftStr = strNum;
            rightStr = "0";
        }
        String returnStr = "";
        if (whichPart.equals("left"))
            returnStr = leftStr;
        else if (whichPart.equals("right"))
            returnStr = rightStr;
        return returnStr;
    }

}