/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

/**
 * <b> </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 3:21:52 PM Dec 17, 2016
 * 
 */

public class SetUsage {

	/**
	 * HashSet is implemented based on HashMap
	 * Note: 
	 * <li> will quietly ignore and return false if add same value
	 * <li> no order guarantee
	 * 
	 */
	@Test
	public void addDuplicatedElement() {
		// Test adding duplicated value
		
		Set<String> set = new HashSet<>();
		assertTrue(set.add("value"));
		// return false
		assertFalse(set.add("value"));
		
		// only one value
		assertTrue(set.size() == 1);;
		
		//
		System.out.println("\n===========> Test Order of HashSet");

		for ( int i = 0; i < 10; i++ ) {
			set.add("v" + i);
		}
		
		set.forEach(System.out::print);
	}
	
	/**
	 * TreeSet : ordered set implementation based on TreeMap
	 */
	@Test
	public void testOrderedSet() {
		System.out.println("\n===========> Test Order of TreeSet");
		
		SortedSet<String> set = new TreeSet<>();
		
		for ( int i = 0 ; i < 10; i++ ) {
			set.add("v" + i);
		}
		
		set.forEach(System.out::print);
	}
	
	/**
	 * LinkedHashSet : ordered set implementation based on LinkedHashMap
	 */
	@Test
	public void estLinkedHashSet () {
		System.out.println("\n===========> Test Order of LinkedHashSet");
		
		Set<String> set = new LinkedHashSet<>();
		
		for ( int i = 0 ; i < 10; i++ ) {
			set.add("v" + i);
		}
		
		set.forEach(System.out::print);
	}
	
	
}
