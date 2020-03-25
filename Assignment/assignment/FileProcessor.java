/*
 	Description: This class will handle files.
 	
 	Author: Shane Riedy
 */
package assignment;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class FileProcessor {

	//Attributes
	File file;
	Scanner scanner;
	
	//Constructors
	public FileProcessor(String fileName) {
			try {
				file = new File(fileName);
				scanner = new Scanner(file);
			}
			catch (Exception ex) {
				System.out.println("Error. File not found.");
				ex.printStackTrace();
			}
	}
	
	//Methods
	public ArrayList<Entry> getData(ArrayList<Entry> dataEntries) {
		scanner.nextLine();
		scanner.nextLine();
		while (scanner.hasNextLine()) dataEntries.add(new Entry(scanner.nextLine().split(",")));
		return dataEntries;
	}
}