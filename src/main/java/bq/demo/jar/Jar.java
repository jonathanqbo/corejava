/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.jar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

/**
 * <b> Demo of JDK package java.util.jar</b>
 * 
 * <p>
 *  
 * </P>
 * 
 * @see {@link JarFile}, {@link JarInputStream}, {@link JarOutputStream}
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class Jar {

	/**
	 * 
	 * @param sourcejarfilepath
	 * @param targetfolderpath : need end with "/"(File.seperator);
	 */
	public void unpackJarFile(String sourcejarfilepath, String targetfolderpath){
		try {
			JarFile jarfile = new JarFile(sourcejarfilepath);
			
			// here get all entry, include child, child's child, so do not need do recursion
			Enumeration<JarEntry> jarfileentries = jarfile.entries();
			while(jarfileentries.hasMoreElements()){
				JarEntry jarentry = jarfileentries.nextElement();
				
				if(jarentry.isDirectory()){
					File file = new File(targetfolderpath + jarentry.getName());
					file.mkdirs();
				}
				else{
					InputStream is = jarfile.getInputStream(jarentry);
					BufferedInputStream bis = new BufferedInputStream(is);
					FileOutputStream fos = new FileOutputStream(targetfolderpath + jarentry.getName());
					byte[] buf = new byte[1024];
					int length = 0;
					while((length = bis.read(buf)) != -1)
						fos.write(buf, 0 ,length);
					
					fos.flush();
					fos.close();
					is.close();
					bis.close();
				}
			}
			
			jarfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param sourcefolderpath : not end with "/"
	 * @param targetfolderpath : can be "null" or end with "/"
	 * @param targetjarfilename :
	 */
	public void packJarFile(String sourcefolderpath, String targetfolderpath, String targetjarfilename){
		try {
			FileOutputStream fos = new FileOutputStream(targetfolderpath+targetjarfilename);
			JarOutputStream jos = new JarOutputStream(new BufferedOutputStream(fos));
			
			File file = new File(sourcefolderpath);
			packOneFile("",jos,file);
			
			jos.flush();
			jos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void packOneFile(String basefolder, JarOutputStream jos, File file) throws IOException {
		if(file.isFile()){
			FileInputStream fis = new FileInputStream(file);

			JarEntry entry = new JarEntry(basefolder + file.getName());
			jos.putNextEntry(entry);
			byte[] buf = new byte[1024];
			int length = 0;
			while((length=fis.read(buf)) != -1){
				jos.write(buf,0,length);
			}
			
			fis.close();
		}
		else{
			//NOTICE: entry of folder must end with "/", 
			//otherwise will create blank file which name is the same with folder 
			String newbasefolder = basefolder + file.getName() + "/";
			JarEntry entry = new JarEntry(newbasefolder);
			jos.putNextEntry(entry);
			for(File childfile : file.listFiles()){
				packOneFile(newbasefolder, jos, childfile);
			}
		}
	}
	
}
