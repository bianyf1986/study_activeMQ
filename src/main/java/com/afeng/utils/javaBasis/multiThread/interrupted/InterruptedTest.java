package com.afeng.utils.javaBasis.multiThread.interrupted;

public class InterruptedTest {

	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(10);
			thread.interrupt();
			Thread.currentThread().interrupt();
			//Thread.currentThread().interrupt();
			System.out.println("是否停止1？="+thread.getName()+":"+thread.isInterrupted());
		} catch (Exception e) {
			
		}
		
	}

}
