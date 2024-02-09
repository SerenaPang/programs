package com.mycompany.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class gets the information for the perfume and map them into a tree map, the order is based 
 * on the likeness of the perfume from lowest 0 to highest 100
 * */
public class PerfumeDataProcessor {
	Perfume perfume = new Perfume();	
	ArrayList<Perfume> listOfPerfumes;
	Map<Person, ArrayList<Perfume>> universalMap = new HashMap<Person, ArrayList<Perfume>>();

	
	public PerfumeDataProcessor(Map<Person, ArrayList<Perfume>> universalMap) {
		this.universalMap = universalMap;
	}

	/**
	 * This method creates a Perfume object using the information provided by the user
	 * */
	public Perfume createPerfume() {
		Perfume p = new Perfume();
		String name = p.getName();
		double rating = p.getRating();
		p.setName(name);
		p.setRating(rating);
		return p;
	}

	/**
	 * This method gets the perfume's name and the user likeness and put them in a list
	 * */
	public void addToList(Perfume perfume) {
		listOfPerfumes.add(perfume);
	}
	
	/**
	 * This method creates a Person object
	 * */
	public Person createPerson() {
		Person person = new Person();
		String name = person.getName();
		person.setName(name);
		return person;
	}
	
	/**
	 * This method checks if this person is already in the map, if yes find the list for him and add the perfume to the list, sort the list, put the list to the map.
	 * if person is not in the map, create a new list, sort it, put the information to the map
	 **/
	public Map<Person, ArrayList<Perfume>> putPersonCommentsToMap(Person person, Perfume aPerfume, Map<Person, ArrayList<Perfume>> commentsMap){
		if (commentsMap.size() == 0 || !commentsMap.containsKey(person)) {
			//create a new list 
			ArrayList<Perfume> perfumeList = new ArrayList<>();
			perfumeList.add(aPerfume);
			sortList(perfumeList);
			commentsMap.put(person, perfumeList);		
		} else {
			ArrayList<Perfume> perfumeList = commentsMap.get(person);
			perfumeList.add(aPerfume);
			sortList(perfumeList);
			commentsMap.put(person, perfumeList);
		}		
		return commentsMap;	
	}
	
	/**
	 * This method sorts the list of perfumes according to the ratings
	 * @param <Perfume>
	 * */
	public ArrayList<Perfume> sortList(ArrayList <Perfume> perfumeList){
		perfumeList.sort(Comparator.comparingDouble(Perfume::getRating));
		return perfumeList;
	}
	
	
	/**
	 * This method display list of perfumes
	 * */
	public void displayList(ArrayList <Perfume> perfumeList) {
		if (perfumeList == null) {
			System.out.println("perfume list is null");
		} else if (perfumeList.size() == 0) {			
			System.out.println("perfume list is empty");
		} else {
			 // Iterating over the elements of the list
			for (Perfume perfume : perfumeList) {
				  System.out.println("name: " + perfume.getName() + ", rating: " + perfume.getRating());
			}
        }
	}
	
	/**
	 * This method display list of perfumes and person who comment them
	 * */
	public void displayList(String personName, ArrayList <Perfume> perfumeList) {
		 System.out.println("Comments from: " + personName);
		 // Iterating over the elements of the list
        for (Perfume element : perfumeList) {
            System.out.println("name: " + element.getName() + ", rating: " + element.getRating());
        }
	}
	
	/**
	 * This method display the map of person and list of perfumes
	 * */
	public void displayMap(Map<Person, ArrayList<Perfume>> map) {	
		System.out.println("printing map: ");
		for (Person person : map.keySet()) {
		    System.out.println("person: " + person.getName()); 
		    for (Perfume perfume: map.get(person)){
		        System.out.println("Perfume: " + perfume.getName() + ", Rating: " + perfume.getRating());
		    }
		}
			System.out.println();
		}
	
	/**
	 * test sort method and put to map in this class
	 * */
	public static void main(String[] args) {
		//test sorted list
	//	ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();
		Map<Person, ArrayList<Perfume>> myMap = new HashMap<Person, ArrayList<Perfume>>();
		
		PerfumeDataProcessor p = new PerfumeDataProcessor(myMap);
		Perfume a = new Perfume();
		a.setName("a");
		a.setRating(5.5);
		
		Perfume b = new Perfume();
		b.setName("b");
		b.setRating(3.3);
		
		Perfume c = new Perfume();
		c.setName("c");
		c.setRating(1.1);
		
		Perfume d = new Perfume();
		d.setName("d");
		d.setRating(1.1);
		
		Perfume e = new Perfume();
		e.setName("e");
		e.setRating(6.6);
		
	//	test put to map
		Person aPerson = new Person();
		aPerson.setName("Serena");	
		p.putPersonCommentsToMap(aPerson, a, myMap);
		p.putPersonCommentsToMap(aPerson, b, myMap);
	
		
		Person person2 = new Person();
		person2.setName("Silver");
		p.putPersonCommentsToMap(person2, c, myMap);
		p.putPersonCommentsToMap(person2, d, myMap);
		p.putPersonCommentsToMap(person2, e, myMap);
		//print map
		p.displayMap(myMap);	
	}
}
