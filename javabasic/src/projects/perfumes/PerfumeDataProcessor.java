package projects.perfumes;
import java.util.TreeMap;

/**
 * This class gets the information for the perfume and map them into a tree map, the order is based 
 * on the likeness of the perfume from lowest 0 to highest 100
 * */
public class PerfumeDataProcessor {
	TreeMap<Double, String> mapOfPerfume = new TreeMap<Double, String>();
	PerfumeRecorder info = new PerfumeRecorder();
	
	/**
	 * This method gets the perfume's name and the user likeness and put them in a map
	 * */
	public TreeMap<Double, String> infoToMap() {
		String name = info.getName();
		double score = info.getScore();	
		mapOfPerfume.put(score, name);
		return mapOfPerfume;
	}
	
	/**
	 * This method display the map in sorted order based on the likeness ranking
	 * */
	public void displayMap() {
		 // Iterating over the elements of the tree map
        for (Double key : mapOfPerfume.keySet()) {
            System.out.println("Likeness: " + key + ", Name: " + mapOfPerfume.get(key));
        }
	}
	
}
