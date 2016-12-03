package bq.demo.regex;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User name validation:
 * 
 * The username can contain alphanumeric characters and/or underscores(_).
 * The username must start with an alphabetic character.
 * 8 < |Username| < 30.

 * 
 * @author qibo
 *
 */
public class MatchWholeString {

	public void test(){
		// Note: to match whole string, must use ^$, otherwise will search match inside string
		
		String regex = "^[A-Za-z][A-Za-z0-9_]{7,29}$";
		String input = "123Swakkhar";
		
		Pattern p = Pattern.compile(regex);
        
        Matcher m = p.matcher(input);
        assertTrue(m.find());
	}
	
}
