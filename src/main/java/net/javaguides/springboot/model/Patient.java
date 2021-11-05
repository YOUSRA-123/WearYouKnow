package net.javaguides.springboot.model;


import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.google.cloud.firestore.GeoPoint;



@Component
public class Patient {
	
	private UUID id;
	private boolean is_admin;
	private String name;
	private String phone;
	//private Map<String, Object> positions = new HashMap<>();
	private ArrayList<GeoPoint> positions = new ArrayList<GeoPoint>();
	

	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/*
	 * public ArrayList<GeoPoint> getPositions() { return positions; } public void
	 * setPositions(ArrayList<GeoPoint> positions) { this.positions = positions; }
	 */
	public ArrayList<GeoPoint> getPositions() {
		return positions;
	}
	public void setPositions(ArrayList<GeoPoint> positions) {
		this.positions = positions;
				
	}
	
	
	
	
	
	
	

	

}