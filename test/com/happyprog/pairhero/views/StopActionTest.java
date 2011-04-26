package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class StopActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StopAction action = new StopAction(view);

		action.run();

		verify(view).onStop();
	}
}
