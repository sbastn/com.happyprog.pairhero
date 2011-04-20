package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class TimerTest {
	@Test
	public void updatesGameWhenTimeChanges() throws Exception {
		Timer timer = new StubbedTimer();
		Game game = mock(Game.class);

		timer.start(game);

		verify(game).onTimeChange(Timer._25_MINS - 1);
	}

	class StubbedTimer extends Timer {
		@Override
		void reRunInASecond() {
			// Change this to a mockito spy. Not sure why it does not work with
			// spy(new Timer())
		}
	}
}
