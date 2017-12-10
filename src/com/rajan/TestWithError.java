package com.rajan;

public class TestWithError implements Runnable {
	public static void main(String[] args) throws InterruptedException {
		new TestWithError();
	}

	public TestWithError() throws InterruptedException {
		super();
		new Thread(this);
		Thread.sleep(1000);
	}

	@Override
	public void run() {

	}

}
