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

			// only allow max 20 characters, 20 * 2 bytes = 40 bytes
			// store the string in byte array, write it to file, put the unfilled bytes to 0
			String name = data.getName();
			// fill the byte array with the right length and content
			char[] nameArray = name.toCharArray();
			int totalBytes = nameArray.length * CHAR_SIZE_IN_BYTES;

			int bytesToSave = totalBytes <= NAME_FIELD_SIZE_IN_BYTES ? totalBytes : NAME_FIELD_SIZE_IN_BYTES;
			int charsToSave = bytesToSave / CHAR_SIZE_IN_BYTES;

			int index = 0;
			for (index = 0; index < charsToSave; index++) {
				randomFile.writeChar(nameArray[index]);
			}

			for (int i = NAME_FIELD_SIZE_IN_CHARS - charsToSave; i > 0; i--) {
				randomFile.writeChar(0 /* NULL in ASCII */);
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
	public List<Data> findAll() {
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int end = 0;
			while (end < fileSize) {
				// sum 16 bytes of data
				int id = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				// read string
				StringBuilder sb = new StringBuilder();
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
				String name = wholeStr.substring(0, strLen);

				// TODO: use constants for FIELDS
				end = end + ID_FIELD_SIZE_IN_BYTES + ZIP_FIELD_SIZE_IN_BYTES + NUM_FIELD_SIZE_IN_BYTES + NAME_FIELD_SIZE_IN_BYTES;
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
	// TODO: Use constants for fields.
	public Data findById(int dataId) {
		Data data = null;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int pointer = 0;
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +56 every time, when read the cursor move by 4, 8, and 4, and
			// 40 bytes of string
			// update pointer position for every iteration to know when reach the end
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				long zip = randomFile.readLong();
				int num = randomFile.readInt();
				// read string
				StringBuilder sb = new StringBuilder();
				int strLen = 0;
				for (int i = 0; i < 20; i++) {
					char character = randomFile.readChar();
					if (character != 'X') {
						strLen++;
					}
					sb.append(character);
				}

				// convert to string
				String wholeStr = sb.toString();
				String name = wholeStr.substring(0, strLen);
				pointer = pointer + 56;

				if (currentId == dataId) {
					data = new Data(currentId, zip, num, name);
					System.out.println("id: " + currentId + " zip: " + zip + " num: " + num + " name: " + name);
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

	// TODO: Consolidate update() and update() data.
	// TODO: Use constants for fields.
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
			// when it's the end of the file stop, pointer >= size of file
			// set the pointer +56 every time, when read the cursor move by 4, 8, and 4, and
			// 40 bytes of string
			// update pointer position for every iteration to know when reach the end
			// to get the data, create the data object and add to list
			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				pointer = pointer + 56;
				randomFile.seek(pointer);
				if (currentId == dataId) {
					System.out.println("found id " + dataId);
					// move pointer backward to update num
					pointer = pointer - 52;
					randomFile.seek(pointer);
					randomFile.writeInt(data.getNum());
					randomFile.writeLong(data.getZip());

					String name = data.getName();

					// fill the byte array with the right length and content
					char[] strArray = name.toCharArray();
					char[] byteArr = new char[20];

					// use X to fill out all blank spaces
					for (int j = 0; j < byteArr.length; j++) {
						byteArr[j] = 'X';
						// System.out.print(strArray[i] + " ");
					}
					// update the new string
					if (strArray.length <= byteArr.length) {
						// case 1: string length <= set array length
						for (int i = 0; i < strArray.length; i++) {
							byteArr[i] = strArray[i];
							// System.out.print(strArray[i] + " ");
						}

					} else { // case 2: string length > set array length
						for (int i = 0; i < byteArr.length; i++) {
							byteArr[i] = strArray[i];
						}
					}
					// write each character to the file
					for (int i = 0; i < byteArr.length; i++) {
						randomFile.writeChar(byteArr[i]);
						// System.out.println(i + " " + byteArr[i] + " ");
					}
					break;
				} else {
					System.out.println(dataId + " NOT FOUND");
				}
			}
			// findAll();
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
		int dataId = data.getId();
		long dataZip = data.getZip();
		int dataNum = data.getNum();

		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			// where the current byte location is
			int pointer = 0;
			int end = 0;
			// position of the data to be deleted
			int expectedLocation = findDataPosition(dataId);
			// now we know expectedLocation is where the current data entry to be deleted
			// case 2 where data to be deleted is at the end of the file
			if (expectedLocation + 56 == fileSize) {
				randomFile.seek(expectedLocation);
				randomFile.writeInt(0);
				randomFile.writeLong(0);
				randomFile.writeInt(0);
				for (int i = 0; i < 20; i++) {
					randomFile.writeChar(' ');
				}
			} else // case 1, data is at the beginning or the middle of the file
			{
				System.out.println("...");
				pointer = expectedLocation;
				while (end + 56 < fileSize) {
					randomFile.seek(expectedLocation + 56);

					// read the next data entry of the current data entry to be deleted
					int nxtId = randomFile.readInt();
					long nxtZip = randomFile.readLong();
					int nxtNum = randomFile.readInt();
					pointer = pointer + 16;
					String nxtName;
					// read string
					StringBuilder sb = new StringBuilder();
					int strLen = 0;
					for (int i = 0; i < 20; i++) {
						char character = randomFile.readChar();
						if (character != 'X') {
							strLen++;
						}
						sb.append(character);
					}
					pointer = pointer + 40;
					// convert to string
					String wholeStr = sb.toString();
					nxtName = wholeStr.substring(0, strLen);
					// System.out.println(" id : " + nxtId + " zip : " + nxtZip + " num: " + nxtNum
					// + " name: " + nxtName);

					// overwrite the data
					randomFile.seek(expectedLocation);
					randomFile.writeInt(nxtId);
					randomFile.writeLong(nxtZip);
					randomFile.writeInt(nxtNum);

					char[] strArray = nxtName.toCharArray();
					char[] byteArr = new char[20];

					// use X to fill out all blank spaces
					for (int j = 0; j < byteArr.length; j++) {
						byteArr[j] = 'X';
						// System.out.print(strArray[i] + " ");
					}
					// update the new string
					if (strArray.length <= byteArr.length) {
						// case 1: string length <= set array length
						for (int i = 0; i < strArray.length; i++) {
							byteArr[i] = strArray[i];
							// System.out.print(strArray[i] + " ");
						}

					} else { // case 2: string length > set array length
						for (int i = 0; i < byteArr.length; i++) {
							byteArr[i] = strArray[i];
						}
					}

					// write each character to the file
					for (int i = 0; i < byteArr.length; i++) {
						randomFile.writeChar(byteArr[i]);
					}

					expectedLocation = expectedLocation + 56;
					end = end + 56;
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

	/***
	 * Find the data entry by id, return the starting position of the data entry
	 * 
	 * @param id the data id to be found
	 * @return the starting position of the data entry
	 */
	private int findDataPosition(int id) {
		int position = -1;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int pointer = 0;

			while (pointer < fileSize) {
				int currentId = randomFile.readInt();
				System.out.println("current Id: " + currentId);
				pointer = pointer + 56;
				randomFile.seek(pointer);
				if (currentId == id) {
					position = pointer - 56;
					System.out.println("id: " + currentId + " at position " + position);
					return position;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
		return position;
	}

	/**
	 * Read a string from the text file
	 * 
	 * @param startPoint where to start to read characters in file
	 * @return the String starting from the pass in position in file
	 */
	private String readString(int startPoint) {
		String result;
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			randomFile.seek(startPoint);
			// read string
			StringBuilder sb = new StringBuilder();
			int strLen = 0;
			for (int i = 0; i < 20; i++) {
				char character = randomFile.readChar();
				if (character != 'X') {
					strLen++;
				}
				sb.append(character);
			}
			// convert to string
			String wholeStr = sb.toString();
			result = wholeStr.substring(0, strLen);
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
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
	private int writeString(String aString, int startingPosition, int expectedLength) {
		int endingPosition = startingPosition + expectedLength;
		// fill the byte array with the right length and content
		char[] strArray = aString.toCharArray();
		char[] byteArr = new char[expectedLength];

		// use X to fill out all blank spaces
		for (int j = 0; j < byteArr.length; j++) {
			byteArr[j] = 'X';
			// System.out.print(strArray[i] + " ");
		}
		// update the new string
		if (strArray.length <= byteArr.length) {
			// case 1: string length <= set array length
			for (int i = 0; i < strArray.length; i++) {
				byteArr[i] = strArray[i];
				// System.out.print(strArray[i] + " ");
			}

		} else { // case 2: string length > set array length
			for (int i = 0; i < byteArr.length; i++) {
				byteArr[i] = strArray[i];
			}
		}
		try (RandomAccessFile randomFile = new RandomAccessFile(pathFile, "rw")) {
			randomFile.seek(startingPosition);
			// write each character to the file
			for (int i = 0; i < byteArr.length; i++) {
				randomFile.writeChar(byteArr[i]);
				// System.out.println(i + " " + byteArr[i] + " ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + pathFile);
		}
		return endingPosition;
	}

	public static void main(String args[]) {
		Data d1 = new Data(1, 000, 000000111, "BabyCat 1");
		Data d2 = new Data(2, 94102, 111, "BabyCat 2");
		Data d3 = new Data(3, 94103, 222, "BabyCat 3");
		Data d4 = new Data(4, 94104, 333, "BabyCat 4");

		Data d5 = new Data(5, 94105555, 5555, "BabyCat");
		Data d6 = new Data(6, 94106, 555, "12345678901234567890ABCDEF");
		Data d7 = new Data(7, 94107, 666, "Tiramisu");
		Data d8 = new Data(8, 94108, 777, "Tres Leches");
		Data d9 = new Data(9, 94109, 888, "Coconut cream");
		Data d10 = new Data(10, 2, 3, "Dark C");

		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myrandomdata.txt";
		DataDao datadao = new DataDao(path);
		datadao.save(d1);
		datadao.save(d2);
		datadao.save(d3);
		datadao.save(d4);

		datadao.save(d5);
		datadao.save(d6);
		datadao.save(d7);
		datadao.save(d8);
		datadao.save(d9);
		datadao.save(d10);

		List<Data> all = datadao.findAll();
		for (Data data : all) {
			System.out.println(data);
		}

//		Data res = datadao.findById(3);
//		System.out.println(res);
//		datadao.update(d1);
//		datadao.readAll();
//		datadao.delete(d2);
//		System.out.println("After: ");

		// datadao.findAll();
		// datadao.searchById(10);
//		int pos = datadao.findDataPosition(6);

//		datadao.writeString("HIIII", pos + 16, 20);
//		String name = datadao.readString(pos + 16);
//		System.out.println(pos + 6 + " name: " + name);
		// datadao.updateData(d6);

		// datadao.deleteData(d10);
		// datadao.findAll();
	}
}
