package org.bigjava.ch24.rationaldatabases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * Enters an invoice into the database
 * Be sure to add Customer.sql, Product.sql, Invoice.sql, and LineItem.sql to the database before running this program
 * */
public class InvoiceEntry {
	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("Usage: java - classpath driver_class_path" + File.pathSeparator + ". InvoiceEntry propertiesFile");
			return;
		}
		
		try {
			SimpleDataSource.init(args[0]);
			try(Connection conn = SimpleDataSource.getConnection();
					Scanner in = new Scanner(System.in))
			{
				addInvoice(in, conn);
			}
		}
		catch (SQLException ex) {
			System.out.println("Database error");
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error loading database driver");
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println("Error loading database properties");
			ex.printStackTrace();
		}
	}

	private static void addInvoice(Scanner in, Connection conn) throws SQLException{
		int customerNumber = newCustomer(conn, in);
		
		int id = getNewId(conn, "Invoice");
		try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Invoice VALUE(?,?,0)")) {
			stat.setInt(1, id);
			stat.setInt(2, customerNumber);
			stat.executeUpdate();
		}
		boolean done = false;
		while (!done) {
			String productCode = nextLine(in, "Product code(D=Done, L=List)");
			if (productCode.equals("D")) {
				done = true;
			} else if (productCode.equals("L")) {
				listProducts(conn);
			} else if (findProduct(conn, productCode)) {
				int quantity = nextInt(in, "Quantity");
				addLineItem(conn, id, productCode, quantity);
			} 
			else {
				System.out.println("Invalid product code.");
			}
		}
		showInvoice(conn, id);
	}

	private static void addLineItem(Connection conn, int id, String productCode, int quantity) {
		// TODO Auto-generated method stub
		
	}

	private static int nextInt(Scanner in, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static boolean findProduct(Connection conn, String productCode) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void listProducts(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	private static void showInvoice(Connection conn, int id) {
		// TODO Auto-generated method stub
		
	}

	private static String nextLine(Scanner in, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int getNewId(Connection conn, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int newCustomer(Connection conn, Scanner in) {
		// TODO Auto-generated method stub
		return 0;
	}
}
