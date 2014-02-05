/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * <b> Demo of JDK GZipOutputStream</b>
 * 
 * <p>
 *  
 * </P>
 * 
 * @see GZIPOutputStream
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class Gzip {

	public void compressFile(String srcFile, String targetFile){
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(targetFile);
			GZIPOutputStream gzos = new GZIPOutputStream(fos);
			
			byte[] buf = new byte[1024];
			int length = 0;
			length = fis.read(buf);
			while( length != -1){
				gzos.write(buf, 0, length);
				length = fis.read(buf);
			}
			
			fis.close();
			gzos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
