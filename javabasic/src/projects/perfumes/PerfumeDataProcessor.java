package projects.perfumes;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class gets the information for the perfume and map them into a tree map, the order is based 
 * on the likeness of the perfume from lowest 0 to highest 100
 * */
public class PerfumeDataProcessor {
	Perfume perfume = new Perfume();	
	ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();
	Map<Person, ArrayList<Perfume>> mapOfcommentsForPerfumes;
	
	/**
	 * This method creates a Perfume object using the information provided by the user
	 * */
	public Perfume createPerfume() {
		Perfume p = new Perfume();
		String name = p.getName();
		double rating = p.getRatingScore();
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
	 * This method put the user info into a map, and adds a list of perfumes to a map
	 **/
	public Map<Person, ArrayList<Perfume>> getCommentsForPerfumes(Person name, Perfume aPerfume){
		ArrayList<Perfume> perfumes = new ArrayList<Perfume>();
		mapOfcommentsForPerfumes.putIfAbsent(name, perfumes);
		
		
		return mapOfcommentsForPerfumes;	
	}
	
	/**
	 * This method display list of perfumes
	 * */
	public void displayList() {
		 // Iterating over the elements of the list
        for (Perfume element : listOfPerfumes) {
            System.out.println("name: " + element.getName() + ", rating: " + element.getRatingScore());
        }
	}
	
	/**
	 * This method sorts the list of perfumes according to the ratings
	 * */

}
