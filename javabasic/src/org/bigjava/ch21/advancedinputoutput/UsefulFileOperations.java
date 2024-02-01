package org.bigjava.ch21.advancedinputoutput;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsefulFileOperations {
	public static void main(String[] args) throws IOException{
		//getting the size of a file
		Path input = Paths.get("UsefulFileOperations.java");
		long size = Files.size(input);
		System.out.println("Size of " + input + ": " + size + " bytes");
		//reading all lines
		List<String> lines = Files.readAllLines(input);
		System.out.println("Line 11: " + lines.get(10));
		
		//reading all bytes
		byte[] bytes = Files.readAllBytes(input);
		byte[] firstTen = Arrays.copyOf(bytes, 10);
		System.out.println("First ten bytes: " + Arrays.toString(firstTen));
		//writing lines
		Collections.sort(lines);
		Path output1 = Paths.get(input + ".sorted");
		Files.write(output1, lines);
		System.out.println("Sorting the lines of " + input + " yields " + output1);
		//writing bytes
		for(int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)(bytes[i] + 3);
		}
		Path output2 = Paths.get(input + ".caesar");
		Files.write(output2, bytes);
		System.out.println("The Caesar encryption of " + input + " is in " + output2);
		
		//reading a file into a string
		String contents = new String(Files.readAllBytes(input), "UTF-8");
		//writing a string to a file
		Path output3 = Paths.get(input + ".lowercase");
		
	}
}
