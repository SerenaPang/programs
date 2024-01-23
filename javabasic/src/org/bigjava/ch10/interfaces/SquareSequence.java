package org.bigjava.ch10.interfaces;

public class SquareSequence implements Sequence {
	private int n;
	@Override
	public int next() {
		n++;
		return n*n;
	}

}
