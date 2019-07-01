package xgxt.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Check_Input_ValueTest {
	private String str ;
	@Before
	public void setUp() throws Exception {
		str = "abcandd";
	}

	@After
	public void tearDown() throws Exception {
		str = null;
	}

	@Test
	public void testCheck2() {
		assertEquals(true, Check_Input_Value.check2(str));
	}

}
