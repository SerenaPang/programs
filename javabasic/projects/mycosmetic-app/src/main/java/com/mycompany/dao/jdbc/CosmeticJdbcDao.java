package com.mycompany.dao.jdbc;

import java.util.List;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;


public class CosmeticJdbcDao implements CosmeticDao {
	
	public void save(Cosmetic cosmetic) {
		System.out.println("jdbc save");
	}
	
	public Cosmetic findById(Integer id) {
		System.out.println("jdbc findbyid");
		return null;
	}

	public List<Cosmetic> findAll() {
		
		
		return null;
	}
}
