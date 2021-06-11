package org.tain.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class CipherUtils {

	//private static int keySize = 1024;
	private static int RSABIT_SIZE = 2048;
	//private static int RSABIT_SIZE = 1024;
	private static String ALGORITHM_PADDING = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
	
	/*
	 * 1024비트 RSA 키쌍을 생성합니다.
	 */
	public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(RSABIT_SIZE, secureRandom);
		KeyPair keyPair = gen.generateKeyPair();
		return keyPair;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static void testPadding() throws Exception {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(RSABIT_SIZE, random);
		@SuppressWarnings("unused")
		KeyPair keyPair = keyGen.generateKeyPair();
		
		/* constant 117 is a public key size - 11 */
		byte[] plainText = null;
		byte[] encText = null;
		byte[] binText = null;
		if (!Boolean.TRUE) {
			plainText = new byte[16];
			random.nextBytes(plainText);
			StringTools.printHex(plainText);
		}
		
		if (!Boolean.TRUE) {
			String file = "/Users/kangmac/KANG/monster/20210320/KEY/HW_PubKey.b64";
			PublicKey pubKey = CipherUtils.getPublicKeyFromBase64(StringTools.bytesFromFile(file));
			
			//Cipher cipher = Cipher.getInstance("RSA");
			//Cipher cipher = Cipher.getInstance("ECB");  // NoSuchAlgorithm
			//Cipher cipher = Cipher.getInstance("RSA/ECB/NOPADDING");
			//Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT);
			//cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
			//cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey, oaepParams);
			encText = cipher.doFinal(plainText);
			StringTools.printHex(encText);
		}
		
		byte[] byteB64 = null;
		byte[] byteEnc = null;
		if (Boolean.TRUE) {
			String b64file = "/Users/kangmac/KANG/monster/20210320/KEY/MO_OTK.b64";
			byteB64 = StringTools.bytesFromFile(b64file);
			StringTools.printHex(byteB64);
			
			byteEnc = Base64.getDecoder().decode(byteB64);
			StringTools.printHex(byteEnc);
			String encfile = "/Users/kangmac/KANG/monster/20210320/KEY/MO_OTK.enc";
			StringTools.bytesToFile(byteEnc, encfile);
		}
		
		if (Boolean.TRUE) {
			String file = "/Users/kangmac/KANG/monster/20210320/KEY/HW_PriKey.b64";
			PrivateKey priKey = CipherUtils.getPrivateKeyFromBase64(StringTools.bytesFromFile(file));
			
			//Cipher cipher = Cipher.getInstance("RSA");
			//Cipher cipher = Cipher.getInstance("ECB");  // NoSuchAlgorithm
			//Cipher cipher = Cipher.getInstance("RSA/ECB/NOPADDING");
			//Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			//cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			//binText = cipher.doFinal(encText);
			binText = cipher.doFinal(byteEnc);
			StringTools.printHex(binText);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * Public Key로 RSA 암호화를 수행합니다.
	 */
	public static String encryptRSA(String plainText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytePlain = cipher.doFinal(plainText.getBytes());
		String encrypted = Base64.getEncoder().encodeToString(bytePlain);
		return encrypted;
	}
	
	public static byte[] encryptRSA(byte[] byteText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] byteEncText = cipher.doFinal(byteText);
		return byteEncText;
	}
	
	/*
	 * Private Key로 RAS 복호화를 수행합니다.
	 */
	public static String decryptRSA(String encrypted, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
		byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytePlain = cipher.doFinal(byteEncrypted);
		String decrypted = new String(bytePlain, "utf-8");
		return decrypted;
	}
	
	public static byte[] decryptRSA(byte[] byteEncText, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] byteText = cipher.doFinal(byteEncText);
		return byteText;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * Base64 엔코딩된 개인키 문자열로부터 PrivateKey객체를 얻는다.
	 */
	public static PrivateKey getPrivateKeyFromBase64String(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String privateKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
		
		return keyFactory.generatePrivate(keySpecPKCS8);
	}
	
	/*
	 * Base64 엔코딩된 공용키키 문자열로부터 PublicKey객체를 얻는다.
	 */
	public static PublicKey getPublicKeyFromBase64String(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String publicKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
		
		return keyFactory.generatePublic(keySpecX509);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/*
	 * Base64 엔코딩된 개인키 문자열로부터 PrivateKey객체를 얻는다.
	 */
	public static PrivateKey getPrivateKeyFromBase64(final byte[] byteKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(byteKey));
		
		return keyFactory.generatePrivate(keySpecPKCS8);
	}
	
	/*
	 * Base64 엔코딩된 공용키키 문자열로부터 PublicKey객체를 얻는다.
	 */
	public static PublicKey getPublicKeyFromBase64(final byte[] byteKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(byteKey));
		
		return keyFactory.generatePublic(keySpecX509);
	}
}
