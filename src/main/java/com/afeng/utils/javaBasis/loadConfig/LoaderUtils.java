package com.afeng.utils.javaBasis.loadConfig;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description : 加载系统配置文件工具类
 * @author      : yfbian
 */
public class LoaderUtils {
	
	private static Logger logger = LoggerFactory.getLogger(LoaderUtils.class);
	
	private PropertyResourceBundle bundle;
	
	private PropertyResourceBundle webConfigBundle;
	
	private PropertyResourceBundle threadpoolConfigBundle;
	
	private static LoaderUtils loader = new LoaderUtils();
	
	private LoaderUtils(){
		
		try{
			logger.info("load config file: ApplicationResources.properties start");
			InputStream in = LoaderUtils.class.getResourceAsStream("/ApplicationResources.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			bundle = new PropertyResourceBundle(r);
			r.close();
			logger.info("load config file: ApplicationResources.properties end");
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}
		
		try{
			logger.info("load config file: web-config.properties start");
			InputStream in = LoaderUtils.class.getResourceAsStream("/web-config.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			webConfigBundle = new PropertyResourceBundle(r);
			r.close();
			logger.info("load config file: web-config.properties end");
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}
		
		try{
			logger.info("load config file: threadpool-config.properties start");
			InputStream in = LoaderUtils.class.getResourceAsStream("/threadpool-config.properties");
			InputStreamReader r;
			r = new InputStreamReader(in, "UTF-8");
			threadpoolConfigBundle = new PropertyResourceBundle(r);
			r.close();
			logger.info("load config file: threadpool-config.properties end");
		}catch(Exception e){
			logger.error("Exception:"+e.getMessage(), e);
		}
		
	}
	
	public final static LoaderUtils getInstance() {
		return loader;
	}
	
	public String getApplicationResources(String key){
		return bundle.getString(key);
	}
	
	public String getWebConfig(String key){
		return webConfigBundle.getString(key);
	}
	
	public String getThreadpoolConfig(String key){
		return threadpoolConfigBundle.getString(key);
	}
	
	public static void main(String[] args) {
		System.out.println(LoaderUtils.getInstance().getWebConfig("key2"));
		System.out.println(LoaderUtils.getInstance().getApplicationResources("name"));
	}
}
