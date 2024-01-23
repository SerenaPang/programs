package org.bigjava.ch13.recursion;
/**
 * A triangular shape composed of stacked unit squares like this
 * []
 * [] []
 * [] [] []
 * ... ... ...
 * */
public class Triangle {
	private int width;	
	/**
	 * constructs a triangular shape
	 * 
	 * */
	public Triangle(int aWidth) {
		width = aWidth;
	}
	
	/**
	 * computes the area of the triangle
	 * */
	public int getArea() {
		if (width <= 0) {
			return 0;
		} else if (width == 1) {
			return 1;
		}else {
			Triangle smallerTriangle = new Triangle(width - 1);
			int smallerArea = smallerTriangle.getArea();
			return smallerArea + width;
		}
	}
}
