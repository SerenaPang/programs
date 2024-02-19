package com.mycompany.app;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestCatDB {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib" + File.pathSeparator + ".TestCatDB propertiesFile");
			return;
		}
		
		SimpleDataSource.init(args[0]);
		
		try(Connection conn = SimpleDataSource.getConnection()){
			Statement stat = conn.createStatement();
			
			stat.execute("CREATE TABLE Test(Name VARCHAR(20))");
			stat.execute("INSERT INTO Test VALUES('Silver')");
			
			ResultSet result = stat.executeQuery("SELECT * FROM Test");
			result.next();
			System.out.println(result.getString("Name"));
			stat.execute("DROP TABLE Test");
		}
	}
}
