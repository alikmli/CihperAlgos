package sample.security.utils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class KeyUtils {

	public static SecretKey generateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(keySize);

		return generator.generateKey();
	}
	
	public static KeyPair generateKeyPair(String algorithm,int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator generator=KeyPairGenerator.getInstance(algorithm);
		generator.initialize(keySize);
		return generator.generateKeyPair();
	}

	public static String hexToString(byte[] bytes) {
		StringBuilder builder = new StringBuilder();

		for (byte item : bytes) {
			builder.append(String.format("%02x", item));
		}

		return builder.toString();
	}
}
