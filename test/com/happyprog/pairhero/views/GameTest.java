package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private MainView view;
	private Timer timer;
	private Programmer programmer1;
	private Programmer programmer2;
	private JUnitSubscriber testSubscriber;

	private Game game;
	private RefactoringSubscriber refactoringSubscriber;

	@Before
	public void before() {
		view = mock(MainView.class);
		timer = mock(Timer.class);
		programmer1 = mock(Programmer.class);
		programmer2 = mock(Programmer.class);
		testSubscriber = mock(JUnitSubscriber.class);
		refactoringSubscriber = mock(RefactoringSubscriber.class);

		game = new Game(view, timer, programmer1, programmer2, testSubscriber, refactoringSubscriber);
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

	@Test
	public void onSwitchRole_tellProgrammersToSwitch() throws Exception {
		game.start();

		game.onSwitchRole();

		verify(programmer1).switchRole();
		verify(programmer2).switchRole();
	}

	@Test
	public void onGreenTest_updateScore() throws Exception {
		game.start();

		game.onGreenTest();

		verify(view).updateScore(Game.GREEN_TEST_POINTS);
	}

	@Test
	public void onRefactoring_updateScore() throws Exception {
		game.start();

		game.onRefactoring();

		verify(view).updateScore(Game.REFACTORING_POINTS);
	}
}
