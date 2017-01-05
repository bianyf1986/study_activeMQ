package com.afeng.utils.multiThread.producerConsumer;

public class FirstMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FirstProductPool pool = new FirstProductPool();
		
		FirstProducer producer = new FirstProducer(pool);
		FirstConsumer consumer = new FirstConsumer(pool);
		
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		
		threadProducer.start();
		threadConsumer.start();
		
	}

}
