package com.mycompany.model;

import java.io.Serializable;

/**
 * A cosmetic object class that has getters and setters
 * CosmeID int, Brand varchar(255), 
 * 
 * */
public class Cosmetic implements Serializable {
	private String brand;
	private String name;
	private String category;
	private int id;
	
	public Cosmetic() {
		
	}
	
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
	
	/**
	 * Use StringBuffer to concatenate the information of Cosmetic because StringBuffer is faster than String when performing simple concatenations
	 * */
	public String toString() {
		String cosme = new StringBuffer().append("ID: ").append(id).append(" Name: ").append(name).append(" Brand: ").append(brand).append(" Category: ").append(category).toString();
		return cosme;
	}
}