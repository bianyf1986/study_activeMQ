package com.afeng.utils.multiThread.executorService;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @Description : 执行任务的线程
 * @author      : yfbian
 */
public class JobWorkerThread implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(JobWorkerThread.class);

	private JobTaskVo jobTaskVo = null;

	public JobWorkerThread(JobTaskVo jobTaskVo) {
		this.jobTaskVo = jobTaskVo;
	}
	
	private Gson gson = new Gson();

	@Override
	public void run() {
		
		String name = jobTaskVo.getName();
		Integer age = jobTaskVo.getAge();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", name.toString());
		paramMap.put("age", age.toString());
		
		String json = gson.toJson(paramMap);
		
		//logger.info(json);
		
		/*
		 * 任务完成后，计数器：-1
		 */
		ThreadPoolExecutorManager.sendWorkerSize.decrementAndGet();
		
	}
	
}
