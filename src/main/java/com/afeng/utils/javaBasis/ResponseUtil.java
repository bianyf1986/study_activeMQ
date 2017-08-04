package com.afeng.utils.javaBasis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author bianyongfeng
 *
 */
public class ResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    public static void handleResponse(HttpServletResponse response, Object obj) throws Exception{
        
        /*
         * 方式1:
         * String tmpStr = JSON.toJSONString(obj);
         * response.getOutputStream().write(tmpStr.getBytes("utf8"));
         * response.setContentType("application/json");
         * response.getOutputStream().flush();
         * response.getOutputStream().close();
         */
        
        String json = JSON.toJSONString(obj);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        		LOGGER.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
