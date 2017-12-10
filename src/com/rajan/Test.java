package com.rajan;

/**
 * figure out the error
 * 
 * @author rajan
 *
 */
public class Test implements Runnable {
	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void run() {
		System.out.println("Run");
	}

	public Test() {
		super();
		// new Thread(new Test()).start();
		new Thread(this).start();
		new Thread(this).start();
	}

}
