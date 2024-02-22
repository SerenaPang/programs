package org.bigjava.ch19.streamprocessing;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDruncateDemo {
	public static void main(String[] args) throws IOException{
		try (Stream<String> lines = Files.lines(Paths.get("/Users/serenapang/Development/JavaBasics/javabasic/src/org/bigjava/ch19/streamprocessing/countries.txt"))) {		
			List<String> result = lines //read the lines
					.filter(w -> w.length() > 10) //keep only the long words
					.map(w -> w.substring(0, 7)) //truncate to 7 characters
					.map(w -> w + "...") //add ellipses
					.distinct() //remove duplicates
					.limit(20) //keep only the first 20 
					.collect(Collectors.toList()); //collect into list
			System.out.println(result);
		}
	}
}
