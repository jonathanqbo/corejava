package bq.demo.security;
/**
 * JUST FOR STUDY, COPYRIGHT JONATHAN.Q.BO@GMAIL.COM  2013.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * <b> Demo of JDK Digest Algorithm</b>
 * <p>
 *    Show the digest algorithm usage of MD5 and SHA-1
 * </p>
 * <p>
 *    There is a opensource alternation "bouncycastle" (bcprov-jdk14.jar), 
 *    it support more digest algorithm, but has less performance.
 * </P>
 * 
 * @see {@linkplain MessageDigest}
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class DigestTest {
	
	private String hello1 = "I am loving living every single day but sometimes I feel so. I hope to find a little peace of mind and I just want to know. And who can heal those tiny broken hearts, and what are we to be. Where is home on the Milky way of stars, I dry my eyes again. In my dreams I am not so far away from home. What am I in a world so far away from home. All my life all the time so far away from home. Without you I will be so far away from home. If we could make it through the darkest night we have a brighter day. The world I see beyond your pretty eyes, makes me want to stay. d who can heal those tiny broken hearts, and what are we to be.";
	private String hello2 = hello1 + "!";
	
	@Test
	public void testRSA(){
		try {
			MessageDigest digester = MessageDigest.getInstance("SHA-1");

			long begintime = System.currentTimeMillis();
			//do digest!
			byte[] rsabytes = digester.digest(hello1.getBytes());
			long endtime = System.currentTimeMillis();
			System.out.println("---------- sha-1 1 of hello1 ----------");
			System.out.println("cost time(ms) : " + (endtime-begintime));
			System.out.println(getStringFromByte(rsabytes));
			
			begintime = System.currentTimeMillis();
			//do digest!
			byte[] rsabytes2 = digester.digest(hello2.getBytes());
			endtime = System.currentTimeMillis();
			System.out.println("---------- sha-1 2 of hello2 ----------");
			System.out.println("cost time(ms) : " + (endtime-begintime));
			System.out.println(getStringFromByte(rsabytes2));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMD5(){
		try {
			MessageDigest digester = MessageDigest.getInstance("MD5");
			long begintime = System.currentTimeMillis();
			byte[] md5bytes = digester.digest(hello1.getBytes());
			long endtime = System.currentTimeMillis();
			System.out.println("---------- md5 1 of hello1 ----------");
			System.out.println("cost time(ms) : " + (endtime-begintime));
			System.out.println(getStringFromByte(md5bytes));
			
			begintime = System.currentTimeMillis();
			byte[] md5bytes2 = digester.digest(hello2.getBytes());
			endtime = System.currentTimeMillis();
			System.out.println("---------- md5 2 of hello2 ----------");
			System.out.println("cost time(ms) : " + (endtime-begintime));
			System.out.println(getStringFromByte(md5bytes2));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * TODO TODO
	 * 
	 * @param domain
	 * @return
	 */
	private String getStringFromByte(byte[] domain){
		StringBuffer md5StrBuff = new StringBuffer();  

		for (int i = 0; i < domain.length; i++) {  
            if (Integer.toHexString(0xFF & domain[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & domain[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & domain[i]));  
        }
        
        return md5StrBuff.toString();
	}
	
}
