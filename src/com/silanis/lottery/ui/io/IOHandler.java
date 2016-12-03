package com.silanis.lottery.ui.io;

import java.io.Closeable;

/**
 * Input / Output handler interface.  
 * 
 * @author yudong
 *
 */
public interface IOHandler extends Closeable {
	
	/**
	 * Get input String
	 * 
	 * @return
	 */
	public String getInput();
	/**
	 * Output a String
	 * 
	 * @param s
	 */
	public void print(String s);
	
	/**
	 * From <code>Closable<code> interface
	 */
	public void close();

}
