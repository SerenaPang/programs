package org.bigjava.ch25.xml;

public class Product {
	double price;
	String description;
	
	public Product(String description, double price) {
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

}
