package com.iiitd.ap.lab10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeTemperature implements Observer{
	Subject subject;
	@Override
	public void setSubject(Subject subject){
		this.subject = subject;
	}
	
	@Override
	public void update(){
		ArrayList<TemperatureLog> temperatureLogs = (ArrayList<TemperatureLog>) subject.getUpdate();
		for (TemperatureLog temperatureLog : temperatureLogs) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(temperatureLog.getLocation()+temperatureLog.getTimestamp()+".dat"));
				out.writeObject(temperatureLog);
				out.close();
				System.out.println("Temperature Serializer: Temperature serialized for " + temperatureLog.getLocation() + " with timestamp " + temperatureLog.getTimestamp());
			} catch (IOException e) {
				System.out.println("Temperature Serializer: Error Serializing " + temperatureLog.getLocation() + " with timestamp " + temperatureLog.getTimestamp());
				e.printStackTrace();
			}
		}
	}
}
