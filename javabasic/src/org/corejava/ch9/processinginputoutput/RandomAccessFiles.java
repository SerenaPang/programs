package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RandomAccessFiles {

	public static void main(String[] args) {
		String filePath = "test.dat";
		String delimiter = "///!///";
		// read lines2 to line 4
		String dialog = readRandomAccessFile(filePath, 2, 4, 26, delimiter);
		System.out.println(dialog);
	}

	/**
	 * 
	 * 
	 * @param filePath    file
	 * @param lineStart   to read from the line (inclusive)
	 * @param lineEnd     to read until the line (inclusive)
	 * @param charPerLine the number of character to be read in this line
	 * @param delimiter   to separate the line when
	 */
	private static String readRandomAccessFile(String filePath, int lineStart, int lineEnd, int charPerLine,
			String delimiter) {
		File file = new File(filePath);
		String data = "";
		ArrayList<String> dialogLinesRead = new ArrayList<String>();

		int bytesPerLine = charPerLine + 2;// the line has /n

		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			for (int i = lineStart; i < lineEnd; i++) {
				// read each line
				randomAccessFile.seek(bytesPerLine * i);
				data = randomAccessFile.readLine();
				dialogLinesRead.add(data);
			}
			randomAccessFile.close();
		} catch (Exception e) {
			System.out.println("Error occured");
		}
		// convert the list to String so we can return it
		String returnData = "";
		for (int i = 0; i < dialogLinesRead.size(); i++) {
			returnData += dialogLinesRead.get(i);
			// Separate the line
			returnData += delimiter;
		}
		// if no data found
		if (dialogLinesRead.isEmpty()) {
			data = "no data found";
		}
		return returnData;
	}
}
