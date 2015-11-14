package com.iiitd.ap.lab10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureSensor implements Subject{
	private List<Observer> observers = Collections.synchronizedList(new ArrayList<>());
	private ArrayList<TemperatureGenerator> updaters = new ArrayList<>();
	private TemperatureLog lastUpdate;
	RandomNumberGenerator r = new RandomNumberGenerator();
	TemperatureSensor(){
		updaters.add(new TemperatureGenerator(new TemperatureLog("Delhi", 35.0)).startThread());
		updaters.add( new TemperatureGenerator( new TemperatureLog("Mumbai", 35.0) ).startThread());
		updaters.add(new TemperatureGenerator(new TemperatureLog("Shrinagar", 35.0)).startThread());

		notifyObservers();
	}
	public void senseTemperatures(){
		notifyAll();
	}
	public void addLocation(String name){

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
		observers.forEach(Observer::update);
	}
	
	@Override
	synchronized public Object getUpdate(){
		return lastUpdate;
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
				notifyObservers();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ignored) {
				}
			}
		}

		public void stopUpdating(){
			isWorking = false;
		}

		synchronized private void updateTemperature() {
			temperatureLog.setTemperature(r.next());
			lastUpdate = this.temperatureLog;
		}
	}
}