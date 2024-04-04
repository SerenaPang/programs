package com.mycompany.app;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//run :

/**
java -classpath ./target/mycosmetic-app-2.0-RELEASE-jar-with-dependencies.jar \
com.mycompany.app.TestCosmeticDB  \
./src/main/java/com/mycompany/app/database.properties
 * */
//java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.TestCosmeticDB /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/src/main/java/com/mycompany/app/database.properties
public class TestCosmeticDB {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: java -classpath /Users/serenapang/Downloads/pathof the db file" + File.pathSeparator + ".TestCosmeticDB propertiesFile");
			return;
		}
		
		SimpleDataSource.init(args[0]);
		
		try(Connection conn = SimpleDataSource.getConnection()){
			
			System.out.println("Connected " + conn.getCatalog());
//			Statement stat = conn.createStatement();
//			
//			stat.execute("CREATE TABLE Test(Name VARCHAR(20))");
//			stat.execute("INSERT INTO Test VALUES('Silver')");
//			
//			ResultSet result = stat.executeQuery("SELECT * FROM Test");
//			result.next();
//			System.out.println(result.getString("Name"));
//			stat.execute("DROP TABLE Test");
		}
	}
}
