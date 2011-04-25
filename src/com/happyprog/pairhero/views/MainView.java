package com.happyprog.pairhero.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {

	public static final String ID = "com.happyprog.pairhero.views.MainView";
	private Label timerLabel;
	private Label playerOneLabel;
	private Label playerTwoLabel;

	@Override
	public void createPartControl(Composite parent) {
		createStartButton();
		createPlayerOneGroup(parent);
		createPlayerTwoGroup(parent);
		createTimerGroup(parent);

		parent.layout();
	}

	private void createTimerGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());
		timerLabel = new Label(group, SWT.NONE);
		timerLabel.setText(formatTime(Timer._25_MINS));
	}

	private void createPlayerOneGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());
		playerOneLabel = new Label(group, SWT.NONE);
		playerOneLabel.setText("Press start to add Player 1");
	}

	private void createPlayerTwoGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());
		playerTwoLabel = new Label(group, SWT.NONE);
		playerTwoLabel.setText("Press start to add Player 2");
	}

	private RowLayout createLayout() {
		RowLayout layout = new RowLayout();
		layout.wrap = true;
		layout.pack = true;
		layout.justify = false;
		layout.type = SWT.VERTICAL;
		return layout;
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
		collectPlayerNames();
		startGame();
	}

	private void startGame() {
		Game game = new Game(this, new Timer());
		game.start();
	}

	private void collectPlayerNames() {
		StartDialog dialog = new StartDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();

		playerOneLabel.setText(dialog.getPlayerOneName());
		playerTwoLabel.setText(dialog.getPlayerTwoName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void onGameFinished(String message) {
		EndDialog dialog = new EndDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), message);
		dialog.open();
	}

	public void onTimeChange(int timeInSeconds) {
		updateInfo(timerLabel, formatTime(timeInSeconds));
	}

	private String formatTime(int timeInSeconds) {
		int minutes = timeInSeconds / 60;
		int seconds = timeInSeconds % 60;

		return String.format("%s:%s", withZeroes(minutes), withZeroes(seconds));
	}

	private String withZeroes(int time) {
		if (time < 10) {
			return String.format("0%d", time);
		}
		return String.format("%d", time);
	}

	private void updateInfo(final Label label, final String text) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				label.setText(text);
			}
		});
	}

}