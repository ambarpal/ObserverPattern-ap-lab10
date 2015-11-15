/**
 * 
 */
package com.iiitd.ap.lab10;

/**
 * @author ambar14012
 * @author palash14072
 */
public class Main {
	public static void main(String[] args){
		TemperatureSensor mainsensor = new TemperatureSensor();
		SerializeTemperature weatherLog = new SerializeTemperature();
		TemperaturePredictor1 bbc = new TemperaturePredictor1();
		TemperaturePredictor2 yahooweather = new TemperaturePredictor2();
		GenerateStats googleWeatherStats = new GenerateStats();
		mainsensor.registerObserver(weatherLog);
		mainsensor.registerObserver(bbc);
		mainsensor.registerObserver(yahooweather);
		mainsensor.registerObserver(googleWeatherStats);
	}
}
