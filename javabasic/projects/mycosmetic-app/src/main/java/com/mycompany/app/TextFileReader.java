package com.mycompany.app;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileReader {
	//create a list to store all cosmetic products
	ArrayList<Cosmetic> products = new ArrayList<Cosmetic>();
	
	public void read() {
		File file = new File("cosmetic.txt");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line; //has to be seperate or else will be infinite loop
			while((line = br.readLine())!= null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void readFile() {
		try {
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
				
				Cosmetic cosme = new Cosmetic(brand, name, category, numId);
				//add the object to the list
				products.add(cosme);
				System.out.println(brand + " " + name + " " + category + " " + numId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Returns the list of products that contains all the cosmetic
	 * */
	public ArrayList<Cosmetic> getList(){
		return products;
	}	
}

