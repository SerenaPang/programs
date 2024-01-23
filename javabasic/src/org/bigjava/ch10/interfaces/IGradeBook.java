package org.bigjava.ch10.interfaces;

public interface IGradeBook {

	public void load(String filename);

	public void addScore(int id, double score);

	public double getAverageScore(int id);

	public void save(String filename);
}
