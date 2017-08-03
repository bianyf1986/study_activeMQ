package com.afeng.utils.javaBasis.loadConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description : 测试Autowired注解
 * @author      : yfbian
 */
@Component
public class TestLoaderSpringBeanAutowired {
	
	private static Logger logger = LoggerFactory.getLogger(TestLoaderSpringBeanAutowired.class);

	@Autowired
	TestLoaderSpringBean testLoaderSpringBean;
	
	public void doSomething(){
		logger.info("TestLoaderSpringBeanAutowired start");
		testLoaderSpringBean.doSomething();
		logger.info("TestLoaderSpringBeanAutowired end");
	}
	
}
