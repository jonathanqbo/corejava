package bq.demo.security;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import org.bouncycastle.asn1.DERConstructedSequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInputStream;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import org.junit.Test;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.x509.X509CertImpl;

/**
 * 
 * @author boqi
 *
 */
public class CATest {
	
	private static final String KEYSTORE_FILE="C:\\boqi\\security\\cademo.keystore";
	private static final String PRIVATEKEY_FILE="C:\\boqi\\security\\cademo.txt";
	private static final String KEYSTORE_PASSWORD="password";
	// signature includes digest and asymmetric cipher
	private static final String SIGNATURE_ALGORITHM="MD5withRSA";
	private static final String ALISE_CA="ca";
	
	@Test
	public void testKeystore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InvalidKeySpecException, InvalidKeyException, SecurityException, SignatureException, UnrecoverableKeyException{
		//
		Security.addProvider(new BouncyCastleProvider());
		
		//
		CATest tester = new CATest();
		
		// private key
		String privatekeyFile = "C:\\boqi\\security\\privatekey1.txt";
		tester.generatePrivateKeyToFile(privatekeyFile);
		tester.readPrivateKeyFromFile(privatekeyFile);
		
		// certificate
		String cerFile = "C:\\boqi\\security\\cer1.cer";
		tester.generateCertificateToFile(cerFile);
		tester.readCertificateFromFile(cerFile);
		
		// keystore
		String keystoreFile = "C:\\boqi\\security\\keystore1.keystore";;
		String keystorePassword = "12345678";
		tester.generateKeystoreToFile(keystoreFile, keystorePassword);
		tester.storePrivatekeyAndCertificate2Keystore(keystoreFile, keystorePassword,"root1");
		tester.readPrivatekeyAndCertificateFromKeystore(keystoreFile, keystorePassword,"root1");
	}
	
	@Test
	public void testCAByKeystore(){
		CATest tester = new CATest();
		
		// create CA
		tester.createCA();
		
		// create Applicant
		Certificate cert1 = tester.createApplicantCertificate("applicant1");
		PKCS10CertificationRequest certificationquest = tester.createCertificateRequest();
		tester.signCertificate(certificationquest);
		tester.vertifyCertificate(cert1);
		
		Certificate cert1Signed = tester.signatureCertificateWithCA(cert1);
		tester.vertifyCertificate(cert1Signed);
	}

	private void createCA() {
		
	}

	private void generatePrivateKeyToFile(String privatekeyFile) throws NoSuchAlgorithmException, IOException {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		KeyPair key = keygen.generateKeyPair();
		PrivateKey privateKey = key.getPrivate();
		byte[] encodedKey = privateKey.getEncoded();
		
		// serialize to file
		flushBase64ToFile(encodedKey, privatekeyFile);
		
		// print key
		printPrivatekey(privateKey);
	}
	
	private void readPrivateKeyFromFile(String privatekeyFile) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		// read file
		byte[] decodedKey = readBase64File(privatekeyFile);
		
		// byte[] to PrivateKey
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    PrivateKey privateKey = kf.generatePrivate(spec);
	    
