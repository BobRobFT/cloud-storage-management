package me.bobrob.com;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class encrypt {
	static int CIPHER_BITS = 128; //number of bits to encrypt a file. 256 is not possible unless you change some settings 
	public static void encryptOffline(String password, String file_locationString) throws Exception{
		
		//http://javapapers.com/java/java-file-encryption-decryption-using-aes-password-based-encryption-pbe/
		// file to be encrypted
		FileInputStream inFile = new FileInputStream(file_locationString);
		String file_locationString_output = null;

		// encrypted file
		final JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File(file_locationString));
		//Create a file chooser
		int returnVal = fc.showSaveDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file_location = fc.getSelectedFile();
            file_locationString_output = file_location.toString();
            file_locationString_output = file_locationString_output.replace("\\", "/");
            
        } else {
        }
		
		//FileOutputStream outFile = new FileOutputStream("C:/Users/bob-dc-3/Documents/encryptedfile.aes");
		FileOutputStream outFile = new FileOutputStream(file_locationString_output + ".aes");
		
		//offline
		byte[] saltOffline = new byte[32];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(saltOffline);
		String destDir1 = file_locationString_output.substring(0,file_locationString_output.lastIndexOf("/"));
		String destDir21 = file_locationString_output.substring(file_locationString_output.lastIndexOf("/")+1);
		FileOutputStream saltOutFile = new FileOutputStream(destDir1 +"/salt"+ destDir21);
		saltOutFile.write(saltOffline);
		saltOutFile.close();

	
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltOffline, 65536, CIPHER_BITS);
		SecretKey secretKey = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

	
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();

		// iv adds randomness to the text and just makes the mechanism more
		// secure
		// used while initializing the cipher
		// file to store the iv
		String destDir = file_locationString_output.substring(0,file_locationString_output.lastIndexOf("/"));
		String destDir2 = file_locationString_output.substring(file_locationString_output.lastIndexOf("/")+1);
		FileOutputStream ivOutFile = new FileOutputStream(destDir +"/iv"+ destDir2); //Initialization vector
		
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		ivOutFile.write(iv);
		ivOutFile.close();

		//file encryption
		byte[] input = new byte[64];
		int bytesRead;

		while ((bytesRead = inFile.read(input)) != -1) {
			byte[] output = cipher.update(input, 0, bytesRead);
			if (output != null)
				outFile.write(output);
		}

		byte[] output = cipher.doFinal();
		if (output != null)
			outFile.write(output);

		inFile.close();
		outFile.flush();
		outFile.close();

		System.out.println("File Encrypted.");
	}
	
	
	
	
	

	
public static void encryptOnlineStudent(String password, String file_locationString, String salt) throws Exception{
		//selects file to be encrypted
		FileInputStream inFile = new FileInputStream(file_locationString);
		String file_locationString_output = null;
		final JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File(file_locationString));
		int returnVal = fc.showSaveDialog(fc);//Create a file chooser
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file_location = fc.getSelectedFile();
            file_locationString_output = file_location.toString();
            file_locationString_output = file_locationString_output.replace("\\", "/");
            
        } else {
        }

        //adds the .aes extension onto the end of the file name
		FileOutputStream outFile = new FileOutputStream(file_locationString_output + ".aes");
		byte[] saltOnline = salt.getBytes();
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); 
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltOnline, 65536, CIPHER_BITS);
		SecretKey secretKey = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		// Cipher provides the functionality of a cryptographic cipher for encryption and decryption
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		String destDir = file_locationString_output.substring(0,file_locationString_output.lastIndexOf("/"));
		String destDir2 = file_locationString_output.substring(file_locationString_output.lastIndexOf("/")+1);
		FileOutputStream ivOutFile = new FileOutputStream(destDir +"/iv"+ destDir2); //Initialization vector
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		ivOutFile.write(iv);
		ivOutFile.close();

		//file encryption
		byte[] input = new byte[64];
		int bytesRead;
		while ((bytesRead = inFile.read(input)) != -1) {
			byte[] output = cipher.update(input, 0, bytesRead);
			if (output != null)
				outFile.write(output);
		}
		byte[] output = cipher.doFinal();
		if (output != null)
			outFile.write(output);

		inFile.close();
		outFile.flush();
		outFile.close();

		System.out.println("File Encrypted.");
	}	
}