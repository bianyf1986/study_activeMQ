package com.afeng.utils.javaBasis.multiThread.producerConsumer;

public class FirstProducer implements Runnable {
	
	private FirstProductPool pool;

	public FirstProducer(FirstProductPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			pool.set();
		}
	}
}
