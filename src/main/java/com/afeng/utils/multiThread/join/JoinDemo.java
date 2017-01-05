package com.afeng.utils.multiThread.join;

public class JoinDemo implements Runnable{

	@Override
	public void run() {
		
		System.out.println("线程："+Thread.currentThread().getName()+",开始执行！");
		
		for(int i=0;i<10;i++){
			System.out.println("线程："+Thread.currentThread().getName()+",执行过程 "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("线程："+Thread.currentThread().getName()+",has been interrupted");
			}
			
		}
	}

}
