package bq.demo.enumeration;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jul 21, 2014 10:17:22 AM
 *
 */
public class EnumTest {

	/**
	 * loop on enum
	 */
	@Test
	public void testLoop(){
		for (KeyValueEnum enum1 : KeyValueEnum.values()) {
			System.out.println(enum1);
		}
	}
	
	/**
	 * complex enum cannot be search by valueOf(String)
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testValueofWithComplexEnum(){
		KeyValueEnum.valueOf("red");
	}
	
	@Test
	public void testValueof2WithComplexEnum(){
		KeyValueEnum red = KeyValueEnum.get("red");
		assertEquals(red.getKey(), "red");
	}
	
	/**
	 * single value enum cannot be search by valueOf(String)
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testValueofWithSingleValueEnum(){
		SingleValueEnum.valueOf("red");
	}
	
	@Test
	public void testValueofWithSimpleEnum(){
		SimpleEnum red = SimpleEnum.valueOf("Red");
		assertTrue(red.toString().equals("Red"));
	}
}
