package com.bootcamp.app;

public class CountingUp implements Runnable {

	public void run() {
		for (int i = 0; i <= 100; i++) {
			System.out.println("Counting Up Thread: " + i);			
		}
	}
}
