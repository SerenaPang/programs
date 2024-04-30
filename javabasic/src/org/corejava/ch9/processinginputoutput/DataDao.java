package org.corejava.ch9.processinginputoutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DataDao {
	private List<Data> listOfData = new ArrayList<>();
	private String pathFile;

	public DataDao(String pathFile) {
		this.pathFile = pathFile;
	}

	/**
	 * store the data in the text file
	 */
	public void save(Data data) {
		// create a new RandomAccessFile with filename test "c:/test.txt"
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			System.out.println("Initial file size: " + randomFile.length() + " bytes");

			// Set the pointer till the end.
			randomFile.seek(randomFile.length());

			randomFile.writeInt(data.getId());
			randomFile.writeLong(data.getZip());
			randomFile.writeInt(data.getNum());

			System.out.println("Final file size: " + randomFile.length() + " bytes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
	}

	/**
	 * find all the data in the text file read each line, output to each field, form
	 * a data object add the object to list
	 */
	public List<Data> readAll() {
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			System.out.println("Initial file size: " + randomFile.length() + " bytes");

			long fileSize = randomFile.length();
			System.out.println("start reading file");

			int pointer = 0;

			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time read a line, move the pointer by 4, 8, and 4, update pointer position for every iteration
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				randomFile.seek(pointer);
				int id = randomFile.readInt();
				pointer = pointer + 4;
				randomFile.seek(pointer);
				long zip = randomFile.readLong();
				pointer = pointer + 8;
				randomFile.seek(pointer);
				int num = randomFile.readInt();
				pointer = pointer + 4;
				System.out.println("id: " + id + " zip: " + zip + " num: " + num);
				Data data = new Data(id, zip, num);
				listOfData.add(data);
			}
			System.out.println("done reading file");
			printList(listOfData);
			// System.out.println("Final file size: " + randomFile.length() + " bytes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
		return listOfData;
	}

	/**
	 * print the list of data object
	 */
	private void printList( List<Data> listOfData) {
		for (Data d : listOfData) {
			System.out.println(d.toString());
		}
	}
	
	/**
	 * find a data object with specified id
	 * */
	public Data findById(int dataId) {
		Data data = null;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");

			int pointer = 0;
			
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time read a line, move the pointer by 4, 8, and 4, update pointer position for every iteration
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				randomFile.seek(pointer);
				int currentId = randomFile.readInt();
				pointer = pointer + 4;
				randomFile.seek(pointer);
				long zip = randomFile.readLong();
				pointer = pointer + 8;
				randomFile.seek(pointer);
				int num = randomFile.readInt();
				pointer = pointer + 4;
				if (currentId == dataId) {
					data = new Data(currentId, zip, num);
					System.out.println("id: " + currentId + " zip: " + zip + " num: " + num);		
					return data;
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
		return data;
	}

	/**
	 * update specific data entry in the text file read the first filed in each line
	 * to find the matching id replace the line with the new one
	 */
	public void update(Data data) {
		//new data entry id to be updated
		int dataId = data.getId();
		
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");

			int pointer = 0;
			
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time read a line, move the pointer by 4, 8, and 4, update pointer position for every iteration
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				randomFile.seek(pointer);
				int currentId = randomFile.readInt();
				pointer = pointer + 4;
				randomFile.seek(pointer);
				long zip = randomFile.readLong();
				pointer = pointer + 8;
				randomFile.seek(pointer);
				int num = randomFile.readInt();
				pointer = pointer + 4;
				//found the matching id, move the pointer to each location, write the new info
				if (currentId == dataId) {
					System.out.println("found id " + dataId);
//					move pointer backward to update num
					pointer = pointer - 4;		
					randomFile.seek(pointer);
					randomFile.writeInt(data.getNum());
					//move pointer backward to /update zip
					pointer = pointer - 8;
					randomFile.seek(pointer);
					randomFile.writeLong(data.getZip());
					break;
				}	
			}
			readAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
	}

	/**
	 * delete the data entry in the text file
	 * 
	 */
	public void delete(Data data) {

	}

	public static void main(String args[]) {
		Data d1 = new Data(18, 23547768, 300);
//		Data d2 = new Data(2, 94102, 111);
//		Data d3 = new Data(3, 94103, 222);
//		Data d4 = new Data(4, 94104, 333);
		Data d5 = new Data(5, 94105555, 5555);
//		Data d6 = new Data(6, 94106, 555);
//		Data d7 = new Data(7, 94107, 666);
//		Data d8 = new Data(8, 94108, 777);
//		Data d9 = new Data(9, 94109, 888);
//		Data d10 = new Data(10, 94101, 999);

		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myrandomdata.txt";
		DataDao datadao = new DataDao(path);
		//datadao.save(d5);
		//datadao.readAll();
		// System.out.println("Finish saving");
		// datadao.save(d2);
		//datadao.findById(18);
		datadao.update(d5);
	}
}
