package com.afeng.utils;

public class Test {
	
	private static final String NUMBER = "/IDVALUE/";

	public static void main(String[] args) {
		
		String uri = "https://bamai.xiaojukeji.com/diagnose/zhuanche?tagtype=driverListenOrder/123/123123";
		uri = uri.replaceAll("/[0-9]*/", NUMBER);
		System.out.println(uri);
		uri = uri.substring(0, uri.lastIndexOf("/"));;
		System.out.println(uri);
	}

}
