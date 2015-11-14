package com.iiitd.ap.lab10;

import java.util.ArrayList;

public class SerializeTemperature implements Observer{
	Subject subject;
	@Override
	public void setSubject(Subject subject){
		this.subject = subject;
	}
	
	@Override
	public void update(){
		ArrayList<TemperatureLog> a = (ArrayList<TemperatureLog>)subject.getUpdate();
	}
}
