package com.mycompany.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {
	public void write() {
		File file = new File("cosmetic.txt");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("Brand:Name:Color:ID");
			bw.newLine();
			bw.write("This is another line");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}