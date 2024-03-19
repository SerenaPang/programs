package com.mycompany.app;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class process the cosmetic information 
 * */
public class InfoProcessor {
	//create a reader to read file
	FileProcessor fileReader = new FileProcessor();
	//map of <Cosmetic> products, key:product id, value: cosmetic object that has the matching product id
	Map<Integer, Cosmetic> productMap;
	/**
	 * find the cosmetic object that matches the user id and return it
	 * @param id product id to search
	 * @return cosmetic object that has the matching search id
	 * */
	public Cosmetic getSearchItem(int id) {
		fileReader.readFile();
		productMap = fileReader.getMap();
		System.out.println("Printing map: ");

			//if the search id matches with the current id, return it
			if (productMap.containsKey(id)) {
				System.out.println(productMap.get(id).toString());
				return productMap.get(id);
			}	
		return null;
	}
}