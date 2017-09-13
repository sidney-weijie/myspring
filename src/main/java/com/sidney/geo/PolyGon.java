package com.sidney.geo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PolyGon {
	private List<Point> pointList;
	private String id;
	private String areaCode;
	
	
	public List<Point> getPointList() {
		return pointList;
	}
	public void setPointList(List<Point> pointList) {
		this.pointList = pointList;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public PolyGon(){
		
	}
	
	
    public PolyGon(String id,String areaCode, String points){
		this.id = id;
		this.areaCode = areaCode;
		pointList = new ArrayList<Point>();
		if(StringUtils.isNotBlank(points)){
			
			String [] pointsArray = points.split(";");
			for(String str:pointsArray){
				
				if(StringUtils.isNotBlank(str)){
					String []pArray = str.split(",");
					Point p = new Point(Double.parseDouble(pArray[0]), Double.parseDouble(pArray[1]));
					pointList.add(p);
				}
				
			}
		}
	}
	
}
