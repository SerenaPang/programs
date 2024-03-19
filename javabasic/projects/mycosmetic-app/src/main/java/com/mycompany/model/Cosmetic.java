package com.mycompany.app;


// TODO When the information is saved, clean all the fields.
// TODO When JOptionDiaglog is open, used the WARNING OR INFO icons.
// TODO Delete the .class and all generated files from github.

/**
 * A cosmetic object class that has getters and setters
 * */
public class Cosmetic {
	private String brand;
	private String name;
	private String category;
	private int id;
	
	//Store entries in this order id:name:brand:category
	public Cosmetic(int id, String name, String brand, String category) {
		this.brand = brand;
		this.name = name;
		this.category = category;
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getId() {
		return id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setId(int id) {
		this.id  = id;
	}
	
	public String toString() {
		// TODO Use StringBuffer instead.
		// TODO Research why StringBuffer is preferred when strings are being  concatenated.
		return this.brand + " " + this.name + " " + this.category + " " + this.id;
	}
}