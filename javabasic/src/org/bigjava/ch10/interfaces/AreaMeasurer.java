package org.bigjava.ch10.interfaces;
/**
 * objects of this class measure rectangles by area
 * */
public class AreaMeasurer implements Measurer{
	@Override
	public double measure(Object anObject) {
		Rectangle aRectangle = (Rectangle) anObject;
		double area = aRectangle.getWidth() * aRectangle.getHeight();
		return area;
	}
}
