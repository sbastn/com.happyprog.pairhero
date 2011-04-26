package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private MainView view;
	private Timer timer;
	private Programmer programmer1;
	private Programmer programmer2;

	private Game game;

	@Before
	public void before() {
		view = mock(MainView.class);
		timer = mock(Timer.class);
		programmer1 = mock(Programmer.class);
		programmer2 = mock(Programmer.class);

		game = new Game(view, timer, programmer1, programmer2);
	}

	@Test
	public void onGameStart_startTimer() throws Exception {
		game.start();

		verify(timer).start(game);
	}

	@Test
	public void onGameStart_programmersHaveRoles() throws Exception {
		game.start();

		verify(programmer1).drive();
		verify(programmer2).observe();
	}

	@Test
	public void onTimeChange_UpdateView() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(view).onTimeChange(1);
	}

	@Test
	public void gameEndsWhenTimerIsZero() throws Exception {
		game.start();

		game.onTimeChange(0);

		verify(view).onGameFinished("Awesome!");
	}

	@Test
	public void gameOnlyEndsWhenTimeIsUp() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(view, never()).onGameFinished("Awesome!");
		verify(timer, never()).stop();
	}

	@Test
	public void gameEndsStopsTheTimer() throws Exception {
		game.start();

		game.onTimeChange(0);

		verify(timer).stop();
	}
}
