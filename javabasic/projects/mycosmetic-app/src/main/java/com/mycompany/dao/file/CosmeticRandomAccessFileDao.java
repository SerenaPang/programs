package com.mycompany.dao.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

public class CosmeticRandomAccessFileDao implements CosmeticDao {
	// create a map to store all the product, the key is the product id
	Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();
	private List<Cosmetic> listOfCosmetic = new ArrayList<>();
	// create a file object
	File file;
	long fileSize;

	private static final int CHAR_SIZE_IN_BYTES = 2;
	private static final int ID_FIELD_SIZE_IN_BYTES = 4;
	private static final int STRING_FIELD_SIZE_IN_BYTES = 20 * 2; // Every character is 2 bytes.
	private static final int STRING_FIELD_SIZE_IN_CHARS = 20; // Every character is 2 bytes.
	/*
	 * The record size for the data record {@code Data}.
	 */
	private static final int DATA_RECORD_SIZE_IN_BYTES = ID_FIELD_SIZE_IN_BYTES + STRING_FIELD_SIZE_IN_BYTES
			+ STRING_FIELD_SIZE_IN_BYTES + STRING_FIELD_SIZE_IN_BYTES;

	// create a constructor that receive the file pass in the command line
	public CosmeticRandomAccessFileDao(File file) {
		this.file = file;

	}

