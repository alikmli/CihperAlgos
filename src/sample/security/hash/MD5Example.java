package sample.security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Example {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {

			System.out.println("Enter your text : ");
			String message = input.nextLine().trim();
			
			
			MessageDigest digest=MessageDigest.getInstance("MD5");
			
			System.out.println("provider information : ");
			System.out.println(digest.getProvider().getInfo());
			
			digest.update(message.getBytes());
			
			String result=bytesToHex(digest.digest());
			
			System.out.println("hash result : ");
			System.out.println(result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

}
