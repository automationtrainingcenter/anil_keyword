package utilities;

import java.io.File;

public class GenericHelper {
	
	// get the file path from the given folder name and file name
	public static String getFilePath(String folderName, String fileName) {
		File folder = new File(System.getProperty("user.dir")+File.separator+folderName);
		if(!folder.exists()) {
			folder.mkdir();
		}
		return folder.getAbsolutePath()+File.separator+fileName;
	}
	
	
	

}
