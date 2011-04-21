package com.happyprog.pairhero.views;

public class Game {

	private final Timer timer;
	private final MainView view;

	public Game(MainView view, Timer timer) {
		this.view = view;
		this.timer = timer;
	}

	public void start() {
		timer.start(this);
	}

	public void onTimeChange(int seconds) {
		if (seconds <= 0) {
			timer.stop();
			view.gameFinished();
			return;
		}

		view.onTimeChange(seconds);
	}
}
