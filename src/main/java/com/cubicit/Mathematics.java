package com.cubicit;

public class Mathematics {
	
	public int calculate(int num,int offset) {
		if(offset<0) {
			throw new ArithmeticException("offset cannot be negative");
		}
		
		if(offset>100) {
			throw new NumberFormatException("offset cannot be more than hundred");
		}
		int tempa=num*num+offset;
		return tempa;
	}

}
