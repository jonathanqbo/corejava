/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <b> Demo of JDK ZipOutputStream</b>
 * 
 * <p>
 * NOTICE: JDK ZipOutputStream cannot support Chinese file name, 
 * if Chinese is necessary, we can replace it with apache ZipOutputStream. 
 * </P>
 * 
 * @see ZipOutputStream
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class Zip {

	/**
	 * compress source file or folder to target zip file
	 * 
	 * @param srcFile
	 * @param targetFile
	 */
	public void compress(String srcDirectory, String targetFile){
		try {
			File directoryOrFile = new File(srcDirectory);
			
			FileOutputStream fos = new FileOutputStream(targetFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			// directory
			if(directoryOrFile.isDirectory()){
				compressDirectory(zos, "", directoryOrFile);
			}
			else{
				compressFile(zos, "", directoryOrFile);
			}
			
			zos.flush();
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param zos
	 * @param zipdirbase , must end with "/",  eg:  "C:/Users/boqi/Desktop/"
	 * @param directory
	 * @throws IOException
	 */
	private void compressDirectory(ZipOutputStream zos, String zipdirbase, File directory) throws IOException{
		File[] files = directory.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				if(zipdirbase.trim().length() == 0)
					compressDirectory(zos, zipdirbase+file.getName()+"/", file);
				else{
					zos.putNextEntry(new ZipEntry(zipdirbase));
					compressDirectory(zos, zipdirbase+file.getName()+"/", file);
				}
			}
			else
				compressFile(zos, zipdirbase, file);
		}
	}
	
	/**
	 * 
	 * @param zos
	 * @param zipdirbase , must end with "/",  eg:  "C:/Users/boqi/Desktop/"
	 * @param file
	 * @throws IOException
	 */
	private void compressFile(ZipOutputStream zos, String zipdirbase, File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		
		//
		if(zipdirbase.trim().length() != 0)
			zos.putNextEntry(new ZipEntry(new String((zipdirbase + file.getName()).getBytes("gb2312"))));
		else
			zos.putNextEntry(new ZipEntry(file.getName()));
		
		//
		byte[] buf = new byte[1024];
		int length = 0;
		length = fis.read(buf);
		while( length != -1){
			zos.write(buf, 0, length);
			length = fis.read(buf);
		}
		
		fis.close();
	}
}
