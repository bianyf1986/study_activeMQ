package com.afeng.utils.multiThread.join;

import java.util.Date;

public class JoinDemo implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("i="+i);  
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("The JoinDemo has been interrupted");  
			}
			
		}
	}

}
