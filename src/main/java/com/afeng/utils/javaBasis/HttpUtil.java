package com.afeng.utils.javaBasis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class HttpUtil {

    /**
     * 获取http错误状态码
     * @param request
     * @return
     */
	public static HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    } 
}
