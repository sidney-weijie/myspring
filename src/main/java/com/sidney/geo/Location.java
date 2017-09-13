package com.sidney.geo;

import java.io.Serializable;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lng;
	private String lat;
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
}
