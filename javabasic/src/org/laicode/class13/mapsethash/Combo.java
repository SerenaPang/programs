package org.laicode.class13.mapsethash;

/**
 * common hashCode() implementation
 * */
public class Combo {
	int a;
	B b;
	C c;
	
	public Combo(int a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public int hashCode() {
		return a * 31 * 31 +b.hashCode() * 31 + c.hashCode();
	}
}
