package com.mycompany.dao.jdbc;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.dao.jdbc.JdbcDataSource;
import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

/**
 * This class connects to the database and enables the save, update, find,
 * delete opereation for the database
 */
public class CosmeticJdbcDao implements CosmeticDao {
	private JdbcDataSource dataSource;

	public CosmeticJdbcDao(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Save the cosmetic information to the database
	 * 
	 * @param cosmetic the cosmetic object to be saved in database
	 */
	public void save(Cosmetic cosmetic) {
		System.out.println("jdbc save");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO Cosmetics(id, brand, name, category) " + "VALUES(?,?,?,?)");

			ps.setInt(1, cosmetic.getId());
			ps.setString(2, cosmetic.getName());
			ps.setString(3, cosmetic.getBrand());
			ps.setString(4, cosmetic.getCategory());
			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("jdbc save cosmetic info to database");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the cosmetic information from the database
	 * 
	 * @param id the cosmetic object with coresbonding id to be searched in database
	 * @return Cosmetic object with specified id
	 */
	public Cosmetic findById(Integer id) {
		System.out.println("jdbc findbyid");

		try (Connection connection = dataSource.getConnection()) {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT id, brand, name, category FROM cosmetics WHERE id =" + id);
//			if (rs.next()) {
//				Cosmetic cosmetic = new Cosmetic();
//				cosmetic.setId(rs.getInt("id"));
//				cosmetic.setName(rs.getString("name"));
//				cosmetic.setBrand(rs.getString("brand"));
//				cosmetic.setCategory(rs.getString("category"));
			PreparedStatement ps = connection
					.prepareStatement("SELECT id, brand, name, category FROM cosmetics WHERE id =?");

			ps.setInt(1, id);
			Cosmetic cosmetic = new Cosmetic();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					cosmetic.setId(rs.getInt("id"));
					cosmetic.setName(rs.getString("name"));
					cosmetic.setBrand(rs.getString("brand"));
					cosmetic.setCategory(rs.getString("category"));
				}
			}
			System.out.println(
					"id: " + cosmetic.getId() + "name: " + cosmetic.getName() + " brand: " + cosmetic.getBrand());
			return cosmetic;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all the cosmetic information from the database
	 * 
	 * @return List of Cosmetic objects
	 */
	public List<Cosmetic> findAll() {
		System.out.println("jdbc find all");

		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, brand, name, category from Cosmetics");
			List<Cosmetic> cosmetics = new ArrayList<>();
			while (rs.next()) {
				Cosmetic cosmetic = new Cosmetic();
				cosmetic.setId(rs.getInt("id"));
				cosmetic.setName(rs.getString("name"));
				cosmetic.setBrand(rs.getString("brand"));
				cosmetic.setCategory(rs.getString("category"));
				cosmetics.add(cosmetic);
			}
			return cosmetics;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/***
	 * This methods update the cosmetic object info store in the database
	 * 
	 * @param cosmetic the cosmetic object to be updated in the database
	 */
	public boolean updateCosmetic(Cosmetic cosmetic) {
		System.out.println("jdbc update Cosmetic");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE Cosmetics SET id=?, brand=?, name=?, category=? WHERE id=?");
			ps.setInt(1, cosmetic.getId());
			ps.setString(2, cosmetic.getBrand());
			ps.setString(3, cosmetic.getName());
			ps.setString(4, cosmetic.getCategory());
			ps.setInt(5, cosmetic.getId());

			int i = ps.executeUpdate();

			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/***
	 * This method deletes the cosmetic object in file
	 * 
	 * @param id the cosmetic object with corresbonding id to be deleted in the
	 *           database
	 */
	public boolean deleteCosmetic(int id) {
		System.out.println("jdbc delete Cosmetic");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Cosmetics WHERE id= ?");
			ps.setInt(1, id);

			int i = ps.executeUpdate();

			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
