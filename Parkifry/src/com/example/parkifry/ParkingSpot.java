package com.example.parkifry;

import java.util.Calendar;
import java.util.Date;

import android.location.Location;


public class ParkingSpot {
	private double[] location;
	private String name;
	private Date creationDateTime;
	
	public ParkingSpot(double[] location,String name){
		this.location = location;
		this.name = name;
		this.creationDateTime = Calendar.getInstance().getTime();
	}
	
	public double[] getLocation(){
		return location;
	}
	
	public String getName(){
		return name;
	}
	
	public Date getCreationDateTime(){
		return creationDateTime;
	}
	
	public void setLocation(Location location){
		this.location = new double[] {location.getLatitude(), location.getLongitude()};
	}
	
	public void setName(String name){
		this.name = name;
	}
}
