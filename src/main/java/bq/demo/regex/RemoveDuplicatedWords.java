package bq.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * matcher.group() return whole match string
 * matcher.group(name/index) return specific group string
 * 
 * @author qibo
 *
 */
public class RemoveDuplicatedWords {

	@Test
	public void test() {
		String input = "Goodbye bye bye world world world";
		
		// Note: to support replace repeated string with occur more than 2, use: (\s+\b\1\b)+
		String regex = "\\b(\\w+)(\\s+\\1\\b)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        
        Matcher m = p.matcher(input);
        while (m.find()) {
        	//System.out.println(m.group()); //==> world world
        	//System.out.println(m.group(1)); // ==> world
            input = input.replaceAll(m.group(), m.group(1));
        }
        
        System.out.println(input);
        
	}
	
}
