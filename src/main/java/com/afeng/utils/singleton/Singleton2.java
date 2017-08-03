package com.afeng.utils.singleton;

/**
 * 懒汉式单例、线程锁定
 * 
 */
public class Singleton2 {
	
	//volatile修饰的成员变量可以确保多个线程都能够正确处理
	private volatile static Singleton2 instance = null;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		//双重检查锁定(Double-Check Locking)
		if (instance == null) {
			synchronized (Singleton2.class) {
				if (instance == null) {
					instance = new Singleton2();
				}
			}
        }  
		return instance;
	}
}