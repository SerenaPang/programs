package org.bigjava.ch22.multithreading;
import java.util.Date;
import java.lang.Runnable;

/**
 * A runnable that repeatedly prints a g
 * */
public class GreetingRunnable implements Runnable{
	private static final int REPETITIONS = 10;
	private static final int DELAY = 1000;
	private String greeting;
	
	public GreetingRunnable() {
	}
	
	/**
	 * constructs the runnable object
	 * @param  aGreeting the greeting to display
	 * */
	public GreetingRunnable(String aGreeting) {
		greeting = aGreeting;
	}
	
	@Override
	public void run() {
		System.out.println("In GreetingRunnable run");
		try {
			for (int i = 1; i <= REPETITIONS; i++) {
				Date now = new Date();
				System.out.println(now + " " + greeting);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception){
			
		}
	}
}
