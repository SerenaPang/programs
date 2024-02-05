package projects.perfumes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * This class gets the information for the perfume and map them into a tree map, the order is based 
 * on the likeness of the perfume from lowest 0 to highest 100
 * */
public class PerfumeDataProcessor {
	Perfume perfume = new Perfume();	
	ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();


	
	/**
	 * This method creates a Perfume object using the information provided by the user
	 * */
	public Perfume createPerfume() {
		Perfume p = new Perfume();
		String name = p.getName();
		double rating = p.getRating();
		p.setName(name);
		p.setRatingScore(rating);
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
		Map<Person, ArrayList<Perfume>> mapOfcommentsForPerfumes = commentsMap;
		//adds the perfume to the list of perfumes
		perfumeList.add(aPerfume);
		//sort the list of perfumes based on the rating
		ArrayList<Perfume> sortedListOfPerfumes = sortList(perfumeList);
		//put perfume and rating and person to the map
		mapOfcommentsForPerfumes.putIfAbsent(name, sortedListOfPerfumes);
		return commentsMap;	
	}
	
	/**
	 * This method sorts the list of perfumes according to the ratings
	 * */
	public ArrayList<Perfume> sortList(ArrayList <Perfume> perfumeList){
		perfumeList.sort(Comparator.comparingDouble(Perfume::getRating));
		return perfumeList;
	}
	
	
	/**
	 * This method display list of perfumes
	 * */
	public void displayList(ArrayList <Perfume> perfumeList) {
		 // Iterating over the elements of the list
        for (Perfume element : perfumeList) {
            System.out.println("name: " + element.getName() + ", rating: " + element.getRating());
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
		for(Entry<Person, ArrayList<Perfume>> entry : map.entrySet()) {
			String personName = entry.getKey().getName();
			//get the perfume list and print the list of perfumes
			displayList(personName, entry.getValue()); // the list of perfume objects
		}
	}
	
	/**
	 * test sort method and put to map in this class
	 * */
	public static void main(String[] args) {
		//test sorted list
		ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();
		PerfumeDataProcessor p = new PerfumeDataProcessor(listOfPerfumes);
		Perfume a = new Perfume();
		a.setName("a");
		a.setRating(5.5);
	//	p.addToList(a);
		
		Perfume b = new Perfume();
		b.setName("b");
		b.setRating(3.3);
	//	p.addToList(b);
		
		Perfume c = new Perfume();
		c.setName("c");
		c.setRating(1.1);
	//	p.addToList(c);
		
		
		Perfume d = new Perfume();
		d.setName("d");
		d.setRating(1.1);
	//	p.addToList(d);
		
		Perfume e = new Perfume();
		e.setName("e");
		e.setRating(6.6);
	//	p.addToList(e);
		
		p.sortList(listOfPerfumes);
		p.displayList(listOfPerfumes);
		
		//test put to map
		Person aPerson = new Person();
		aPerson.setName("Jude");
		Map<Person, ArrayList<Perfume>> myMap = new HashMap<Person, ArrayList<Perfume>>();
		p.putPersonCommentsToMap(aPerson, a, listOfPerfumes, myMap);
		//print map
		p.displayMap(myMap);
		
	}
}
