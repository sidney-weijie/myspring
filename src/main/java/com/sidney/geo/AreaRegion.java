package com.sidney.geo;

import java.util.List;

public class AreaRegion {
	
	private String id;
	private String regionCode;
	private String level;
	private List<Point> pointList;
	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Point> getPointList() {
		return pointList;
	}
	public void setPointList(List<Point> pointList) {
		this.pointList = pointList;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
