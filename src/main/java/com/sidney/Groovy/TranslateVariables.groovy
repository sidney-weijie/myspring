import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.HashMap;


/**
 * @author zengweijie
 *
 */
public class TranslateVariables{
	
	public Map map ;
	
	Map verify(Map map){
	
		telcomOnlineTime(map);
		cmccOnlineTime(map);
		unicomOnlineTime(map);
		return map;
	}
	
	
	Map custRegister(Map map){
		
		unicomOnlineTime(map);
		return map;
	}
	
	
	/**
	 * 电信在网时长
	 *
	 */
	void telcomOnlineTime(Map map){
		/**
		 *	电信在网时长 telcomOnlineTim
		 *    inTimeTc
		 */
		def telcomOnlineTime = map.get("inTimeTc");
		if(isStringNotBlank(telcomOnlineTime)){
			if( '2'.equals(telcomOnlineTime)||'3'.equals(telcomOnlineTime) || '4'.equals(telcomOnlineTime) || '5'.equals(telcomOnlineTime) || '6'.equals(telcomOnlineTime) ||'7'.equals(telcomOnlineTime)||'8'.equals(telcomOnlineTime)){
				map.put("telcomOnlineTime",'1');
			}else{
				map.put("telcomOnlineTime",'0');
			}
		}
	}
	
	
	/**
	 *联通在网时长判断字段  unicomOnlineTime   mobileNoInUseTimeLt
	 *	新增“移动在网时长判断结果“字段，对值为1/2/3的，转换为0，表示判断不通过（在网时长小于3个月）；其余值转为1，表示判断通过。
	 */
	
	void unicomOnlineTime(Map map){
	
		def unicomOnlineTime = map.get("mobileNoInUseTimeLt");
		if(isStringNotBlank(unicomOnlineTime)){
			if( '3'.equals(unicomOnlineTime) || '4'.equals(unicomOnlineTime) || '5'.equals(unicomOnlineTime) || '6'.equals(unicomOnlineTime) || '7'.equals(unicomOnlineTime)){
				map.put("unicomOnlineTime",'1');
			}else{
				map.put("unicomOnlineTime",'0');
			}
		}
	}
	/**
	 *移动在网时长判断字段  cmccOnlineTime
	 *新增“移动在网时长判断结果“字段，对值为1/2/3的，转换为0，表示判断不通过（在网时长小于3个月）；其余值转为1，表示判断通过。
	 */
	void cmccOnlineTime(Map map){
		
		def cmccOnlineTime = map.get("mobileInnetPeriodSjs");
		if(isStringNotBlank(cmccOnlineTime)){
			if( '4'.equals(cmccOnlineTime) || '5'.equals(cmccOnlineTime) || '6'.equals(cmccOnlineTime) || '7'.equals(cmccOnlineTime)){
				map.put("cmccOnlineTime",'1');
			}else{
				map.put("cmccOnlineTime",'0');
			}
		}
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
	
	static boolean isBlank(String str){
		if( null == str) {return true;}
		if(str.length == 0 ) {return true;}
		
		return false; 
	}
	
}
