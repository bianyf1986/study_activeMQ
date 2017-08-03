package com.afeng.utils.javaBasis.multiThread.threadLocal;

import java.util.concurrent.TimeUnit;

public class UnsafeTaskMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UnsafeTask task = new UnsafeTask();  
        for(int i = 0; i<10;i++){  
            Thread thread = new Thread(task);  
            thread.start();  
              
            try {  
                TimeUnit.SECONDS.sleep(2);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        } 
	}

}
