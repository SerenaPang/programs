package org.bigjava.ch13.recursion;
import java.io.File;
public class FileFinder {
	private File[] children;
	/**
	 * constructs a file finder for a given directory tree
	 * */
	public FileFinder(File startingDirectory) {
		children = startingDirectory.listFiles();
	}
	
	/**
	 * prints all files whose names ends in a given extension
	 * */
	public void find(String extension) {
		for (File child : children) {
			String fileName = child.toString();
			if (child.isDirectory()) 
			{
				FileFinder finder = new FileFinder(child);
				finder.find(extension);
			}
			else if (fileName.endsWith(extension)) 
			{
				System.out.println(fileName);
			}
		}
	}
}
