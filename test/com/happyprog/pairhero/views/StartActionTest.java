package com.happyprog.pairhero.views;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class StartActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StartAction action = new StartAction(view);

		action.run();

		verify(view).onStart();
	}

}
