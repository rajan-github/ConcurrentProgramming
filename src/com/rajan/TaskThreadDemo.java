package com.rajan;

public class TaskThreadDemo {
	public static void main(String[] args) {
		Runnable printRajan = new GenericPrinter<String>("Rajan");
		Runnable printAge = new GenericPrinter<Integer>(24);
		Runnable printJob = new GenericPrinter<String>("Engineer");
		Thread thread1, thread2, thread3;
		thread1 = new Thread(printRajan);
		thread2 = new Thread(printAge);
		thread3 = new Thread(printJob);
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
