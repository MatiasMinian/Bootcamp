package com.bootcamp.app;

public class CountingDown implements Runnable {

	public void run() {
		for(int i = 100; i >= 0; i--) {
			System.out.println("Counting Down Thread: " + i);
		}
		
	}

}
