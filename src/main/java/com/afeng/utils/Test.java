package com.afeng.utils;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private static final String NUMBER = "/IDVALUE/";
	
	public static ThreadLocal<Map<String, Object>> logInfoThreadLocal = new ThreadLocal<Map<String, Object>>();

	public static void main(String[] args) {
		
//		String uri = "https://bamai.xiaojukeji.com/diagnose/zhuanche?tagtype=driverListenOrder/123/123123";
//		uri = uri.replaceAll("/[0-9]*/", NUMBER);
//		System.out.println(uri);
//		uri = uri.substring(0, uri.lastIndexOf("/"));;
//		System.out.println(uri);
		
		System.out.println(logInfoThreadLocal.get());
		Map logInfoMap = new HashMap<>();
		logInfoMap.put("dsl", "i am dsl value");
		logInfoThreadLocal.set(logInfoMap);
		System.out.println(logInfoThreadLocal.get());
		logInfoThreadLocal.remove();
		System.out.println(logInfoThreadLocal.get());
		
		try {
			int i = 0;
			int j = 8/i;
		} catch (Exception e) {
			System.out.println("catch");
		}finally {
			System.out.println("finally");
		}
		
	}

}
