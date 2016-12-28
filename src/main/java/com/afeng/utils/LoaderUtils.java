package com.afeng.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * @Description : 加载配置文件工具类
 * @author      : yfbian
 */
public class LoaderUtils {
	
	private static Logger logger = LoggerFactory.getLogger(LoaderUtils.class);
	
	private ApplicationContext ctx = null;
	private PropertyResourceBundle bundle;
	
	private PropertyResourceBundle tradeBundle;
	
	private PropertyResourceBundle messageBundle;
	
	private static LoaderUtils loader = new LoaderUtils();

	private LoaderUtils(){
		
		//加载spring配置文件
		ctx = ContextLoader.getCurrentWebApplicationContext();
		
		logger.info("加载spring配置文件");
	
		try{
			InputStream in = LoaderUtils.class.getResourceAsStream("/ApplicationResources.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			bundle = new PropertyResourceBundle(r);
			r.close();
			
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}
		
		try{
			InputStream in = LoaderUtils.class.getResourceAsStream("/MessageResources.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			messageBundle = new PropertyResourceBundle(r);
			r.close();
			
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}
		
		try{
			InputStream in = LoaderUtils.class.getResourceAsStream("/trade-show.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			tradeBundle = new PropertyResourceBundle(r);
			r.close();
			
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}	
	
	}
	
	public final static LoaderUtils getInstance() {
		return loader;
	}
	
	public Object getObject(String id) {
		
		return ctx.getBean(id);
	}
	
	public String getProps(String key){
		return bundle.getString(key);
	}
	
	public String getMessage(String key){
		return messageBundle.getString(key);
	}
	
	public String getTradeProps(String key){
		return tradeBundle.getString(key);
	}

}
