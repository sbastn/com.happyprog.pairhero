package com.happyprog.pairhero.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeFormatLearningTest {
	@Test
	public void oneSecond() throws Exception {
		assertEquals("00:01", format(1));
	}

	@Test
	public void twentySeconds() throws Exception {
		assertEquals("00:20", format(20));
	}

	@Test
	public void oneMinute() throws Exception {
		assertEquals("01:00", format(60));
	}

	@Test
	public void oneMinuteAndThirtySeconds() throws Exception {
		assertEquals("01:30", format(90));
	}

	@Test
	public void twentyFiveMinutes() throws Exception {
		assertEquals("25:00", format(1500));
	}

	@Test
	public void twentyFourMinutesAndFiftyNineSeconds() throws Exception {
		assertEquals("24:59", format(1499));
	}

	private String format(int time) {
		int minutes = time / 60;
		int seconds = time % 60;

		return String.format("%s:%s", fill(minutes), fill(seconds));
	}

	private Object fill(int seconds) {
		if (seconds < 10) {
			return String.format("0%d", seconds);
		}
		return String.format("%d", seconds);
	}
}
