package org.corejava.ch9.processinginputoutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SerializationDataDao {
	private List<Data> listOfData = new ArrayList<>();
	private HashSet<Data> setsOfData = new HashSet<>();
//	private String pathFile;
	Path filePath;
	String pathFile;
	// = Path.of(pathFile);

	private static final int CHAR_SIZE_IN_BYTES = 2;
	private static final int ID_FIELD_SIZE_IN_BYTES = 4;
	private static final int ZIP_FIELD_SIZE_IN_BYTES = 8;
	private static final int NUM_FIELD_SIZE_IN_BYTES = 4;
	private static final int NAME_FIELD_SIZE_IN_BYTES = 20 * 2; // Every character is 2 bytes.
	private static final int NAME_FIELD_SIZE_IN_CHARS = 20; // Every character is 2 bytes.

//	public SerializationDataDao(String pathFile) {
//		this.pathFile = pathFile;
//	}
//	
	public SerializationDataDao(Path filePath) {
		this.filePath = filePath;
		pathFile = filePath.toString();
	}

	/**
	 * Saving of object in a file
	 * 
	 * @param data object to be serialized
	 */
	public void save(Data data) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			// listOfData.add(data);
			setsOfData.add(data);
			System.out.println("adding " + data.toString());
			// printList();
			// serialization of the data object (write to the file)
			// objectOutputStream.writeObject(data);
			 write(filePath, setsOfData);

			// System.out.println(data.getId() + " Object has been serialized");
			//printSet();
			objectOutputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	/**
	 * Saving of object in a file
	 * 
	 * @param data object to be serialized
	 */
	public void save(List<Data> dataList) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			FileInputStream fileInputStream = new FileInputStream(pathFile);
			long fileLength = fileInputStream.available();

			System.out.println("Initial file size: " + fileLength + " bytes");
			// serialization of the data object (write to the file)
			for (Data d : dataList) {
				objectOutputStream.writeObject(d);
			}

			System.out.println("Final file size: " + fileLength + " bytes");

			objectOutputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	public void write(Path path, HashSet<Data> dataSet) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
//			Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
//			Data d2 = new Data(2, 94102, 111, "BabyCat 2");
//			out.writeObject(d1);
//			out.writeObject(d2);
			Iterator itr = setsOfData.iterator();
			while (itr.hasNext()) {
				Data d = (Data) itr.next();
				//System.out.println(itr.next());
				System.out.println(d.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Reading the object from a file
	 * 
	 * @return List<Data> list of data object
	 */
	public List<Data> findAll() {
		try {
			FileInputStream fileInputStream = new FileInputStream(pathFile);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			long fileLength = fileInputStream.available();
			System.out.println("file len: " + fileLength);
//			//deserialization of object
			Data data1 = (Data) objectInputStream.readObject();
//			listOfData.add(data);

			// deserialization of object
//			Data data = (Data) objectInputStream.readObject();
//
//			// listOfData.add(data);
//			System.out.println(data.toString());

			Data data2 = (Data) objectInputStream.readObject();
			System.out.println(data1.toString());
			System.out.println(data2.toString());
			objectInputStream.close();
			fileInputStream.close();
			System.out.println("Object has been deserialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException" + " is caught");
		}
		return listOfData;
	}

	/**
	 * 
	 * */
	public Data findById(Integer id) {
		Data data = null;

		return data;
	}

	/**
	 * print data object
	 */
	public void printdata(Data data) {
		System.out.println(data.toString());
	}

	/**
	 * print the list of data object
	 */
	public void printList() {
		System.out.println("Printing list");
		for (Data d : listOfData) {
			System.out.println(d.toString());
		}
	}

	/**
	 * print the set of data object
	 */
	public void printSet() {
		System.out.println("Printing Set");
		// creates Iterator object.
		Iterator itr = setsOfData.iterator();
		// check element is present or not. if not loop will
		// break.
		while (itr.hasNext()) {
			Data d = (Data) itr.next();
			//System.out.println(itr.next());
			System.out.println(d.toString());
		}
	}

	/**
	 * 
	 * */
	public boolean updateData(Data data) {
		boolean updated = false;

		// value of static variable changed
		data.setId(0);

		return updated;
	}

	/**
	 * 
	 * */
	public boolean deleteData(int id) {
		boolean deleted = false;

		return deleted;
	}

	public static void main(String[] args) {
		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myserializationdata.txt";
		List<Data> listOfData = new ArrayList<>();

		Path filePath = Path.of(path);
		// SerializationDataDao serializationDataDao = new SerializationDataDao(path);
		SerializationDataDao serializationDataDao = new SerializationDataDao(filePath);
		// serializationDataDao.write(filePath);

		Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
		Data d2 = new Data(2, 94102, 111, "BabyCat 2");
		Data d3 = new Data(3, 94103, 222, "BabyCat 3");
		Data d4 = new Data(4, 94104, 333, "BabyCat 4");

		serializationDataDao.save(d1);
		serializationDataDao.save(d2);
		serializationDataDao.save(d3);
		serializationDataDao.save(d4);

//		HashSet<Data> setsOfData = new HashSet<>();
//		serializationDataDao.printSet();
		
		
//		listOfData.add(d1);
//		listOfData.add(d2);
//		listOfData.add(d3);
//		listOfData.add(d4);
//		
//		serializationDataDao.save(listOfData);
//		List<Data> after = serializationDataDao.findAll();
		// serializationDataDao.printList();
		// serializationDataDao.findAll();
	}
}
