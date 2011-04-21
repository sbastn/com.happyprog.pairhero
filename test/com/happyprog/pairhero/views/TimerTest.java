package com.happyprog.pairhero.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class TimerTest {

	private StubbedTimer timer;
	private Game game;

	@Before
	public void before() {
		timer = new StubbedTimer();

		game = mock(Game.class);
	}

	@Test
	public void updatesGameWhenTimeChanges() throws Exception {
		timer.start(game);

		verify(game).onTimeChange(Timer._25_MINS - 1);

		assertEquals(1, timer.ticks);
	}

	@Test
	public void onStopDoNotRerunInASecond() throws Exception {
		timer.start(game);

		assertEquals(1, timer.ticks);

		timer.stop();

		timer.run();

		assertEquals(1, timer.ticks);
	}

	class StubbedTimer extends Timer {

		private int ticks;

		@Override
		void reRunInASecond() {
			ticks++;
		}
	}
}
