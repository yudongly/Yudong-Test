package com.silanis.lottery.core;

import com.silanis.lottery.core.base.LotterySystem;

/**
 * Lottery System factory
 * 
 * @author yudong
 *
 */
public class LotterySystemFactory {
	
	
	public static LotterySystem getLotterySystem(LotterySystemType type) {
		if(type == null)
			throw new IllegalArgumentException("Lottery System type should not be null.");
		if(type.equals(LotterySystemType.SILANIS))
			return SilanisLotterySystem.getInstance();
		else
			throw new IllegalArgumentException("Lottery System not supported.");
	}
	
	public enum LotterySystemType {
		SILANIS;
	}
}
