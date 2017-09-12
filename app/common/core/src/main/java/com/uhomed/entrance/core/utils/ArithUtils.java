/**
 * created liming.lm 2012年9月16日
 */
package com.uhomed.entrance.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 高精度计算（适用于金钱类型）
 * @author liming.lm
 * @version $Id:ArithUtils.java,v 0.1 2012年9月16日 下午4:30:45 liming.lm Exp $
 */
public class ArithUtils {

	//默认除法运算精度 
    private static final int DEF_DIV_SCALE = 10; 
    
    /** 
     * 高精确的加法运算。 
     * @param v1 被加数 
     * @param v2 加数 
     * @return 两个参数的和 
     */ 
    public static double add(double v1,double v2){ 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.add(b2).doubleValue(); 
    } 
    
    /**
     * 高精度加法计算
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal add(BigDecimal b1,BigDecimal b2){
    	return b1.add(b2);
    }
    
    /** 
     * 高精确的减法运算。 
     * @param v1 被减数 
     * @param v2 减数 
     * @return 两个参数的差 
     */ 
    public static double sub(double v1,double v2){ 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.subtract(b2).doubleValue(); 
    }  
    
    /**
     * 高精度减法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal sub(BigDecimal b1,BigDecimal b2){
    	return b1.subtract(b2);
    }
    
    /** 
     * 高精确的乘法运算。 
     * @param v1 被乘数 
     * @param v2 乘数 
     * @return 两个参数的积 
     */ 
    public static double mul(double v1,double v2){ 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.multiply(b2).doubleValue(); 
    }
    
    /** 
     * 高精确的乘法运算。 
     * @param v1 被乘数 
     * @param v2 乘数 
     * @return 两个参数的积 
     */ 
    public static BigDecimal mul(BigDecimal b1,BigDecimal b2){
    	return b1.multiply(b2);
    }
    
    /** 
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 
     * 小数点以后10位，以后的数字四舍五入。 
     * @param v1 被除数 
     * @param v2 除数 
     * @return 两个参数的商 
     */ 
    public static double div(double v1,double v2){ 
        return div(v1,v2,DEF_DIV_SCALE); 
    } 
    
    /**
     * 高精度除法（默认4位小数）
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal div(BigDecimal b1,BigDecimal b2){
    	return b1.divide(b2,BigDecimal.ROUND_HALF_UP);
    }
    
    /** 
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 
     * 定精度，以后的数字四舍五入。 
     * @param v1 被除数 
     * @param v2 除数 
     * @param scale 表示表示需要精确到小数点以后几位。 
     * @return 两个参数的商 
     */ 
    public static double div(double v1,double v2,int scale){ 
        if(scale<0){ 
            throw new IllegalArgumentException( 
                "The scale must be a positive integer or zero"); 
        } 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    } 
 
    /** 
     * 提供精确的小数位四舍五入处理。 
     * @param v 需要四舍五入的数字 
     * @param scale 小数点后保留几位 
     * @return 四舍五入后的结果 
     */ 
    public static double round(double v,int scale){ 
        if(scale<0){ 
            throw new IllegalArgumentException( 
                "The scale must be a positive integer or zero"); 
        } 
        BigDecimal b = new BigDecimal(Double.toString(v)); 
        BigDecimal one = new BigDecimal("1"); 
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    }
    
    public static BigDecimal round(BigDecimal b){
    	return b.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    public static String formatAmount(BigDecimal b,String parent){
    	DecimalFormat df = new DecimalFormat(parent);
    	return df.format(b);
    }
    
    
}
