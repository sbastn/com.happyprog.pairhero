package com.happyprog.pairhero.views;

import org.eclipse.ui.PlatformUI;

public class Timer implements Runnable {

	private static final int ONE_SECOND = 1000;
	static final int _25_MINS = 1500;

	private Game game;
	private int countdownInSeconds = _25_MINS;

	public void start(Game game) {
		this.game = game;
		run();
	}

	@Override
	public void run() {
		countdownInSeconds--;

		game.onTimeChange(countdownInSeconds);

		reRunInASecond();
	}

	void reRunInASecond() {
		PlatformUI.getWorkbench().getDisplay().timerExec(ONE_SECOND, this);
	}

}
