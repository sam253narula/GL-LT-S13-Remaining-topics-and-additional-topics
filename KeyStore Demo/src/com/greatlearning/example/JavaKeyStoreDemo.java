package com.greatlearning.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class JavaKeyStoreDemo {

	private static final String UNICODE_FORMAT = "UTF-8";

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {
		String text = "Welcome To Great Learning";
		
		// AES Stands for Advanced Encryption standards
		SecretKey myKey = generateKey("AES");
		storeToKeyStore(myKey, "12345","myKeystore.keystore");
	//	SecretKey myKey = loadKeyFromKeyStore("myKeystore.keystore", "12345");
		Cipher cipher = Cipher.getInstance("AES");
		byte[] encryptedString = encryptString(text, myKey, cipher);
		System.out.println("Encrypted text: " + encryptedString);
		String decryptedString = decryptString(encryptedString, myKey, cipher);
		System.out.println("Decrypted Text: " + decryptedString);

	}

	public static void storeToKeyStore(SecretKey keyToStore, String password, String filePath)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		File file = new File(filePath);
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		if (!file.exists()) {
			keyStore.load(null, null);
		}
		keyStore.setKeyEntry("samarthKeyStoreAlias2", keyToStore, password.toCharArray(), null);
		OutputStream writeStream = new FileOutputStream(filePath);
		keyStore.store(writeStream, password.toCharArray());
	}

	public static SecretKey loadKeyFromKeyStore(String filePath, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		InputStream readStream  = new FileInputStream(filePath);
		keyStore.load(readStream, password.toCharArray());
		SecretKey key = (SecretKey) keyStore.getKey("samarthKeyStoreAlias2", password.toCharArray());
		return key;
	}
	
	public static SecretKey generateKey(String encrypionType) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(encrypionType);
		SecretKey myKey = keyGenerator.generateKey();
		return myKey;
	}

	public static byte[] encryptString(String dataToEncrypt, SecretKey myKey, Cipher cipher)
			throws UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// We are using UTF-8 becz otherwise getBytes() method will use default format
		// of your computer
		byte[] bytes = dataToEncrypt.getBytes(UNICODE_FORMAT);
		cipher.init(Cipher.ENCRYPT_MODE, myKey);
		byte[] encryptedText = cipher.doFinal(bytes);
		return encryptedText;
	}

	public static String decryptString(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, myKey);
		byte[] decryptedData = cipher.doFinal(dataToDecrypt);
		String originalText = new String(decryptedData);
		return originalText;
	}

}
