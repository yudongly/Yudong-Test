package com.silanis.lottery.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.silanis.lottery.core.base.AbstractLotterySystem;
import com.silanis.lottery.core.base.LotterySystem;
import com.silanis.lottery.core.base.LotteryException;
import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;
import com.silanis.lottery.core.pojo.Winner;
import com.silanis.lottery.utils.LotteryUtils;

/**
 * This is the core of the lottery system.
 * It encapsulates all the lottery logic.
 * 
 * @author yudong
 *
 */
public class SilanisLotterySystem extends AbstractLotterySystem implements LotterySystem{
	
	private static final Logger logger = Logger.getLogger(SilanisLotterySystem.class.getName());
		
	/* Default percentages for each prize */
	private double[] percentages = {PERCENT_1ST, PERCENT_2ND,PERCENT_3RD};	
	
	/* Single instance of SilanisLottery class */
	private static SilanisLotterySystem instance = new SilanisLotterySystem();
	
	public static SilanisLotterySystem getInstance() {
		return instance;
	}
		
	protected SilanisLotterySystem() {
		this.pot = INITIAL_POT;		
		this.drawCount = percentages.length;
		this.prizePercentage = PERCENT_PRIZE;		
		this.numOfBalls = MAX - MIN + 1;
		
		this.ticketCount = 0;
		this.bm = new BallMachine(MIN, MAX);			
	}
	
	
	/**
	 * Purchase a ticket by providing first name.
	 * 
	 * @param firstName
	 * @return
	 * @throws LotteryException 
	 */
	public LotteryTicket purchaseTicket(Purchaser purchaser) throws LotteryException {
		logger.fine("begin of purchaseTicket");
		if(!isOpen()) {
			LotteryException e = new LotteryException(LotteryException.ErrorCode.PURCHASE_ERROR);
			logger.warning(e.getMessage());
			throw e;
		}
		
		if(purchaser == null || LotteryUtils.isNullOrEmpty(purchaser.getFirstName())) {
			LotteryException e = new LotteryException(LotteryException.ErrorCode.INVALID_PURCHASER);
			logger.warning(e.getMessage());
			throw e;
		}
		
		// generate a ticket
		LotteryTicket ticket = new LotteryTicket(++ticketCount);	
		//add to pot
		pot += ticket.getPrice();
		//register in lottery
		lotterySold.put(ticket, purchaser);
		logger.fine("end of purchaseTicket");
		return ticket;
	}
	
	/**
	 * Draw the winning tickets.
	 * @throws LotteryException 
	 */
	public List<LotteryTicket> lotteryDraw() throws LotteryException {
		logger.fine("begin of lotteryDraw");
		// check if the lottery is still open for purchase
		if(isOpen()) {
			LotteryException e = new LotteryException(LotteryException.ErrorCode.DRAW_ERROR);
			logger.warning(e.getMessage());
			throw e;
		}
		//already draw?
		if(winningTickets.size() > 0) {
			return winningTickets;
		}
		//prize draws
		for(int k = 0; k <drawCount; k++) {
			draw();
		}
		logger.fine("end of lotteryDraw");
		return winningTickets;
	}
	
	
	/**
	 * Get winners' first names.
	 * 
	 * @return A list of first names of winners.
	 * @throws LotteryException 
	 */
	public List<Winner> getWinners() throws LotteryException {
		logger.fine("begin of getWinners");
		// check if the lottery is still open for purchase
		if(isOpen()) {
			LotteryException e = new LotteryException(LotteryException.ErrorCode.GET_WINNER_ERROR);
			logger.warning(e.getMessage());
			throw e;
		}
		List<Winner> winners = new ArrayList<Winner>();
		int count = 0;
		double expenses = 0;
		for(LotteryTicket t : winningTickets) {
			double prize = pot * percentages[count] * prizePercentage;
			expenses += prize;
			BigDecimal bd = new BigDecimal(prize);			
			Winner winner = new Winner(lotterySold.get(t), bd.setScale(0, BigDecimal.ROUND_HALF_UP));
			
			winners.add(winner);
			count++;
		}
		//prize allocated, reset everything
		pot -= expenses;
		resetLottery();
		logger.fine("end of getWinners");
		return winners;
	}
}
