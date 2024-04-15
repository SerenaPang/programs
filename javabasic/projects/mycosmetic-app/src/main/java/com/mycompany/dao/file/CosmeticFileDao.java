package com.mycompany.dao.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

/**
 * This class read the file and extract the information from the file. and write
 * the user input into the file. The format of the file is
 * id:name:brand:category
 */
public class CosmeticFileDao implements CosmeticDao {
	// create a map to store all the product, the key is the product id
	Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();

	// create a file object
	File file;

	// create a constructor that receive the file pass in the command line
	public CosmeticFileDao(File file) {
		this.file = file;
	}

	/**
	 * This method writes the user input into the text file
	 * 
	 * @param id       id of the cosmetic product
	 * @param name     name of the cosmetic product
	 * @param brand    brand of the cosmetic product
	 * @param category category of the cosmetic product
	 */
	public void save(Cosmetic cosmetic) {
		// if the file exsist, open it append it, if not ccreate a new one
		try {
			// File file = new File("cosmetic.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			// add string to the file in new lines
			pw.println(cosmetic.getId() + ":" + cosmetic.getName() + ":" + cosmetic.getBrand() + ":"
					+ cosmetic.getCategory());
			pw.close();
			System.out.println("Data successfully appended at the end of file");

		} catch (IOException ioe) {
			System.out.println("Exception occur opening file:");
			ioe.printStackTrace();
		}
	}

	/**
	 * read file context and populate map of cosmetics The key is the id of the
	 */
	private void readFile() {
		try {
			// File file = new File("cosmetic.txt");
			Scanner scanner = new Scanner(file);
			System.out.println("reading file...");
			while (scanner.hasNextLine()) {
				// seperate the line with :, and create a cosmetic object
				String line = scanner.nextLine();
				String[] details = line.split(":");
				String id = details[0];
				// System.out.println("String id: " + id);
				// int numId = Integer.parseInt(details[0]);
				int numId = Integer.parseInt(id);

				String name = details[1];
				String brand = details[2];
				String category = details[3];

				Cosmetic cosme = new Cosmetic(numId, name, brand, category);
				productMap.put(numId, cosme);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * find the cosmetic object that matches the user id and return it
	 * 
	 * @param id product id to search
	 * @return cosmetic object that has the matching search id
	 */
	public Cosmetic findById(Integer id) {
		readFile();
		System.out.println("Finding id " + id + " in text file...");

		// if the search id matches with the current id, return it
		if (productMap.containsKey(id)) {
			return productMap.get(id);
		}

		return null;
	}

	/**
	 * Returns all cosmetic objects in the text file
	 */
	public List<Cosmetic> findAll() {
		List<Cosmetic> cosmetics = new ArrayList<>();

		try {
			// File file = new File("cosmetic.txt");
			Scanner scanner = new Scanner(file);
			System.out.println("reading file...");
			while (scanner.hasNextLine()) {
				// seperate the line with :, and create a cosmetic object
				String line = scanner.nextLine();
				String[] details = line.split(":");
				String id = details[0];
				// System.out.println("String id: " + id);
				// int numId = Integer.parseInt(details[0]);
				int numId = Integer.parseInt(id);

				String name = details[1];
				String brand = details[2];
				String category = details[3];

				Cosmetic cosme = new Cosmetic(numId, name, brand, category);
				cosmetics.add(cosme);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cosmetics;
	}

	/***
	 * remove the cosme element from the map, add the new cosme object, delete the
	 * old file, create a new file with the same name, populate all elements to new
	 * file with the passed in updated one
	 * 
	 * @param cosmetic the cosmetic object with the same id but different info to be
	 *                 updated
	 */
	public boolean updateCosmetic(Cosmetic cosmetic) {
		System.out.println("update cosmetics");
		int idToBeUpdated = cosmetic.getId();
		// make sure map is populated with all cosmetic product in file
		readFile();

		// remove the old cosmetic, add the new one to map
		if (productMap.containsKey(idToBeUpdated)) {
			productMap.remove(idToBeUpdated);
			productMap.put(idToBeUpdated, cosmetic);

			// delete the old file and populate all cosmetic product to the new file
			boolean result = file.delete();
			if (result) {
				System.out.println("Delete successful");
			}

			try {

				if (!file.exists()) {
					file = new File("cosmetic.txt");
				}
				System.out.println("new file: ");
				System.out.println(file.getName());
				System.out.println(file.getAbsolutePath());
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);

				// add each cosmetic object to the new file
				for (Map.Entry<Integer, Cosmetic> cosme : productMap.entrySet()) {
					 Cosmetic currentCosmetic = cosme.getValue();
						pw.println(currentCosmetic.getId() + ":" + currentCosmetic.getName() + ":" + currentCosmetic.getBrand() + ":"
						+ currentCosmetic.getCategory());			
				}
				pw.close();
				System.out.println("Data successfully updated in file");
				return true;
			} catch (IOException ioe) {
				System.out.println("Exception occur opening file:");
				ioe.printStackTrace();
			}
		} else {
			System.out.println("Product id " + idToBeUpdated + " is not in file.");
			return false;
		}
		System.out.println("Unable to update id " + idToBeUpdated);
		return false;
	}

	/***
	 * 
	 * */
	public boolean deleteCosmetic(int id) {
		System.out.println("delete cosmetics");
		productMap.remove(id);

		return false;
	}
}
