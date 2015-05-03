package me.bobrob.com;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;


public class decrypt {
	static int CIPHER_BITS = 128; //number of bits to encrypt a file. 256 is not possible unless you change some settings 
	public static void decryptOffline(String password, String file_locationString) throws Exception{
		
		// reading the salt
		// user should have secure mechanism to transfer the
		// salt, iv and password to the recipient
		String destDirIV1 = file_locationString.substring(0,file_locationString.lastIndexOf("/"));
		String destDirIV21 = file_locationString.substring(file_locationString.lastIndexOf("/")+1);
		destDirIV21 = destDirIV21.replace(".aes", "");
		System.out.println(destDirIV1);
		FileInputStream saltFis = new FileInputStream(destDirIV1 +"/salt"+ destDirIV21);
		byte[] saltOffline = new byte[32];
		saltFis.read(saltOffline);
		saltFis.close();

		// reading the iv
		String destDirIV = file_locationString.substring(0,file_locationString.lastIndexOf("/"));
		String destDirIV2 = file_locationString.substring(file_locationString.lastIndexOf("/")+1);
		destDirIV2 = destDirIV2.replace(".aes", "");
		System.out.println(destDirIV);
		FileInputStream ivFis = new FileInputStream(destDirIV +"/iv"+ destDirIV2);
		byte[] iv = new byte[16];
		ivFis.read(iv);
		ivFis.close();
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltOffline, 65536, CIPHER_BITS);
		SecretKey tmp = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

		// file decryption
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		FileInputStream fis = new FileInputStream(file_locationString);
		
		//choose file location to save file
		String file_locationString_output = null;
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
        
        String file_locationString_output_noaes = file_locationString_output.replace(".aes", ""); //removes .aes from the end //does not fully work, re work later
		FileOutputStream fos = new FileOutputStream(file_locationString_output_noaes);

		byte[] in = new byte[64];
		int read;
		while ((read = fis.read(in)) != -1) {
			byte[] output = cipher.update(in, 0, read);
			if (output != null)
				fos.write(output);
		}

		try{
			byte[] output = cipher.doFinal();
			if (output != null)
			fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("File Decrypted.");
		}catch(Exception e){
			//System.out.println(e);
		}
		
		
		//unzip file (if it is .zip)
		String destDir = file_locationString_output_noaes.substring(0,file_locationString_output_noaes.lastIndexOf("/"));
		System.out.println(destDir);
		
		if (file_locationString_output_noaes.endsWith(".zip")) {
			zip_folder.unzip(file_locationString_output_noaes, destDir);
			
			Path path = Paths.get(file_locationString_output_noaes);	
			try {
			    Files.delete(path);
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", file_locationString_output_noaes);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", file_locationString_output_noaes);
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			}
			//log.append("Deleted : " + file_locationString + newline);
			System.out.println("Deleted: " + file_locationString_output_noaes);
		}
		else{
			//does nothing 
		}
	}
	
	
	
	
public static void decryptOnlineStudent(String password, String file_locationString, String salt) throws Exception{
		
		//read the IV and salt 
		byte[] salt2 = salt.getBytes();
		String destDirIV = file_locationString.substring(0,file_locationString.lastIndexOf("/"));
		String destDirIV2 = file_locationString.substring(file_locationString.lastIndexOf("/")+1);
		destDirIV2 = destDirIV2.replace(".aes", "");
		System.out.println(destDirIV);
		FileInputStream ivFis = new FileInputStream(destDirIV +"/iv"+ destDirIV2); // reading the iv
		byte[] iv = new byte[16];
		ivFis.read(iv);
		ivFis.close();
		
		// Cipher provides the functionality of a cryptographic cipher for encryption and decryption
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt2, 65536, CIPHER_BITS);
		SecretKey tmp = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		FileInputStream fis = new FileInputStream(file_locationString);
		
		//setting the file location
		String file_locationString_output = null;
		final JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File(file_locationString));
		int returnVal = fc.showSaveDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file_location = fc.getSelectedFile();
            file_locationString_output = file_location.toString();
            file_locationString_output = file_locationString_output.replace("\\", "/");
            
        } else {
        }
        String file_locationString_output_noaes = file_locationString_output.replace(".aes", ""); //removes .aes from the end 
		FileOutputStream fos = new FileOutputStream(file_locationString_output_noaes);
		
		//decrypt file
		byte[] in = new byte[64];
		int read;
		while ((read = fis.read(in)) != -1) {
			byte[] output = cipher.update(in, 0, read);
			if (output != null)
				fos.write(output);
		}

		try{
			byte[] output = cipher.doFinal();
			if (output != null)
			fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("File Decrypted.");
		}catch(Exception e){
			//System.out.println(e);
		}
		
		
		//unzip file (if it is .zip)
		
		String destDir = file_locationString_output_noaes.substring(0,file_locationString_output_noaes.lastIndexOf("/"));
		System.out.println(destDir);
		
		if (file_locationString_output_noaes.endsWith(".zip")) {
			zip_folder.unzip(file_locationString_output_noaes, destDir);
			
			Path path = Paths.get(file_locationString_output_noaes);	
			try {
			    Files.delete(path);
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", file_locationString_output_noaes);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", file_locationString_output_noaes);
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			}
			//log.append("Deleted : " + file_locationString + newline);
			System.out.println("Deleted: " + file_locationString_output_noaes);
		}
		else{
			//does nothing 
		}
	}
}