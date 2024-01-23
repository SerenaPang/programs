package org.bigjava.ch10.interfaces;

public interface Named {
	default String name() {return "(NONE)";}
}
