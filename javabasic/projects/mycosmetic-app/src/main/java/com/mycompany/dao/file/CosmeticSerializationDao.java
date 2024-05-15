package com.mycompany.dao.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Path;
import java.util.List;
import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

public class CosmeticSerializationDao implements CosmeticDao {
	File file;
	Path path;
	private List<Cosmetic> cosmeticList = new ArrayList<>();

	public CosmeticSerializationDao(File file) {
		this.file = file;
		path = file.toPath();
	}

	public void save(Cosmetic cosmetic) {
		cosmeticList.add(cosmetic);
		write(cosmeticList);
	}

	/**
	 * To serialize a list of objects
	 * 
	 * @throws IOException
	 */
	private void write(List<Cosmetic> cosmeticList) {
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
			out.writeObject(cosmeticList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To read list of object from the serialized file
	 * 
	 * @throws IOException
	 */
	public List<Cosmetic> findAll(){	
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			List<Cosmetic> cosmeticList = (ArrayList<Cosmetic>) in.readObject();
			return cosmeticList;
		} catch (ClassNotFoundException | IOException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Find the target element in file by target's id
	 * 
	 * reading all the object list from the file,if the element id matches target
	 * id, return that element as result
	 */
	public Cosmetic findById(Integer id) {
		Cosmetic cosmetic = null;
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			cosmeticList = (ArrayList<Cosmetic>) in.readObject();
			for (Cosmetic aCosme : cosmeticList) {
				if (aCosme.getId() == id) {
					// System.out.println(aCosme.toString());
					cosmetic = aCosme;
					return cosmetic;
				}
			}
			System.out.println("Data not found");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return cosmetic;
	}

	/**
	 * Update the object by reading all the object list from the file, delete the
	 * original element, add the new element, delete the old file, create a new
	 * file, then write the new list to the file
	 * 
	 */
	public boolean updateCosmetic(Cosmetic newCosmeticEntry) {
		boolean updated = false;
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			cosmeticList = (ArrayList<Cosmetic>) in.readObject();
			// find the target object
			for (Cosmetic aCosme : cosmeticList) {
				if (aCosme.getId() == newCosmeticEntry.getId()) {
					// System.out.println("found: " + aCosme.toString());
					cosmeticList.remove(aCosme);
					// add the new element to the list for update
					cosmeticList.add(newCosmeticEntry);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("serialiazationcosmetic.txt");
					}
					// populate all cosmetic product with the updated object to the new file
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(cosmeticList);
					} catch (IOException e) {
						e.printStackTrace();
					}
					updated = true;
					return updated;
				}
			}
			System.out.println("Data not found");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return updated;
	}

	/**
	 * Delete the target object with the specific id reading all the object list
	 * from the file, delete the original element, delete the old file, create a new
	 * file, then write the new list to the file
	 */
	public boolean deleteCosmetic(int id) {
		boolean deleted = false;
		File file = path.toFile();
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			cosmeticList = (ArrayList<Cosmetic>) in.readObject();
			// delete the target object
			for (Cosmetic aCosme : cosmeticList) {
				if (aCosme.getId() == id) {
					// System.out.println("found: " + aCosme.toString());
					cosmeticList.remove(aCosme);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("serialiazationcosmetic.txt");
					}
					// populate all employee product to the new file
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(cosmeticList);
					} catch (IOException e) {
						e.printStackTrace();
					}
					deleted = true;
					return deleted;
				}
			}
			System.out.println("Data not found");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}