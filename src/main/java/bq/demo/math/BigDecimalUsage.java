/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.math;

import java.math.BigDecimal;

/**
 * <b> </b>
 *
 * <p> NOTE: use {@link BigDecimal#BigDecimal(String)} instead of {@link BigDecimal#BigDecimal(double)}, 
 * otherwise will get precision issue.
 * </p>
 * 
 * <p> NOTE:
 * <li>Don’t use float and double on monetary calculation.
 * <li>Use BigDecimal, long or int for monetary calculation.
 * <li>Use BigDecimal with String constructor and avoid double one.
 * <li>Don’t use floating point result for comparing loop conditions.
 * </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 4:19:38 PM Dec 9, 2016
 * 
 */

public class BigDecimalUsage {

	public static void main(String[] args) {
		BigDecimal db1 = new BigDecimal("1.88");
		BigDecimal db2 = new BigDecimal("1.00");
		// ==> 0.88
		System.out.println("BigDecimal constructed with string: " + db1.subtract(db2));
		
		BigDecimal db3 = new BigDecimal(1.88);
		BigDecimal db4 = new BigDecimal(1.00);
		// ==> 0.87999999999999989341858963598497211933135986328125
		System.out.println("BigDecimal constructed with float/double: " + db3.subtract(db4));
		
		double d1 = 1.88;
		double d2 = 1.00;
		// ==> 0.8799999999999999
		System.out.println("double : " + (d1 - d2));
		
		float f1 = 1.88f;
		float f2 = 1.00f;
		// ==> 0.88
		System.out.println("float: " + (f1 - f2));
	}
	
}
