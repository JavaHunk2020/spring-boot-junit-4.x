package com.cubicit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MathematicsTest {
	
	Mathematics mathematics;

	@Before
	public void init() {
	   mathematics=new Mathematics();
	}

	@Test
	public void testCalculateWhenNumPositive() {
		int result=mathematics.calculate(3, 4);
        assertEquals(13, result); 
	}
	
	@Test
	public void testCalculateWhenNumNegative() {
		int result=mathematics.calculate(-4, 4);
        assertEquals(20, result); 
	}
	
	@Test
	public void testCalculateWhenNumAndOffsetZero() {
		int result=mathematics.calculate(0,0);
        assertEquals(0, result); 
	}
	
	@Test(expected = ArithmeticException.class)
	public void testCalculateWhenOffsetLessThanZero() {
		mathematics.calculate(10,-2);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testCalculateWhenOffsetGreterThan100() {
		mathematics.calculate(10,101);
	}

}
