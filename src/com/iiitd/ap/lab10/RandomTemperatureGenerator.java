package com.iiitd.ap.lab10;

import java.util.Date;

public class RandomTemperatureGenerator {
	long seed;
	public RandomTemperatureGenerator() {
		seed = new Date().getTime()%10000;
	}
	public double next(){
		// Returns double between -30 and 60. with accuracy of 10^-3
		seed = Math.abs(8253729 * seed + 2396403);
		Double retVal = (seed%90000)/1000.0d - 30;
		//System.out.println(retVal + " " + seed);
		return retVal;
	}
}
