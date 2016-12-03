/**
 * 
 */
package com.silanis.lottery.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.silanis.lottery.core.base.LotteryException;
import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;

/**
 * @author yudong
 *
 */
public class SilanisLotterySystemTest {
	
	SilanisLotterySystem sl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sl = new SilanisLotterySystem();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sl = null;
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		assertSame(SilanisLotterySystem.getInstance(), SilanisLotterySystem.getInstance());
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#purchaseTicket(com.silanis.lottery.core.pojo.Purchaser)}.
	 * @throws LotteryException 
	 */
	@Test(expected = LotteryException.class)
	public final void testPurchaseTicketNull() throws LotteryException {
		sl.purchaseTicket(null);
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#purchaseTicket(com.silanis.lottery.core.pojo.Purchaser)}.
	 * @throws LotteryException 
	 */
	@Test(expected = LotteryException.class)
	public final void testPurchaseTicketEmptyFirstName() throws LotteryException {
		sl.purchaseTicket(new Purchaser(""));
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#purchaseTicket(com.silanis.lottery.core.pojo.Purchaser)}.
	 * @throws LotteryException 
	 */
	@Test
	public final void testPurchaseTicket() throws LotteryException {
		LotteryTicket ticket = sl.purchaseTicket(new Purchaser("yudong"));
		assertNotNull(ticket);		
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#purchaseTicket(com.silanis.lottery.core.pojo.Purchaser)}.
	 * @throws LotteryException 
	 */
	@Test(expected = LotteryException.class)
	public final void testPurchaseTicketSoldOut() throws LotteryException {
		sl.setNumOfBalls(2);
		sl.purchaseTicket(new Purchaser("Yudong1"));
		sl.purchaseTicket(new Purchaser("Yudong2"));
		sl.purchaseTicket(new Purchaser("Yudong3"));
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#purchaseTicket(com.silanis.lottery.core.pojo.Purchaser)}.
	 * @throws LotteryException 
	 */
	@Test
	public final void testPurchaseTicketPot() throws LotteryException {
		sl.purchaseTicket(new Purchaser("yudong"));
		assertEquals(sl.getPot(), 300, 0.1); 			
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#lotteryDraw()}.
	 * @throws LotteryException 
	 */
	@Test(expected = LotteryException.class)
	public final void testLotteryDraw() throws LotteryException {
		sl.lotteryDraw();
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.SilanisLotterySystem#getWinners()}.
	 * @throws LotteryException 
	 */
	@Test(expected = LotteryException.class)
	public final void testGetWinners() throws LotteryException {
		sl.getWinners();
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#isOpen()}.
	 */
	@Test
	public final void testIsOpen() {
		assertTrue(sl.isOpen());
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#isOpen()}.
	 * @throws LotteryException 
	 */
	@Test
	public final void testIsOpenFalse() throws LotteryException {
		sl.setNumOfBalls(2);
		sl.purchaseTicket(new Purchaser("Yudong1"));
		sl.purchaseTicket(new Purchaser("Yudong2"));
		assertFalse(sl.isOpen());
		
	}

}
