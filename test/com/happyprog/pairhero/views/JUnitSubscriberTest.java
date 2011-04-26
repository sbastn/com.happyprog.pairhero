package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class JUnitSubscriberTest {

	private Game game;
	private JUnitSubscriber subscriber;

	@Before
	public void before() {
		game = mock(Game.class);
		subscriber = new StubbedSubscriber();
	}

	@Test
	public void onTheFirstRedTest_switchRoles() throws Exception {
		subscriber.subscribe(game);

		subscriber.onTestFailed();

		verify(game).onSwitchRole();
	}

	@Test
	public void onConsecutiveRedTests_doNotSwitchRoles() throws Exception {
		subscriber.subscribe(game);

		subscriber.onTestFailed();
		subscriber.onTestFailed();
		subscriber.onTestFailed();

		verify(game, times(1)).onSwitchRole();
	}

	@Test
	public void onRedToGreenToRed_switchRoles() throws Exception {
		subscriber.subscribe(game);

		subscriber.onTestFailed();
		subscriber.onTestPass();
		subscriber.onTestFailed();

		verify(game, times(2)).onSwitchRole();
	}

	@Test
	public void onGreenTests_updateGame() throws Exception {
		subscriber.subscribe(game);

		subscriber.onTestPass();

		verify(game).onGreenTest();
	}

	class StubbedSubscriber extends JUnitSubscriber {
		void registerTestRuns() {
			// do nothing to call the JUnit core code
		}
	}

}
