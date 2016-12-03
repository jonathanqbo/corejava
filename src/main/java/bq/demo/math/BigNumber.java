package bq.demo.math;

import java.math.BigInteger;

import org.junit.Test;

public class BigNumber {

	@Test
	public void testBigInteger() {
		String num1 = "1234567890";
		String num2 = "9876543210";
		
		BigInteger int1 = new BigInteger(num1);
		BigInteger int2 = new BigInteger(num2);
		
		BigInteger resultAdd = int1.add(int2);
		System.out.println(num1 + "+" + num2 + "=" + resultAdd);
		
		BigInteger resultMultiply = int1.multiply(int2);
		System.out.println(num1 + "x" + num2 + "=" + resultMultiply);
	}
	
}
