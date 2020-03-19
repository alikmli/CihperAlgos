package sample.security.DESAndAES;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import sample.security.DESAndAES.SymetricCipherAlgorithms.Modes;
import sample.security.utils.KeyUtils;

public class Main {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			KeyPair key = KeyUtils.generateKeyPair("RSA", 1024);

			
		
			AsymetricCipherAlgorithms cipherAlgorithms = new AsymetricCipherAlgorithms("RSA", Modes.ECB,
					AsymetricCipherAlgorithms.PaddingModes.OAEPWithSHA_256AndMGF1Padding, key);

			System.out.println("Enter your plaintext : ");
			String plainInput = input.nextLine();
			byte[] cipherText = cipherAlgorithms.encrypt(plainInput);
			System.out.println(KeyUtils.hexToString(cipherText));

			byte[] plainText = cipherAlgorithms.decrypt(cipherText);

			System.out.println(new String(plainText));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
