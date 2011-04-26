package com.happyprog.pairhero.views;

public class TimeFormatter {

	public static String formatTime(int timeInSeconds) {
		int minutes = timeInSeconds / 60;
		int seconds = timeInSeconds % 60;

		return String.format("%s:%s", withZeroes(minutes), withZeroes(seconds));
	}

	private static String withZeroes(int time) {
		if (time < 10) {
			return String.format("0%d", time);
		}
		return String.format("%d", time);
	}
}
