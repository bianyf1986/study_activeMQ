package com.afeng.utils.multiThread.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.afeng.utils.loadConfig.LoaderUtils;

/**
 * @Description : 任务管理器
 * @author      : yfbian
 */
public class ThreadPoolExecutorManager {
	
	private static Logger logger = Logger.getLogger(ThreadPoolExecutorManager.class);
	
	private static ExecutorService executorService = null;

	public static AtomicInteger sendWorkerSize = new AtomicInteger();

	private static Integer poolSize = 1000;

	static {
		init();
	}

	public static void init() {
		
		logger.info("ThreadPoolExecutorManager Init called start.");
		
		// 初始化线程数
		int threadCoreSize = Integer.parseInt(LoaderUtils.getInstance().getThreadpoolConfig("job.corePoolSize"));
		int threadMaxSize = Integer.parseInt(LoaderUtils.getInstance().getThreadpoolConfig("job.maximumPoolSize"));
		long keepLive = Long.parseLong(LoaderUtils.getInstance().getThreadpoolConfig("job.keepAliveTime"));
		poolSize = Integer.parseInt(LoaderUtils.getInstance().getThreadpoolConfig("job.workQueue"));
		
		executorService = new ThreadPoolExecutor(threadCoreSize, threadMaxSize,keepLive, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(poolSize));
		
		logger.info("ThreadPoolExecutorManager Init called end.");
		
	}

	public static boolean addTask(JobTaskVo jobTaskVo) {
		if (executorService == null){
			init();
		}
			
		if (!isFull()) {
			sendWorkerSize.incrementAndGet();
			executorService.execute(new JobWorkerThread(jobTaskVo));
			return true;
		} else{
			logger.info("ThreadPoolExecutorManager ThreadPoolExecutor:pool isFull.");
			return false;
		}
		
	}

	private static boolean isFull() {
		//logger.info("当前活动的线程数:"+((ThreadPoolExecutor)executorService).getActiveCount());
		return sendWorkerSize.intValue() >= poolSize;
	}
}
