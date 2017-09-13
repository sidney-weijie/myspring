package com.sidney.geo;

import java.util.ArrayList;
import java.util.List;

public class ChinaInside {
	private List<Point> chinaAreaLine;
	
	public static List<String> latlngList; 
	static{
		latlngList = new ArrayList<String>();
		
		latlngList.add("21.8003080510,110.3027343750");
		latlngList.add("22.3094258412,113.5107421875");
		latlngList.add("23.1807635831,113.0493164063");
		latlngList.add("22.8976832106,114.2578125000");
		latlngList.add("25.7009378814,119.2456054688");
		latlngList.add("29.8025179058,120.1684570313");
		latlngList.add("30.9587685708,121.6625976563");
		latlngList.add("35.5501053359,118.1469726563");
		latlngList.add("39.2832938689,117.0922851563");
		latlngList.add("43.2131833007,130.4736328125");
		latlngList.add("49.1242192486,128.0566406250");
		latlngList.add("52.6430634367,123.5961914063");
		latlngList.add("46.6343507029,120.4760742188");
		latlngList.add("44.4808302786,113.0932617188");
		latlngList.add("41.4427263777,108.4350585938");
		latlngList.add("42.1634034242,95.3613281250");
		latlngList.add("47.1598400130,86.9238281250");
		latlngList.add("39.3173003733,75.6298828125");
		latlngList.add("29.7834494568,83.8696289063");
		latlngList.add("28.6327467992,97.2949218750");
		latlngList.add("28.2850332946,98.5693359375");
		latlngList.add("23.3220800114,99.5141601563");
		latlngList.add("22.3297523044,101.3378906250");
		latlngList.add("23.4632463316,105.6665039063");
		latlngList.add("22.8774404649,107.0727539063");
		latlngList.add("21.9022779667,108.2153320313");

	}
	
	public ChinaInside(){
		chinaAreaLine = new ArrayList<Point>();
		int size = latlngList.size();
		for(int i = 0; i < size;i ++){
			String tmpStr = latlngList.get(i);
			String []array = tmpStr.split(",");
			Point p = new Point(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
			
			chinaAreaLine.add(p);
		}
	}
	
	public boolean isInChinaMainLand(double lng,double lat){
		return GpsUtils.isPointsInPolygon(lng, lat, chinaAreaLine);
	}
	
	
	public static void main(String []args){
		ChinaInside inside = new ChinaInside();
		
		System.err.println(inside.isInChinaMainLand(121.34938013331576,40.559806621799204));
	}
	
}
