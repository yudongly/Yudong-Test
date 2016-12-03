/**
 * 
 */
package com.silanis.lottery.core.base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.silanis.lottery.core.BallMachine;
import com.silanis.lottery.core.pojo.LotteryTicket;
import com.silanis.lottery.core.pojo.Purchaser;

/**
 * @author yudong
 *
 */
public class AbstractLotterySystemTest {
	
	ConcretAbstractLotterySystem cal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cal = new ConcretAbstractLotterySystem();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cal = null;
	}

	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#draw()}.
	 */
	@Test
	public final void testDraw() {
		LotteryTicket t = cal.draw();
		assertNotNull(t);
	}
	
	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#draw()}.
	 */
	@Test
    public void testDrawMockito() {
		AbstractLotterySystem als = Mockito.mock(AbstractLotterySystem.class, Mockito.CALLS_REAL_METHODS);
		als.setBm(new BallMachine(1, 5));
		als.setWinningTickets(new ArrayList<LotteryTicket>());
		LotteryTicket t = als.draw();
		assertNotNull(t);
    }
	
	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#draw()}.
	 */
	@Test
    public void testDrawMockitoNotSame() {
		AbstractLotterySystem als = Mockito.mock(AbstractLotterySystem.class, Mockito.CALLS_REAL_METHODS);
		als.setBm(new BallMachine(1, 5));
		als.setWinningTickets(new ArrayList<LotteryTicket>());
		LotteryTicket t1 = als.draw();
		LotteryTicket t2 = als.draw();
		assertFalse(t1.equals(t2));
    }
	
	/**
	 * Test method for {@link com.silanis.lottery.core.base.AbstractLotterySystem#draw()}.
	 */
	@Test
	public final void testDrawNotSame() {
		LotteryTicket t1 = cal.draw();
		LotteryTicket t2 = cal.draw();
		assertFalse(t1.equals(t2));
	}
	
	// Helper concrete class in order to test AbstractLottery
	class ConcretAbstractLotterySystem extends AbstractLotterySystem {
		public ConcretAbstractLotterySystem() {
			this.bm = new BallMachine(1, 5);
			this.lotterySold = new HashMap<LotteryTicket, Purchaser>();
			this.winningTickets = new ArrayList<LotteryTicket>();
		}
	}

}
