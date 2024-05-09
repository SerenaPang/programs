package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Turning an object into a bunch of bytes that can be stored on disk, and
 * reconstitude the object from those bytes
 */
public class Serialization {
	/**
	 * To serialize objects
	 */
	public void write(Path path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
			Employee paul = new Employee("Paul", 10000);
			Employee peter = new Employee("Peter", 18000);
			out.writeObject(peter);
			out.writeObject(paul);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * To read object back in
	 */
	public void read(Path path) {
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			try {
				Employee e1 = (Employee) in.readObject();
				Employee e2 = (Employee) in.readObject();
				System.out.println(e1.toString());
				System.out.println(e2.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * */

	/**
	 * 
	 * 
	 * */

	public static void main(String args[]) {
		// pass the absolute path of the file
		// Path filePath =
		// Paths.get("/Users/serenapang/Development/JavaBasics/javabasic/src/org/corejava/ch9/processinginputoutput/employee.txt");
		// Serialization serialization = new Serialization();
		// serialization.write(filePath);
		// serialization.read(filePath);
	}
}
