package com.happyprog.pairhero.views;

import org.eclipse.jdt.junit.JUnitCore;
import org.eclipse.jdt.junit.TestRunListener;
import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestElement.Result;
import org.eclipse.jdt.junit.model.ITestRunSession;

public class JUnitSubscriber {

	private Game game;
	// assuming that the first test was green makes the role switch on the first
	// test failure.
	private Result previousTestRun = ITestElement.Result.OK;
	private MyTestListener listener;

	public JUnitSubscriber() {
		listener = new MyTestListener();
		registerTestRuns();
	}

	public void subscribe(Game game) {
		this.game = game;
	}

	public void unregister() {
		JUnitCore.removeTestRunListener(listener);
	}

	void registerTestRuns() {
		JUnitCore.addTestRunListener(listener);
	}

	void onTestPass() {
		previousTestRun = ITestElement.Result.OK;
		game.onGreenTest();
	}

	void onTestFailed() {
		if (previousTestRun == ITestElement.Result.OK) {
			game.onSwitchRole();
		}
		previousTestRun = ITestElement.Result.ERROR;
	}

	class MyTestListener extends TestRunListener {

		@Override
		public void sessionFinished(ITestRunSession session) {
			Result result = session.getTestResult(true);
			if (ITestElement.Result.OK == result) {
				onTestPass();
			} else {
				onTestFailed();
			}
		}
	}
}
