package org.bigjava.ch24.rationaldatabases.practice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A cat house have different cats
 * */
public class CatHouse {
	/**
	 * Finds a cat with a given id and name
	 * @param id the cat's id
	 * @param name the cat's name
	 * @return the matching cat, or null if none found
	 * */
	public Cat findCat(int catId, String name) throws SQLException{
		try(Connection conn = SimpleDataSource.getConnection()) {
			Cat c = null;
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM Cats WHERE Id = ?");
			stat.setInt(1, catId);
			ResultSet result = stat.executeQuery();
			if (result.next() && name == result.getString("Name")) {
				c = new Cat(catId, result.getString("Name"));
			}
			return c;
		}
	}
}
