package me.bobrob.com;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class zip_folder {

	static public void zipFolder(String srcFolder, String destZipFile)
			throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);

		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}

	static private void addFileToZip(String path, String srcFile,
			ZipOutputStream zip) throws Exception {

		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}

	static private void addFolderToZip(String path, String srcFolder,
			ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/"+ fileName, zip);
			}
		}
	}

	// http://mrbool.com/how-to-unzip-files-with-java/24969
	static void unzip(String zipFilePath, String destDir) {


			FileInputStream fis;
			//buffer for read and write data to file
			byte[] buffer = new byte[1024];
			try {
				fis = new FileInputStream(zipFilePath);
				ZipInputStream zis = new ZipInputStream(fis);
				ZipEntry ze = zis.getNextEntry();
				while(ze != null){
					String fileName = ze.getName();
					File newFile = new File(destDir + File.separator + fileName);
					System.out.println("Unzipping to "+newFile.getAbsolutePath());
					//create directories for sub directories in zip
					new File(newFile.getParent()).mkdirs();
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					//close this ZipEntry
					zis.closeEntry();
					ze = zis.getNextEntry();
				}
				//close last ZipEntry
				zis.closeEntry();
				zis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		        


	
	}
}