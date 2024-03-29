package org.bigjava.ch11.ioexception;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * this program reads a file whose lines contain items and prices
 * like this: 
 * item name 1: price 1
 * item name 2: price 2
 * 
 * each item name is terminated with a colon
 * the program writes a file in which the items are left aligned
 * and the prices are right-aligned. the last line has the total of the prices
 * */
public class Items {
	public static void main(String[] args) throws FileNotFoundException{
		//prompt for the input and output file names
		Scanner console = new Scanner(System.in);
		System.out.print("Input File: ");
		String inputFileName = console.next();
		System.out.print("Output file: ");
		String outputFileName = console.next();
		
		//construct the Scanner and PrintWiter objects for reading and writing
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter(outputFileName);
		
		//read the input and write the output
		double total = 0;
		//we read a line at a time since there may be spaces in the item names
		while(in.hasNextLine()) {
			String line = in.nextLine();
			boolean found = false;
			String item = "";
			double price = 0;
			for (int i = 0; !found && i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch == ':') {
					found = true;
					item = line.substring(0, i + 1);
					price = Double.parseDouble(line.substring(i+1).trim());
					total = total + price;
				}
			}
			//If no colon was found, we skip the line
			if (found) {
				out.printf("%-20s%10.2f\n", item,price);
			}
			
		}
		out.printf("%-20s%10.2f\n", "Total:", total);
		
		in.close();
		out.close();
	}

}
