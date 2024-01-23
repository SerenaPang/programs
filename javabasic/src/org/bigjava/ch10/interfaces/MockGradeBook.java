package org.bigjava.ch10.interfaces;

import java.util.ArrayList;

public class MockGradeBook implements IGradeBook {
private ArrayList<Double> scores;

public MockGradeBook() {
	scores = new ArrayList<Double>();
}
	@Override
	public void load(String filename) {
		// add sample scores
		scores.add(1.0);
		scores.add(7.0);
		scores.add(2.0);
		scores.add(9.0);
	}

	@Override
	public void addScore(int id, double score) {
		// ignore student id
		scores.add(score);
	}

	@Override
	public double getAverageScore(int id) {
		double total = 0;
		for (double x : scores) {
			total = total + x;
		}
		return total / scores.size();
	}

	@Override
	public void save(String filename) {
		// do nothing
	}

}
