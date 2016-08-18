package com.bootcamp.app;

import java.util.Scanner;

public class App {
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Sticks game!");
		System.out.println("First Player Name: ");
		String firstPlayerName = scanner.next();
		System.out.println("Second Player Name: ");
		String secondPlayerName = scanner.next();
		
		Game game = new Game();
		Player playerOne = new Player(firstPlayerName);
		Player playerTwo = new Player(secondPlayerName);
		game.addPlayer(playerOne);
		game.addPlayer(playerTwo);
		
		game.play();
		scanner.close();
	}
}
