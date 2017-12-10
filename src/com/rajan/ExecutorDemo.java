package com.rajan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.execute(new GenericPrinter<String>("Karnataka"));
		executor.execute(new GenericPrinter<String>("Apple"));
		executor.execute(new GenericPrinter<String>("Google"));
		executor.execute(new GenericPrinter<String>("Microsoft"));
		executor.execute(new GenericPrinter<String>("Oracle"));
		executor.execute(new GenericPrinter<String>("Rajan"));
	}
}
