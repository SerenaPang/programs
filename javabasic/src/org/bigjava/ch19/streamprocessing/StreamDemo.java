package org.bigjava.ch19.streamprocessing;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamDemo {
	public static void main(String[] args) throws IOException
	{
		Scanner in= new Scanner(new File("/Users/serenapang/Development/JavaBasics/javabasic/src/org/bigjava/ch19/streamprocessing/countries.txt "));
		List<String> wordList = new ArrayList<>();
		while(in.hasNextLine()) {
			wordList.add(in.nextLine());
		}
		//traditional loop for counting the long words
		long count = 0;
		for (String w : wordList) {
			if (w.length() > 10) {
				count++;
			}
		}
		System.out.println("Long Words: " + count);
		
		//the same computation with streams
		count = wordList.stream().filter(w -> w.length() > 10).count();
		System.out.println("Long Words: " + count);
	}
}
