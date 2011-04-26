package com.happyprog.pairhero.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
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
	private Label scoreLabel;

	private Programmer leftProgrammer;
	private Programmer rightProgrammer;

	private Composite parent;

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		createStartButton();
		createProgrammers(parent);
		createScoreboard(parent);

		parent.layout();
	}

	private void createProgrammers(Composite parent) {
		leftProgrammer = new Programmer(parent);
		rightProgrammer = new Programmer(parent);
	}

	private void createScoreboard(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());

		scoreLabel = new Label(group, SWT.NONE);
		scoreLabel.setText("000000");

		timerLabel = new Label(group, SWT.NONE);
		timerLabel.setText(TimeFormatter.formatTime(Timer._25_MINS));
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
		if (ableToCreatePlayers()) {
			startGame();
		}
		parent.layout();
	}

	private void startGame() {
		Game game = new Game(this, new Timer(), leftProgrammer, rightProgrammer, new JUnitSubscriber(),
				new RefactoringSubscriber());
		game.start();
	}

	private boolean ableToCreatePlayers() {
		StartDialog dialog = new StartDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();

		if (dialog.getReturnCode() == Dialog.OK) {
			leftProgrammer.setName(dialog.getPlayerOneName());
			rightProgrammer.setName(dialog.getPlayerTwoName());

			return true;
		}

		return false;
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
		updateInfo(timerLabel, TimeFormatter.formatTime(timeInSeconds));
	}

	private void updateInfo(final Label label, final String text) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				label.setText(text);
			}
		});
	}

	public void updateScore(int score) {
		updateInfo(scoreLabel, String.format("%d", score));
	}

}