package org.bigjava.ch10.interfaces;

public class RandomSequence implements Sequence{
	public int next() {
		return (int) (Integer.MAX_VALUE * Math.random());
	}
}
