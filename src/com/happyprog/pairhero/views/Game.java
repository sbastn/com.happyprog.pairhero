package com.happyprog.pairhero.views;

public class Game {

	private final Timer timer;
	private final MainView view;
	private final Programmer programmer1;
	private final Programmer programmer2;

	public Game(MainView view, Timer timer, Programmer programmer1, Programmer programmer2) {
		this.view = view;
		this.timer = timer;
		this.programmer1 = programmer1;
		this.programmer2 = programmer2;
	}

	public void start() {
		timer.start(this);
		programmer1.drive();
		programmer2.observe();
	}

	public void onTimeChange(int seconds) {
		view.onTimeChange(seconds);

		if (seconds <= 0) {
			timer.stop();
			view.onGameFinished("Awesome!");
			return;
		}
	}
}
