package org.laicode.class13.mapsethash;

public class String {
	private char[] array;
	private int offset;
	private int length;

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof String)) {
			return false;
		}
		String another = (String) obj;
		// check each of the characters of the two String object if they are the same
		if (this.length != another.length) {
			return false;
		}
		for (int i = 0; i < this.length; i++) {
			if (this.array[this.offset + i] != another.array[another.offset + i]) {
				return false;
			}
		}
		// return true if they match
		return true;
	}
}
