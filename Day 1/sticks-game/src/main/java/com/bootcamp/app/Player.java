package com.bootcamp.app;

public class Player {

	private String name;

	public Player(String name) {
		this.name = name;
	}

	public int take() {
		int sticks;
		do {
			System.out.println(name + ", how many sticks?: ");
			sticks = Integer.parseInt(App.scanner.next());
			if (sticks > 2 || sticks < 1) {
				System.out.println("Take 1 or 2 sticks");								
			}
		} while (sticks > 2 || sticks < 1);
		return sticks;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
