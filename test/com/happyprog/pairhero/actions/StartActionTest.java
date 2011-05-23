package com.happyprog.pairhero.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pairhero.views.MainView;

public class StartActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StartAction action = new StartAction(view);

		action.run();

		verify(view).onStart();
	}

	@Test
	public void avodingMousefeedCrashBySettingActionText() throws Exception {
		StartAction action = new StartAction(null);
		assertEquals("Start", action.getText());
	}

}
