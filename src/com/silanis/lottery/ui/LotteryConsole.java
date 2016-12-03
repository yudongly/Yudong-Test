package com.silanis.lottery.ui;

import java.util.List;

import com.silanis.lottery.core.LotterySystemFactory;
import com.silanis.lottery.core.LotterySystemFactory.LotterySystemType;
import com.silanis.lottery.core.base.LotterySystem;
import com.silanis.lottery.core.base.LotteryException;
import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;
import com.silanis.lottery.core.pojo.Winner;
import com.silanis.lottery.ui.io.ConsoleIOHandler;
import com.silanis.lottery.ui.io.IOHandler;
import com.silanis.lottery.utils.LotteryUtils;

public class LotteryConsole {
	
	private IOHandler handler;
	private LotterySystem sl;
	
	public LotteryConsole() {
		handler = new ConsoleIOHandler();
		sl = LotterySystemFactory.getLotterySystem(LotterySystemType.SILANIS);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LotteryConsole console = new LotteryConsole();		
		
		console.handler.print("##############################################");
		console.handler.print("#                                            #");
		console.handler.print("#         Welcome to Silanis Lottery         #");
		console.handler.print("#                                            #");
		console.handler.print("##############################################");
					
			
		while (true) {
			console.printMenu();
			
			//get input
			String input = console.handler.getInput();

			if ("purchase".equalsIgnoreCase(input) || "1".equals(input)) {
				console.purchase();
			} else if ("draw".equalsIgnoreCase(input) || "2".equals(input)) {
				console.draw();
			} else if ("winners".equalsIgnoreCase(input) || "3".equals(input)) {
				console.printWinnders();
			} else if ("exit".equalsIgnoreCase(input) || "4".equals(input)) {
				console.exit();
				break;
			} else {
				// back
			}
		}		
	}
	
	public void printMenu() {
		// print menu
		handler.print("\nPlease select following 4 options by entering the number: ");
		handler.print("1. purchase \tPurchase a lottery ticket.");
		handler.print("2. draw \tStart a lottery draw.");
		handler.print("3. winners \tDisplay winners' name and prizes");
		handler.print("4. exit \tExit the lottery.");				
	}
	
	public void purchase() {
		//sold out for the draw?
		if(!sl.isOpen()) {
			handler.print("All sold out. Please wait for the next draw.");
			return;
		}
		
		//prompt for first name
		handler.print("Please enter your first name: ");
		String name = handler.getInput();
		
		//No input?
		if(LotteryUtils.isNullOrEmpty(name)) {
			handler.print("First name is empty. Can not process the purchase. ");
			return;
		}
		
		//prepare ticket and message
		LotteryTicket ticket;
		try {
			ticket = sl.purchaseTicket(new Purchaser(name));
		} catch (LotteryException e) {
			handler.print(e.getMessage());
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Received $").append(LotteryUtils.format(ticket.getPrice())).
			append(". Your ticket number is: ").append(ticket.getTicketNumber());
		handler.print(sb.toString());
		
		//Reset string builder
		sb.setLength(0);
		sb.append("Thank you, ").append(name).append(", for purchasing Silanis lottery ticket.\n");
		handler.print(sb.toString());
	}
	
	public void draw() {
		List<LotteryTicket> winningTickets;
		try {
			winningTickets = sl.lotteryDraw();
		} catch (LotteryException e) {
			handler.print(e.getMessage());
			return;
		}
		
		
		int count = 1;
		StringBuilder sb = new StringBuilder();
		for(LotteryTicket t : winningTickets) {
			sb.append("Prize#").append(count).append(": ").append(t.getTicketNumber()).append("\t");
			count++;
		}		
		
		handler.print(sb.toString());
	}

	public void printWinnders() {		
		List<Winner> winners;
		try {
			winners = sl.getWinners();
		} catch (LotteryException e) {
			handler.print(e.getMessage());
			return;
		}
		
		handler.print("    Winners are: ");
		
		int count = 1;
		StringBuilder line1 = new StringBuilder();
		StringBuilder line2 = new StringBuilder();
		line1.append("\t");
		line2.append("\t");
		for(Winner w : winners) {
			line1.append("Prize#").append(count).append("\t\t");
			line2.append(w.getFirstName()).append(": ").append(w.getPrize()).append("\t\t");
			count++;
		}
		handler.print(line1.toString());
		handler.print(line2.toString());
		handler.print("");
	}
	
	public void exit() {
		handler.print("Thank you for playing Silanis Lottery.");
		handler.print("Bye!");		
	}
}
