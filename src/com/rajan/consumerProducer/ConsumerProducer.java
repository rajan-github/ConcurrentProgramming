package com.rajan.consumerProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
	private static Buffer buffer = new Buffer();

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
		System.out.println("Task completed");
	}

	private static class ProducerTask implements Runnable {
		@Override
		public void run() {
			try {
				int i = 1;
				while (true) {
					System.out.println("Producer writes " + i);
					buffer.write(i++);
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class ConsumerTask implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					System.out.println("\t\t\tConsumer reads " + buffer.read());
					// Put the thread into sleep
					Thread.sleep((int) (Math.random() * 10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private static class Buffer {
		private static int CAPACITY = 1;
		private List<Integer> queue = new ArrayList<>();
		private static Lock lock = new ReentrantLock();
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();

		public void write(int n) {
			lock.lock();
			try {
				while (CAPACITY == queue.size()) {
					System.out.println("Wait for notFull condition");
					notFull.await();
				}
				queue.add(n);
				notEmpty.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public Integer read() {
			Integer returnedValue = null;
			lock.lock();
			try {
				while (queue.size() < CAPACITY) {
					System.out.println("\t\tWait for notEmpty condition");
					notEmpty.await();
				}
				returnedValue = queue.remove(0);
				notFull.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return returnedValue;
		}
	}
}
