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
 * Turning an object into a bunch of bytes that can be stored on disk, and re
 * constitude the object from those bytes
 */
public class Serialization {
	private Path path;

	public Serialization(Path path) {
		this.path = path;
	}

	/**
	 * To serialize a list of Employee objects
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
	 * To read list of employee object from the serialized file
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
	 * Find the target element in file by target's id
	 * 
	 * reading all the object list from the file,if the element id matches target
	 * id, return that element as result
	 * 
	 */
	public Employee findById(int id) {
		Employee emp = null;
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Employee> employees;
			employees = (ArrayList<Employee>) in.readObject();
			for (Employee em : employees) {
				if (em.getId() == id) {
					// System.out.println(em.toString());
					emp = em;
					return emp;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return emp;
	}

	/**
	 * Update the object by reading all the object list from the file, delete the
	 * original element, add the new element, delete the old file, create a new
	 * file, then write the new list to the file
	 * 
	 */
	public boolean updateData(Employee employee) {
		boolean updated = false;
		File file = path.toFile();
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Employee> employees;
			employees = (ArrayList<Employee>) in.readObject();
			// find the target object
			for (Employee em : employees) {
				if (em.getId() == employee.getId()) {
					// System.out.println("found: " + em.toString());
					employees.remove(em);
					// add the new element to the list for update
					employees.add(employee);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("employee.txt");
					}
					// populate all employee product with the updated object to the new file
					path = file.toPath();
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(employees);
					} catch (IOException e) {
						e.printStackTrace();
					}
					updated = true;
					return updated;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return updated;
	}

	/**
	 * Delete the target object with the specific id reading all the object list
	 * from the file, delete the original element, delete the old file, create a new
	 * file, then write the new list to the file
	 */
	public boolean deleteData(int id) {
		boolean deleted = false;
		File file = path.toFile();
		// find the target object and delete from the list
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			ArrayList<Employee> employees;
			employees = (ArrayList<Employee>) in.readObject();
			// delete the target object
			for (Employee em : employees) {
				if (em.getId() == id) {
					// System.out.println("found: " + em.toString());
					employees.remove(em);
					// delete the old file
					file.delete();
					if (!file.exists()) {
						file = new File("employee.txt");
					}
					// populate all employee product to the new file
					path = file.toPath();
					try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
						out.writeObject(employees);
					} catch (IOException e) {
						e.printStackTrace();
					}
					deleted = true;
					return deleted;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return deleted;
	}

	/**
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		// pass the absolute path of the file
		Path filePath = Paths.get(
				"/Users/serenapang/Development/JavaBasics/javabasic/src/org/corejava/ch9/processinginputoutput/employee.txt");
		Serialization serialization = new Serialization(filePath);
		// test add
		Employee paul = new Employee(1, "Paul", 10000);
		Employee peter = new Employee(2, "Peter", 18000);
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(paul);
		employees.add(peter);
		employees.add(new Employee(3, "Silver", 9000));
		employees.add(new Employee(4, "Diana", 9000));
		employees.add(new Employee(5, "Tucy", 9000));
		employees.add(new Employee(6, "Watermelon", 9000));

		serialization.write(employees);
		// test read all
		ArrayList<Employee> empls = serialization.read();

		for (Employee em : empls) {
			System.out.println(em);
		}
		// test findById
		Employee target = serialization.findById(1);
		System.out.println(target.toString());

		// test delete
		serialization.deleteData(3);
		ArrayList<Employee> emplsDeleted = serialization.read();
		for (Employee em : emplsDeleted) {
			System.out.println(em);
		}

		// test update
		Employee bbCat = new Employee(6, "bbCat", 9000);
		serialization.updateData(bbCat);
		ArrayList<Employee> emplsUpdated = serialization.read();
		for (Employee em : emplsUpdated) {
			System.out.println(em);
		}
	}
}
