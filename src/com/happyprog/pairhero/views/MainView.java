package com.happyprog.pairhero.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {

	public static final String ID = "com.happyprog.pairhero.views.MainView";

	@Override
	public void createPartControl(Composite parent) {
		createStartButton();

	}

	private void createStartButton() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(new Action() {
			@Override
			public void run() {
				onStart();
			}

		});
	}

	private void onStart() {
		new Game(new Timer(), new EndDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}