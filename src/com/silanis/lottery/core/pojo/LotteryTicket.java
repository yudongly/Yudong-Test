package com.silanis.lottery.core.pojo;

/**
 * Lottery ticket class. This class is immutable.
 * 
 * @author yudong
 *
 */
public class LotteryTicket {
	
	private static final double DEF_PRICE = 100.00; 
	
	private final int ticketNumber;
	private final double price;
	
	public LotteryTicket(int ballNumber) {
		this(ballNumber, DEF_PRICE);
	}

	public LotteryTicket(int ticketNumber, double price) {
		this.ticketNumber = ticketNumber;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public boolean equals(Object obj) {
		if(obj instanceof LotteryTicket) {
			return ticketNumber == ((LotteryTicket) obj).getTicketNumber();
		}
		return false;
	}
	
	
	public int hashCode() {
		return ticketNumber;
	}
	
	public String toString() {
		return "" + ticketNumber;
	}
}
