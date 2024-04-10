package com.mycompany.dao.jdbc;

import java.util.List;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.dao.jdbc.JdbcDataSource;
import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

public class CosmeticJdbcDao implements CosmeticDao {
	private JdbcDataSource dataSource;

	public CosmeticJdbcDao(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}

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

	public Cosmetic findById(Integer id) {
		System.out.println("jdbc findbyid");

		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cosmetics WHERE id= " + id);
			if (rs.next()) {
				Cosmetic cosmetic = new Cosmetic();
				cosmetic.setId(rs.getInt("id"));
				cosmetic.setName(rs.getString("name"));
				cosmetic.setBrand(rs.getString("brand"));
				cosmetic.setCategory(rs.getString("category"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public List<Cosmetic> findAll() {
		System.out.println("jdbc find all");

		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cosmetics");
			if (rs.next()) {
				Cosmetic cosmetic = new Cosmetic();
				cosmetic.setId(rs.getInt("id"));
				cosmetic.setName(rs.getString("name"));
				cosmetic.setBrand(rs.getString("brand"));
				cosmetic.setCategory(rs.getString("category"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
