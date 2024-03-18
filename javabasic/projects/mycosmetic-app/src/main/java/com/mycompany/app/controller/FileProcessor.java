package com.mycompany.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * This class read the file and extract the information from the file.
 * and write the user input into the file.
 * */
public class FileProcessor{
	//create a map to store all the product, the key is the product id
		Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();
	
	/**
	 * This method writes the user input into the text file
	 * */
	public void writeLine(String brand, String name, String category, String id) {
		//if the file exsist, open it append it, if not ccreate a new one
		try {
			// TODO when the application is initialized passed the file name using -store /path/to/file
			File file = new File("cosmetic.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			//add string to the file in new lines
			pw.println(brand + ":" + name + ":" + category + ":" + id);		
		
			pw.close();
			System.out.println("Data successfully appended at the end of file");
			
		} catch(IOException ioe) {
			System.out.println("Exception occur opening file:");
			ioe.printStackTrace();
		}	
	}
	
	/**
	 * read file context and populate map of cosmetics
	 * The key is the id of the 
	 * */
	public void readFile() {
		try {
			// TODO when the application is initialized passed the file name using -store /path/to/file
			File file = new File("cosmetic.txt");
			Scanner scanner = new Scanner(file);
		
			while(scanner.hasNextLine()) {
				//seperate the line with :, and create a cosmetic object
				String line = scanner.nextLine();
				String[] details = line.split(":");
				String brand = details[0];
				String name = details[1];
				String category = details[2];
				int numId = Integer.parseInt(details[3]);

				// TODO Store entries in this order id:name:brand:category
				Cosmetic cosme = new Cosmetic(brand, name, category, numId);
				//put cosmetic object to the map
				productMap.put(numId, cosme);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Returns the map of products that contains all the cosmetic
	 * */
	public Map<Integer, Cosmetic> getMap(){
		return productMap;
	}
}
 