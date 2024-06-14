package org.laicode.class19.nestedclassiteratorgenerics;

public class NonAnonymousClass implements Runnable {
	// non anonymous
	public void run() {

	}

	public static void main(String[] args) {
		NonAnonymousClass t = new NonAnonymousClass();
		new Thread(t).start();
	}
}
