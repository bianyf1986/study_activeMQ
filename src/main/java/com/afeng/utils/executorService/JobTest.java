package com.afeng.utils.executorService;

public class JobTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {
		
		
		for(int i = 0;i<20;i++){
			JobTaskVo vo = new JobTaskVo("name_"+i, i);
			ThreadPoolExecutorManager.addTask(vo);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
