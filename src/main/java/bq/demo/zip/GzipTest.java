package bq.demo.zip;

import java.io.IOException;

import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Aug 12, 2014 6:26:24 PM
 *
 */
public class GzipTest {

	@Test
	public void test(){
		String orignContent = "Hello World, Jonathan Q. Bo";
		
		Gzip zipper = new Gzip();
		try {
			System.out.println("Before Zipped: " + orignContent);
			
			String zippedContent = zipper.CompressString(orignContent);
			System.out.println("After zipped: " + zippedContent);
			
			String unzippedContent = zipper.deCompressString(zippedContent);
			System.out.println("After unzipped: " + unzippedContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
