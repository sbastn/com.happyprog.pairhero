package com.happyprog.pairhero.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pairhero.Activator;
import com.happyprog.pairhero.views.MainView;

public class StartAction extends Action {

	private final MainView view;

	public StartAction(MainView view) {
		this.view = view;
		setText("Start");
		setImageDescriptor(Activator.getImageDescriptor("icons/start.gif"));
	}

	@Override
	public void run() {
		view.onStart();
	}

}
