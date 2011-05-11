package com.happyprog.pairhero.game;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pairhero.subscribers.JUnitSubscriber;
import com.happyprog.pairhero.subscribers.RefactoringSubscriber;
import com.happyprog.pairhero.time.Timer;
import com.happyprog.pairhero.views.MainView;

public class GameTest {

	private MainView view;
	private Timer timer;
	private Programmer leftProgrammer;
	private Programmer rightProgrammer;
	private JUnitSubscriber testSubscriber;
	private RefactoringSubscriber refactoringSubscriber;
	private Scoreboard scoreboard;

	private Game game;

	@Before
	public void before() {
		view = mock(MainView.class);
		timer = mock(Timer.class);
		leftProgrammer = mock(Programmer.class);
		rightProgrammer = mock(Programmer.class);
		testSubscriber = mock(JUnitSubscriber.class);
		refactoringSubscriber = mock(RefactoringSubscriber.class);
		scoreboard = mock(Scoreboard.class);

		game = new Game(view, timer, leftProgrammer, rightProgrammer, testSubscriber, refactoringSubscriber, scoreboard);
	}

	@Test
	public void onGameStart_startTimer() throws Exception {
		game.start();

		verify(timer).start(game);
	}

	@Test
	public void onGameStart_programmersHaveRoles() throws Exception {
		game.start();

		verify(leftProgrammer).drive();
		verify(rightProgrammer).observe();
	}

	@Test
	public void onGameStart_subscribeToJUnitRuns() throws Exception {
		game.start();

		verify(testSubscriber).subscribe(game);
	}

	@Test
	public void onGameStart_subscribeToRefactorings() throws Exception {
		game.start();

		verify(refactoringSubscriber).subscribe(game);
	}

	@Test
	public void onGameStop_stopTimer() throws Exception {
		game.stop();

		verify(timer).stop();
	}

	@Test
	public void onGameStop_unsubscribeFromTests() throws Exception {
		game.stop();

		verify(testSubscriber).unregister();
	}

	@Test
	public void onGameStop_unsubscribeFromRefactorings() throws Exception {
		game.stop();

		verify(refactoringSubscriber).unregister();
	}

	@Test
	public void onTimeChange_UpdateView() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(view).onTimeChange(1);
	}

	@Test
	public void onTimeChange_updateProgrammers() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(leftProgrammer).onTimeChange();
		verify(rightProgrammer).onTimeChange();
	}

	@Test
	public void onTimeChange_updateScoreboard() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(scoreboard).onTimeChange();
	}

	@Test
	public void gameEndsWhenTimerIsZero() throws Exception {
		game.start();

		game.onTimeChange(0);

		verify(view).onGameFinished();
	}

	@Test
	public void gameOnlyEndsWhenTimeIsUp() throws Exception {
		game.start();

		game.onTimeChange(1);

		verify(view, never()).onGameFinished();
		verify(timer, never()).stop();
	}

	@Test
	public void gameEndsStopsTheTimer() throws Exception {
		game.start();

		game.onTimeChange(0);

		verify(timer).stop();
	}

	@Test
	public void onSwitchRole_tellProgrammersToSwitch() throws Exception {
		game.start();

		game.onSwitchRole();

		verify(leftProgrammer).switchRole();
		verify(rightProgrammer).switchRole();
	}

	@Test
	public void onSwitchRole_tellViewThatProgrammerShouldSwitch() throws Exception {
		game.start();

		game.onSwitchRole();

		verify(view).onSwitchRole();
	}

	@Test
	public void whenSwitchingRolesTakesLessThan30Seconds_add4xMultiplierToScore() throws Exception {
		game.start();

		game.onTimeChange(1);

		game.onSwitchRole();

		verify(scoreboard).addSwitch();
		verify(view).onSwitchRole();
	}

	@Test
	public void whenSwitchingRoleTakesBetween30And120Seconds_add2xMultiplierToScore() throws Exception {
		game.start();

		for (int i = 0; i < 40; i++) {
			game.onTimeChange(1);
		}

		game.onSwitchRole();

		verify(scoreboard).addSwitch();
		verify(view).onSwitchRole();
	}

	@Test
	public void whenSwitchingRoleTakesMoreThan120Seconds_noMuliplierIsAddedToScore() throws Exception {
		game.start();

		for (int i = 0; i < 130; i++) {
			game.onTimeChange(1);
		}

		game.onSwitchRole();

		verify(scoreboard).addSwitch();
		verify(view).onSwitchRole();

	}

	@Test
	public void onGreenTest_updateScoreboard() throws Exception {
		game.start();

		game.onGreenTest();

		verify(scoreboard).addGreenTest();
		verify(view).onGreenTest();
	}

	@Test
	public void onRefactoring_updateScoreboard() throws Exception {
		game.start();

		game.onRefactoring();

		verify(scoreboard).addRefactoring();
		verify(view).onRefactoring();
	}
}
