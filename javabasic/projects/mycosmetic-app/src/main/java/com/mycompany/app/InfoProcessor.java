package com.mycompany.app;
import java.util.ArrayList;

/**
 * This class process the cosmetic information 
 * */
public class InfoProcessor {
	TextFileReader reader = new TextFileReader();
	ArrayList<Cosmetic> products;
	/**
	 * Iterate the list of the cosmetic object to find the cosmetic object that matches the user id and return it
	 * @param id product id to search
	 * @return cosmetic object that has the matching search id
	 * */
	public Cosmetic getSearchItem(int id) {
		reader.readFile();
		products = reader.getList();
		System.out.println("Printing list: ");
		for (Cosmetic cosme : products) {
			//if the search id matches with the current id, return it
			if (id == cosme.getId()) {
				System.out.println(cosme.toString());
				return cosme;
			}
		}
		return null;
	}
}