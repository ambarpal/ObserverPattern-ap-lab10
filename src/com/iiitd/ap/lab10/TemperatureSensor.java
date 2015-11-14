package com.iiitd.ap.lab10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureSensor implements Subject{
	private List<Observer> observers = Collections.synchronizedList(new ArrayList<Observer>());
	private ArrayList<TemperatureLog> temps = new ArrayList<TemperatureLog>();
	TemperatureSensor(){
		temps.add(new TemperatureLog("Home", 35.00));
		temps.add(new TemperatureLog("Dog's Home", 35.00));
		temps.add(new TemperatureLog("Mausi's Home", 35.00));
	}
	public void senseTemperatures(){
	}
	@Override
	synchronized public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	@Override
	synchronized public void removeObserver(Observer observer){
		observers.remove(observer);
	}
	
	@Override
	synchronized public void notifyObservers(){
		for (Observer o : observers) o.update();
	}
}