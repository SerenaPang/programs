package com.mycompany.app;
import java.io.File;

/**
 * The app to save and search for the cosmetic product information from a text file
 * run the application with this command if not using database:
 * java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt
 * 
 * run with database 
 * 
 * java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt database
 * 
 */
public class CosmeticApp 
{
//    public static void main( String[] args) {
//    	//if flag is true, we use the database to store info
//    	boolean flag = true;
//    	   	
//    	//take the command line arguments which is the file path, and the flag to indictae if database is used
//    	if (args.length == 1) {
//    		
//    		//System.out.println(args[0]);
//    		if (args[0].endsWith("txt")) {
//    			flag = false;
//    		} else {
//    			flag = true;
//    		}
//    		
//    		//choose to use text file or database
//    		if (!flag) {
//    			System.out.println("Use text file");
//    			File file = new File(args[0]);
//        		System.out.println("Found text file name: " + file.getName());
//        		System.out.println("Path: " + file.getAbsolutePath());
//        		pass the file to File processor
//        		GridLayoutFrame ui = new GridLayoutFrame(file);
//            	ui.createUi();
//    		} else {
//    			System.out.println("Use database");
//    			//System.out.println(args[0]);
//    			System.out.println("Usage: java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib" + File.pathSeparator + ".TestCosmeticDB propertiesFile");
//    			return;
//    		}
//    		 		
//    		
//    	}else {
//    		System.out.println("Please pass the path of the file or as an argument");
//    	}
//    }
}
