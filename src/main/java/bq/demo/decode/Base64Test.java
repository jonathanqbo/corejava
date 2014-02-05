/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
package bq.demo.decode;

import org.junit.Test;

public class Base64Test {

	@Test
	public void testEncodeAndDecodeString(){
		String orignData = "http://www.google.cm/��ã�����";
		
		Base64 base64 = new Base64();
		String encodeString = base64.encodeString(orignData);
		System.out.println("after encode: " + encodeString);
		
		String decodeString = base64.decodeString(encodeString);
		System.out.println("after decode: " + decodeString);
	}
	
	@Test
	public void testEncodeAndDecodeImage(){
		String imgfilepath = "C:\\boqi\\decode\\�������׶�԰.png";
		
		Base64 base64 = new Base64();
		String encodeImage = base64.encodeImage(imgfilepath);
		System.out.println("after encode:");
		System.out.println(encodeImage);
		
		//NOTIFY:
		base64.decodeImage(encodeImage, "C:\\boqi\\decode\\�������׶�԰decoded.png");
	}
	
}
