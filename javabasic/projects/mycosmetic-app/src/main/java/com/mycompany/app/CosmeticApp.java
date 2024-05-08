package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.dao.jdbc.CosmeticJdbcDao;
import com.mycompany.dao.jdbc.JdbcDataSource;
import com.mycompany.dao.file.CosmeticFileDao;
import com.mycompany.dao.file.CosmeticRandomAccessFileDao;
import com.mycompany.model.Cosmetic;

/**
 * The app to save and search for the cosmetic product information from a
 * textfile / database run the application with this command if not using
 * database: java -classpath
 * ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
 * com.mycompany.app.CosmeticApp -db or -file
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
			cosmeticDao = new CosmeticFileDao(file);

			// Cosmetic cosme = new Cosmetic(3, "coco", "chanel","perfume");
			// cosmeticDao.updateCosmetic(cosme);
			// cosmeticDao.deleteCosmetic(4);
			// String filePath = file.getAbsolutePath();
			// System.out.println(filePath);

		} else if("-rafile".equals(flag)) { // rafile stands for random access file
			File file = new File(
					"/Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/randomcosmetic.txt");
			cosmeticDao = new CosmeticRandomAccessFileDao(file);

			System.out.println("Using random access file");
			Cosmetic d1 = new Cosmetic(1, "aa", "aaaa", "BabyCat 1");
			Cosmetic d2 = new Cosmetic(2, "bb", "bbbbb", "BabyCat 2");
			Cosmetic d3 = new Cosmetic(3, "cc", "ccccc", "BabyCat 3");
			Cosmetic d4 = new Cosmetic(4, "dd", "ddddd", "BabyCat 4");

			Cosmetic d5 = new Cosmetic(5, "ee", "eee", "BabyCat");
			Cosmetic d6 = new Cosmetic(6, "ff", "ffff", "Hello Good night");
			Cosmetic d7 = new Cosmetic(7, "gg", "ggg", "Tiramisu");
			Cosmetic d8 = new Cosmetic(8, "hh", "hhhh", "Tres Leches");
			Cosmetic d9 = new Cosmetic(9, "ii", "iii", "Coconut cream");
			Cosmetic d10 = new Cosmetic(10, "jjjj", "jjjj", "Dark C");
//			 cosmeticDao.save(d1);
//			 cosmeticDao.save(d2);
//			 cosmeticDao.save(d3);
//			 cosmeticDao.save(d4);
//			 cosmeticDao.save(d5);
//			 cosmeticDao.save(d6);
//			 cosmeticDao.save(d7);
//			 cosmeticDao.save(d8);
//			 cosmeticDao.save(d9);
//			 cosmeticDao.save(d10);
			 
			 List<Cosmetic> listOfCosmetic = cosmeticDao.findAll();
			 for (Cosmetic c : listOfCosmetic) {
					System.out.println(c.toString());
			 }
			 
			// cosmeticDao.findById(4);
		}
		 else if  ("-db".equals(flag)){
			System.out.println("Use database");
			JdbcDataSource jdbcDataSource = new JdbcDataSource("mysqldb.properties");
			cosmeticDao = new CosmeticJdbcDao(jdbcDataSource);
			// Cosmetic cosme = new Cosmetic(3, "coco", "chanel","perfume");
//					Cosmetic cosme = new Cosmetic(1, "aa", "cc","aa");
//					cosmeticDao.updateCosmetic(cosme);
			// cosmeticDao.deleteCosmetic(90);
			// cosmeticDao.findById(4);
		}
			 else {
				 //serilization
			 }			 
//				GridLayoutFrame ui = new GridLayoutFrame(cosmeticDao, flag);
//				ui.createUi(); 
	}
}
