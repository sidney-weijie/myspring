package com.sidney.geo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;









import javax.swing.plaf.synth.Region;

import com.alibaba.fastjson.JSON;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class ZwjTest {
	
	
	public final static String TEST_FILE_PATH = "E:\\mytest\\lat_lng_test_mainLand_10w.data";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		teatLatLng();
		HashMap<String, String> abc;
	}

	
	 
	    public static void teatLatLng() {
	    	  RTree<String, Rectangle> tree = RTree.star().create();
	    	  Map<String,AreaCode> map = getAreaCode();
	    	  
	    	  Map<String,AreaRegion> regionMap = new HashMap<String, AreaRegion>();
	    	String filePath = "E:\\mytest\\amapResult.txt";
	    	int lineNum = 0;
	    	int num = 1;
	        try {
	            BufferedReader input =  new BufferedReader(new FileReader(filePath));
	            String line;
	            String[] lineSplit;

				while ((line = input.readLine()) != null) {
					lineNum++;
					
					if(!line.startsWith("#")){
						continue;
					}
									
	                lineSplit = line.split("\\s");
	               /* if(!"3".equals(lineSplit[3])&&!"2".equals(lineSplit[3])){
	                	continue;
	                }*/
	                
	               
	                AreaRegion region = new AreaRegion();
	                try {
	                	
	                	
	              
	                double minLat = Double.parseDouble(lineSplit[4]);
	                double minLng =  Double.parseDouble(lineSplit[5]);
	                double maxLat = Double.parseDouble(lineSplit[6]);
	                double maxLng = Double.parseDouble(lineSplit[7]);
	               
	                
	                String id = "" + lineNum;
	                Rectangle rect = Geometries.rectangle(minLat, minLng, maxLat, maxLng);	                
	                        String areaCode= lineSplit[1];	  
	                        
	                        region.setRegionCode(areaCode);
	                       
	                        region.setLevel(lineSplit[3]);
		                    region.setId(id);
	                        
	                        tree = tree.add(id, rect);		                        	                      
	                    } catch (Exception e) {
	                      e.printStackTrace();
	                      System.err.println("exception1");
	                        break;
	                    }
	                    catch (AssertionError error){
	                      error.printStackTrace();
	                      System.err.println("exception2"); 
	                      
	                      break;
	                    }
	                	                	                	                     
	                region.setPointList(parsePolyGon(lineSplit[8]));
	                
	                regionMap.put(region.getId(), region);
	      
	                
	                
	             }

	           input.close();
	           //tree.visualize(3000, 3000).save("E:\\china\\tree.png", "PNG");
	           System.err.println("size=" + tree.size());
	           System.err.println("depth=" + tree.calculateDepth());
	           
	           List<AreaCode> list = getInfoFromFile1();
	           
	           
	           int cityEqual = 0;
	           int countEqual = 0;
	           int provinceEquals = 0;
	           
	           Entry<String,Rectangle> entry = null;
	           long start;
	           List<String> timeusedList = new ArrayList<String>();
	           
	           List<String> allResult = new ArrayList<String>();
	           boolean isCityLevelInside = false;
	           for(AreaCode code :list){
	        	   
	        	   
	        	   
	        	   LngLat lnglat = CoodinateCovertor.bd_decrypt(new LngLat(code.getLng(),code.getLat()));
	        	   
	        	   
	        	   
	        	   boolean isProvEquals;
	        	   boolean isCityEquals;
	        	   boolean isCountyEquals;
	        	 
	        	   allResult.add("***********************************");
	        	   
	        	   allResult.add(JSON.toJSONString(code));
	        	   
	        	   start = System.currentTimeMillis();
	        	   boolean flag = false;
	        	   Iterable<Entry<String, Rectangle>> it = tree.search(Geometries.point(lnglat.getLantitude(),lnglat.getLongitude())).toBlocking().toIterable();;
	               Iterator<Entry<String, Rectangle>> iterator = it.iterator();
	               
		           while(iterator.hasNext()){
		        	   entry = iterator.next();
		        	   allResult.add(entry.value() +"  " + entry.geometry().toString());
		        	   
		        	   
		        	  AreaRegion region =  regionMap.get(entry.value());
		        	 
		        	  boolean isInSide = GpsUtils.isPointsInPolygon(code.getLng(), code.getLat(), region.getPointList());
		        	 
		        	  
		        	  
		        	  AreaCode resultAreaCode = map.get(region.getRegionCode());
	        		  
	        		  String areaFullName = "";
	        		  if(null != resultAreaCode ){
	        			  areaFullName = resultAreaCode.getAreaFullName();
	        		  }
		        	  
	        		  String tmp = "";
		        	  
		        	  if(isInSide ){
		        		  
		        		  if("district".equals(region.getLevel())){
		        			  flag = true;	  
		        		  }
		        		  
		        		  if(resultAreaCode != null){
		        			  		        		
			        		  if(code.getProv().equals(resultAreaCode.getProv())){
			        			  isProvEquals = true;
			        			  provinceEquals++;
			        		  }
			        		  
			        		  if(code.getCity().equals(resultAreaCode.getCity())){
			        			  isCityEquals = true;
			        			  cityEqual++;
			        		  }
			        		  if(code.getDistrict().equals(resultAreaCode.getDistrict())){
			        			  isCountyEquals = true;
			        			  countEqual++;
			        		  }		        		  
		        		  }
		        		  tmp += "true  "; 
		        		
		        		  
		        	  }
		        	  
		        	  
		        	 
		        	  tmp += code.getLat()+","+ code.getLng()+" "+ region.getRegionCode()+ " " +areaFullName;
		        		//  System.err.println(isInSide + "  " + tmp);
		        	  allResult.add(tmp);  
		        	  
		        	 
		           }
		           
		        	  if(!flag){
			        	   
			        	   String tmp = code.getLat()+","+ code.getLng()+" " + code.getProv() + code.getCity()+ code.getDistrict() + "    not match";
			        	   
			        	   allResult.add(tmp);
			           }
		           timeusedList.add((System.currentTimeMillis() - start)+"");
		          
		        	   		        	   		           
		        //   allResult.add("result=" + flag);		           	          
		           allResult.add("");	          
	        	   
	           }
	           
	          
	           
	           System.err.println("provinceEquals=" + provinceEquals +"  cityEqual=" + cityEqual + "  countEqual=" + countEqual );
	           
	           com.sidney.util.FileUtils.writeLineToFile("E:\\mytest\\searchTimeBtree.txt", timeusedList);
	           com.sidney.util.FileUtils.writeLineToFile("E:\\mytest\\searchResult.txt",allResult);
	                        
	        }
			catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error while reading input file. Line " + lineNum + " Skipped\nError Details:");
			}
	    	
	    	
	    }
	    
	    
	    public static List<Point> parsePolyGon(String str){
	    	List<Point> list = new ArrayList<Point>();
	    	String  []pointsArray = str.split(";");
	    	int pointsNum = pointsArray.length;
	    	for(int i = 0; i < pointsNum; i++) {
	    		
	    		String []point = pointsArray[i].split(",");
	    		
	    		Point p = new Point();
	    		
	    		p.setLat(Double.parseDouble(point[1]));
	    		p.setLng(Double.parseDouble(point[0]));
	    		list.add(p);
	    		
	    	}
	    	
	    	return list;
	    	
	    }
	    
	    
	    
	    public static List<AreaCode> getInfoFromFile1(){
	    	
	    	List<AreaCode> resuleList = new ArrayList<AreaCode>();
	    	List<String> list = new ArrayList<String>();
	    	 BufferedReader input;
			try {
				input = new BufferedReader(new FileReader(TEST_FILE_PATH));
		
	            String line;
	            String[] lineSplit;
	            int lineNum = 0;
	            String areaCode = null;
	            AreaCode code = null;
				try {
					while ((line = input.readLine()) != null){
						lineSplit = line.split("\\s");
						areaCode = lineSplit[2];
						
						double lat = Double.parseDouble(lineSplit[0]);
						double lng = Double.parseDouble(lineSplit[1]);
						
						code = new AreaCode();
						code.setLat(lat);
						code.setLng(lng);
						code.setProv(lineSplit[2]);
						code.setCity(lineSplit[3]);
						code.setDistrict(lineSplit[4]);
						resuleList.add(code);
					}
					input.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	return resuleList;
	    	
	    }
	    
	    public static List<AreaCode> getInfoFromFile(){
	    	
	    	List<AreaCode> resuleList = new ArrayList<AreaCode>();
	    	List<String> list = new ArrayList<String>();
	    	 BufferedReader input;
			try {
				input = new BufferedReader(new FileReader("E:\\mytest\\quhuaWithLatLng.txt"));
		
	            String line;
	            String[] lineSplit;
	            int lineNum = 0;
	            String areaCode = null;
	            AreaCode code = null;
				try {
					while ((line = input.readLine()) != null){
						lineSplit = line.split("\\s");
						areaCode = lineSplit[2];
						
						double lat = Double.parseDouble(lineSplit[0]);
						double lng = Double.parseDouble(lineSplit[1]);
						
						code = new AreaCode(areaCode,lng,lat);
						resuleList.add(code);
					}
					input.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	return resuleList;
	    	
	    }
	    
	    
	    public static Map<String,AreaCode> getAreaCode(){
	    	
	    	List<String> areacodeList = com.sidney.util.FileUtils.loadFromFile("E:\\mytest\\quhua.txt");
	    
	    
	    Map<String,AreaCode> map = new TreeMap<String, AreaCode>();
		for(int i = 0; i< areacodeList.size();i++){
			String str = areacodeList.get(i);
			
			String []strArray = str.split("\\s");
			
			AreaCode areaCode = new AreaCode();
			
			areaCode.setCode(strArray[0]);
			if(strArray.length > 1){
				areaCode.setProv(strArray[1]);
			}
			if(strArray.length > 2){
				areaCode.setCity(strArray[2]);
			}
			if(strArray.length > 3){
				areaCode.setDistrict(strArray[3]);
			}
			
			
			String code = strArray[0];
			
			String addr = str.replace(code, "").replace("\t", "");
			
			//System.err.println(addr);
			
			
			map.put(code, areaCode);
			
		}
	    return map;
	    }
	  
	    
	    public static class AreaCode{
	    	private double lng;
	    	private double lat;
	    	private String areaCode;
	    	private String code;
	    	private String prov;
	    	private String city;
	    	private String district;
	    	public AreaCode(){
	    		
	    	}
	    	public AreaCode(String areaCode,double lng,double lat){
	    		this.areaCode = areaCode;
	    		this.lat = lat;
	    		this.lng = lng;
	    	}
	    	
			public double getLng() {
				return lng;
			}
			public void setLng(double lng) {
				this.lng = lng;
			}
			public double getLat() {
				return lat;
			}
			public void setLat(double lat) {
				this.lat = lat;
			}
			public String getAreaCode() {
				return areaCode;
			}
			public void setAreaCode(String areaCode) {
				this.areaCode = areaCode;
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public String getProv() {
				return prov;
			}
			public void setProv(String prov) {
				this.prov = prov;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getDistrict() {
				return district;
			}
			public void setDistrict(String district) {
				this.district = district;
			}
			
			
			public String getAreaFullName(){
				String result = "";
				if(prov != null){
					result += prov;
				}
				if(city != null){
					result += city;
				}
				if(district != null){
					result += district;
				}
				
				return result;
			}
			
	    	
	    }
	    
	    

}
