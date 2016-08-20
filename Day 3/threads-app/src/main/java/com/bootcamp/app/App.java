package com.bootcamp.app;

import java.util.concurrent.Executor;

public class App {
	public static void main(String[] args) {
		
		Executor executor = new Executor() {
			
			public void execute(Runnable r) {
				new Thread(r).start();				
			}
		};
		
		executor.execute(new CountingUp());
		executor.execute(new CountingDown());
	}
}
