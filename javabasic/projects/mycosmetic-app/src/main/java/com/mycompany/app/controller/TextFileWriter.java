package com.mycompany.app;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedWriter;
import java.io.IOException;

// TODO Create a class name FileProcessor that will contain the logic of TextFileWriter and TextFileReader.

public class TextFileWriter {
		
	public void writeLine(String brand, String name, String category, String id) {
		//if the file exsist, open it append it, if not ccreate a new one
		try {
			// TODO when the application is initialized passed the file name using -store /path/to/file
			File file = new File("cosmetic.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			//add string to the file in new lines
			pw.println(brand + ":" + name + ":" + category + ":" + id);		
		
			pw.close();
			System.out.println("Data successfully appended at the end of file");
			
		} catch(IOException ioe) {
			System.out.println("Exception occur opening file:");
			ioe.printStackTrace();
		}	
	}
}