package bq.demo.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.Test;

import com.sun.crypto.provider.SunJCE;

import bq.demo.util.TimeCost;

/**
 * <b> Demo of JDK Symmetric Cipher Algorithm (encryption and decryption)</b>
 * 
 * <p>
 *    Show the usage of different cryptography:DES,3DES,AES
 *    2)Asymmetric: DSA,RSA
 * </p>
 * 
 * @author jonathan.q.bo@gmail.com
 * @see {@linkplain Cipher}, {@linkplain KeyGenerator}, {@linkplain SecretKey}	
 */
public class SymmetricCipherTest {
	
	private static final String DES="DES";
	private static final String DES3 = "DESede";
	private static final String AES = "AES";
	

	@Test
	public void testDES() throws IllegalBlockSizeException, BadPaddingException{
		Security.addProvider(new SunJCE());
		
		//encryption
		SecretKey key = generateKey(DES);
		Cipher cipher = createCipher(key,Cipher.ENCRYPT_MODE, DES);

		TimeCost.begin("des encryption");
		byte[] encrypdata = cipher.doFinal(getTestData());
		TimeCost.end();
		System.out.println("des encryption result:" + new String(encrypdata));
		
		//decrption
		Cipher cipher2 = createCipher(key,Cipher.DECRYPT_MODE, DES);

		TimeCost.begin("des decryption");
		byte[] decrypdata = cipher2.doFinal(encrypdata);
		TimeCost.end();
		System.out.println("des decryption result:" + new String(decrypdata));
	}

	@Test
	public void test3DES() throws IllegalBlockSizeException, BadPaddingException{
		Security.addProvider(new SunJCE());
		
		//encryption
		SecretKey key = generateKey(DES3);
		Cipher cipher = createCipher(key,Cipher.ENCRYPT_MODE, DES3);

		TimeCost.begin("DES3 encryption");
		byte[] encrypdata = cipher.doFinal(getTestData());
		TimeCost.end();
		System.out.println("DES3 encryption result:" + new String(encrypdata));
		
		//decrption
		Cipher cipher2 = createCipher(key,Cipher.DECRYPT_MODE, DES3);

		TimeCost.begin("DES3 decryption");
		byte[] decrypdata = cipher2.doFinal(encrypdata);
		TimeCost.end();
		System.out.println("DES3 decryption result:" + new String(decrypdata));
	}
	
	@Test
	public void testAES() throws IllegalBlockSizeException, BadPaddingException{
		Security.addProvider(new SunJCE());
		
		//encryption
		SecretKey key = generateKey(AES);
		Cipher cipher = createCipher(key,Cipher.ENCRYPT_MODE, AES);

		TimeCost.begin("AES encryption");
		byte[] encrypdata = cipher.doFinal(getTestData());
		TimeCost.end();
		System.out.println("AES encryption result:" + new String(encrypdata));
		
		//decrption
		Cipher cipher2 = createCipher(key,Cipher.DECRYPT_MODE, AES);

		TimeCost.begin("AES decryption");
		byte[] decrypdata = cipher2.doFinal(encrypdata);
		TimeCost.end();
		System.out.println("AES decryption result:" + new String(decrypdata));
	}
	
	private Cipher createCipher(SecretKey key, int mode, String encryptionType) {
		try {
			Cipher cipher = Cipher.getInstance(encryptionType);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private SecretKey generateKey(String encrytpionType) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(encrytpionType);
			SecretKey key = keygen.generateKey();
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	private byte[] getTestData() {
		return new String("Jonathan.q.bo@gmail.com").getBytes();
	}
}
