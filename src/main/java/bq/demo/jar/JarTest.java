/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
package bq.demo.jar;

import org.junit.Test;

public class JarTest {

	@Test
	public void testPackJar(){
		String sourcefolder = "C:\\boqi\\jar\\ddd\\";
		String targetfolder = "C:\\boqi\\jar\\packfolder\\";
		String targetfile = "myjarfile.jar";
		
		Jar jar = new Jar();
		jar.packJarFile(sourcefolder, targetfolder, targetfile);
	}
	
	@Test
	public void testUnpackJar(){
		String jarfile = "C:\\boqi\\jar\\packfolder\\myjarfile.jar";
		String targetfolder = "C:\\boqi\\jar\\unpackfolder\\";
		
		Jar jar = new Jar();
		jar.unpackJarFile(jarfile, targetfolder);
	}
	
}
