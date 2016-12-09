/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.math;

/**
 * <b> </b>
 *
 * <p> Don't use double value in "=="  </p>
 * 
 * <p> A CASE : 
 * You should really use a specific Currency class instead. 
 * Imagine this: You calculate a discount of 50% on a price of $5.55. 
 * That results in a discount of $2.755 and a discounted price of $2.755. 
 * Since we cannot pay $2.755 we need to round the numbers. 
 * So we round them. Result: Discount is now $2.76 and price is now $2.76 . 
 * Total is $0.02 higher than the total price before discount. 
 * The solution is of course to calculate the discount, round it, and then *subtract* it from the original price,
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * Create at 4:34:55 PM Dec 9, 2016
 * 
 */

public class DoubleUsage {

	public static void main(String[] args) {

		double d1 = 1.88;
		double d2 = 1.00;
		
		// NOTE
		if ( (d1 - d2) == 0.88 ) {
			System.out.println("WILL NOT RUN TO HERE");
		}
	}
	
}
