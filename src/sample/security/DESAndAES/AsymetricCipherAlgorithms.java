package sample.security.DESAndAES;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AsymetricCipherAlgorithms {
	private Cipher cipher;
	private KeyPair key;
	

	public static class PaddingModes{
		public final static String PKCS1Padding = "PKCS1Padding";
		public final static String OAEPWithSHA_1AndMGF1Padding = "OAEPWithSHA-1AndMGF1Padding";
		public final static String OAEPWithSHA_256AndMGF1Padding = "OAEPWithSHA-256AndMGF1Padding";
	}
	public static class Modes {
		public static final String ECB = "ECB";
	}

	public AsymetricCipherAlgorithms(String algo, String mode, String paddingType, KeyPair keyPair) {
		try {
			this.cipher = Cipher.getInstance(algo + "/" + mode + "/" + paddingType);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		this.key = keyPair;
	}

	public byte[] encrypt(String plainText) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
			return cipher.doFinal(plainText.getBytes());

		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] decrypt(byte[] cipherText) {
		try {

			cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
			return cipher.doFinal(cipherText);

		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
			return null;
		}
	}

}
