package com.mycompany.dao.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

public class CosmeticRandomAccessFileDao implements CosmeticDao {
	// create a map to store all the product, the key is the product id
	Map<Integer, Cosmetic> productMap = new HashMap<Integer, Cosmetic>();

	// create a file object
	File file;
	// create a constructor that receive the file pass in the command line
	public CosmeticRandomAccessFileDao(File file) {
		this.file = file;
	}
	
	
	
	/**
	 *  This method writes the user input into the text  at the end of the text file using RandomAccessFile
	 * @param filePath the file to write
	 * @param line content to write into the file
	 * */
//	public void save(String line) {
//		System.out.println("appending " + line);
//		
////		RandomAccessFile raFile = new RandomAccessFile(filePath, "rw");
////		raFile.seek(raFile.length());
////		System.out.println("current pointer = "+raFile.getFilePointer());
////		raFile.write(line.getBytes());
////		raFile.close();	
//	}
//	
	public void save(Cosmetic cosmetic) {
		int numId = cosmetic.getId();
		String id = String.valueOf(numId);
		String name = cosmetic.getName();
		String brand = cosmetic.getBrand();
		String category = cosmetic.getCategory();
		
		String line = new StringBuffer().append(id).append(":").append(name).append(":").append(brand).append(":").append(category).toString();
		try {
		RandomAccessFile raFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
		raFile.seek(raFile.length());
		System.out.println("current pointer = "+raFile.getFilePointer());
		raFile.write(line.getBytes());
		raFile.close();	
		System.out.println("Data successfully appended at the end of file");

		} catch (IOException ioe) {
			System.out.println("Exception occur opening file:");
			ioe.printStackTrace();
		}
	//	System.out.println(line);
	}
	
	public Cosmetic findById(Integer id) {
		return null;
	}

	public List<Cosmetic> findAll(){
		return null;
	}
	
	public boolean updateCosmetic(Cosmetic cosmetic){
		return false;
	}
	
	public boolean deleteCosmetic(int id){
		return false;
	}
	
	
	
	
}