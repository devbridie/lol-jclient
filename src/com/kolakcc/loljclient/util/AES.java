package com.kolakcc.loljclient.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class AES {
	private static final byte[] SEED = {42, 1, 10, 8};
	
	private static SecretKey getKey() {
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SecureRandom random = new SecureRandom(SEED); // seeded so that the secret key is always the same
		keyGen.init(128, random);
		return keyGen.generateKey();
	}
	
	private static Cipher getCipher() {
		Cipher aesCipher = null;
		try {
			aesCipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return aesCipher;
	}
	
	// encrypts using AES private key cryptography
	public static String encrypt(String str) {
		SecretKey secretKey = getKey();	
		Cipher aesCipher = getCipher();
	    try {
			aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	    
	    byte[] byteDataToEncrypt = str.getBytes(); // convert to byte[]
	    byte[] byteCipherText = null;
		try {
			byteCipherText = aesCipher.doFinal(byteDataToEncrypt); // encrypt
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(byteCipherText); // encode byte[] as string
	}
	
	public static String decrypt(String str) {
		byte[] byteCipherText = Base64.decodeBase64(str); // decode string to byte[]
		SecretKey secretKey = getKey();
		Cipher aesCipher = getCipher();
		try {
			aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
	    byte[] byteDecryptedText = null;
		try {
			byteDecryptedText = aesCipher.doFinal(byteCipherText); // decrypt
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	    return new String(byteDecryptedText); // convert to string
	}
}
