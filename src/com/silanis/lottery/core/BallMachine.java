package com.silanis.lottery.core;

import java.util.Random;

/**
 * This represents ball machine for lottery draw.
 * 
 * @author yudong
 *
 */
public class BallMachine {
		
	private int min;
	private int max;	
	
	public BallMachine(int min, int max) {
		if(min < 0 || max < 0 || min >= max)
			throw new IllegalArgumentException("Invalid range for ball draw.");
		
		this.min = min;
		this.max = max;
	}
	
	public int getBall() {
		Random random = new Random();
		//get next int between Min and max inclusive
		return random.nextInt(max - min + 1) + min;
	}	
}
