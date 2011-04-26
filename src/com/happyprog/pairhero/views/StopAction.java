package com.happyprog.pairhero.views;

import org.eclipse.jface.action.Action;

import com.happyprog.pairhero.Activator;

public class StopAction extends Action {

	private final MainView view;

	public StopAction(MainView view) {
		this.view = view;
		setImageDescriptor(Activator.getImageDescriptor("icons/stop.gif"));
	}

	@Override
	public void run() {
		view.onStop();
	}

}
