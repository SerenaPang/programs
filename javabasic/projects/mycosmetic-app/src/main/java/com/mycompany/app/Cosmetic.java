package com.mycompany.app;

/**
 * A cosmetic object class that has getters and setters
 * */
public class Cosmetic() {
	private String brand;
	private String name;
	private String category;
	private int id;
	
	public Cosmetic(String brand, String name, String category, int id) {
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
}