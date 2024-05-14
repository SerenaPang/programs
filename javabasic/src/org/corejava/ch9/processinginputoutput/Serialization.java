package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Turning an object into a bunch of bytes that can be stored on disk, and
 * reconstitude the object from those bytes
 */
public class Serialization {
	private Path path;

	public Serialization(Path path) {
		this.path = path;
	}

	/**
	 * To serialize objects
	 * 
	 * @throws IOException
	 */
	public void write(ArrayList<Employee> employees) {
		
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
			out.writeObject(employees);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To read object back in
	 * 
	 * @throws IOException
	 */
	public ArrayList<Employee> read() throws IOException {

		ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
		try {
			ArrayList<Employee> employees = (ArrayList<Employee>) in.readObject();
			return employees;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * 
	 * */

	/**
	 * @throws IOException
	 * 
	 * 
	 */

	public static void main(String args[]) throws IOException {
		// pass the absolute path of the file
		Path filePath = Paths.get(
				"/Users/serenapang/Development/JavaBasics/javabasic/src/org/corejava/ch9/processinginputoutput/employee.txt");
		Serialization serialization = new Serialization(filePath);

		Employee paul = new Employee("Paul", 10000);
		Employee peter = new Employee("Peter", 18000);
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(paul);
		employees.add(peter);
		employees.add(new Employee("TC", 9000));
		
		
		serialization.write(employees);

		ArrayList<Employee> empls = serialization.read();
		
		for (Employee em : empls) {
			System.out.println(em);
		}
		
	}
}
