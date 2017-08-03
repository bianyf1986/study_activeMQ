package com.afeng.utils.javaBasis.loadConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description : 测试Component注解
 * @author      : yfbian
 */
@Component
public class TestLoaderSpringBean {
	
	private static Logger logger = LoggerFactory.getLogger(TestLoaderSpringBean.class);

	private String name;
	public void doSomething(){
		logger.info("doSomething ok");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
