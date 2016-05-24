package bq.demo.regex;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexDemo2 {

	private int countOccur(String value, String target) {
		Pattern p = Pattern.compile(target);
		Matcher matcher = p.matcher(value);
		
		int count = 0;
		while(matcher.find())
			count++;
		
		return count;
	}
	
	@Test
	public void testRegex(){
		int count = countOccur("bqbqbqbbbqq", "bq");
		assertEquals(count, 4);
		
		count = countOccur("bbbbbqqqqqq", "bq");
		assertEquals(count, 1);
		
		count = countOccur("dddddd", "bq");
		assertEquals(count, 0);
	}
}
