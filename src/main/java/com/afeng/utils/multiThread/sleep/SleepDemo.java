package com.afeng.utils.multiThread.sleep;

import java.util.Date;

public class SleepDemo implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("The SleepDemo has been interrupted");  
			}
			
		}
	}

}
