package com.happyprog.pairhero.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScoreboardTest {

	private Scoreboard scoreboard;

	@Before
	public void before() {
		scoreboard = new Scoreboard();
	}

	@Test
	public void scoreWithOneGreenTest() throws Exception {
		scoreboard.addGreenTest();

		assertEquals(Scoreboard.GREEN_TEST_POINTS, scoreboard.getScore());
	}

	@Test
	public void scoreWithMultipleGreenTests() throws Exception {
		scoreboard.addGreenTest();
		scoreboard.addGreenTest();

		assertEquals(Scoreboard.GREEN_TEST_POINTS * 2, scoreboard.getScore());
	}

	@Test
	public void greenTestCounterIncrementsWithGreenTests() throws Exception {
		scoreboard.addGreenTest();
		scoreboard.addGreenTest();

		assertEquals(2, scoreboard.getNumberOfGreenTests());
	}

	@Test
	public void scoreWithOneRefactoring() throws Exception {
		scoreboard.addRefactoring();

		assertEquals(Scoreboard.REFACTORING_POINTS, scoreboard.getScore());
	}

	@Test
	public void scoreWithMultipleRefactorings() throws Exception {
		scoreboard.addRefactoring();
		scoreboard.addRefactoring();

		assertEquals(Scoreboard.REFACTORING_POINTS * 2, scoreboard.getScore());
	}

	@Test
	public void refactoringCounterIncrementsWithEachRefactoring() throws Exception {
		scoreboard.addRefactoring();
		scoreboard.addRefactoring();

		assertEquals(2, scoreboard.getNumberOfRefactorings());
	}

	@Test
	public void onTimeChangeIncrementsTheSecondsSinceTheLastProgrammerSwitch() throws Exception {
		scoreboard.onTimeChange();

		assertEquals(1, scoreboard.getSecondsSinceLastSwitch());
	}

	@Test
	public void scoreWithOneSwitchWhenLastTimePlayerSwitchWas2MinutesAgo() throws Exception {
		incrementTimeSinceLastSwitchInSeconds(120);

		scoreboard.addSwitch();

		assertEquals(Scoreboard.SWITCHING_POINTS, scoreboard.getScore());
	}

	@Test
	public void scoreWithOneSwitchWhenLastTimePlayerSwitchWasOneMinuteAgo() throws Exception {
		incrementTimeSinceLastSwitchInSeconds(60);

		scoreboard.addSwitch();

		assertEquals(Scoreboard.SWITCHING_POINTS * 2, scoreboard.getScore());
		assertEquals(Scoreboard.MULTIPLIER_2X, scoreboard.getLastMultiplier());
	}

	@Test
	public void scoreWithOneSwitchWhenLastTimePlayerSwitchWasLessThan30SecondsAgo() throws Exception {
		incrementTimeSinceLastSwitchInSeconds(30);

		scoreboard.addSwitch();

		assertEquals(Scoreboard.SWITCHING_POINTS * 4, scoreboard.getScore());
		assertEquals(Scoreboard.MULTIPLIER_4X, scoreboard.getLastMultiplier());
	}

	@Test
	public void lastMultiplierChangesOnMultipleSwitches() throws Exception {
		incrementTimeSinceLastSwitchInSeconds(30);
		scoreboard.addSwitch();

		assertEquals(Scoreboard.MULTIPLIER_4X, scoreboard.getLastMultiplier());

		incrementTimeSinceLastSwitchInSeconds(200);
		scoreboard.addSwitch();

		assertEquals(Scoreboard.MULTIPLIER_1X, scoreboard.getLastMultiplier());

		incrementTimeSinceLastSwitchInSeconds(60);
		scoreboard.addSwitch();

		assertEquals(Scoreboard.MULTIPLIER_2X, scoreboard.getLastMultiplier());
	}

	@Test
	public void switchingRolesIncrementsMultiplierCounters() throws Exception {
		incrementTimeSinceLastSwitchInSeconds(30);
		scoreboard.addSwitch();

		incrementTimeSinceLastSwitchInSeconds(200);
		scoreboard.addSwitch();

		incrementTimeSinceLastSwitchInSeconds(60);
		scoreboard.addSwitch();

		incrementTimeSinceLastSwitchInSeconds(30);
		scoreboard.addSwitch();

		assertEquals(2, scoreboard.getNumberOf4xMultipliers());
		assertEquals(1, scoreboard.getNumberOf2xMultipliers());
		assertEquals(1, scoreboard.getNumberOf1xMultipliers());
	}

	private void incrementTimeSinceLastSwitchInSeconds(int seconds) {
		for (int i = 0; i < seconds; i++) {
			scoreboard.onTimeChange();
		}
	}

}
