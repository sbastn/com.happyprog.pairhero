package com.happyprog.pairhero.actions;

import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pairhero.actions.StartAction;
import com.happyprog.pairhero.views.MainView;

public class StartActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StartAction action = new StartAction(view);

		action.run();

		verify(view).onStart();
	}

}
