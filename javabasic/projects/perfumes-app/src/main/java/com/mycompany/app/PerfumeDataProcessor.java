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

	
	public PerfumeDataProcessor(ArrayList<Perfume> listOfPerfumes, Map<Person, ArrayList<Perfume>> universalMap) {
		this.listOfPerfumes = listOfPerfumes;
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
	 * This method put the user info into a map, adds the perfume to the list of perfumes and put a list of perfumes to a map
	 **/
	public Map<Person, ArrayList<Perfume>> putPersonCommentsToMap(Person name, Perfume aPerfume, ArrayList<Perfume> perfumeList,Map<Person, ArrayList<Perfume>> commentsMap){
		//adds the perfume to the list of perfumes
		perfumeList.add(aPerfume);
		//sort the list of perfumes based on the rating
		 sortList(perfumeList);
		//put perfume and rating and person to the map
		commentsMap.putIfAbsent(name, perfumeList);
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
//		for(Entry<Person, ArrayList<Perfume>> entry : map.entrySet()) {
//			String personName = entry.getKey().getName();
//			//get the perfume list and print the list of perfumes
//			//displayList(personName, entry.getValue()); // the list of perfume objects
//			for (Perfume element : entry.getValue()) {
//	            System.out.println("perfume name: " + element.getName() + ", rating: " + element.getRating() + "comment by: " + personName);
//	        }
		for (Person person : map.keySet()) {
		    System.out.println("person: " + person.getName()); 
//		    for (Perfume perfume: map.get(person.getName())){
//		        System.out.println("Person: " + person.getName() + " Perfume: " + perfume.getName() + ", rating: " + perfume.getRating());
//		    }
		}
			System.out.println();
		}
	
	/**
	 * test sort method and put to map in this class
	 * */
	public static void main(String[] args) {
		//test sorted list
		ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();
		Map<Person, ArrayList<Perfume>> myMap = new HashMap<Person, ArrayList<Perfume>>();
		
		PerfumeDataProcessor p = new PerfumeDataProcessor(listOfPerfumes, myMap);
		Perfume a = new Perfume();
		a.setName("a");
		a.setRating(5.5);
	//	listOfPerfumes.add(a);
	//	p.addToList(a);
		
		Perfume b = new Perfume();
		b.setName("b");
		b.setRating(3.3);
	//	listOfPerfumes.add(b);
	//	p.addToList(b);
		
		Perfume c = new Perfume();
		c.setName("c");
		c.setRating(1.1);
//		p.addToList(c);
		
		
		Perfume d = new Perfume();
		d.setName("d");
		d.setRating(1.1);
//		p.addToList(d);
		
		Perfume e = new Perfume();
		e.setName("e");
		e.setRating(6.6);
//		p.addToList(e);
		
		//ArrayList<Perfume> sortedListOfPerfumes = p.sortList(listOfPerfumes);
		p.sortList(listOfPerfumes);
		
		
	//	test put to map
		Person aPerson = new Person();
		aPerson.setName("Serena");
		
		
		p.putPersonCommentsToMap(aPerson, a, listOfPerfumes, myMap);
		p.putPersonCommentsToMap(aPerson, b, listOfPerfumes, myMap);
	
		
		Person person2 = new Person();
		person2.setName("Silver");
		p.putPersonCommentsToMap(person2, c, listOfPerfumes, myMap);
		p.putPersonCommentsToMap(person2, d, listOfPerfumes, myMap);
		p.putPersonCommentsToMap(person2, e, listOfPerfumes, myMap);
		
		System.out.println("Print list of perfumes");
		p.displayList(listOfPerfumes);
		//print map
		p.displayMap(myMap);
//		
	}
}
