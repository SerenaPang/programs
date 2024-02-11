package com.mycompany.app;

import java.util.Comparator;

//create the comparator for comparing rate value
public class RateComparator implements Comparator<Perfume>{
	//override the compare method
	@Override
	public int compare(Perfume p1, Perfume p2) {
		if (p1.getRating() == p2.getRating()) {
			return 0;
		} else if (p1.getRating() < p2.getRating()) {
			return 1;
		}else {
			return -1;
		}
	}
}
