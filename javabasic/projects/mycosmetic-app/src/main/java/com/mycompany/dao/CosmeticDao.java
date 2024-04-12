package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Cosmetic;


public interface CosmeticDao {
	
	public void save(Cosmetic cosmetic);
	
	public Cosmetic findById(Integer id);

	public List<Cosmetic> findAll();
	
	public boolean updateCosmetic(Cosmetic cosmetic);
	
	public boolean deleteCosmetic(int id);
}
