package com.example.parkifry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
	private String name;
	private Map<String,ParkingSpot> parkingSpots;
	private Date lastUpdate;
	
	public Person(String name,List<ParkingSpot> parkingSpots){
		this.name = name;
		this.parkingSpots = new HashMap<String,ParkingSpot>();
		for(ParkingSpot spot : parkingSpots){
			this.parkingSpots.put(spot.getName(), spot);
		}
		this.lastUpdate = Calendar.getInstance().getTime();
	}
	
	public String getName(){
		return name;
	}
	
	public List<ParkingSpot> getParkingSpots(){
		return new ArrayList<ParkingSpot>(parkingSpots.values());
	}
	
	public Date getLastUpdate(){
		return lastUpdate;
	}
	
	public void addParkingSpot(ParkingSpot spot){
		parkingSpots.put(spot.getName(), spot);
		lastUpdate = Calendar.getInstance().getTime();
	}
	
	public void setName(String name){
		this.name = name;
	}
}

