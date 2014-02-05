package bq.demo.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import bq.demo.util.TimeCost;

import com.sun.crypto.provider.SunJCE;

/**
 * <b> Demo of JDK Symmetric Cipher Algorithm (encryption and decryption)</b>
 * 
 * <p>
 *    Show the usage of different cryptography: 
 *    <li>DSA : generally used for signature , Cipher cannot support DSA instance
 *    <li>RSA
 * </p>
 * 
 * @author jonathan.q.bo@gmail.com
 * @see {@linkplain Cipher}, {@linkplain KeyPairGenerator}, {@linkplain KeyPair}	
 */
public class AsymmetricCipherTest {
	
	private static final String DSA = "DSA";
	private static final String RSA = "RSA";
	
	/**
	 * Cipher cannot support "DSA"
	 * Signature support "DSA" 
	 * 
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 */
	@Test
	public void testDSA() throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException{
		System.out.println("---------------------------");
		Security.addProvider(new SunJCE());
		
		//encryption,
		KeyPair keypair = generateKey(DSA);
		//ERROR! NOTICE: Cipher cannot support "DSA"
		Cipher cipher = createCipher(keypair.getPublic(), Cipher.ENCRYPT_MODE, RSA);

//		TimeCost.begin("DSA encryption");
//		byte[] encrypdata = cipher.doFinal(getTestData());
//		TimeCost.end();
//		System.out.println("DSA encryption result:" + new String(encrypdata));
		
		// sign
		Signature signature = Signature.getInstance("DSA");
		signature.initSign(keypair.getPrivate());
		signature.update(getTestData());
		byte[] signdata = signature.sign();
		System.out.println("DSA signed result:" + new String(signdata));
		
		// check sign
//		Signature signature2 = Signature.getInstance("DSA");
		signature.initVerify(keypair.getPublic());
		signature.update(getTestData());
		boolean isValid = signature.verify(signdata);
		System.out.println("DSA check result:" + isValid);
	}
	
	@Test
	public void testRSA() throws IllegalBlockSizeException, BadPaddingException{
		System.out.println("---------------------------");
		Security.addProvider(new SunJCE());
		
		//encryption
		KeyPair keypair = generateKey(RSA);
		Cipher cipher = createCipher(keypair.getPublic(),Cipher.ENCRYPT_MODE, RSA);

		TimeCost.begin("RSA encryption");
		byte[] encrypdata = cipher.doFinal(getTestData());
		TimeCost.end();
		System.out.println("RSA encryption result:" + new String(encrypdata));
		
		//decrption
		Cipher cipher2 = createCipher(keypair.getPrivate(),Cipher.DECRYPT_MODE, RSA);

		TimeCost.begin("RSA decryption");
		byte[] decrypdata = cipher2.doFinal(encrypdata);
		TimeCost.end();
		System.out.println("RSA decryption result:" + new String(decrypdata));
	}

	private Cipher createCipher(Key key, int mode, String encryptionType) {
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

	private KeyPair generateKey(String encrytpionType) {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance(encrytpionType);
			KeyPair key = keygen.generateKeyPair();
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
