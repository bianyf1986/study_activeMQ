package com.afeng.utils.singleton;

/**
 * IoDH技术（Initialization Demand Holder ）
 *
 */
public class Singleton3 {
	private Singleton3() {
	}

	private static class HolderClass {
		private final static Singleton3 instance = new Singleton3();
	}

	public static Singleton3 getInstance() {
		return HolderClass.instance;
	}

	public static void main(String args[]) {
		Singleton3 s1, s2;
		s1 = Singleton3.getInstance();
		s2 = Singleton3.getInstance();
		System.out.println(s1 == s2);
	}
}
