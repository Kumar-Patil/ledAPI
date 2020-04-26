/**
 * 
 */
package com.ledVan.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.ledVan.exception.EncryptionDecryptionException;

/**
 * @author santopat
 *
 */
public class EncryptionDecryption {

	/**
	 * 
	 */
	public EncryptionDecryption() {
		// TODO Auto-generated constructor stub
	}

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) throws EncryptionDecryptionException {
		MessageDigest sha = null;
			try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionDecryptionException("No suchAlgorithm exception: " + e.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt, String secret) throws EncryptionDecryptionException {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new EncryptionDecryptionException("Error while encrypting: " + e.toString());
		}
	}

	public static String decrypt(String strToDecrypt, String secret) throws EncryptionDecryptionException {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			throw new EncryptionDecryptionException("Error while decrypting: " + e.toString());
		}
	}
}
