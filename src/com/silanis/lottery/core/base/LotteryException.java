/**
 * 
 */
package com.silanis.lottery.core.base;

/**
 * Exception class for Silanis Lottery system.
 * 
 * @author yudong
 *
 */
public class LotteryException extends Exception {
	
	private ErrorCode error;

	/**
	 * Error code 
	 */
	public LotteryException(ErrorCode error) {
		super(error.message);
		this.setError(error);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LotteryException(ErrorCode error, Throwable cause) {
		super(error.message, cause);
		this.setError(error);
	}

	public ErrorCode getError() {
		return error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

	public enum ErrorCode {
		
		DRAW_ERROR("Not ready for draw. Lottery is open for purchase."),
		PURCHASE_ERROR("All sold out. Please wait for the next draw."),
		INVALID_PURCHASER("Invalid purchaser info. Can not process the purchase."),
		GET_WINNER_ERROR("No winners. Please wait for the next draw.");
		
		private String message;
		
		ErrorCode(String message) {
			this.message = message;
		}
	}

}
