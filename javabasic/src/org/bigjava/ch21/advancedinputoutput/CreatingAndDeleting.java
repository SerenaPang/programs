package org.bigjava.ch21.advancedinputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatingAndDeleting {
	public static void main(String[] args) throws IOException{
		//creating a directory and a file inside it
		Files.createDirectories(Paths.get("subdir"));
		Path testPath = Paths.get("subdir/test.txt");
		Files.createFile(testPath);
		
		//checking that the file exists
		System.out.println(testPath + " exists: " + Files.exists(testPath));
		//but this one doesn't exist
		Path badPath = Paths.get("subdir/bad");
		System.out.println(badPath + " exists: " + Files.exists(badPath));
		
		//confirming that it's a file and not a directory
		System.out.println(testPath + " is a directory: " + Files.isRegularFile(testPath));
		
		//deleting what was created
		Files.delete(testPath);
		Files.delete(testPath.getParent());
		
		Path tempFile = Files.createTempFile("bigjava", ".txt");
		System.out.println("Creating a temporary file " + tempFile);
		Path tempDir = Files.createTempDirectory("bigjava");
		System.out.println("Created a temporary directory " + tempDir);
	}
}
