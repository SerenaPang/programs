package com.mycompany.app;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Get connected to the database
 * */
public class TestCosmeticDb {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Pass the database.properties file");
//			System.out.println("java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \\\n"
//					+ "    com.mycompany.app.TestCosmeticDB  \\\n"
//					+ "    ./src/main/java/com/mycompany/app/database.properties");
			return;
		}
		
		SimpleDataSource.init(args[0]);
		
		try(Connection conn = SimpleDataSource.getConnection()){
			
			System.out.println("Connected " + conn.getCatalog());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
