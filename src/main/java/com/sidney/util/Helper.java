package com.sidney.util;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Helper {
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	
	public static boolean isNumeric(String str){
		
		boolean result = false;
		if(StringUtils.isNotBlank(str)){
			result = true;
			int strSize = str.length();
			for(int i = 0; i < strSize; i ++ ){
				if(!Character.isDigit(str.charAt(i))){
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
