package com.cubicit;

import org.junit.Test;
import static org.junit.Assert.*;


public class FactorialTest {

	@Test
	public void testComputeWhenInputZero() {
		Factorial factorial=new Factorial(0);
		//0! = 1
		int result=factorial.compute();
		assertEquals(1, result);				
	}
	
	@Test
	public void testComputeWhenInputOne() {
		Factorial factorial=new Factorial(1);
		//0! = 1
		int result=factorial.compute();
		assertEquals(1, result);				
	}
	
	@Test
	public void testComputeWhenInputFive() {
		Factorial factorial=new Factorial(5);
		//0! = 1
		int result=factorial.compute();
		assertEquals(120, result);				
	}
	
	@Test(expected=ArithmeticException.class)
	public void testComputeWhenInputNegative() {
		Factorial factorial=new Factorial(-1);
        factorial.compute();
	}

}
