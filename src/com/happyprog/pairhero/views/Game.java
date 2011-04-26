package com.happyprog.pairhero.views;

public class Game {

	public static final int GREEN_TEST_POINTS = 10;
	private int score;

	private final Timer timer;
	private final MainView view;
	private final Programmer leftProgrammer;
	private final Programmer rightProgrammer;
	private final JUnitSubscriber testSubscriber;

	public Game(MainView view, Timer timer, Programmer leftProgrammer, Programmer rightProgrammer,
			JUnitSubscriber testSubscriber) {
		this.view = view;
		this.timer = timer;
		this.leftProgrammer = leftProgrammer;
		this.rightProgrammer = rightProgrammer;
		this.testSubscriber = testSubscriber;
	}

	public void start() {
		timer.start(this);

		testSubscriber.subscribe(this);

		leftProgrammer.drive();
		rightProgrammer.observe();
	}

	public void onTimeChange(int seconds) {
		view.onTimeChange(seconds);

		if (seconds <= 0) {
			timer.stop();
			view.onGameFinished("Awesome!");
			return;
		}
	}

	public void onSwitchRole() {
		leftProgrammer.switchRole();
		rightProgrammer.switchRole();
	}

	public void onGreenTest() {
		score += GREEN_TEST_POINTS;
		view.updateScore(score);

	}
}
