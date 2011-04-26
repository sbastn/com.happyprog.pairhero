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

	public JUnitSubscriber() {
		registerTestRuns();
	}

	public void subscribe(Game game) {
		this.game = game;
	}

	void registerTestRuns() {
		JUnitCore.addTestRunListener(new TestRunListener() {
			@Override
			public void sessionFinished(ITestRunSession session) {
				Result result = session.getTestResult(true);
				if (ITestElement.Result.OK == result) {
					onTestPass();
				} else {
					onTestFailed();
				}
			}
		});
	}

	void onTestPass() {
		// TODO: we want to score some points for making the test pass.
		previousTestRun = ITestElement.Result.OK;
	}

	void onTestFailed() {
		if (previousTestRun == ITestElement.Result.OK) {
			game.onSwitchRole();
		}
		previousTestRun = ITestElement.Result.ERROR;
	}

}
