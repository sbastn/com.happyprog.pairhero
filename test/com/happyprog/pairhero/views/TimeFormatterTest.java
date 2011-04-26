package com.happyprog.pairhero.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeFormatterTest {
	@Test
	public void oneSecond() throws Exception {
		assertEquals("00:01", TimeFormatter.formatTime(1));
	}

	@Test
	public void twentySeconds() throws Exception {
		assertEquals("00:20", TimeFormatter.formatTime(20));
	}

	@Test
	public void oneMinute() throws Exception {
		assertEquals("01:00", TimeFormatter.formatTime(60));
	}

	@Test
	public void oneMinuteAndThirtySeconds() throws Exception {
		assertEquals("01:30", TimeFormatter.formatTime(90));
	}

	@Test
	public void twentyFiveMinutes() throws Exception {
		assertEquals("25:00", TimeFormatter.formatTime(1500));
	}

	@Test
	public void twentyFourMinutesAndFiftyNineSeconds() throws Exception {
		assertEquals("24:59", TimeFormatter.formatTime(1499));
	}
}
