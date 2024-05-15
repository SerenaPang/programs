package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SerializationDataDao {
	Path path;
	public SerializationDataDao(Path path) {
		this.path = path;
	}
	/**
	 * To serialize a list of objects
	 * 
	 * @throws IOException
	 */
	public void write(ArrayList<Data> dataList) {

		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
			out.writeObject(dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To read list of object from the serialized file
	 * 
	 * @throws IOException
	 */
	public ArrayList<Data> read() throws IOException {
		ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
		try {
			ArrayList<Data> dataList = (ArrayList<Data>) in.readObject();
			return dataList;
		} catch (ClassNotFoundException e) {
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
	public Data findById(int id) {
		Data data = null;
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Data> dataList;
			dataList = (ArrayList<Data>) in.readObject();
			for (Data dataEntry : dataList) {
				if (dataEntry.getId() == id) {
					// System.out.println(data.toString());
					data = dataEntry;
					return data;
				}
			}
			System.out.println("Data not found");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	/**
	 * Update the object by reading all the object list from the file, delete the
	 * original element, add the new element, delete the old file, create a new
	 * file, then write the new list to the file
	 * 
	 */
	public boolean updateData(Data newDataEntry) {
		boolean updated = false;
		File file = path.toFile();
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Data> dataList = (ArrayList<Data>) in.readObject();
			//find the target object		
			for (Data aDataEntry : dataList) {
				if (aDataEntry.getId() == newDataEntry.getId()) {
					// System.out.println("found: " + em.toString());
					dataList.remove(aDataEntry);
					//add the new element to the list for update
					dataList.add(newDataEntry);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("myserializationdata.txt");
					}
					// populate all employee product with the updated object to the new file
					path = file.toPath();
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(dataList);
					} catch (IOException e) {
						e.printStackTrace();
					}
					updated = true;
					return updated;
				}
				System.out.println("Data not found");
			}
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
	public boolean deleteData(int id) {
		boolean deleted = false;
		File file = path.toFile();
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Data> dataList = (ArrayList<Data>) in.readObject();
			// delete the target object
			for (Data aDataEntry : dataList) {
				if (aDataEntry.getId() == id) {
					// System.out.println("found: " + em.toString());
					dataList.remove(aDataEntry);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("myserializationdata.txt");
					}
					// populate all employee product to the new file
					path = file.toPath();
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(dataList);
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

	public static void main(String[] args) throws IOException {
		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myserializationdata.txt";
		Path filePath = Path.of(path);
		SerializationDataDao serializationDataDao = new SerializationDataDao(filePath);
	
		Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
		Data d2 = new Data(2, 94102, 111, "BabyCat 2");
		Data d3 = new Data(3, 94103, 222, "BabyCat 3");
		Data d4 = new Data(4, 94104, 333, "BabyCat 4");
		ArrayList<Data> dataList = new ArrayList<>();
		//test write
		dataList.add(d1);
		dataList.add(d2);
		dataList.add(d3);
		dataList.add(d4);		
		serializationDataDao.write(dataList);	
		//test read
		ArrayList<Data> dataListReadAll = serializationDataDao.read();
		for (Data aData : dataListReadAll) {
			System.out.println(aData.toString());
		}
		//test find by id		
		Data target = serializationDataDao.findById(1);
		System.out.println("find by id: " + target.toString());
		//test delete
		serializationDataDao.deleteData(1);
		ArrayList<Data> dataListDelete = serializationDataDao.read();
		for (Data aData : dataListDelete) {
			System.out.println(aData.toString());
		}
		//test update
		Data dd4 = new Data(4, 1, 222, "Tucy");
		serializationDataDao.updateData(dd4);
		ArrayList<Data> dataListUpdate = serializationDataDao.read();
		for (Data aData : dataListUpdate) {
			System.out.println(aData.toString());
		}
	}
}
