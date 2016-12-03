package com.silanis.lottery.core.pojo;

/**
 * Purchase bean 
 * Note: later on more fields like last name, ID, purchased date can be added.
 * 
 * @author yudong
 *
 */
public class Purchaser {
	
	private String firstName;
	
	public Purchaser(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}	
	
	public String toString() {
		return firstName;
	}
}
