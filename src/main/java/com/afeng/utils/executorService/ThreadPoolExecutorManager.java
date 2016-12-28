package com.afeng.utils.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cyou.tv.zebra.common.util.Loader;

/**
 * @category 任务管理器
 * @author bianyongfeng
 * @date 2016-11-24
 */
public class ThreadPoolExecutorManager {
	private static Logger logger = Logger.getLogger(ThreadPoolExecutorManager.class);
	private static ExecutorService service = null;

	public static AtomicInteger sendWorkerSize = new AtomicInteger();

	private static Integer poolSize = 1000;

	static {
		init();
	}

	public static void init() {
		logger.warn("AppJobManager Init called.");
		// 初始化线程数
		int threadCoreSize = Integer.parseInt(LoaderUtils.getInstance().getProps(
				"show.job.JobThreadCoreSize"));
		int threadMaxSize = Integer.parseInt(LoaderUtils.getInstance().getProps(
				"show.job.JobThreadMaxSize"));
		long keepLive = Long.parseLong(LoaderUtils.getInstance().getProps(
				"show.job.JobThreadKeepLiveTime"));// 线程定时执行时间
		poolSize = 1000;//Integer.parseInt(Loader.getInstance().getProps("show.job.JobQueueSize"));// 线程池子大小
		service = new ThreadPoolExecutor(threadCoreSize, threadMaxSize,
				keepLive, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(poolSize));
	}

	public static boolean addTask(AppJobTask task) {
		if (service == null)
			init();
		if (!isFull()) {
			sendWorkerSize.incrementAndGet();
			service.execute(new AppJobWorker(task));
			return true;
		} else
			return false;
		
	}

	private static boolean isFull() {
		return sendWorkerSize.intValue() >= poolSize;
	}
}
