package bq.demo.collection;

import java.util.Arrays;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 11, 2015 11:47:32 AM
 *
 */
public class ArraySort {

	/**
	 * quick sort
	 */
	public void test(){
		int[] a = new int[100];
		for(int i = 0; i < a.length; i++){
			a[i] = (int) (Math.random()*100);
		}
		
		System.out.println("before sort:");
		new TestUtil().printArray(a);
		
		Arrays.sort(a);
	}
	
}
