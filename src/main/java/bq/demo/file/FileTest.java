/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
package bq.demo.file;

import java.io.File;

import org.junit.Test;

public class FileTest {

	/**
	 * show the different usage of mkdir() and mkdirs():
	 * <p>
	 * <li>mkdir() can only create one level folder
	 * <li>mkdirs() can create multi levels folder
	 */
	@Test
	public void testMkdirAndMkdirs(){
		String mkdirpath = "C:\\boqi\\file\\mkdir1\\mkdir2\\mkdir3\\";
		String mkdirpath2 = "C:\\boqi\\file\\mkdir1\\";
		String mkdirspath = "C:\\boqi\\file\\mkdirs1\\mkdirs2\\mkdirs3\\";
		
		// MKDIR create folder FAILED !
		File file = new File(mkdirpath);
		file.mkdir();
		
		// MKDIR create one folder SUCCESSFULLY !
		File file1 = new File(mkdirpath2);
		file1.mkdir();
		
		// MKDIRS create folder SUCCESSFULLY !
		File file2 = new File(mkdirspath);
		file2.mkdirs();
		
	}
	
}
