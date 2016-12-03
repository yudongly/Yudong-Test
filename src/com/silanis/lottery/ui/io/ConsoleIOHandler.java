package com.silanis.lottery.ui.io;

import java.util.Scanner;

public class ConsoleIOHandler implements IOHandler {
	
	private static final Scanner scanner = new Scanner(System.in);

	public String getInput() {
		return scanner.nextLine();
	}

	public void print(String s) {
		System.out.println(s == null ? "" : s);
	}

	public void close() {
		scanner.close();		
	}

}
