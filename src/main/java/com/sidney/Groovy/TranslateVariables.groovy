/**
 * 
 */
package com.sidney.Groovy
import com.alibaba.fastjson.JSONObject;
import java.util.List;import java.util.HashMap;

/**
 * @author zengweijie
 *
 */
public class TranslateVariables{
	
	public groupList =[ 
	                    ['AAA','aaa','bbb','ccc'],
	                    ['BBB','ddd','eee','fff'],
	                    ['realName','xxx','yyy','zzz']
					  ];
	
	public valueList = [
	                    [
	                      ['0','0','0'],
	                      ['0','0','1'],
	                      ['0','1','0'],
	                      ['0','1','1'],
	                      ['1','0','0'],
	                      ['1','0','1'],
	                      ['1','1','0'],
	                      ['1','1','1']
	                     ],
	                     [
	                      ['0','0','0'],
	                      ['0','0','1'],
	                      ['0','1','0'],
	                      ['0','1','1'],
	                      ['1','0','0'],
	                      ['1','0','1'],
	                      ['1','1','0'],
	                      ['1','1','1']
	                     ],
	                     [
	                      ['0','0','0'],
	                      ['0','0','1'],
	                      ['0','1','0'],
	                      ['0','1','1'],
	                      ['1','0','0'],
	                      ['1','0','1'],
	                      ['1','1','0'],
	                      ['1','1','1']
	                      ]                  
	                     ];
	public targetValue = [
	                      [
	                       '00','01','02','03','04','05','06','07'
	                      ],
	                      
	                      [
	                       	'00','01','02','03','04','05','06','07'
	                       ],
	                       
	                       [
	                        '00','01','02','03','04','05','06','07'
	                       ]
	                      ];
	public Map map ;
	
	TranslateVariables(){
		map = new HashMap<String,String>();
		for(int i = 0; i < groupList.size();i++){
			 def valueGroupListTmp = groupList.getAt(i);
			 def valueListTmp = valueList.getAt(i);
			 for(int j = 0; j < valueListTmp.size();j ++){
				 map.put(valueGroupListTmp.toString()+valueListTmp.getAt(j).toString(),targetValue.getAt(i).getAt(j)); 
			 }
		}
		println map.toMapString();
	}
	JSONObject verify(JSONObject jobj){
		computeVariable(jobj);
		return jobj;
	}
	
	JSONObject custRegister(JSONObject jobj){
		/*computeVariable(jobj)*/
		if(jobj.get("aaa") == '0'){
			jobj.put("TTT","TT");
		}else{
			jobj.put("TTT","ABC");
		}
		
		return jobj;
	}
	
	
	
	void computeVariable(JSONObject jobj){
		for(int i = 0; i < groupList.size();i++){
			 def tmpList = [];
			 def groupListTmp = groupList.getAt(i);
			 for( int j = 1; j <groupListTmp.size();j ++ ){
				 def sourceKey =groupListTmp.getAt(j) ;
				 def sourceValue = jobj.get(sourceKey);
				 if( sourceValue == null ){
					 tmpList.add('');
				 }else{
					 tmpList.add(sourceValue);
				 } 
			 }
			 
			 def targetKey = groupListTmp.getAt(0);
			 def mapKey = groupListTmp.toString() + tmpList.toString();
			 def targetValue = map.get(mapKey);
			/* println "targetKey="+targetKey + "  targetValue" +  targetValue + "  mapKey=" + mapKey*/
			 if(null != targetValue){
				 jobj.put(targetKey,targetValue);
			 }
		}
	}
	
	
	/**
	 * @param args
	 */
/*	 static void main(def args){
		 JSONObject jobj = new JSONObject();
		 jobj.put("aaa","0");
		 jobj.put("bbb","1");
		 jobj.put("ccc","1");
		 jobj.put("ddd","0");
		 jobj.put("eee","1");
		 jobj.put("fff","1");
		 TranslateVariables var = new TranslateVariables();
		 println var.map.get("[BBB, ddd, eee, fff][0, 1, 0]");
		 jobj = var.verify(jobj);
		 println jobj.toString();
		
	}*/
	
	
	static boolean isStringListEquals(List a, List b){
		
		if( a != null && b != null  && a.size() == b.size() && a.size() > 0){
			boolean flag = true ;
			(0..a.size()).each{ flag = flag && isStringEquals(a.getAt(it),b.getAt(it))}
			
			return flag;
		}		
		return false;		
	}
	
	 static boolean isStringEquals(String a, String b){
			
		if( isStringNotBlank( a )){
			if(a.equals(b)){
				return true;
			}
		}else if(!isStringNotBlank(b)){
			return true;
		}
		return false ;
	}
	
	static  boolean isStringNotBlank(String str){
		 if( null == str ) return false;
		 if( str.length() == 0 ) return false;
		 return true;
	 }
	
}
