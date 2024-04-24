package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.dao.jdbc.CosmeticJdbcDao;
import com.mycompany.dao.jdbc.JdbcDataSource;
import com.mycompany.dao.file.CosmeticFileDao;
import com.mycompany.dao.file.CosmeticRandomAccessFileDao;
import com.mycompany.model.Cosmetic;

/**
 * The app to save and search for the cosmetic product information from a textfile / database
 * run the application with this command if not using database: 
 java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
    com.mycompany.app.CosmeticApp -db  or  -file
 */
public class CosmeticApp {
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
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
	//		cosmeticDao = new CosmeticFileDao(file);

	//		Cosmetic cosme = new Cosmetic(3, "coco", "chanel","perfume");
	//		cosmeticDao.updateCosmetic(cosme);
	//		cosmeticDao.deleteCosmetic(4);
			//String filePath = file.getAbsolutePath();
			//System.out.println(filePath);
			cosmeticDao = new CosmeticRandomAccessFileDao(file);
			//String line = new String("4:Flora:Chloe:Perfume");
			//String line = new String("hi");
			//System.out.println(line);
			Cosmetic cosme = new Cosmetic(4, "Flora", "Chloe","Perfume");
			//System.out.println(cosme.toString());
			//cosmeticDao.save(cosme);
			//cosmeticDao.findAll();
			cosmeticDao.findById(4);
		} else {
			System.out.println("Use database");
			JdbcDataSource jdbcDataSource = 
					new JdbcDataSource("mysqldb.properties");
			cosmeticDao = new CosmeticJdbcDao(jdbcDataSource);
			//		Cosmetic cosme = new Cosmetic(3, "coco", "chanel","perfume");
//					Cosmetic cosme = new Cosmetic(1, "aa", "cc","aa");
//					cosmeticDao.updateCosmetic(cosme);
			//cosmeticDao.deleteCosmetic(90);
			//cosmeticDao.findById(4);
		}
//		GridLayoutFrame ui = new GridLayoutFrame(cosmeticDao, flag);
//		ui.createUi();
	}
}
