package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerializationDataDao {
	private List<Data> listOfData = new ArrayList<>();
	private String pathFile;

	private static final int CHAR_SIZE_IN_BYTES = 2;
	private static final int ID_FIELD_SIZE_IN_BYTES = 4;
	private static final int ZIP_FIELD_SIZE_IN_BYTES = 8;
	private static final int NUM_FIELD_SIZE_IN_BYTES = 4;
	private static final int NAME_FIELD_SIZE_IN_BYTES = 20 * 2; // Every character is 2 bytes.
	private static final int NAME_FIELD_SIZE_IN_CHARS = 20; // Every character is 2 bytes.

	public SerializationDataDao(String pathFile) {
		this.pathFile = pathFile;
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
			
			FileInputStream fileInputStream = new FileInputStream(pathFile);
			long fileLength = fileInputStream.available();

			System.out.println("Initial file size: " + fileLength +  " bytes");
			// serialization of the data object (write to the file)
			 objectOutputStream.writeObject(data);
//			objectOutputStream.writeInt(data.getId());
//			objectOutputStream.writeLong(data.getZip());
//			objectOutputStream.writeInt(data.getNum());
//			String name = data.getName();
//
//			char[] nameArray = name.toCharArray();
//			int totalBytes = nameArray.length * CHAR_SIZE_IN_BYTES;
//
//			int bytesToSave = totalBytes <= NAME_FIELD_SIZE_IN_BYTES ? totalBytes : NAME_FIELD_SIZE_IN_BYTES;
//			int charsToSave = bytesToSave / CHAR_SIZE_IN_BYTES;
//
//			int index = 0;
//			for (index = 0; index < charsToSave; index++) {
//
//				objectOutputStream.writeChar(nameArray[index]);
//			}
//			for (int i = NAME_FIELD_SIZE_IN_CHARS - charsToSave; i > 0; i--) {
//
//				objectOutputStream.writeChar(0 /* NULL in ASCII */);
//			}
			 System.out.println("Final file size: " + fileLength + " bytes");
			// serialization of the data object (write to the list)
			// objectOutputStream.writeObject(listOfData);

			// System.out.println(data.getId() + " Object has been serialized");
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

			System.out.println("Initial file size: " + fileLength +  " bytes");
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
	
	public void write(Path path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
			Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
			Data d2 = new Data(2, 94102, 111, "BabyCat 2");
			out.writeObject(d1);
			out.writeObject(d2);

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
		SerializationDataDao serializationDataDao = new SerializationDataDao(path);
		
		serializationDataDao.write(filePath);
//
//		Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
//		Data d2 = new Data(2, 94102, 111, "BabyCat 2");
//		Data d3 = new Data(3, 94103, 222, "BabyCat 3");
//		Data d4 = new Data(4, 94104, 333, "BabyCat 4");
//
//		serializationDataDao.save(d1);
//		serializationDataDao.save(d2);
//		serializationDataDao.save(d3);
//		serializationDataDao.save(d4);
		
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