	    // print key
	    printPrivatekey(privateKey);
	}
	
	private void flushBase64ToFile(byte[] bytes, String filepath) throws IOException{
		// to make it readable, base64 it
		BASE64Encoder base64 = new BASE64Encoder();
		String base64code = base64.encode(bytes);
		
		// serialize to file
		FileOutputStream fos = new FileOutputStream(filepath);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeUTF(base64code);
		dos.close();
		fos.close();
	}
	
	private byte[] readBase64File(String filepath) throws IOException{
		// read file
		FileInputStream fis = new FileInputStream(filepath);
		DataInputStream dis = new DataInputStream(fis);
		String base64encodedPrivateKey = dis.readUTF();
		
		// base64 to byte[]
		BASE64Decoder base64 = new BASE64Decoder();
		byte[] decodedbytes = base64.decodeBuffer(base64encodedPrivateKey);

		dis.close();
		fis.close();
		
		return decodedbytes;
	}
	
	private void printPrivatekey(PrivateKey privatekey){
	    BASE64Encoder base64encoder = new BASE64Encoder();
	    String base64encodedKey = base64encoder.encode(privatekey.getEncoded());
	    System.out.println(base64encodedKey);
	}
	
	private void generateCertificateToFile(String privatekeyFile) throws InvalidKeyException, SecurityException, SignatureException, CertificateEncodingException, IOException, NoSuchAlgorithmException {
		Certificate certificate = createCertificate("filecer1");
		flushBase64ToFile(certificate.getEncoded(), privatekeyFile);
	}
	
	/**
	 * BoundryCastle Impl
	 * 
	 * @param privatekeyFile
	 * @throws IOException
	 * @throws CertificateException
	 */
	private void readCertificateFromFile(String privatekeyFile) throws IOException, CertificateException {
		byte[] certificatebytes = readBase64File(privatekeyFile);
		ByteInputStream bis = new ByteInputStream(certificatebytes, certificatebytes.length);

		CertificateFactory certCF = CertificateFactory.getInstance("X.509");
		X509Certificate caCert = (X509Certificate) certCF.generateCertificate(bis);
		bis.close();
		
	    printCertificate(caCert);
	}
	
	private void readPrivatekeyAndCertificateFromKeystore(String keystoreFile,
			String keystorePassword, String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		KeyStore ks = KeyStore.getInstance("JKS");
		char[] password = keystorePassword.toCharArray();
		
		// load
		FileInputStream fis = new FileInputStream(keystoreFile);
		ks.load(fis, password);
		
		PrivateKey privatekey = (PrivateKey) ks.getKey(alias, password);
		Certificate certificate = ks.getCertificate(alias);
		
		printPrivatekey(privatekey);
		printCertificate((X509Certificate) certificate);
	}
	
	private void storePrivatekeyAndCertificate2Keystore(String keystoreFile,
			String keystorePassword, String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InvalidKeyException, SecurityException, SignatureException {
		KeyStore ks = KeyStore.getInstance("JKS");
		char[] password = keystorePassword.toCharArray();
		
		// load
		FileInputStream fis = new FileInputStream(keystoreFile);
		ks.load(fis, password);
		
		// add
		KeyPair keypair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PrivateKey privatekey = keypair.getPrivate();
		PublicKey publickey = keypair.getPublic();
		
		Certificate certificate = createCertificate(alias, publickey, privatekey);
		ks.setKeyEntry(alias, privatekey, password, new Certificate[]{certificate});
		// ERROR! if user below two sentences replace above sentence 
//		ks.setKeyEntry(alias, enkey.getEncryptedData(), new Certificate[]{certificate});
//		ks.setCertificateEntry(alias, certificate);
		
		//Then store
		FileOutputStream fos = new FileOutputStream(keystoreFile);
		ks.store(fos, password);
		
		fos.close();
	}
	
	private void generateKeystoreToFile(String keystoreFile,
			String keystorePassword) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");
		FileOutputStream fos = new FileOutputStream(keystoreFile);
		char[] password = keystorePassword.toCharArray();
		
		//MUST load first! or error!
		ks.load(null, password);
		
		//Then store
		ks.store(fos, password);
		
		fos.close();
	}
	
	private void printCertificate(X509Certificate certificate){
		System.out.println("serial number :" + certificate.getSerialNumber());
		System.out.println("issuer dn:" + certificate.getIssuerDN());
		System.out.println("suggest dn:" + certificate.getSubjectDN());
		System.out.println("unvalid before:" + certificate.getNotBefore());
		System.out.println("unvalid after:" + certificate.getNotAfter());
	}
	
	private PKCS10CertificationRequest createCertificateRequest(String applicantID) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException{
		String signaturealogrithm = SIGNATURE_ALGORITHM;
		X509Name dn = createX509Name("CN","BJ","BEIJING","BQ","DEMO",applicantID);
		KeyPair keypair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publickey = keypair.getPublic();
		PrivateKey privatekey = keypair.getPrivate();
		
		PKCS10CertificationRequest certificateRequest = new PKCS10CertificationRequest(signaturealogrithm, dn, publickey, null, privatekey);
		return certificateRequest;
	}
	
	/**
	 * Signature itself!
	 * 
	 * @param id
	 * @param publickey
	 * @param privatekey
	 * @return
	 * @throws InvalidKeyException
	 * @throws SecurityException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings("deprecation")
	private Certificate createCertificate(String id) throws InvalidKeyException, SecurityException, SignatureException, NoSuchAlgorithmException{
		// keypair
		KeyPair keypair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publickey = keypair.getPublic();
		PrivateKey privatekey = keypair.getPrivate();
		
		return createCertificate(id, publickey, privatekey);
	}
	
	/**
	 * 
	 * @param id
	 * @param publickey
	 * @param privatekey
	 * @return
	 * @throws InvalidKeyException
	 * @throws SecurityException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings("deprecation")
	private Certificate createCertificate(String id, PublicKey publickey, PrivateKey privatekey) throws InvalidKeyException, SecurityException, SignatureException, NoSuchAlgorithmException{
		//
		X509V3CertificateGenerator cergener = new X509V3CertificateGenerator();

		// serial number
		BigInteger serialnumber = BigInteger.probablePrime(20, new Random());
		cergener.setSerialNumber(serialnumber);
		
		// valid time
		Date validdateBefore = new Date(2013,8,18);
		Date validdateAfter = new Date(2018,8,18);
		cergener.setNotBefore(validdateBefore);
		cergener.setNotAfter(validdateAfter);
		
		// public key
		cergener.setPublicKey(publickey);
		
		// signature algorithm
		cergener.setSignatureAlgorithm(SIGNATURE_ALGORITHM);
		
		// about Applicant
		X509Name dn = createX509Name("CN","BJ","BEIJING","BQ","DEMO",id);;
		cergener.setSubjectDN(dn);
		
		// about authority
		X509Name cadn = dn;
		cergener.setIssuerDN(cadn);
		
		// do generation
		return cergener.generateX509Certificate(privatekey);
	}
	
	@SuppressWarnings("deprecation")
	private Certificate signatureCertificate(PKCS10CertificationRequest certificationquest, PrivateKey caprivatekey, X509Certificate cacertificate) throws InvalidKeyException, SecurityException, SignatureException, IOException, NoSuchAlgorithmException, NoSuchProviderException{
		X509V3CertificateGenerator cergener = new X509V3CertificateGenerator();

		// serial number
		BigInteger serialnumber = BigInteger.probablePrime(20, new Random());
		cergener.setSerialNumber(serialnumber);
		
		// valid time
		Date validdateBefore = new Date(2013,8,18);
		Date validdateAfter = new Date(2018,8,18);
		cergener.setNotBefore(validdateBefore);
		cergener.setNotAfter(validdateAfter);
		
		// public key
		PublicKey publickey = certificationquest.getPublicKey();
		cergener.setPublicKey(publickey);
		
		// signature algorithm
		String signatureAlgorithm = certificationquest.getSignatureAlgorithm().toString();
		cergener.setSignatureAlgorithm(signatureAlgorithm);
		
		// about Applicant
		X509Name dn = certificationquest.getCertificationRequestInfo().getSubject();
		cergener.setSubjectDN(dn);
		
		// about authority
		DERInputStream dnStream = new DERInputStream(new ByteArrayInputStream(cacertificate.getSubjectX500Principal().getEncoded()));
		DERConstructedSequence  dnSequence = (DERConstructedSequence)dnStream.readObject();
		X509Name cadn = new X509Name(dnSequence);
		cergener.setIssuerDN(cadn);
		
		// do generation
		return cergener.generateX509Certificate(caprivatekey);
	}

//	private void generateKeystore(String keystorefilepath,String keystorepassword) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
//		KeyStore ks = KeyStore.getInstance("JKS");
//		FileOutputStream fos = new FileOutputStream(keystorefilepath);
//		char[] password = keystorepassword.toCharArray();
//		
//		//MUST load first! or error!
//		ks.load(null, password);
//		//Then store
//		ks.store(fos, password);
//		
//		fos.close();
//	}
//	
//	/**
//	 * different with normal certificate
//	 * DN name
//	 * use publickey's privatekey to do signature
//	 * 
//	 * @throws SignatureException 
//	 * @throws SecurityException 
//	 * @throws InvalidKeyException 
//	 * @throws NoSuchAlgorithmException 
//	 * @throws IOException 
//	 * @throws KeyStoreException 
//	 * @throws CertificateException 
//	 */
//	@SuppressWarnings("deprecation")
//	private void createCA() throws InvalidKeyException, SecurityException, SignatureException, NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException{
//		// make keypair
//		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
//		KeyPair key = keygen.generateKeyPair();
//		PrivateKey privateKey = key.getPrivate();
//		PublicKey publicKey = key.getPublic();
//		
//		// create certificate
//		X509Name dn = createX509Name("CN","BJ","BEIJING","BQ","DEMO","CA");
//		Certificate X509Certificate = createCertificate(dn, publicKey, dn, privateKey);
//		
//		// open keystore
//		KeyStore ks = KeyStore.getInstance("JKS");
//		FileInputStream fis = new FileInputStream(KEYSTORE_FILE);
//		char[] password = KEYSTORE_PASSWORD.toCharArray();
//		
//		//MUST load first! or error!
//		ks.load(fis, password);
//		
//		//store privatekey and certificate to keystore
//		ks.setCertificateEntry(ALISE_CA, X509Certificate);
//		ks.setKeyEntry(ALISE_CA, privateKey, password, new Certificate[]{X509Certificate});
//		
//		//Then store
//		FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE);
//		ks.store(fos, password);
//		
//		fos.close();
//	}
//	
//	@SuppressWarnings("deprecation")
//	private void createNormal() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, InvalidKeyException, SecurityException, SignatureException{
//		
//		// make keypair
//		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
//		KeyPair key = keygen.generateKeyPair();
//		PrivateKey privateKey = key.getPrivate();
//		PublicKey publicKey = key.getPublic();
//		
//		// get CA publickey and privatekey
//		// open keystore
//		KeyStore ks = KeyStore.getInstance("JKS");
//		FileInputStream fis = new FileInputStream(KEYSTORE_FILE);
//		char[] password = KEYSTORE_PASSWORD.toCharArray();
//		
//		ks.load(fis, password);
//		fis.close();
//		
//		X509Certificate cacertificate = (X509Certificate) ks.getCertificate(ALISE_CA);
//		PrivateKey caprivatekey = (PrivateKey) ks.getKey(ALISE_CA, password);
//		
//		// ? TODO
//		DERInputStream dnStream = new DERInputStream(new ByteArrayInputStream(cacertificate.getSubjectX500Principal().getEncoded()));
//		DERConstructedSequence  dnSequence = (DERConstructedSequence)dnStream.readObject();
//		X509Name cadn = new X509Name(dnSequence);
//		
//		X509Name dn = createX509Name("CN","BJ","BEIJING","BQ","DEMO","normal1");
//		Certificate certificate = createCertificate(dn, publicKey, cadn, caprivatekey);
//		
//		//store to keystore
//		FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE);
//		ks.setKeyEntry("normal1", privateKey.getEncoded(), new Certificate[]{cacertificate,certificate});
//		ks.setCertificateEntry("normal1", certificate);
//		ks.store(fos, password);
//		fos.close();
//	}
//	
//	@SuppressWarnings("deprecation")
//	private Certificate createCertificate(X509Name dn, PublicKey publickey,X509Name cadn, PrivateKey caprivatekey) throws InvalidKeyException, SecurityException, SignatureException, IOException{
//		X509V3CertificateGenerator cergener = new X509V3CertificateGenerator();
//
//		// serial number
//		BigInteger serialnumber = BigInteger.probablePrime(20, new Random());
//		cergener.setSerialNumber(serialnumber);
//		// valid time
//		Date validdateBefore = new Date(2013,8,18);
//		Date validdateAfter = new Date(2018,8,18);
//		cergener.setNotBefore(validdateBefore);
//		cergener.setNotAfter(validdateAfter);
//		// public key
//		cergener.setPublicKey(publickey);
//		// signature algorithm
//		String signatureAlgorithm=SIGNATURE_ALGORITHM;
//		cergener.setSignatureAlgorithm(signatureAlgorithm);
//		// about Authority
////		X509Name x509AuthorityDN = createX509Name("CN","BJ","BEIJING","BQ","DEMO","CA1");
////		PublicKey authorityPublickey = capublickey;
////		DEREncodable capublickeyid = createAuthorityKeyID(authorityPublickey);
//		cergener.setIssuerDN(cadn);
////		cergener.addExtension(X509Extensions.AuthorityKeyIdentifier, false, capublickeyid);
//		// about Subject
////		X509Name x509SubDN = createX509Name("CN","BJ","BEIJING","BQ","DEMO","CER1");
////		DEREncodable publickeyid = createSubjectKeyID(publickey);
//		cergener.setSubjectDN(dn);
////		cergener.addExtension(X509Extensions.SubjectKeyIdentifier, false, publickeyid);
//		
//		// do generation
//		return cergener.generateX509Certificate(caprivatekey);
//	}
//	
	private X509Name createX509Name(String country, String province, String city, String orgnize, String suborgnize, String person) {
		Vector<DERObjectIdentifier> oids = new Vector<DERObjectIdentifier>();
		Vector<String> atts = new Vector<String>();
		
		//country
		oids.addElement(X509Name.C);
		// ?
		oids.addElement(X509Name.SN);
		// ?
		oids.addElement(X509Name.L);
		// Organize
		oids.addElement(X509Name.O);
		// sub organizer
		oids.addElement(X509Name.OU);
		// common name
		oids.addElement(X509Name.CN);
		
		atts.addElement(country);
		atts.addElement(province);
		atts.addElement(city);
		atts.addElement(orgnize);
		atts.addElement(suborgnize);
		atts.addElement(person);
		
		return new X509Name(oids, atts);
	}

	@SuppressWarnings("deprecation")
	private DEREncodable createSubjectKeyID(PublicKey subjectPublickey) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(subjectPublickey.getEncoded());
	    SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((DERConstructedSequence)new DERInputStream(bais).readObject());
	    return new AuthorityKeyIdentifier(info);
	}

	@SuppressWarnings("deprecation")
	private DEREncodable createAuthorityKeyID(PublicKey authorityPublickey) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(authorityPublickey.getEncoded());
	    SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((DERConstructedSequence)new DERInputStream(bais).readObject());
	    return new SubjectKeyIdentifier(info);
	}

//	/**
//	 * Need create certificate which contains public, before store privatekey
//	 * 
//	 * @param keystorefilepath
//	 * @param password
//	 * @throws NoSuchAlgorithmException
//	 * @throws KeyStoreException
//	 * @throws CertificateException
//	 * @throws FileNotFoundException
//	 * @throws IOException
//	 */
//	public void storePrivateKey2Keystore(String keystorefilepath,String password) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException{
//		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
//		KeyPair key = keygen.generateKeyPair();
//		PrivateKey privateKey = key.getPrivate();
//		
//		KeyStore ks = KeyStore.getInstance("JKS");
//		ks.load(new FileInputStream(keystorefilepath), password.toCharArray());
//		ks.setk
//	}
//	
//	private void convertJKS2PFX(){
//		
//	}
	
}
