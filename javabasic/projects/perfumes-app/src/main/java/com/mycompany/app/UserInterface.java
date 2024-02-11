package com.mycompany.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class gets the information from the user and put it to the processor
 * */
public class UserInterface {
	Map<String, ArrayList<Perfume>> mapOfcommentsForPerfumes = new HashMap<String, ArrayList<Perfume>>();
	PerfumeDataProcessor dp = new PerfumeDataProcessor(mapOfcommentsForPerfumes);
	
	/**
	 * This method prints a welcome message to the user
	 * */
	public void printWelcome() {
		System.out.println("============================");
		System.out.println("Welcome to House of Perfume!");
		System.out.println("============================");
	}
	
	/**
	 * This method takes in the likeness rating from the user
	 * */
	public double getScoreFromUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the score of how much you like the perfume lowest(0 - 100)to highest: ");
		double score = input.nextDouble();
		System.out.println(score + " Recorded!");
		return score;
	}
	
	/**
	 * This method gets the number of perfumes to record
	 * */
	public int getNumber() {
		//gets the number of perfume user wants to put
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter how many perfumes you want to record: ");
		int numbers = input.nextInt();
		System.out.println("Ready to record " + numbers + " of perfumes now");
		return numbers;	
	}
	
	/**
	 * This method takes in the names of the perfume, 
	 * */
	public String getPerfumeName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the perfume: ");
		String name = input.nextLine();
		System.out.println(name + " Recorded!");
		return name;
	}
	
	/**
	 * This method takes in the names of the person 
	 * */
	public String getPersonName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		String name = input.nextLine();
		System.out.println("Hi " + name + "!");
		return name;
	}
	/**
	 * gets user input for the perfume
	 * */
	public void getInfo() {
		Person person = new Person();
		Perfume perfume = new Perfume();
		
		String personName = getPersonName();
		String perfumeName = getPerfumeName();
		double ratingScore = getScoreFromUser();
		person.setName(personName);
		perfume.setName(perfumeName);
		perfume.setRating(ratingScore);
		mapOfcommentsForPerfumes = dp.putPersonCommentsToMap(personName, perfume, mapOfcommentsForPerfumes);
	}
	
	/**
	 * This method display the summary of the ranking of the perfumes
	 * */
	public void displayInfo() {
		dp.displayMap(mapOfcommentsForPerfumes);
	}
	
	/**
	 * This method prints a goodbye message to the user
	 * */
	public void printGoodBye() {
		System.out.println("============================");
		System.out.println("Thank you for coming to House of Perfume! Goodbye!");
		System.out.println("============================");
	}
}
