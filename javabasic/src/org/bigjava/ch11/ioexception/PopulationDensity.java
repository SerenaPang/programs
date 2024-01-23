package org.bigjava.ch11.ioexception;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PopulationDensity {
	public static void main(String[] args) throws FileNotFoundException{
		//open input files
		Scanner in1 = new Scanner(new File("worldpop.txt"));
		Scanner in2 = new Scanner(new File("worldarea.txt"));
		
		//open output file
		PrintWriter out = new PrintWriter("world_pop_density.txt");
		
		//read lines from each file
		while (in1.hasNextLine() && in2.hasNextLine()) {
			CountryValue population = new CountryValue(in1.nextLine());
			CountryValue area = new CountryValue(in2.nextLine());
			
			//compute and print the population density
			double density = 0;
			if (area.getValue() != 0)//protect against division by zero
				{
				density = population.getValue() / area.getValue();
			}
			out.printf("%-40s%15.2f\n", population.getCountry());
		}
		in1.close();
		in2.close();
		out.close();
	}
}
