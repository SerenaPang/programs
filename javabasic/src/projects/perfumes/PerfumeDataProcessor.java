package projects.perfumes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
	
	public PerfumeDataProcessor(ArrayList<Perfume> listOfPerfumes){
		this.listOfPerfumes = listOfPerfumes;
	}
	
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
	 * This method put the user info into a map, and adds a list of perfumes to a map
	 **/
	public Map<Person, ArrayList<Perfume>> getCommentsForPerfumes(Person name, Perfume aPerfume){
		ArrayList<Perfume> perfumes = new ArrayList<Perfume>();
		mapOfcommentsForPerfumes.putIfAbsent(name, perfumes);
		//TODO: ADD THE PERFUMES TO THE LST
		//SORT THE LIST OF PERFUMES IN THE LIST
		//PUT THE PERSON AND THE SORTED PERFUME TO THE MAP		
		//PRINT THE MAP WITH NAME OF THE PERSON WHO MAKE THE COMMENT; NAME OF PERFUME, AND THEIR RATINGS
		
		return mapOfcommentsForPerfumes;	
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
	 * This method sorts the list of perfumes according to the ratings
	 * */
	public List<Perfume> sortList(ArrayList <Perfume> perfumeList){
		perfumeList.sort(Comparator.comparingDouble(Perfume::getRating));
		return perfumeList;
	}
	
	/**
	 * test sort method for the list of perfumes
	 * */
	public static void main(String[] args) {
		ArrayList<Perfume> listOfPerfumes = new ArrayList<Perfume>();
		PerfumeDataProcessor p = new PerfumeDataProcessor(listOfPerfumes);
		Perfume a = new Perfume();
		a.setName("a");
		a.setRating(5.5);
		p.addToList(a);
		
		Perfume b = new Perfume();
		b.setName("b");
		b.setRating(3.3);
		p.addToList(b);
		
		Perfume c = new Perfume();
		c.setName("c");
		c.setRating(1.1);
		p.addToList(c);
		
		
		Perfume d = new Perfume();
		d.setName("d");
		d.setRating(1.1);
		p.addToList(d);
		
		Perfume e = new Perfume();
		e.setName("e");
		e.setRating(6.6);
		p.addToList(e);
		
		p.sortList(listOfPerfumes);
		p.displayList(listOfPerfumes);
	}
}
