package com.mycompany.app;

import java.io.File;
import java.sql.Connection;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.dao.jdbc.CosmeticJdbcDao;
import com.mycompany.dao.file.CosmeticFileDao;
import com.mycompany.model.Cosmetic;

/**
 * The app to save and search for the cosmetic product information from a text
 * file run the application with this command if not using database: java -cp
 * target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp
 * /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt
 * 
 * run with database
 * 
 * 
 * - delete the target jar file in github - save the entries from the user to
 * database - search the result in the database for the user - elaborate read me
 */
public class CosmeticApp {
	public static void main(String[] args) {
		// if flag is true, we use the database to store info
		for (String s : args) {
			System.out.println("Parameter " + s);
		}

		if (args.length < 1) {
			System.out.println("One parameter is required <-db|-file>");
			return;
		}

		CosmeticDao cosmeticDao = null;
		String flag = args[0];

		if ("-file".equals(flag)) {
			System.out.println("Use text file");
			File file = new File(
					"/Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt");
			cosmeticDao = new CosmeticFileDao(file);
		} else {
			System.out.println("Use database");
			cosmeticDao = new CosmeticJdbcDao();
		}

		GridLayoutFrame ui = new GridLayoutFrame(cosmeticDao);
		ui.createUi();
	}
}
