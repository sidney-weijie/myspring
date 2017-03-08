/**
 * 
 */
package com.sidney.Groovy
import com.alibaba.fastjson.JSONObject


/**
 * @author zengweijie
 *
 */
public class TranslateVariables{
	
	JSONObject verify(JSONObject jobj){
		
	}
	
	/**
	 * @param args
	 */
	 static void main(def args){
		 JSONObject jobj = new JSONObject();
		 jobj.put("abc","def");
		 
		 TranslateVariables var = new TranslateVariables()
		 println isGetStringEquals(jobj,"def","")
	}
	
	static boolean isGetStringEquals(JSONObject jobj, String key, String value){
		def str = jobj.get(key)
		
		if( isStringNotBlank( str )){
			if(str.equals(value)){
				return true;
			}
		}else if(!isStringNotBlank(value)){
			return true;
		}
		return false 
	}
	
	static  boolean isStringNotBlank(String str){
		 if( null == str ) return false
		 if( str.length() == 0 ) return false
		 return true;
	 }
	
}
