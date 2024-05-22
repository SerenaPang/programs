package org.laicode.class13.mapsethash;

public class Coordinate {
	public int x;
	public int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate another = (Coordinate)obj;
		return this.x == another.x && this.y == another.y;
	}

	@Override
	public int hashCode() {
		return x * 101 + y;
	}
	
	public static void main(String[] args) {
		Coordinate one = new Coordinate(0,0);
		Coordinate another = new Coordinate(0,0);
		
		boolean r1 = (one == another);//comparing references
		System.out.println("one == another: " + r1); //false
		boolean r2 = one.equals(another);//comparing content
		System.out.println("one .equals another: " + r2); //true
	}
}
