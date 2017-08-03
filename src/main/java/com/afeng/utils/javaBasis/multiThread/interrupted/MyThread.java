package com.afeng.utils.javaBasis.multiThread.interrupted;

public class MyThread extends Thread {

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 500; i++) {
			System.out.println("i=" + (i + 1));
			System.out.println("是否停止2？="+Thread.currentThread().getName()+":"+Thread.currentThread().isInterrupted());
		}
	}

	
}
