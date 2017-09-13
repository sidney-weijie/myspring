package com.sidney.baidu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sidney.geo.ChinaInside;
import com.sidney.util.AbstractWebUtils;

public class BaiduMap {

	private static final String URL = "http://api.map.baidu.com/geocoder/v2/";
	private static final String KEY = "ABig6HUXCIDqW28m4GZqde3UznhQjNwZ";
	private static final String JSON_FORMATTER= "json";
	
	private static final String CHARSET_UTF8 = "UTF-8";
	
	public AddressResp doGetAddress(LocationReq request) throws IOException{
		
		AddressResp resp = new AddressResp();
		Map<String,String> paramMap = new HashMap<String, String>();
		 paramMap.put("ak", KEY);
	        paramMap.put("location",request.getLatitude() + "," + request.getLongitude());
	        paramMap.put("output", JSON_FORMATTER);
	        paramMap.put("pois", "0");
	        paramMap.put("coordtype", request.getCoordtype());
	        String resultJson = AbstractWebUtils.doGet(URL, paramMap,CHARSET_UTF8, 10000, 10000);
	     if(StringUtils.isNotBlank(resultJson)){
	    	 resp = JSON.parseObject(resultJson,AddressResp.class);
	     }
	     
	     return resp;
	}
	
	
	public static void main(String[]args) throws FileNotFoundException{
		
		BaiduMap baiduMap = new BaiduMap();
		
	
		File file = new File("E:\\mytest\\lat_lng_test_mainLand_10w.data");
		ChinaInside chinaInside = new ChinaInside();
		PrintWriter out = new PrintWriter(file);
		Random random = new Random(System.currentTimeMillis());
		int count = 0;
		for( ; count<100000; ){
			
			double lat =  Math.abs(random.nextInt() % 34) + 20 + random.nextDouble();
			double lng = Math.abs(random.nextInt() % 72) + 65  + random.nextDouble();
			
			if(!chinaInside.isInChinaMainLand(lng, lat)){
				//System.err.println(lat + "," + lng + " out of china");
				//System.err.println(lat + "," + lng);
				continue;
			}
			
			count++;
			try {
				
				LocationReq request = new LocationReq();
				request.setCoordtype("bd09ll");
				request.setLatitude(lat+"");
				request.setLongitude(lng + "");
				
				
				AddressResp resp = baiduMap.doGetAddress(request);
				
				if(null != resp && "0".equals(resp.getStatus())){
					
					if(resp.getResult() != null){
						
						AddressComponent addressComment = resp.getResult().getAddressComponent();
						if(null != addressComment){
						
							if("中国".equals(addressComment.getCountry())){
								//System.err.println(JSON.toJSONString(addressComment));
								addressComment.getDistrict();
								addressComment.getCity();
								addressComment.getProvince();
								
								String tmp = request.getLatitude() + " " + request.getLongitude() +" "+addressComment.getProvince() +" " + addressComment.getCity() + " "+ addressComment.getDistrict();
								
								
								out.println(tmp);

                                 if(count % 10  == 0){
									out.flush();
								}
                                 
                                 if(count%1000 == 0){
                                	 System.err.println(new SimpleDateFormat("YY-MM-dd HH:mm:ss").format(new Date()) + "  " + count);
                                 }
								
							}
						}
					}
				}
				
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.close();
		
	}
	
    public static boolean outOfChina(double lat, double lon) {  
        if (lon < 72.004 || lon > 137.8347)  
            return true;  
        if (lat < 0.8293 || lat > 55.8271)  
            return true;  
        return false;  
    }  
    
   
	
}
