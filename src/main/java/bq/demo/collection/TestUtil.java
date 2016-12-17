package bq.demo.collection;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 11, 2015 11:52:25 AM
 *
 */
public class TestUtil {

	public void printArray(Object[] a){
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i]);
		
		System.out.println();
	}
	
	public void printArray(int[] a) {
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + ", ");
		
		System.out.println();
	}
	
}
