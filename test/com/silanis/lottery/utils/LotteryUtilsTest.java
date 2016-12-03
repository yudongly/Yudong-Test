package com.silanis.lottery.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LotteryUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIsNull() {
		assertTrue(LotteryUtils.isNullOrEmpty(null));
	}
	
	@Test
	public final void testIsEmpty() {
		assertTrue(LotteryUtils.isNullOrEmpty(""));
	}
	
	@Test
	public final void testIsNullOrEmpty() {
		assertFalse(LotteryUtils.isNullOrEmpty("ab@79L"));
	}

	@Test
	public final void testRound() {
		Double d = LotteryUtils.round(11.234656789);
		System.out.println(d);
		assertEquals(d, 11.234656789, 0.5); 
	}
	
	@Test
	public final void testFormat() {
		Double d = LotteryUtils.format(11.234656789);
		System.out.println(d);
		assertEquals(d, 11.234656789, 0.05); 
	}
	
	@Test
	public final void testFormatInt() {
		Double d = LotteryUtils.format(11);
		System.out.println("testFormatInt: " + d);
		assertEquals(d, 11, 0.05); 
	}

}
