package sample.security.DESAndAES;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymetricCipherAlgorithms {
	
	private Cipher cipher;
	private SecretKey key;

	private final int IV_SIZE = 16;
	private final SecureRandom rand = new SecureRandom();

	private boolean isIVEnalbled = false;

	public static class PaddingModes{
		public static String PKCS5Padding="PKCS5Padding";
	}
	public static class Modes {
		public static final String ECB = "ECB";
		public static final String CBC = "CBC";
		public static final String CFB = "CFB";
		public static final String OFB = "OFB";
	}

	public SymetricCipherAlgorithms(String algo, String mode, String paddingType, SecretKey key) {
		try {
			if (mode.equalsIgnoreCase(Modes.CBC) || mode.equalsIgnoreCase(Modes.CFB)
					|| mode.equalsIgnoreCase(Modes.OFB)) {
				isIVEnalbled = true;
			}
			this.cipher = Cipher.getInstance(algo + "/" + mode + "/" + paddingType);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		this.key = key;
	}

	public byte[] encrypt(String plainText) {
		try {
			if (isIVEnalbled) {
				byte[] iv = new byte[IV_SIZE];
				rand.nextBytes(iv);
				IvParameterSpec ivSpec = new IvParameterSpec(iv);
				
				cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

				byte[] cipherText = cipher.doFinal(plainText.getBytes());
				byte[] result = new byte[IV_SIZE + cipherText.length];
				
				System.arraycopy(cipherText, 0, result, 0, cipherText.length);
				System.arraycopy(iv, 0, result, cipherText.length, IV_SIZE);

				return result;

			} else {
				cipher.init(Cipher.ENCRYPT_MODE, key);
				return cipher.doFinal(plainText.getBytes());
			}

		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] extractIV(byte[] cipherText) {
		byte[] iv = new byte[IV_SIZE];

		System.arraycopy(cipherText, cipherText.length - IV_SIZE, iv, 0, IV_SIZE);

		return iv;
	}

	private byte[] extractEncryptedText(byte[] cipherText) {
		byte[] text = new byte[cipherText.length - IV_SIZE];

		System.arraycopy(cipherText, 0, text, 0, text.length);

		return text;
	}

	public byte[] decrypt(byte[] cipherText) {
		try {
			if (isIVEnalbled) {
				byte[] iv = extractIV(cipherText);
				IvParameterSpec iSpec = new IvParameterSpec(iv);
				cipher.init(Cipher.DECRYPT_MODE, key, iSpec);
				
				byte[] text = extractEncryptedText(cipherText);
				return cipher.doFinal(text);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, key);
				return cipher.doFinal(cipherText);
			}
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return null;
		}
	}
	


}
