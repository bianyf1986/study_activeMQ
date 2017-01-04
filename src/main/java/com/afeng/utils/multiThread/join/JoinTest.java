package com.afeng.utils.multiThread.join;

public class JoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JoinDemo demo = new JoinDemo();
		Thread thread = new Thread(demo);
		thread.start();
		
		//demo.notify();
		System.out.println("1111111");
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("2222222");
		
	}

}
