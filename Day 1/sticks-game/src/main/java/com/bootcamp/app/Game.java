package com.bootcamp.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

	private int sticks = 21;
	private List<Player> players = new ArrayList<>();

	public Player play() {
		Iterator<Player> it = players.iterator();
		Player player = null;
		while (sticks > 1) {
			if (!it.hasNext()) {
				it = players.iterator();
			}
			player = it.next();
			sticks -= player.take();
			System.out.println(sticks + " sticks remaining.");
		}
		if (sticks <= 0) {
			System.out.println(player.getName() + " loses.");
			return player;
		} else {
			if (!it.hasNext()) {
				it = players.iterator();
			}
			Player looser = it.next();
			System.out.println(looser.getName() + " loses.");
			return looser;
			
		}
	}

	public int getSticks() {
		return sticks;
	}

	public void setSticks(int sticks) {
		this.sticks = sticks;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
}
