/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
package bq.demo.zip;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

public class ZIPTest{
	
	/**
	 * Test JDKDeflater compress file to .rar format file
	 * result: file generated cannot be read by winrar
	 */
	@Test
	public void testDeflaterToZipFile(){
		String sourceFile = "C:\\boqi\\zip\\ws_gen.txt";
		String targetFile = "C:\\boqi\\zip\\testDeflaterToZipFile.rar";
		JDKDeflater deflater = new JDKDeflater();
		deflater.deflateFile(sourceFile, targetFile);
	}
	
	/**
	 * Test Gzip compress file to .rar format file
	 * result: file generated can be read by winrar
	 */
	@Test
	public void testGipToZipFile(){
		String sourceFile = "C:\\boqi\\zip\\ws_gen.txt";
		String targetFile = "C:\\boqi\\zip\\testGipToZipFile.rar";
		Gzip gzip = new Gzip();
		gzip.compressFile(sourceFile, targetFile);
	}
	
	/**
	 * Test Zip compress file and folder to .rar format file
	 * result:
	 */
	@Test
	public void testZipToZipFile(){
		String sourceFile = "C:\\boqi\\zip\\ws_gen.txt";
		String targetFile = "C:\\boqi\\zip\\testZipToZipFile.rar";
		Zip zip = new Zip();
		zip.compress(sourceFile, targetFile);
		
		String sourceFolder = "C:\\boqi\\zip\\ddd";
		String targetFile2 = "C:\\boqi\\zip\\testZipFolderToZipFile.rar";
		zip.compress(sourceFolder, targetFile2);
	}
	
}	
	
class ZipObject implements Serializable{
	private static final long serialVersionUID = -5170723631383730797L;

	private String name;
	private String code;
	private Date birthday;
	
	public ZipObject(String name, String code, Date birthday){
		this.name = name;
		this.code = code;
		this.birthday = new Date(birthday.getTime());
	}

	@Override
	public String toString() {
		return name + "/" + code + "/" + birthday.toString();
	}
	
}
