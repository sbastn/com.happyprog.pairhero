package com.happyprog.pairhero.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pairhero.views.MainView;

public class StopActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StopAction action = new StopAction(view);

		action.run();

		verify(view).onStop();
	}

	@Test
	public void avoidingMousefeedCrashBySettingActionText() throws Exception {
		StopAction action = new StopAction(null);
		assertEquals("Stop", action.getText());
	}
}
