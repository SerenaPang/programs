package com.mycompany.app;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

// TODO Write comment describing the format of the file.
public class TextFileReader {
	//create a map to store all the product, the key is the product id
	Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();
		
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

