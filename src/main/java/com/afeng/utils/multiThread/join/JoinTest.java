package com.afeng.utils.multiThread.join;

public class JoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JoinDemo demo = new JoinDemo();
		Thread thread = new Thread(demo);
		thread.start();//启动子线程
		
		try {
			System.out.println("线程："+Thread.currentThread().getName()+",开始睡眠！");
			Thread.sleep(2000);//主线程main睡2s，执行时间让给子线程执行2s
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("线程："+Thread.currentThread().getName()+",睡眠结束！");
		
		try {
			System.out.println("线程："+Thread.currentThread().getName()+",等待线程："+thread.getName()+" 执行完毕！");
			thread.join();//主线程main等待子线程thread执行完毕后，才会继续执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("线程："+Thread.currentThread().getName()+",即将结束！");
		
	}

}
