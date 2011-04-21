package com.happyprog.pairhero.views;

public class Game {

	private final Timer timer;
	private final EndDialog endDialog;

	public Game(Timer timer, EndDialog endDialog) {
		this.timer = timer;
		this.endDialog = endDialog;
	}

	public void start() {
		timer.start(this);
	}

	public void onTimeChange(int seconds) {
		if (seconds <= 0) {
			timer.stop();
			endDialog.open();
		}
	}
}
