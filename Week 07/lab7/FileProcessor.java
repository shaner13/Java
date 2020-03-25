/*
 	FileProcessor class to read files.
 */

package lab7;


import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;


public class FileProcessor {
	
	File file;
	Scanner scanner;
	FileWriter writer;
	
	public FileProcessor (String fileName, String fileType) {
		
		
		if (fileType == "write") {
			
			this.file = new File(fileName);
			try {
				
				writer = new FileWriter(file);
			}
			catch (Exception ex) {
				
				System.out.println("File not found.");
				ex.printStackTrace();
			}
			
		}
		
		else {
			
			this.file = new File(fileName);
			try {
				scanner = new Scanner(file);
			}
			catch (Exception ex) {
				System.out.println("File not found.");
				ex.printStackTrace();
			}
			
		}
		
	}


	public boolean roleChecker(String role) {
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains(role)) {
				return true;
			}
		}
		return false;
	}
	
	public void write(String fileText) {
		
		try {
			writer.write(fileText);
			writer.close();
		}
		
		catch (Exception ex) {
			System.out.println("Cannot write to file.");
			ex.printStackTrace();
		}
	}

}