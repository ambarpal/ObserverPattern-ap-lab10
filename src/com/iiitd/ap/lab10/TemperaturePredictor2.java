package com.iiitd.ap.lab10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ambar14012
 * @author palash14072
 */

public class TemperaturePredictor2 implements Observer{
	private static final int MAXLIMIT = 5;
	Subject subject;
	private HashMap<String, Queue<Double>> pastTemperatures = new HashMap<>();
	@Override
	public void setSubject(Subject subject){
		this.subject = subject;
	}
	
	@Override
	public void update(){
		ArrayList<TemperatureLog> temperatureLogs = (ArrayList<TemperatureLog>) subject.getUpdate();
		for (TemperatureLog temperatureLog : temperatureLogs) {
			pastTemperatures.putIfAbsent(temperatureLog.getLocation(), new LinkedList<>());
			Queue<Double> past = pastTemperatures.get(temperatureLog.getLocation());
			if(past.size()==MAXLIMIT) past.poll();
			past.offer(temperatureLog.getTemperature());
			Double prediction = 0.0;
			if(past.size()>1) {
				prediction = temperatureLog.getTemperature() + (temperatureLog.getTemperature() - past.peek())/(past.size()-1);
			}
			System.out.println("Temperature Predictor 2: Temperature prediction for " + temperatureLog.getLocation() + ": " + prediction + " degrees.");
		}
	}
}
