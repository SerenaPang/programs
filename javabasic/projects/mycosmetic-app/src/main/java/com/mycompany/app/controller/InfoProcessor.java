package com.mycompany.app;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class process the cosmetic information 
 * */
public class InfoProcessor {
	TextFileReader reader = new TextFileReader();
	//map of <Cosmetic> products, key:product id, value: cosmetic object that has the matching product id
	Map<Integer, Cosmetic> productMap;
	/**
	 * Iterate the map of the cosmetic object to find the cosmetic object that matches the user id and return it
	 * @param id product id to search
	 * @return cosmetic object that has the matching search id
	 * */
	public Cosmetic getSearchItem(int id) {
		reader.readFile();
		productMap = reader.getMap();
		System.out.println("Printing map: ");

		// TODO Why do we need to iterate through productMap.entrySet() if productMap uses key value pairs??
		for (Map.Entry<Integer, Cosmetic> mapEntries : productMap.entrySet()) {
			//if the search id matches with the current id, return it
			if (id == mapEntries.getKey()) {
				System.out.println(mapEntries.getValue().toString());
				return mapEntries.getValue();
			}
		}
		return null;
	}	
}