package com.sidney.geo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sidney.util.AbstractWebUtils;
import com.sidney.util.FileUtils;



public class GetPolyLineFromAmap {

	public static void main(String[] args) {
		
		
		
		List<String> areacodeList = FileUtils.loadFromFile("E:\\mytest\\amp_areacode_miss.txt");
		int length = areacodeList.size();
		List<String> resultList = new ArrayList<String>();
		List<String> srcDataList = new ArrayList<String>();
		System.err.println("length = " + length);
		for(int areacodeIndex =0 ;areacodeIndex < length; areacodeIndex++){
		
			String tmp = areacodeList.get(areacodeIndex);
			String[]tmpArray = tmp.split("\\s");
		
		try {
			
			
			String url = "http://restapi.amap.com/v3/config/district?key=297369f43d0fbc3c63206b45baa4c0ba&keywords="+ tmpArray[1] +"&subdistrict=3&extensions=all&filter=" + tmpArray[0];
			
			
			//String url = "http://restapi.amap.com/v3/config/district?key=297369f43d0fbc3c63206b45baa4c0ba&subdistrict=3&extensions=all"+ "&keywords=110000" ;
			
			
			String result = AbstractWebUtils.doGet(url, new HashMap<String, String>(), 100000, 100000);
			//System.err.println(result);
			srcDataList.add(result);
			JSONObject jobj = null;
			if(StringUtils.isNotBlank(result)){
				
				jobj = JSON.parseObject(result);
				if("1".equals(jobj.getString("status"))){
					
					int count = jobj.getIntValue("count");
					
					String districts  = jobj.getString("districts");
					
					JSONArray districtArray = new JSONArray();
					
					if(StringUtils.isNotBlank(districts)){
						districtArray = JSON.parseArray(districts);
						
						if(districtArray != null){
							for(int j = 0; j < districtArray.size(); j ++){
								JSONObject districtObj = (JSONObject) districtArray.get(j);
								
								
								String name = districtObj.getString("name");
								String level = districtObj.getString("level");
								String adcode = districtObj.getString("adcode");
								String polyLine = districtObj.getString("polyline");
								String []arrayPolyLine = {""};
								if(StringUtils.isNotBlank(polyLine)){
									arrayPolyLine = polyLine.split("\\|");
								}
								
								if(arrayPolyLine.length > 0){
									
									for(String polyLineStr : arrayPolyLine){
										resultList.add( name + "  " + level + "  " + adcode + "  " + polyLineStr);
									}
									
								}
								
								//System.err.println(districtObj.get("name").toString() +"  " +districtObj.get("level") +"  " +districtObj.get("adcode") +"  " + districtObj.get("polyline"));
								
							}
						}
						
					}
					
				}else{
					System.err.println("error" + "  " + tmpArray[1] + "  " + tmpArray[0] );
				}
				
			}
			
			Thread.sleep(50);
			//System.err.println(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		}
		
		FileUtils.writeLineToFile("E:\\china\\polyLine-miss.data", resultList);
		FileUtils.writeLineToFile("E:\\china\\srcData-miss.data", srcDataList);
	}

}
