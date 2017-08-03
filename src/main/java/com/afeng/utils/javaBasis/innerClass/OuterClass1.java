package com.afeng.utils.javaBasis.innerClass;

/**
 * 成员内部类
 *
 */
public class OuterClass1 {
	private String str;

	public void outerDisplay() {
		System.out.println("outerClass...");
	}

	/**
	 * 1、成员内部类中不能存在任何static的变量和方法
	 * 2、只有先创建了外部类，才能创建内部类
	 *
	 */
	public class InnerClass {
		public String s = "";
		public void innerDisplay() {
			// 使用外围类的属性
			str = "chenssy...";
			System.out.println(str);
			// 使用外围类的方法
			outerDisplay();
		}
	}

	/* 推荐使用getxxx()来获取成员内部类，尤其是该内部类的构造函数无参数时 */
	public  InnerClass getInnerClass() {
		return new InnerClass();
	}

	public static void main(String[] args) {
		OuterClass1 outer = new OuterClass1();
		OuterClass1.InnerClass inner = outer.getInnerClass();
		inner.innerDisplay();
	}
}
