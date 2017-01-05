package com.afeng.utils.multiThread.producerConsumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FirstProductPool {
	private int maxSize;  
    private List<Date> pool;  
      
    public FirstProductPool(){  
        maxSize = 10;  
        pool = new LinkedList<Date>();  
    }  
      
    public synchronized void set(){  
        while(pool.size() == maxSize){  
            try {  
                wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println("set:offer 前"+pool.size());  
        ((LinkedList<Date>) pool).offer(new Date());  
        System.out.println("set:offer 后"+pool.size());  
        notifyAll();  
    }  
      
    public synchronized void get(){  
        while(pool.size()==0){  
            System.out.println("---------------------------等待中--------------------");  
            try {  
                wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println("get:poll 前"+pool.size());  
        ((LinkedList<Date>) pool).poll();
        System.out.println("get:poll 后"+pool.size());  
        notifyAll();  
    }  
}
