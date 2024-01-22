package recursion;
import java.util.ArrayList;

/**
 * This class computes permutations of a string
 * */
public class Permutation {
	/**
	 * gets all permutations of a given word
	 * */
	public static ArrayList<String> permutations(String word) {
		ArrayList<String> result = new ArrayList<String>();
		//the empty string has a single permutation itself
		if (word.length() == 0) 
		{
			result.add(word);
			return result;
		}
		else 
		{
			//loop through all character positions
			for (int i = 0; i < word.length(); i++) 
			{
				//form a shorter word by removing character
				String shorter = word.substring(0, i) + word.substring(i + 1);
				//generate all permutations of this shorter word
				ArrayList<String> shorterPermutations = permutations(shorter);
				for (String s : shorterPermutations) 
				{
					result.add(word.charAt(i) + s);
				}
			}
			return result;
		}
	}
	
	public static void main(String[] args) {
		for (String s : permutations("eat")) {
			System.out.println(s);
		}
	}
}
