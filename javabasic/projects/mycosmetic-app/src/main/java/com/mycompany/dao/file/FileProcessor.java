package com.mycompany.dao.file;

import com.mycompany.model.Cosmetic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * This class read the file and extract the information from the file.
 * and write the user input into the file.
 * The format of the file is 
 * id:name:brand:category
 * */
public class FileProcessor{
	//create a map to store all the product, the key is the product id
	Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();
	
	//create a file object
	File file;	
	//create a constructor that receive the file pass in the command line
	public FileProcessor(File file) {
		this.file = file;
	}
		
	/**
	 * This method writes the user input into the text file
	 * @param id id of the cosmetic product 
	 * @param name name of the cosmetic product 
	 * @param brand brand of the cosmetic product 
	 * @param category category of the cosmetic product 
	 * */
	public void writeLine(String id, String name, String brand, String category) {
		//if the file exsist, open it append it, if not ccreate a new one
		try {
			//File file = new File("cosmetic.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			//add string to the file in new lines
			pw.println(id + ":" + name + ":" + brand + ":" + category);	
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
			//File file = new File("cosmetic.txt");
			Scanner scanner = new Scanner(file);
			System.out.println("reading file...");
			while(scanner.hasNextLine()) {
				//seperate the line with :, and create a cosmetic object
				String line = scanner.nextLine();
				String[] details = line.split(":");
				String id = details[0];
				//System.out.println("String id: " + id);
				//int numId = Integer.parseInt(details[0]);
				int numId = Integer.parseInt(id);
				
				String name = details[1];
				String brand = details[2];
				String category = details[3];
				
				Cosmetic cosme = new Cosmetic(numId, name, brand, category);
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
	
	/**
	 * print map
	 * */
	public void print(Map<Integer, Cosmetic> productMap) {
		for (Integer id : productMap.keySet()) {
			int key = id.intValue();
		    Cosmetic value = productMap.get(key);
		    System.out.println(key + " " + value.toString());
		}
	}
	
	/**
	 * find the cosmetic object that matches the user id and return it
	 * @param id product id to search
	 * @return cosmetic object that has the matching search id
	 * */
	public Cosmetic getSearchItem(int id) {
		readFile();
		System.out.println("Finding id " + id + " in text file...");

			//if the search id matches with the current id, return it
			if (productMap.containsKey(id)) {
				System.out.print("ID " + id + " in the text file: ");
				System.out.println(productMap.get(id).toString());
				return productMap.get(id);
			}
		System.out.println("There is no matching id " + id + " in the text file.");
		return null;
	}
}
 