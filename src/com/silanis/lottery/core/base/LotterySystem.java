package com.silanis.lottery.core.base;

import java.util.List;

import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;
import com.silanis.lottery.core.pojo.Winner;

public interface LotterySystem {
	
	/* Max and Min range for lottery tickets */
	public static final int MIN = 1;
	public static final int MAX = 5;
	
	/* Initial pot value constant */
	public static final double INITIAL_POT = 200.00; 
	/* Percentage for the first prize */
	public static final double PERCENT_1ST = 0.75;
	/* Percentage for the second prize */
	public static final double PERCENT_2ND = 0.15;
	/* Percentage for the third prize */
	public static final double PERCENT_3RD = 0.10;
	/* Percentage for each draw from pot */
	public static final double PERCENT_PRIZE = 0.50;
	
	
	public LotteryTicket purchaseTicket(Purchaser purchaser) throws LotteryException;
	public List<LotteryTicket> lotteryDraw() throws LotteryException;
	public List<Winner> getWinners() throws LotteryException;
	public boolean isOpen();
	public void resetLottery();
}
