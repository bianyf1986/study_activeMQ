package com.afeng.utils.javaBasis;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Description : IP工具类
 * @author      : yfbian
 */
public class IpUtil {

	/**
	 * 
	 * @Function :  getIpAddr
	 * @Desc     :  获取登录用户IP地址
	 * @Author   :  yfbian
	 * @param request HttpServletRequest对象
	 * @return 登录用户IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf("0:") != -1) {
			ip = "本地";
		}
		return ip;
	}

}
