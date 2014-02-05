/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */

package bq.demo.decode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <b> Demo of JDK Base64 Decode and Encode</b>
 * 
 * <p>
 *  
 * </P>
 * 
 * @see {@linkplain BASE64Decoder}, {@link BASE64Encoder}
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class Base64 {

	/**
	 * Usage of BASE64Encoder.encode()
	 * 
	 * @param orignData
	 * @return
	 */
	public String encodeString(String orignData){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(orignData.getBytes());
	}
	
	/**
	 * Usage of BASE64Encoder.encode()
	 * 
	 * @param imgfilepath
	 * @return
	 */
	public String encodeImage(String imgfilepath){
		// read image
		BufferedImage image = null;
		try {
			File file = new File(imgfilepath);
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read image byte
		byte[] imagebytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png" ,bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagebytes = bos.toByteArray();
		
		//encode
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(imagebytes);
	}
	
	/**
	 * Usage of BASE64Encoder.decode()
	 * 
	 * @param orignData
	 * @return
	 */
	public String decodeString(String orignData){
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] decodedbytes = decoder.decodeBuffer(orignData);
			return new String(decodedbytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Usage of BASE64Encoder.decode()
	 * 
	 * NOTICE: origin file do encode and decode, don't have the same size.
	 * 
	 * @param orignData
	 * @param imgfilepath
	 */
	public void decodeImage(String orignData, String imgfilepath){
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//
			byte[] decodedbytes = decoder.decodeBuffer(orignData);
			
			// create file
			FileOutputStream fos = new FileOutputStream(imgfilepath);
			fos.write(decodedbytes);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
