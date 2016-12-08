/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * <b> </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 * Create at 5:12:46 PM Dec 7, 2016
 * 
 */

public class StringFormat {

	public static void main(String[] args) {
		StringFormat.performance();
		StringFormat.ways();
		StringFormat.format();
	}
	
	public static void ways() {
		// way 1
		
		// ==> 100 144 64
		String str1 = String.format("%1$d %1$o %1$x", 100);
		System.out.println(str1);
		
		// way 2
		
		// ==> 100 144 64
		try(Formatter fmt = new Formatter()) {
			str1 = fmt.format("%1$d %1$o %1$x", 100).toString();
			System.out.println(str1);
		}
		
		// ==> 100 144 64
		StringBuilder sb = new StringBuilder();
		try(Formatter fmt2 = new Formatter(sb, Locale.US)) {
			fmt2.format("%1$d %1$o %1$x", 100);
			str1 = sb.toString();
			System.out.println(str1);
		}
	}
	
	/**
	 * output:
	 * String.format() cost 318 milliseconds
	 * String append cost 1 milliseconds
	 * StringBuilder cost 60 milliseconds
	 */
	public static void performance() {
		// worst performance
		long from = System.currentTimeMillis();
		
		for(int i = 0; i < 100000; i++) {
			String str = String.format("value : %d %s %f", 100, "string", Math.PI);
		}
		
		System.out.println("String.format() cost " + (System.currentTimeMillis() - from) + " milliseconds");
		
		// best performance
		from = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			String str = "value : " + 100 + " " + "string" + " " + Math.PI;
		}
		
		System.out.println("String append cost " + (System.currentTimeMillis() - from) + " milliseconds");
		
		from = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			StringBuilder sb = new StringBuilder("value : ");
			sb.append(100);
			sb.append(" ");
			sb.append("string");
			sb.append(" ");
			sb.append(Math.PI);
			String str = sb.toString();
		}
		
		System.out.println("StringBuilder cost " + (System.currentTimeMillis() - from) + " milliseconds");
		
	}
	
	public static void format() {
		// 4$ 3$ represent arg index
		// by default is by the showing sequence of args
		
		// format string
		// ==> a b c d
		String str1 = String.format("%s %s %s %s", "a", "b", "c", "d");
		System.out.println(str1);
		
		// ==> d  c  b  a
		str1 = String.format("%4$s %3$s %2$s %1$s", "a", "b", "c", "d");
		System.out.println(str1);
		
		// format integer
		// ==> 100 144 64
		str1 = String.format("%1$d %1$o %1$x", 100);
		System.out.println(str1);
		
		// format float
		// ==> 3.141593 3.1416
		str1 = String.format("%1$f %1$.4f", Math.PI);
		System.out.println(str1);
		
		// format BigInteger, Big
		//==> 2147483647 4294967294 340282346638528859811704183484516925440.000000 680564693277057719623408366969033850880.000000
		BigInteger bi = new BigInteger(String.valueOf(Integer.MAX_VALUE));
		BigInteger bi2 = bi.add(bi);
		BigDecimal bd = new BigDecimal(Float.MAX_VALUE);
		BigDecimal bd2 = bd.add(bd);
		str1 = String.format("%d %d %f %f", bi, bi2, bd, bd2);
		System.out.println(str1);
		
		// format date
		//==> YEAR:[2016] Month:[12] Day:[8] Hour:[09] Minute:[47] Second:[55]
		Calendar calendar = Calendar.getInstance();
		str1 = String.format("YEAR:[%1$tY] Month:[%1$tm] Day:[%1$te] Hour:[%1$tH] Minute:[%1$tS] Second:[%1$tM]", calendar);
		System.out.println(str1);
		
		// ==> YEAR:[2016] Month:[12] Day:[8] Hour:[09] Minute:[47] Second:[55]
		Date date = new Date();
		str1 = String.format("YEAR:[%1$tY] Month:[%1$tm] Day:[%1$te] Hour:[%1$tH] Minute:[%1$tS] Second:[%1$tM]", date);
		System.out.println(str1);
	}
	
}
