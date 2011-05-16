package com.happyprog.pairhero.game;

public class Scoreboard {

	public static final int GREEN_TEST_POINTS = 10;
	public static final int REFACTORING_POINTS = 2;
	public static final int SWITCHING_POINTS = 1;
	public static final int MULTIPLIER_2X = 2;
	public static final int MULTIPLIER_4X = 4;
	public static final int MULTIPLIER_1X = 1;

	private int score;
	private int secondsSinceLastSwitch;

	private int greenTestsCounter;
	private int refactoringCounter;
	private int lastMultiplier;
	private int _4xMultiplierCounter;
	private int _2xMultiplierCounter;
	private int _1xMultiplierCounter;

	public void addGreenTest() {
		score += GREEN_TEST_POINTS;
		greenTestsCounter++;
	}

	public int getScore() {
		return score;
	}

	public void addRefactoring() {
		score += REFACTORING_POINTS;
		refactoringCounter++;
	}

	public void addSwitch() {
		score += SWITCHING_POINTS;

		if (secondsSinceLastSwitch <= 30) {
			score *= 4;
			lastMultiplier = MULTIPLIER_4X;
			_4xMultiplierCounter++;
		} else if (secondsSinceLastSwitch <= 60) {
			score *= 2;
			lastMultiplier = MULTIPLIER_2X;
			_2xMultiplierCounter++;
		} else {
			lastMultiplier = MULTIPLIER_1X;
			_1xMultiplierCounter++;
		}

		secondsSinceLastSwitch = 0;
	}

	public int getNumberOfGreenTests() {
		return greenTestsCounter;
	}

	public int getNumberOfRefactorings() {
		return refactoringCounter;
	}

	public void onTimeChange() {
		secondsSinceLastSwitch++;
	}

	public int getSecondsSinceLastSwitch() {
		return secondsSinceLastSwitch;
	}

	public int getLastMultiplier() {
		return lastMultiplier;
	}

	public int getNumberOf4xMultipliers() {
		return _4xMultiplierCounter;
	}

	public int getNumberOf2xMultipliers() {
		return _2xMultiplierCounter;
	}

	public int getNumberOf1xMultipliers() {
		return _1xMultiplierCounter;
	}

	public void resetStats() {
		score = 0;
		greenTestsCounter = 0;
		refactoringCounter = 0;

		_4xMultiplierCounter = 0;
		_2xMultiplierCounter = 0;
		_1xMultiplierCounter = 0;

		secondsSinceLastSwitch = 0;
	}
}
