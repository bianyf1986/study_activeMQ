package com.afeng.utils.multiThread.threadLocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable{  
	  
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){  
        protected Date initialValue(){  
            return new Date();  
        }
    };  
    
    private static InheritableThreadLocal<Date> startDateInheritable = new InheritableThreadLocal<Date>(){

    	protected Date initialValue(){  
            return new Date();  
        }

    };  
    
    @Override  
    public void run() {  
  
        System.out.printf("Starting Thread:%s:%s\n", Thread.currentThread().getId(),startDate.get());  
        try {  
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        
        Thread thread = new Thread() {

			@Override
			public void run() {
				System.out.println("InheritableThreadLocal startDateInheritable : " + startDateInheritable.get());
			}
        	
		};
        thread.start();
        
        System.out.printf("Thread Finished:%s:%s\n", Thread.currentThread().getId(),startDate.get()); 
         
         
    }  
}  
