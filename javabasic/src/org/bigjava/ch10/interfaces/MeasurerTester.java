package org.bigjava.ch10.interfaces;

public class MeasurerTester {
	public static void main(String[] args) {
		Measurer areaMeas = new AreaMeasurer();
		Rectangle[] rects = new Rectangle[]
			{
				new Rectangle(5,10,20,30),
				new Rectangle(100,20,30,40),
				new Rectangle(20,30,5,15)
			};
		
		double averageArea = Data.average(rects, areaMeas);
		System.out.println("Average area: " + averageArea);
		System.out.println("Expected: 625");
	}
}
