package com.iiitd.ap.lab10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureSensor implements Subject{
	private List<Observer> observers = Collections.synchronizedList(new ArrayList<>());
	private ArrayList<TemperatureGenerator> updaters = new ArrayList<>();
	private ArrayList<TemperatureLog> lastUpdate;
	RandomTemperatureGenerator r = new RandomTemperatureGenerator();
	public TemperatureSensor(){
		addLocation("Delhi");
		addLocation("Mumbai");
		addLocation("Shrinagar");
	}
	public void addLocation(String name){
		updaters.add(new TemperatureGenerator(new TemperatureLog(name, 35.0)).startThread());
	}
	public void stopSensing(){
		updaters.forEach(TemperatureGenerator::stopGenerating);
	}
	@Override
	synchronized public void registerObserver(Observer observer){
		observers.add(observer);
		observer.setSubject(this);
	}
	
	@Override
	synchronized public void removeObserver(Observer observer){
		observers.remove(observer);
		observer.setSubject(null);
	}
	
	@Override
	synchronized public void notifyObservers(){
		observers.forEach(Observer::update);
	}
	
	@Override
	synchronized public Object getUpdate(){
		return lastUpdate;
	}

	synchronized private void updateAndNotify(TemperatureLog temperatureLog){
		lastUpdate.add(temperatureLog);
		if(lastUpdate.size()==updaters.size()){
			notifyObservers();
			lastUpdate.clear();
		}
	}

	private class TemperatureGenerator extends Thread{
		TemperatureLog temperatureLog;
		private boolean isWorking;

		public TemperatureGenerator(TemperatureLog log){
			temperatureLog = log;
		}
		public TemperatureGenerator startThread(){
			isWorking = true;
			start();
			return this;
		}
		@Override
		public void run() {
			while(isWorking){
				updateTemperature();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ignored) {
				}
			}
		}

		public void stopGenerating(){
			isWorking = false;
		}

		synchronized private void updateTemperature() {
			temperatureLog.setTemperature(r.next());
			temperatureLog.updateTimestamp();
			updateAndNotify(temperatureLog);
		}
	}
}