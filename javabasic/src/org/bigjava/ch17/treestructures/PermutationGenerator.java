package org.bigjava.ch17.treestructures;

import java.util.ArrayList;

/**
 * This class generates permutations of a word
 * */
public class PermutationGenerator {
	private String word;
	/**
	 * constructs a permutation generator
	 * @param aWord the word to permute
	 * */
	public PermutationGenerator(String aWord) {
		word = aWord;
	}
	
	/**
	 * Gets all permutations of a given word
	 * */
	public ArrayList<String> getPermutations() {
		ArrayList<String> permutations = new ArrayList<>();
		//The empty string has a single permutation : itself
		if (word.length() == 0) {
			permutations.add(word);
			return permutations;
		}
		//loop through all character positions
		for(int i = 0; i < word.length(); i++) {
			//form a simpler word by removing the ith character
			String shorterWord = word.substring(0, i) + word.substring(i + 1);
			//generate all permutations of the simpler word
			PermutationGenerator shorterPermutationGenerator = new PermutationGenerator(shorterWord);
			ArrayList<String> shorterWordPermutations = shorterPermutationGenerator.getPermutations();
			//add the removed character to the front of each permutation of the simpler word
			for (String s : shorterWordPermutations) {
				permutations.add(word.charAt(i) + s);
			}
		}
		//return all permutations
		return permutations;
	}
}
