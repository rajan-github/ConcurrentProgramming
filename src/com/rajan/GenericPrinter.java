package com.rajan;

public class GenericPrinter<E> implements Runnable {
	private E valueToPrint;

	public GenericPrinter(E valueToPrint) {
		super();
		this.valueToPrint = valueToPrint;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print(valueToPrint + ", ");
		}
		System.out.println();
	}

}
