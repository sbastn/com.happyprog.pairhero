package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Timer timer;
	private Game game;
	private EndDialog endDialog;

	@Before
	public void before() {
		timer = mock(Timer.class);
		endDialog = mock(EndDialog.class);

		game = new Game(timer, endDialog);
	}

	@Test
	public void onGameStart_startTimer() throws Exception {
		game.start();

		verify(timer).start(game);
	}

	@Test
	public void gameEndsWhenTimerIsZero() throws Exception {
		game.start();

		game.onTimeChange(0);

		verify(endDialog).open();
	}

	@Test
	public void gameOnlyEndsWhenTimeIsUp() throws Exception {
		game.start();

		game.onTimeChange(1);

		verifyNoMoreInteractions(endDialog);
	}
}
