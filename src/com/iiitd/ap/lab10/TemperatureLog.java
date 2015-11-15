package com.iiitd.ap.lab10;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TemperatureLog implements Serializable {
	String location;
	Double temperature;
	Timestamp timestamp;
	public TemperatureLog(String location, Double temperature) {
		super();
		this.location = location;
		this.temperature = temperature;
		timestamp = new Timestamp(new Date().getTime());
	}
	public String getLocation() {
		return location;
	}
	public Double getTemperature() {
		return temperature;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public void updateTimestamp(){
		timestamp.setTime(new Date().getTime());
	}
}
