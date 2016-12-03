package com.silanis.lottery.core.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silanis.lottery.core.BallMachine;
import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;

public abstract class AbstractLotterySystem {
	
	/* Count for lottery ticket sold */
	protected int ticketCount;
	/* Prize count for lottery draw */
	protected int drawCount;
	
	/* Percentage of pot used for draw prizes */
	protected double prizePercentage;
	/* Total number of balls for the lottery */
	protected int numOfBalls;	
	/* Total money amount in pot */
	protected double pot;
	/* A map to hold ticket-first name pairs */
	protected Map<LotteryTicket, Purchaser> lotterySold;
	/* A list to hold winning tickets */
	protected List<LotteryTicket> winningTickets;
	
	/* Instance of Ball Machine for Silanis Lottery */
	protected BallMachine bm;

	public AbstractLotterySystem() {
		this.lotterySold = new HashMap<LotteryTicket, Purchaser>();
		this.winningTickets = new ArrayList<LotteryTicket>();
	}
	
	public Map<LotteryTicket, Purchaser> getLotterySold() {
		return lotterySold;
	}

	public List<LotteryTicket> getWinningTickets() {
		return winningTickets;
	}

	// Helper method to get number randomly.
	protected LotteryTicket draw() {
		int p = bm.getBall();
		LotteryTicket ticket = new LotteryTicket(p);
		while(winningTickets.contains(ticket)) {
			p = bm.getBall();
			ticket = new LotteryTicket(p);
		}
		winningTickets.add(ticket);
		return ticket;
	}
	

	public void setWinningTickets(List<LotteryTicket> winningTickets) {
		this.winningTickets = winningTickets;
	}

	public boolean isOpen() {
		return ticketCount < numOfBalls;
	}
	
	
	public void resetLottery() {
		lotterySold.clear();
		winningTickets.clear();
		ticketCount = 0;
	}

	public double getPrizePercentage() {
		return prizePercentage;
	}

	public void setPrizePercentage(double prizePercentage) {
		this.prizePercentage = prizePercentage;
	}

	public int getNumOfBalls() {
		return numOfBalls;
	}

	public void setNumOfBalls(int numOfBalls) {
		this.numOfBalls = numOfBalls;
	}

	public double getPot() {
		return pot;
	}

	public void setPot(double pot) {
		this.pot = pot;
	}

	public BallMachine getBm() {
		return bm;
	}

	public void setBm(BallMachine bm) {
		this.bm = bm;
	}
		
}
