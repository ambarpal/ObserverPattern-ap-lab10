package com.iiitd.ap.lab10;

import java.util.Random;

public class RandomNumberGenerator {
	int seed;
	Random r = new Random();
	public RandomNumberGenerator() {
		seed = 0;
		r.setSeed(seed);
	}
	public RandomNumberGenerator(int seed){
		this.seed = seed;
		r.setSeed(seed);
	}
	public double next(){
		// TODO: Implement own random number generator
		return r.nextDouble();
	}
}
