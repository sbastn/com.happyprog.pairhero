package com.happyprog.pairhero.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pairhero.Activator;
import com.happyprog.pairhero.views.MainView;

public class StopAction extends Action {

	private final MainView view;

	public StopAction(MainView view) {
		this.view = view;
		setText("Stop");
		setImageDescriptor(Activator.getImageDescriptor("icons/stop.gif"));
	}

	@Override
	public void run() {
		view.onStop();
	}

}
