package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
	public void read() {
		File file = new File("cosmetic.txt");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line; //has to be seperate or else will be infinite loop
			while((line = br.readLine())!= null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

