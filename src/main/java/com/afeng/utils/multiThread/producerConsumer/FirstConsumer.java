package com.afeng.utils.multiThread.producerConsumer;

public class FirstConsumer implements Runnable {

	private FirstProductPool pool;

	public FirstConsumer(FirstProductPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			pool.get();
		}
	}
}