package com.afeng.utils.loadConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * @Description : 加载spring配置文件工具类
 * @author      : yfbian
 */
public class LoaderSpringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(LoaderSpringUtils.class);
	
	private ApplicationContext ctx = null;
	
	private static LoaderSpringUtils loader = new LoaderSpringUtils();
	
	private LoaderSpringUtils(){
		
		logger.info("load spring config file start");
		
		ctx = ContextLoader.getCurrentWebApplicationContext();
		
		logger.info("load spring config file end");
		
	}
	
	public final static LoaderSpringUtils getInstance() {
		return loader;
	}
	
	public Object getObject(String id) {
		
		return ctx.getBean(id);
	}
	
	public static void main(String[] args) {
		
	}
}