	/**
	 * Get the original length of the file
	 */
	public long getLength() {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "r")) {
			long fileLength = randomFile.length();

			return fileLength;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}
	}

	/**
	 * This method writes the user input into the text at the end of the text file
	 * using RandomAccessFile
	 * 
	 * @param cosmetic the cosmetic object to write
	 */
	public void save(Cosmetic cosmetic) {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			System.out.println("Initial file size: " + randomFile.length() + " bytes");
			// Set the pointer till the end.
			randomFile.seek(randomFile.length());
			randomFile.writeInt(cosmetic.getId());
			writeString(cosmetic.getName(), randomFile);
			writeString(cosmetic.getBrand(), randomFile);
			writeString(cosmetic.getCategory(), randomFile);
			System.out.println("Final file size: " + randomFile.length() + " bytes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}
	}

	/**
	 * Search a Cosmetic object with specified id
	 * 
	 * @param id the id of the cosmetic object to search
	 */
	public Cosmetic findById(Integer id) {
		Cosmetic cosmetic = null;
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileSize = randomFile.length();
			int curIndex = 0;
			while (curIndex < fileSize) {
				int currentId = randomFile.readInt();
				String name = readString(randomFile);
				String brand = readString(randomFile);
				String category = readString(randomFile);
				curIndex = curIndex + DATA_RECORD_SIZE_IN_BYTES;
				if (currentId == id) {
					cosmetic = new Cosmetic(id, name, brand, category);
					return cosmetic;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}
		return cosmetic;
	}

	/**
	 * Find all the data in the text file, output to the object to list
	 * 
	 * @return List<Cosmetic> list of Cosmetic in the file
	 */
	public List<Cosmetic> findAll() {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileSize = randomFile.length();
			System.out.println("start reading file");
			int end = 0;
			while (end < fileSize) {
				int id = randomFile.readInt();
				String name = readString(randomFile);
				String brand = readString(randomFile);
				String category = readString(randomFile);
				Cosmetic cosmetic = new Cosmetic(id, name, brand, category);
				listOfCosmetic.add(cosmetic);
				end = end + DATA_RECORD_SIZE_IN_BYTES;
			}
			System.out.println("done reading file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}
		return listOfCosmetic;
	}

	/**
	 * update specific data entry in the text file
	 * 
	 * @param cosmetic a cosmetic object to be updated
	 */
	public boolean updateCosmetic(Cosmetic cosmetic) {
		boolean updated = false;
		// new data entry id to be updated
		int cosmeticId = cosmetic.getId();
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileSize = randomFile.length();
			// where the current byte location is
			int curIndex = 0;
			while (curIndex < fileSize) {
				int currentId = randomFile.readInt();
				curIndex = curIndex + DATA_RECORD_SIZE_IN_BYTES;
				randomFile.seek(curIndex);
				if (currentId == cosmeticId) {
					System.out.println("found id " + cosmeticId);
					// move curIndex backward to update name, brand and category
					curIndex = curIndex
							- (STRING_FIELD_SIZE_IN_BYTES + STRING_FIELD_SIZE_IN_BYTES + STRING_FIELD_SIZE_IN_BYTES);
					randomFile.seek(curIndex);
					writeString(cosmetic.getName(), randomFile);
					writeString(cosmetic.getBrand(), randomFile);
					writeString(cosmetic.getCategory(), randomFile);
					updated = true;
					return updated;
				} else {
					System.out.println(cosmeticId + " NOT FOUND");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}
		return updated;
	}

	private void deleteHelper(int indexToBeDeleted, long fileSize, RandomAccessFile randomFile) {
		int currentIndex = indexToBeDeleted;
		// case 1 : the last data entry is the one to be deleted OR
		// case 2: the beginning or middle data entry to be deleted
		try (randomFile) {
			if (currentIndex + DATA_RECORD_SIZE_IN_BYTES == fileSize) {
				randomFile.seek(currentIndex);
				randomFile.writeInt(0);
				writeString("0", randomFile);
				writeString("0", randomFile);
				writeString("0", randomFile);
				// or just ignore the last entry
				// fileSize = fileSize - DATA_RECORD_SIZE_IN_BYTES;
			} else {
				// overwrite the current entry with the next entry
				int nxt = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
				while (nxt < fileSize) {
					randomFile.seek(nxt);
					//record the next entry
					int nxtId = randomFile.readInt();
					String nxtname = readString(randomFile);
					String nxtbrand = readString(randomFile);
					String nxtcategory = readString(randomFile);

					nxt = nxt + DATA_RECORD_SIZE_IN_BYTES;
					// overwrite the current data
					randomFile.seek(currentIndex);
					randomFile.writeInt(nxtId);
					writeString(nxtname, randomFile);
					writeString(nxtbrand, randomFile);
					writeString(nxtcategory, randomFile);

					// System.out.println("next id: " + nxtId + " starts at position " +
					// nxtIdIndex);
					currentIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
					
				}
				// decrease the size by one entry
				fileSize = fileSize - DATA_RECORD_SIZE_IN_BYTES;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
		}

	}

	/**
	 * delete specific data entry in the text file
	 * 
	 * @param cosmetic a cosmetic object to be deleted
	 */
	public boolean deleteCosmetic(int id) {
		System.out.println("delete cosmetic");
		try (RandomAccessFile randomFile = new RandomAccessFile(file.getPath(), "rw")) {
			long fileLength = randomFile.length();
			fileSize = fileLength;
			int currentIndex = 0;
			// find target id position
			while (currentIndex + DATA_RECORD_SIZE_IN_BYTES < fileSize) {
				randomFile.seek(currentIndex);
				int currentId = randomFile.readInt();
				// System.out.println("current Id: " + currentId);
				// walker = walker + ZIP_FIELD_SIZE_IN_BYTES + NUM_FIELD_SIZE_IN_BYTES +
				// NAME_FIELD_SIZE_IN_BYTES;
				if (currentId == id) {
					// case 1 : the last data entry is the one to be deleted OR
					// case 2: the beginning or middle data entry to be deleted
//					System.out.println(
//							"found the target id: " + currentId + " starts at position " + targetIndexPosition);
					break;
				}
				currentIndex = currentIndex + DATA_RECORD_SIZE_IN_BYTES;
			}

			deleteHelper(currentIndex, fileSize, randomFile);
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open the file " + file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to access " + file);
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
	private void writeString(String aString, RandomAccessFile randomFile) {
		char[] stringArray = aString.toCharArray();
		int totalBytes = stringArray.length * CHAR_SIZE_IN_BYTES;

		int bytesToSave = totalBytes <= STRING_FIELD_SIZE_IN_BYTES ? totalBytes : STRING_FIELD_SIZE_IN_BYTES;
		int charsToSave = bytesToSave / CHAR_SIZE_IN_BYTES;

		int index = 0;
		for (index = 0; index < charsToSave; index++) {
			try {
				randomFile.writeChar(stringArray[index]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int i = STRING_FIELD_SIZE_IN_CHARS - charsToSave; i > 0; i--) {
			try {
				randomFile.writeChar(0 /* NULL in ASCII */);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		StringBuilder sb = new StringBuilder();
		try {
			// read string
			int strLen = 0;
			for (int i = 0; i < STRING_FIELD_SIZE_IN_CHARS; i++) {
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
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * print the list of Cosmetic object
	 */
	private void printList(List<Cosmetic> listOfCosmetic) {
		for (Cosmetic c : listOfCosmetic) {
			System.out.println(c.toString());
		}
	}
}