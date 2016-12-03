package com.silanis.lottery.core.pojo;

import java.math.BigDecimal;

/**
 * Winner is a purchaser with prize.
 * 
 * @author yudong
 *
 */
public class Winner extends Purchaser {
	
	private BigDecimal prize;

	public Winner(Purchaser purchaser, BigDecimal prize) {
		super(purchaser.getFirstName());
		this.prize = prize;
	}
	
	public BigDecimal getPrize() {
		return prize;
	}
}
