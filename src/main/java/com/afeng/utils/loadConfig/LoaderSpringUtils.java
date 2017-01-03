package com.afeng.utils.loadConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
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
		
		/**
		 * 常用方式一
		 * web项目在web.xml中已经加载了spring的配置文件，在这里直接获取ApplicationContext即可
		 * ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		 * web.xml中配置示例
		 * <context-param>
		 * 		<param-name>contextConfigLocation</param-name>
		 * 		<param-value>classpath:applicationContext*.xml</param-value>
		 * </context-param>
		 * 
		 * <listener>
		 * 		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		 * </listener>
		 */
		
		/**
		 * 常用方式二
		 * 使用FileSystemXmlApplicationContext 和 ClassXmlAplicationContext
		 * ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext*.xml");
		 * ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
		 */
		//ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-test-2.xml");
		
		/**
		 * 常用方式三（扫描注解）
		 */
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-test-3.xml");
		
		logger.info("load spring config file end");
		
	}
	
	public final static LoaderSpringUtils getInstance() {
		return loader;
	}
	
	public Object getBean(String id) {
		
		return ctx.getBean(id);
	}
	
	public static void main(String[] args) {
		/*
		 * 测试Component注解
		 */
		TestLoaderSpringBean bean  = (TestLoaderSpringBean)LoaderSpringUtils.getInstance().getBean("testLoaderSpringBean");
		bean.doSomething();
		
		/*
		 * 测试Autowired注解
		 */
		TestLoaderSpringBeanAutowired bean2  = (TestLoaderSpringBeanAutowired)LoaderSpringUtils.getInstance().getBean("testLoaderSpringBeanAutowired");
		bean2.doSomething();
		
	}
}
