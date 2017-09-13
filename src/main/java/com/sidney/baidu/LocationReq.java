package com.sidney.baidu;

import java.io.Serializable;

public class LocationReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String flowNo;
	private String latitude;
	private String longitude;
	private String coordtype;
	private String pois;
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCoordtype() {
		return coordtype;
	}
	public void setCoordtype(String coordtype) {
		this.coordtype = coordtype;
	}
	public String getPois() {
		return pois;
	}
	public void setPois(String pois) {
		this.pois = pois;
	}
	

}
