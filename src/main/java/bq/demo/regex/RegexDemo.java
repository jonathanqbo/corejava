package bq.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 11, 2015 12:35:48 PM
 *
 */
public class RegexDemo {
	
	public void testGroupReplace(){
		String str = "https://www.google.com/webhp?sourceid=chrome-instant&ion=1&key3=value3&key4=value4&key5=value5";
		
		String rex = "(?<seperator>[&|\\?])(?<key>[^=&]*)\\s*=\\s*(?<value>[^&]*)";
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(str);
		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, matcher.group("seperator")+matcher.group("key")+"=******");
		}
		matcher.appendTail(sb);
		
		System.out.println(sb);
	}
	
	public void testGroupExtract(){
		String str = "https://www.google.com/webhp?sourceid=chrome-instant&ion=1&key3=value3&key4=value4&key5=value5";
		
		String rex = "[&|\\?](?<key>[^=&]*)\\s*=\\s*(?<value>[^&]*)";
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()){
			System.out.println(matcher.group("key") + "=" + matcher.group("value"));
		}
	}
	
	public void testGroupSplit(){
		String str = "https://www.google.com/webhp?sourceid=chrome-instant&ion=1&key3=value3&key4=value4&key5=value5";
		
		// do split by String.split() method
		String rex = "(\\?|&|:|=)";
		String[] splitResult = str.split(rex);
		
		String result= "[" + splitResult[0];
		for(int i = 1; i<splitResult.length; i++)
			result = result + "," +splitResult[i];
		result=result+"]";
		System.out.println(result);
		
		// do split by Pattern.split() method
		Pattern pattern = Pattern.compile(rex);
		splitResult = pattern.split(str);
		
		result= "[" + splitResult[0];
		for(int i = 1; i<splitResult.length; i++)
			result = result + "," +splitResult[i];
		result=result+"]";
		System.out.println(result);
	}
	
	public void testPhoneNum(){
		
	}
	
	public void testIDNum(){
		
	}
	
	public void testInteger(){
		
	}
	
	public void testDecimal(){
		
	}
	
	public void testChines(){
		
	}
	
	public void testBirthday(){
		
	}
	
	public void testURL(){
		
	}
	
	@Test
	public void testIP(){
		// IP address, three rules:
		// 1) 1-3 digits or
		// 2) 25[0-5]
		// 3) 24[0-9]
		String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		// String regex = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])(\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])){3}";
		assertTrue(Pattern.matches(regex, "255.255.255.255"));
		assertTrue(Pattern.matches(regex, "1.1.1.1"));
		assertTrue(Pattern.matches(regex, "127.0.0.1"));
	}
	
	/**
	 * usage of regex group
	 */
	@Test
	public void stringOperation(){
		String regex = "\\{(\\w+)\\}";
		String url = "http://www.google.com/rest/resource/{resourceID}/name/{first}-{middle}-{last}";
		Pattern pattern = Pattern.compile(regex);
		
		// match by group index, result: resourceID,first,middle,last,
		Matcher matcher = pattern.matcher(url);
		while(matcher.find()){
			System.out.print(matcher.group(1)+",");
		}
		
		System.out.println();
		
		// split, result:http://www.google.com/rest/resource/,/name/,-,-,
		String[] parts = url.split(regex);
		for(int i = 0; i < parts.length; i++)
			System.out.print(parts[i]+",");
		
		System.out.println();
		
		// replace, result:http://www.google.com/rest/resource/XXX/name/XXX-XXX-XXX
		String newUrl = url.replaceAll(regex, "XXX");
		System.out.println(url + "->" + newUrl);
	}
	
	/**
	 * usage of regex group
	 */
	@Test
	public void group2(){
		// to give a group name: (?<groupname>XXXX)
		String regex = "resource/(?<grpname1>\\w+)/name.*";
		String url = "http://www.google.com/rest/resource/1234567890/name";
		Pattern pattern = Pattern.compile(regex);
		
		// match by group name, result: resourceID,first,middle,last,
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			// result: 1234567890
			System.out.println(matcher.group("grpname1"));
			
			// result: resource/1234567890/name
			System.out.println(matcher.group());
		}
	}

}
