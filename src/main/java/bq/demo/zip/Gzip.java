/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
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
	
	public String CompressString(String originContent) throws IOException{
		ByteArrayOutputStream bo = null;
		GZIPOutputStream gzos = null;
		try{
			bo = new ByteArrayOutputStream();
			gzos = new GZIPOutputStream(bo);
			
			gzos.write(originContent.getBytes());
			
		} finally{
			gzos.close();
			bo.close();
		}
		
		// Must close IO stream first, and then can get whole zipped value
		return new String(bo.toByteArray());
	}
	
	public String deCompressString(String zipedContent) throws IOException{
		byte[] byteContent = zipedContent.getBytes();
		
		InputStream gis = null;
		ByteArrayOutputStream bo = null;
		try{
			gis = new GZIPInputStream(new ByteArrayInputStream(byteContent));
//			gis = new ByteArrayInputStream(byteContent);
			bo = new ByteArrayOutputStream();
			
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = gis.read(buf)) > 0){
				bo.write(buf, 0, length);
			}
			
			return new String(bo.toByteArray());
		} finally{
			bo.close();
			gis.close();
		}
	}
	
}
