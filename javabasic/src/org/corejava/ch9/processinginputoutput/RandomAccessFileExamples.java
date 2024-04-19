package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExamples {
	/**
	 * To read a text file using RandomAccessFile
	 * @param file the file to read
	 * */
	public void read(File file) {
		try (RandomAccessFile reader = new RandomAccessFile(file, "r")){
	        String line;
	        while ( (line = reader.readLine()) != null ) {
	            System.out.println(line);
	        }

	        System.out.println();
	    } catch (FileNotFoundException fnfe) {
	    } catch (IOException ioe) {
	        System.err.println(ioe);
	    }
	}
	
	/**
	 * To read a specified position in the file using RandomAccessFile
	 * @param seek index of the file pointer, moving the file pointer to index
	 * @param charsLen length of the characters
	 * @return byte array the content of the file
	 * */
	public byte[] readCharsFromFile(String filePath, int seek, int charsLen) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(seek);
		byte[] bytes = new byte[charsLen];
		file.read(bytes);
		file.close();
		return bytes;
	}
	
	/**
	 * To write a text file using RandomAccessFile in a specified location
	 * @param filePath the file to write
	 * @param data content you want to write into the file
	 * @param seek the index where you put the data
	 * */
	public void write(String filePath, String data, int seek) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		file.seek(seek);
		file.write(data.getBytes());
		file.close();
	}
	
	/**
	 * To write at the end of the text file using RandomAccessFile
	 * @param filePath the file to write
	 * @param data content you want to write into the file
	 * @param seek the index where you put the data
	 * */
	public void appendData(String filePath, String data) throws IOException {
		RandomAccessFile raFile = new RandomAccessFile(filePath, "rw");
		raFile.seek(raFile.length());
		System.out.println("current pointer = "+raFile.getFilePointer());
		raFile.write(data.getBytes());
		raFile.close();	
	}

	public static void main(String args[]) {
		File file = new File(
				"/Users/serenapang/Development/JavaBasics/javabasic/src/org/corejava/ch9/processinginputoutput/cosmetic.txt");
		RandomAccessFileExamples ra = new RandomAccessFileExamples();
		
		try {
			ra.appendData(file.getAbsolutePath(), "4:aaa:bbb:ccc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ra.read(file);
//		try {
//			System.out.println(new String(ra.readCharsFromFile(file.getAbsolutePath(), 0, 5)));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
}
