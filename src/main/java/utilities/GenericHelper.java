package utilities;

import java.io.File;

public class GenericHelper {
	
	// get the file path from the given folder name and file name
	public static String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir")+File.separator+folderName+File.separator+fileName;
	}
	
	
	

}
