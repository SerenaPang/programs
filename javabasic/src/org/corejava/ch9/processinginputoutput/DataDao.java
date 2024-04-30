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
		return listOfData;
	}

	/**
	 * update specific data entry in the text file read the first filed in each line
	 * to find the matching id replace the line with the new one
	 */
	public void update(Data data) {

	}

	/**
	 * delete the data entry in the text file
	 * 
	 */
	public void delete(Data data) {

	}

	public static void main(String args[]) {
		Data d1 = new Data(19, 24, 23);
//		Data d2 = new Data(2, 94102, 111);
//		Data d3 = new Data(3, 94103, 222);
//		Data d4 = new Data(4, 94104, 333);
//		Data d5 = new Data(5, 94105, 444);
//		Data d6 = new Data(6, 94106, 555);
//		Data d7 = new Data(7, 94107, 666);
//		Data d8 = new Data(8, 94108, 777);
//		Data d9 = new Data(9, 94109, 888);
//		Data d10 = new Data(10, 94101, 999);

		String path = "/Users/serenapang/Development/JavaBasics/javabasic/"
				+ "src/org/corejava/ch9/processinginputoutput/myrandomdata.txt";
		DataDao datadao = new DataDao(path);
		datadao.save(d1);
		
		System.out.println("Finish saving");
		// datadao.save(d2);

	}
}
