package org.corejava.ch9.processinginputoutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DataDao {
	private List<Data> listOfData = new ArrayList<>();
	private String pathFile;

	private static final int CHAR_SIZE_IN_BYTES = 2;
	private static final int ID_FIELD_SIZE_IN_BYTES = 4;
	private static final int ZIP_FIELD_SIZE_IN_BYTES = 8;
	private static final int NUM_FIELD_SIZE_IN_BYTES = 4;
	private static final int NAME_FIELD_SIZE_IN_BYTES = 20 * 2; // Every character is 2 bytes.
	private static final int NAME_FIELD_SIZE_IN_CHARS = 20; // Every character is 2 bytes.

	/*
	 * The record size for the data record {@code Data}.
	 */
	private static final int DATA_RECORD_SIZE_IN_BYTES = ID_FIELD_SIZE_IN_BYTES + ZIP_FIELD_SIZE_IN_BYTES
			+ NUM_FIELD_SIZE_IN_BYTES + NAME_FIELD_SIZE_IN_BYTES;

	public DataDao(String pathFile) {
		this.pathFile = pathFile;
	}

	/**
	 * Store the data with String in the text file
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
			String name = data.getName();
			writeString(name, randomFile);

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
	public List<Data> findAll() {
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int end = 0;
			while (end < fileSize) {
				int id = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				String name = readString(randomFile);
				end = end + DATA_RECORD_SIZE_IN_BYTES;
				Data data = new Data(id, zip, num, name);
				listOfData.add(data);
			}
			System.out.println("done reading file");
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
	 * search a data object with specified id with data type string
	 */
	public Data findById(int dataId) {
		Data data = null;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int pointer = 0;
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				String name = readString(randomFile);
				pointer = pointer + DATA_RECORD_SIZE_IN_BYTES;
				if (currentId == dataId) {
					data = new Data(currentId, zip, num, name);
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
	 * with data type String to find the matching id replace the line with the new
	 * one
	 */
	public void updateData(Data data) {
		// new data entry id to be updated
		int dataId = data.getId();

		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			// where the current byte location is
			int pointer = 0;
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				pointer = pointer + DATA_RECORD_SIZE_IN_BYTES;
				randomFile.seek(pointer);
				if (currentId == dataId) {
					System.out.println("found id " + dataId);
					// move pointer backward to update num
					pointer = pointer - (ZIP_FIELD_SIZE_IN_BYTES + NUM_FIELD_SIZE_IN_BYTES + NAME_FIELD_SIZE_IN_BYTES);
					randomFile.seek(pointer);
					randomFile.writeInt(data.getNum());
					randomFile.writeLong(data.getZip());

					String name = data.getName();
					writeString(name, randomFile);
					break;
				} else {
					System.out.println(dataId + " NOT FOUND");
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

	/**
	 * delete the data entry in the text file
	 * 
	 * case 1: at the beginning and the middle of the file iterations of moving the
	 * current next one to the current position
	 * 
	 * case 2: at the end of the file overwrite all the data to 0
	 */
	// TODO: Use constants for fields
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

	/**
	 * delete the data entry in the text file with String data type
	 * 
	 * case 1: at the beginning and the middle of the file iterations of moving the
	 * current next one to the current position
	 * 
	 * case 2: at the end of the file overwrite all the data to 0
	 */
	public void deleteData(Data data) {
		// data entry id to be deleted
		int targetDataId = data.getId();
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();

			int currentIndex = 0;
			int targetIndexPosition = 0;
			// find target id position
			while (currentIndex + DATA_RECORD_SIZE_IN_BYTES < fileSize) {
				randomFile.seek(currentIndex);
				int currentId = randomFile.readInt();
				System.out.println("current Id: " + currentId);
				// walker = walker + ZIP_FIELD_SIZE_IN_BYTES + NUM_FIELD_SIZE_IN_BYTES +
				// NAME_FIELD_SIZE_IN_BYTES;
				if (currentId == targetDataId) {
					targetIndexPosition = currentIndex;
					System.out.println(
							"found the target id: " + currentId + " starts at position " + targetIndexPosition);
					break;
				}
				currentIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
			}
			// case 1 : the last data entry is the one to be deleted OR
			// case 2: the beginning or middle data entry to be deleted

			if (currentIndex + DATA_RECORD_SIZE_IN_BYTES == fileSize) {
				randomFile.seek(currentIndex);
				randomFile.writeInt(0);
				randomFile.writeLong(0);
				randomFile.writeInt(0);
				writeString("0", randomFile);
			} else {
				// overwrite the current entry with the next entry
				// int nxtIdIndex = currentIndex;
				int nxtEntryIndex = currentIndex;
				int expectedIndexPosition = currentIndex;
				while (currentIndex < fileSize) {
//					int curId = randomFile.readInt();
//					long curZip = randomFile.readLong();
//					int curNum = randomFile.readInt();
//					String curName = readString(randomFile);
					// System.out.println("cur id: " + curId + " cur zip: " + curZip + " cur name: "
					// + curName);
					// read the current's next entry so we have content to overwrite the current one
					nxtEntryIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
					randomFile.seek(nxtEntryIndex);
					int nxtId = randomFile.readInt();
					long nxtZip = randomFile.readLong();
					int nxtNum = randomFile.readInt();
					// might only walk util num index, so has to + string bytes
					String nxtName = readString(randomFile);
					int expectLoc = currentIndex;
					currentIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;

					// System.out.println("nxt id: " + nxtId + " nxt zip: " + nxtZip + "nxt num : "
					// + nxtNum + " nxt name: " + nxtName);

					// overwrite the current data
					randomFile.seek(expectLoc);
					randomFile.writeInt(nxtId);
					randomFile.writeLong(nxtZip);
					randomFile.writeInt(nxtNum);
					// might only walk til num index, so has to + string bytes
					writeString(nxtName, randomFile);

					// System.out.println("next id: " + nxtId + " starts at position " +
					// nxtIdIndex);
					currentIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
					
					// expectedIndexPosition = expectedIndexPosition + DATA_RECORD_SIZE_IN_BYTES;
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

	/**
	 * Read a string from the text file
	 * 
	 * @param startPoint where to start to read characters in file
	 * @return the String starting from the pass in position in file
	 */
	private String readString(RandomAccessFile randomFile) {
		String name = null;
		// read string
		StringBuilder sb = new StringBuilder();
		try {
			// read string
			int strLen = 0;
			for (int i = 0; i < NAME_FIELD_SIZE_IN_CHARS; i++) {
				char character = randomFile.readChar();
				if (character != 0) {
					strLen++;
					sb.append(character);
				}
			}
			// convert to string
			String wholeStr = sb.toString();
			name = wholeStr.substring(0, strLen);
			return name;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * Write a string in the text file
	 * 
	 * @param startingPosition where to start to write the characters in file
	 * @param aString          the String to be written
	 * @param expectedLength   is the length of the bytes we want to write in file
	 *                         for the String
	 * @return the written string's ending position in file
	 */
	private void writeString(String aString, RandomAccessFile randomFile) {

		char[] nameArray = aString.toCharArray();
		int totalBytes = nameArray.length * CHAR_SIZE_IN_BYTES;

		int bytesToSave = totalBytes <= NAME_FIELD_SIZE_IN_BYTES ? totalBytes : NAME_FIELD_SIZE_IN_BYTES;
		int charsToSave = bytesToSave / CHAR_SIZE_IN_BYTES;

		int index = 0;
		for (index = 0; index < charsToSave; index++) {
			try {
				randomFile.writeChar(nameArray[index]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int i = NAME_FIELD_SIZE_IN_CHARS - charsToSave; i > 0; i--) {
			try {
				randomFile.writeChar(0 /* NULL in ASCII */);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
		Data d2 = new Data(2, 94102, 111, "BabyCat 2");
		Data d3 = new Data(3, 94103, 222, "BabyCat 3");
		Data d4 = new Data(4, 94104, 333, "BabyCat 4");

		Data d5 = new Data(5, 94105555, 5555, "BabyCat");
		Data d6 = new Data(6, 94106, 555, "Hello Good night");
		Data d7 = new Data(7, 94107, 666, "Tiramisu");
		Data d8 = new Data(8, 94108, 777, "Tres Leches");
		Data d9 = new Data(9, 94109, 888, "Coconut cream");
		Data d10 = new Data(10, 2, 3, "Dark C");

		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myrandomdata.txt";
		DataDao datadao = new DataDao(path);

//		datadao.save(d1);
//		datadao.save(d2);
//		datadao.save(d3);
//		datadao.save(d4);
//		datadao.save(d5);		
//		datadao.save(d6);
//		datadao.save(d7);
//		datadao.save(d8);
//		datadao.save(d9);
//		datadao.save(d10);

//		datadao.printList(all);
		// Data targetD = datadao.findById(7);
		// System.out.println(targetD.toString());
//		datadao.updateData(d6);
//

//		List<Data> all = datadao.findAll();
//		datadao.printList(all);

		datadao.deleteData(d4);
		List<Data> after = datadao.findAll();
		datadao.printList(after);

	}
}
