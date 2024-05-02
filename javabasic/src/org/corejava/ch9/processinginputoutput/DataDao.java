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
			// we don't have to move the pointer as when writing the content, the pointer
			// moves by the byte size of the data
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
	 * store the data with String in the text file
	 */
	public void add(Data data) {
		// create a new RandomAccessFile with filename test "c:/test.txt"
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			System.out.println("Initial file size: " + randomFile.length() + " bytes");

			// Set the pointer till the end.
			randomFile.seek(randomFile.length());

			randomFile.writeInt(data.getId());
			randomFile.writeLong(data.getZip());
			randomFile.writeInt(data.getNum());

			// only allow max 20 characters, 20 * 2 bytes = 40 bytes
			// store the string in byte array, write it to file, put the unfilled bytes to 0
			String name = data.getName();

			// fill the byte array with the right length and content
			char[] strArray = name.toCharArray();
			char[] byteArr = new char[20];
			if (strArray.length <= byteArr.length) {
				// string length <= set array length
				for (int i = 0; i < strArray.length; i++) {
					byteArr[i] = strArray[i];
					// System.out.print(strArray[i] + " ");
				}
				for (int j = strArray.length; j < byteArr.length; j++) {
					byteArr[j] = 'X';
					// System.out.print(strArray[i] + " ");
				}
			} else { // string length > set array length
				for (int i = 0; i < byteArr.length; i++) {
					byteArr[i] = strArray[i];
				}
			}
//			read array content
//			for (int i = 0; i < byteArr.length; i++) {
//				System.out.println(i + " " + byteArr[i] + " ");
//			}

			// write each character to the file
			for (int i = 0; i < byteArr.length; i++) {
				randomFile.writeChar(byteArr[i]);
				// System.out.println(i + " " + byteArr[i] + " ");
			}
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
			int end = 0;
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time so we know when reached the end of the file,
			// when read the pointer walk by itself by data byte size 4, 8, and 4
			// to get the data, create the data object and add to list
			while (end < fileSize) {
				int id = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				end = end + 16;
				System.out.println("id: " + id + "    zip: " + zip + "    num: " + num);
				Data data = new Data(id, zip, num);
				listOfData.add(data);
			}
			System.out.println("done reading file");
			// printList(listOfData);
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
	 * find all the data in the text file read each line, output to each field, form
	 * a data object add the object to list
	 */
	public List<Data> findAll() {
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			System.out.println("Initial file size: " + randomFile.length() + " bytes");
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int end = 0;

			while (end < fileSize) {
				int id = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				// char name = randomFile.readChar();
				end = end + 16;
				System.out.println("id: " + id + "    zip: " + zip + "    num: " + num);
				Data data = new Data(id, zip, num);
				listOfData.add(data);
			}
			System.out.println("done reading file");
			// printList(listOfData);
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
	private void printList(List<Data> listOfData) {
		for (Data d : listOfData) {
			System.out.println(d.toString());
		}
	}

	/**
	 * find a data object with specified id
	 */
	public Data findById(int dataId) {
		Data data = null;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int pointer = 0;
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time, when read the cursor move by 4, 8, and 4
			// update pointer position for every iteration to know when reach the end
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				pointer = pointer + 16;
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
		// new data entry id to be updated
		int dataId = data.getId();

		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			// where the current byte location is
			int pointer = 0;
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +16 every time read a data entry
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				pointer = pointer + 16;
				// found the matching id, move the pointer to each location, write the new info
				if (currentId == dataId) {
					System.out.println("found id " + dataId);
					// move pointer backward to update num
					pointer = pointer - 4;
					randomFile.seek(pointer);
					randomFile.writeInt(data.getNum());
					// move pointer backward to /update zip
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
	 * case 1: at the beginning and the middle of the file iterations of moving the
	 * current next one to the current position
	 * 
	 * case 2: at the end of the file overwrite all the data to 0
	 */
	public void delete(Data data) {
		// data entry id to be deleted
		int dataId = data.getId();
		long dataZip = data.getZip();
		int dataNum = data.getNum();

		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			// where the current byte location is
			int pointer = 0;
			int end = 0;
			int expectedLocation = 0;
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				// at this moment pointer stands at the location of the start of the next entry
				pointer = pointer + 16;
				end = end + 16;
				// found the matching id, move the pointer to each location, write the new info
				if (currentId == dataId && dataZip == zip && dataNum == num) {
					System.out.println("found Data " + dataId);
					expectedLocation = pointer - 16;
					break;
				}
			}
			// now we know expectedLocation is where the current data entry to be deleted
			// case 2
			if (expectedLocation + 16 == fileSize) {
				randomFile.seek(expectedLocation);
				randomFile.writeInt(0);
				randomFile.writeLong(0);
				randomFile.writeInt(0);
			} else // case 1
			{
				System.out.println("...");
				while (end < fileSize) {
					// update the pointer for each copy iteration so we don't copy the same thing we
					// just did
					randomFile.seek(pointer);
					// read the next data entry
					int nxtId = randomFile.readInt();
					long nxtZip = randomFile.readLong();
					int nxtNum = randomFile.readInt();
					pointer = pointer + 16;

					randomFile.seek(expectedLocation);
					randomFile.writeInt(nxtId);
					randomFile.writeLong(nxtZip);
					randomFile.writeInt(nxtNum);
					// move the tracker to the next data entry position
					end = end + 16;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
	}

	public static void main(String args[]) {
		Data d1 = new Data(1, 000, 000000111);
		Data d2 = new Data(2, 94102, 111);
		Data d3 = new Data(3, 94103, 222);
		Data d4 = new Data(4, 94104, 333);

		Data d5 = new Data(5, 94105555, 5555, "Silver ");
		Data d6 = new Data(6, 94106, 555, "Diana");
		Data d7 = new Data(7, 94107, 666, "Tiramisu");
		Data d8 = new Data(8, 94108, 777, "Tres Leches");
		Data d9 = new Data(9, 94109, 888, "Coconut cream");
		Data d10 = new Data(10, 2, 3, "Dark Chocalate");

		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myrandomdata.txt";
		DataDao datadao = new DataDao(path);
//		datadao.save(d1);
//		datadao.save(d2);
//		datadao.save(d3);
//		datadao.save(d4);

		datadao.add(d5);
//		datadao.add(d6);
//		datadao.add(d7);
//		datadao.add(d8);
//		datadao.add(d9);
//		datadao.add(d10);

		// System.out.println("Before: ");
		// datadao.readAll();
		// System.out.println("Finish saving");
		// datadao.findById(0);
//		datadao.update(d1);
//		datadao.readAll();
//		datadao.delete(d2);
//		System.out.println("After: ");

		// datadao.readAll();
	}
}
