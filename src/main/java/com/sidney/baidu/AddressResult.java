package com.sidney.baidu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidney.geo.Location;

public class AddressResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Location location = new Location();
	private AddressComponent addressComponent = new AddressComponent();
	private String formatted_address;
	private String business;
	private String semantic_description;
	private List<Poi> pois = new ArrayList<Poi>();
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public AddressComponent getAddressComponent() {
		return addressComponent;
	}
	public void setAddressComponent(AddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getSemantic_description() {
		return semantic_description;
	}
	public void setSemantic_description(String semantic_description) {
		this.semantic_description = semantic_description;
	}
	public List<Poi> getPois() {
		return pois;
	}
	public void setPois(List<Poi> pois) {
		this.pois = pois;
	}
	
	
}
