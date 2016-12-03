package bq.demo.lang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Autoboxing {

	@Test
	public void test() {
		int a = 3;
		int b = 4;
		
		double c = (a + b) / 2;
		assertTrue(c == 3.0);
		
		double d = (a + b) / 2.0;
		assertTrue(d == 3.5);
	}
	
}
