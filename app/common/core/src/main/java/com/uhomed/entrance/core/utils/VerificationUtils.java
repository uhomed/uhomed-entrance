/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.uhomed.entrance.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 验证方法
 * @author liming
 * @version $Id: VerificationUtils.java, v 0.1 2015年2月27日 上午11:34:49 liming Exp $
 */
public class VerificationUtils {
	
	/** 邮件校验 */
	private static String EMAIL_PATTERN = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	/** 电话带区号 */
	private static String TEL_AREA_PATTERN = "^[0][1-9]{2,3}-[0-9]{5,10}$";
	/** 电话不带区号 */
	private static String TEL_NO_AREA_PATTERN = "^[1-9]{1}[0-9]{5,8}$";
	/** 手机号校验 */
	private static String PHONE_PATTERN = "^[1][3,4,5,8,7][0-9]{9}$";
	/** 验证数字 */
	private static String NUMBER_PATTERN = "^[0-9]*$";
	
	
	/**
	 * 校验是否存在到此范围
	 * @param lowerLimit
	 * @param limit
	 * @param value
	 * @return
	 */
	public static boolean verificationRange(Integer lowerLimit,Integer limit,Integer value){
		if(lowerLimit == null || limit == null || value == null){
			return false;
		}
		if(value > lowerLimit && value < limit){
			return true;
		}
		return false;
	}

//	/**
//	 * 验证身份证号
//	 * @param numberId
//	 * @return
//	 */
//	public static boolean verificationNumberId(String numberId){
//		if(StringUtils.isEmpty(numberId)){
//			return false;
//		}
//		VerifyIdCard vf = new VerifyIdCard();
//		return vf.verify(numberId);
//	}
	
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static boolean verificationEmail(String email){
		if(StringUtils.isEmpty(email)){
			return false;
		}
		Pattern p1 = null;
		Matcher m = null;
		p1 = Pattern.compile(EMAIL_PATTERN);
		m = p1.matcher(email);
		return m.matches();
	}
	
	/**
	 * 校验纯数字
	 * @param str
	 * @return
	 */
	public static boolean verificationNumber(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p1 = Pattern.compile(NUMBER_PATTERN);
		Matcher m = p1.matcher(str);
		return m.matches();
	}
	
	/** 
     * 电话号码验证 
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean verificationPhone(String str) {  
    	if(StringUtils.isEmpty(str)){
			return false;
		}
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile(TEL_AREA_PATTERN);  // 验证带区号的  
        p2 = Pattern.compile(TEL_NO_AREA_PATTERN);         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }
    
	/** 
     * 手机号验证 
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean verificationMobile(String str) {   
    	if(StringUtils.isEmpty(str)){
			return false;
		}
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile(PHONE_PATTERN); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
	
}

///**
// * 校验身份证号
// * @author liming
// * @version $Id: VerificationUtils.java, v 0.1 2015年2月27日 上午11:46:18 liming Exp $
// */
//class VerifyIdCard {  
//    // wi =2(n-1)(mod 11);加权因子  
//    final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };  
//    // 校验码  
//    final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };  
//    private int[] ai = new int[18];  
//  
//    public VerifyIdCard() {  
//    }  
//  
//    // 校验身份证的校验码  
//    public boolean verify(String idcard) {  
//        if (idcard.length() == 15) {  
//            idcard = uptoeighteen(idcard);  
//        }  
//        if (idcard.length() != 18) {  
//            return false;  
//        }  
//        String verify = idcard.substring(17, 18);  
//        if (verify.equals(getVerify(idcard))) {  
//            return true;  
//        }  
//        return false;  
//    }  
//  
//    // 15位转18位  
//    public String uptoeighteen(String fifteen) {  
//        StringBuffer eighteen = new StringBuffer(fifteen);  
//        eighteen = eighteen.insert(6, "19");  
//        return eighteen.toString();  
//    }  
//  
//    // 计算最后一位校验值  
//    public String getVerify(String eighteen) {  
//        int remain = 0;  
//        if (eighteen.length() == 18) {  
//            eighteen = eighteen.substring(0, 17);  
//        }  
//        if (eighteen.length() == 17) {  
//            int sum = 0;  
//            for (int i = 0; i < 17; i++) {  
//                String k = eighteen.substring(i, i + 1);  
//                ai[i] = Integer.valueOf(k);  
//            }  
//            for (int i = 0; i < 17; i++) {  
//                sum += wi[i] * ai[i];  
//            }  
//            remain = sum % 11;  
//        }  
//        return remain == 2 ? "X" : String.valueOf(vi[remain]);  
//  
//    }  
//}  


