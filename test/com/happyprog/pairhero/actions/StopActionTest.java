package com.happyprog.pairhero.actions;

import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pairhero.actions.StopAction;
import com.happyprog.pairhero.views.MainView;

public class StopActionTest {

	@Test
	public void onClick_notifiesView() throws Exception {
		MainView view = mock(MainView.class);
		StopAction action = new StopAction(view);

		action.run();

		verify(view).onStop();
	}
}
