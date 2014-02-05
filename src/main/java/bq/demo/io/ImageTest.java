/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
package bq.demo.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {

	/**
	 * image file -> BufferedImage
	 * USUAGA: ImageIO.read()
	 */
	@Test
	public void testImageRead(){
		String imagefilepath = "C:\\boqi\\io\\�������׶�԰.png";
		try {
			// read bufferedimage from file
			File file = new File(imagefilepath);
			BufferedImage image = ImageIO.read(new FileInputStream(file));
			
			System.out.println("image file name : " + file.getName());
			System.out.println("image width :" + image.getWidth());
			System.out.println("image height : " + image.getHeight());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * BufferedImage -> byte[]
	 * USUAGE: ImageIO.write()
	 */
	@Test
	public void testImageToBytes(){
		try {
			String imagefilepath = "C:\\boqi\\io\\�������׶�԰.png";
			File file = new File(imagefilepath);
			BufferedImage image = ImageIO.read(new FileInputStream(file));
			
			// read byte
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", bos);
			byte[] imagebytes = bos.toByteArray();
			
			//
			System.out.println("image bytes length : " + imagebytes.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * mutual transformation between different kinds of image types
	 * USUAGE: ImageIO.write();
	 */
	@Test
	public void testTranformImageFormat(){
		// png -> jpg
		try {
			String imagefilepath = "C:\\boqi\\io\\�������׶�԰.png";
			
			// read png
			File file = new File(imagefilepath);
			BufferedImage image = ImageIO.read(new FileInputStream(file));

			// to png
			String pngimagefilepath = "C:\\boqi\\io\\�������׶�԰2.png";
			ImageIO.write(image, "png", new FileOutputStream(pngimagefilepath));
			
			// to jpg
			String jpgimagefilepath = "C:\\boqi\\io\\�������׶�԰.jpg";
			ImageIO.write(image, "jpg", new FileOutputStream(jpgimagefilepath));
			
			// to bmp
			String bmpimagefilepath = "C:\\boqi\\io\\�������׶�԰.bmp";
			ImageIO.write(image, "bmp", new FileOutputStream(bmpimagefilepath));
			
			// to gif
			String gifimagefilepath = "C:\\boqi\\io\\�������׶�԰.gif";
			ImageIO.write(image, "gif", new FileOutputStream(gifimagefilepath));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
