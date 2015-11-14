package com.iiitd.ap.lab10;

public class TemperatureLog {
	String location;
	Double temperature;
	public TemperatureLog(String location, Double temperature) {
		super();
		this.location = location;
		this.temperature = temperature;
	}
	public String getLocation() {
		return location;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
