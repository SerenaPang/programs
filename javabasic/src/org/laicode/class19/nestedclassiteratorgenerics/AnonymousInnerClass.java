package org.laicode.class19.nestedclassiteratorgenerics;

public class AnonymousInnerClass {
	public void test() {
		new Thread(new Runnable() { //Runnable is an interface
			@Override
			public void run() {}		
		}).start();
	}
}
