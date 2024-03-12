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
//	public Cosmetic getSearchItem(int id) {
//		reader.readFile();
//		products = reader.getList();
//		for (Cosmetic cosme : products) {
//			System.out.println(cosme.toString());
//		}
//	}
	
	public void getSearchItem(int id) {
		reader.readFile();
		products = reader.getList();
		for (Cosmetic cosme : products) {
			System.out.println(cosme.toString());
		}
	}
	
	/**
	 * 
	 * */
	
}