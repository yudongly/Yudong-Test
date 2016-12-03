package com.silanis.lottery.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yudong
 *
 */
public class BallMachineTest {
	
	private BallMachine bm;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {	
		bm = new BallMachine(1, 5);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		bm = null;
	}
	
	/**
	 * Test constructor
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testBallMachine51() {
		new BallMachine(5, 1);
	}
	
	/**
	 * Test constructor
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testBallMachineminus1() {
		new BallMachine(-5, 1);
	}
	
	/**
	 * Test constructor
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testBallMachineminus2() {
		new BallMachine(-5, -1);
	}


	/**
	 * Test method for {@link com.silanis.lottery.core.BallMachine#getBall()}.
	 */
	@Test
	public final void testGetBall() {
		int n = bm.getBall();
		assertTrue(n >= 1 && n <=5);
	}	
}
