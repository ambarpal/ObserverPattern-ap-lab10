package com.iiitd.ap.lab10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GenerateStats implements Observer{
	private static final int MAXLIMIT = 100;
	Subject subject;
	HashMap<String, Queue<Double>> pastTemperatures = new HashMap<>();

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
			//TODO: Calculate Median
			Double mean = 0.0, max = 0.0, min = Double.MAX_VALUE, median = 0.0;
			for(Double d : past) {
				mean += d;
				max = Double.max(max, d);
				min = Double.min(min, d);
			}
			mean/=past.size();
			System.out.println("Temperature Predictor 1: Temperature Stats for " + temperatureLog.getLocation() + ": ");
			System.out.println("\tMean: " + mean);
			System.out.println("\tMaximum: " + max);
			System.out.println("\tMinimum: " + min);
			System.out.println("\tMedian: " + median);
		}
	}
}
