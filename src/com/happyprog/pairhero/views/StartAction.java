package com.happyprog.pairhero.views;

import org.eclipse.jface.action.Action;

import com.happyprog.pairhero.Activator;

public class StartAction extends Action {

	private final MainView view;

	public StartAction(MainView view) {
		this.view = view;
		setImageDescriptor(Activator.getImageDescriptor("icons/start.gif"));
	}

	@Override
	public void run() {
		view.onStart();
	}

}
