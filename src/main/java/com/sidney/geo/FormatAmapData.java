package com.sidney.geo;

import java.util.ArrayList;
import java.util.List;

import com.sidney.util.FileUtils;

public class FormatAmapData {

	public static void main(String[] args) {
		List<String> srcList = FileUtils.loadFromFile("E:\\china\\AmapData.txt");
		
		List<String> dstList = new ArrayList<String>();
		int size = srcList.size();
		for(int i = 0; i<srcList.size();i++){
			String tmp = srcList.get(i);
			
			String[]array = tmp.split("  ");
			
			String [] points = array[3].split(";");
			
			
			double minX = Double.MAX_VALUE;
			double minY = Double.MAX_VALUE;
			double maxX = Double.MIN_VALUE;
			double maxY = Double.MIN_VALUE;
/*			System.err.println(array[0]);
			System.err.println(array[1]);
			System.err.println(array[2]);
			System.err.println(array[3]);*/
			for(String str:points){
				String []pointsArray = str.split(",");
				//System.err.println(str);
				double x = Double.parseDouble(pointsArray[1]);
				double y = Double.parseDouble(pointsArray[0]);
				
				if( minX > x ){
					minX = x;
				}
				if(minY > y ){
					minY = y;
				}
				if(maxX < x ){
					maxX = x;
				}
				if(maxY < y ){
					maxY = y;
				}
				
				
			}
			
			
			String result = "#" +" " + array[2] + " "+ array[0] + " "+ array[1] + " "+ minX + " " + minY + " " + maxX + " "+ maxY + " " + array[3];
			
			dstList.add(result);
		}
		
		FileUtils.writeLineToFile("E:\\china\\amapResult.txt", dstList);
		

	}
	
	
	
}
