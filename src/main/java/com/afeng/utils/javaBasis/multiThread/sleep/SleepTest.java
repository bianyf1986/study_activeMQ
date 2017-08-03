package com.afeng.utils.javaBasis.multiThread.sleep;

public class SleepTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SleepDemo demo = new SleepDemo();
		Thread thread = new Thread(demo);
		thread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
		
	}

}
