package com.happyprog.pairhero.game;

import com.happyprog.pairhero.subscribers.JUnitSubscriber;
import com.happyprog.pairhero.subscribers.RefactoringSubscriber;
import com.happyprog.pairhero.time.Timer;
import com.happyprog.pairhero.views.MainView;

public class Game {

	public static final int GREEN_TEST_POINTS = 10;
	public static final int REFACTORING_POINTS = 2;
	public static final int ROLE_SWITCH_POINTS = 1;
	private int score;
	private int timeSinceSwitch;

	private final Timer timer;
	private final MainView view;
	private final Programmer leftProgrammer;
	private final Programmer rightProgrammer;
	private final JUnitSubscriber testSubscriber;
	private final RefactoringSubscriber refactoringSubscriber;

	public Game(MainView view, Timer timer, Programmer leftProgrammer, Programmer rightProgrammer,
			JUnitSubscriber testSubscriber, RefactoringSubscriber refactoringSubscriber) {
		this.view = view;
		this.timer = timer;
		this.leftProgrammer = leftProgrammer;
		this.rightProgrammer = rightProgrammer;
		this.testSubscriber = testSubscriber;
		this.refactoringSubscriber = refactoringSubscriber;
	}

	public void start() {
		testSubscriber.subscribe(this);
		refactoringSubscriber.subscribe(this);

		leftProgrammer.drive();
		rightProgrammer.observe();

		timer.start(this);

		view.updateScore(score);
	}

	public void stop() {
		timer.stop();
		testSubscriber.unregister();
		refactoringSubscriber.unregister();
	}

	public void onTimeChange(int seconds) {
		view.onTimeChange(seconds);
		leftProgrammer.onTimeChange();
		rightProgrammer.onTimeChange();
		timeSinceSwitch++;

		if (seconds <= 0) {
			stop();
			view.onGameFinished("Awesome!");
			return;
		}
	}

	public void onSwitchRole() {
		score += ROLE_SWITCH_POINTS;
		if (timeSinceSwitch < 30) {
			score *= 4;
		} else if (timeSinceSwitch >= 30 && timeSinceSwitch < 120) {
			score *= 2;
		}

		leftProgrammer.switchRole();
		rightProgrammer.switchRole();
		view.onSwitchRole();
		view.updateScore(score);
	}

	public void onGreenTest() {
		score += GREEN_TEST_POINTS;
		view.updateScore(score);
	}

	public void onRefactoring() {
		score += REFACTORING_POINTS;
		view.updateScore(score);
	}

}
