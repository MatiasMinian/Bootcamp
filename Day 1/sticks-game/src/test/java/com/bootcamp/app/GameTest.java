package com.bootcamp.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	Game game = new Game();
	Player playerOne = mock(Player.class);
	Player playerTwo = mock(Player.class);

	@Before
	public void setUp() throws Exception {
	
		when(playerOne.take()).thenReturn(2);
		when(playerTwo.take()).thenReturn(2);
		when(playerOne.getName()).thenReturn("playerOne");
		when(playerTwo.getName()).thenReturn("playerTwo");
	
		game.addPlayer(playerOne);
		game.addPlayer(playerTwo);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayerOneLoses() {
		assertTrue(game.play() == playerOne);;
	}
	
	@Test
	public void testOneStickLeft() {
		game.play();
		assertTrue(game.getSticks() == 1);
	}

}
