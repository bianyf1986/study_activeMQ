package com.afeng.utils.singleton;

/**
 * 饿汉式单例
 * 
 */
public class Singleton1 {
	/**
	 * 类加载时，静态变量instance会被初始化，
	 */
	
	private static final Singleton1 instance = new Singleton1();

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		return instance;
	}
}
