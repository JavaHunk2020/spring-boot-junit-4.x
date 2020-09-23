package com.cubicit;

public class Factorial {
	
	private int num;

	public Factorial(int num) {
		this.num = num;
	}
	
	public int compute() {
		
		if(num<0) {
			throw new ArithmeticException("Number cannot be negative");
		}
		
		int sum=1;
		for(int x=2;x<=num;x++) {
			sum=sum*x;
		}
		return sum;
	}

}
