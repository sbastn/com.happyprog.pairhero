package com.happyprog.pairhero.game;

import com.happyprog.pairhero.subscribers.JUnitSubscriber;
import com.happyprog.pairhero.subscribers.RefactoringSubscriber;
import com.happyprog.pairhero.time.Timer;
import com.happyprog.pairhero.views.MainView;

public class Game {

	private final Timer timer;
	private final MainView view;
	private final Programmer leftProgrammer;
	private final Programmer rightProgrammer;
	private final JUnitSubscriber testSubscriber;
	private final RefactoringSubscriber refactoringSubscriber;
	private final Scoreboard scoreboard;

	public Game(MainView view, Timer timer, Programmer leftProgrammer, Programmer rightProgrammer,
			JUnitSubscriber testSubscriber, RefactoringSubscriber refactoringSubscriber, Scoreboard scoreboard) {
		this.view = view;
		this.timer = timer;
		this.leftProgrammer = leftProgrammer;
		this.rightProgrammer = rightProgrammer;
		this.testSubscriber = testSubscriber;
		this.refactoringSubscriber = refactoringSubscriber;
		this.scoreboard = scoreboard;
	}

	public void start() {
		testSubscriber.subscribe(this);
		refactoringSubscriber.subscribe(this);

		leftProgrammer.drive();
		rightProgrammer.observe();

		timer.start(this);
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
		scoreboard.onTimeChange();

		if (seconds <= 0) {
			stop();
			view.onGameFinished();
			return;
		}
	}

	public void onSwitchRole() {
		leftProgrammer.switchRole();
		rightProgrammer.switchRole();
		scoreboard.addSwitch();
		view.onSwitchRole();
	}

	public void onGreenTest() {
		scoreboard.addGreenTest();
		view.onGreenTest();
	}

	public void onRefactoring() {
		scoreboard.addRefactoring();
		view.onRefactoring();
	}

}
