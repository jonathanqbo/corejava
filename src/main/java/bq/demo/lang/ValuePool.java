package bq.demo.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValuePool {

	@Test
	public void test() {
		// new is not from pool
		Integer int1 = new Integer(1);
		Integer int2 = new Integer(1);
		assertTrue(int1.equals(int2));
		assertFalse(int1 == int2);
		
		// valueOf is from pool
		Integer int3 = Integer.valueOf(1);
		Integer int4 = Integer.valueOf(1);
		assertTrue(int3 == int4);
		
		// autoboxing is from pool
		int int5 = 1;
		assertTrue(int3 == int5);
	}
	
}
