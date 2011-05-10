package com.happyprog.pairhero.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.happyprog.pairhero.Activator;
import com.happyprog.pairhero.actions.StartAction;
import com.happyprog.pairhero.actions.StopAction;
import com.happyprog.pairhero.game.Game;
import com.happyprog.pairhero.game.Programmer;
import com.happyprog.pairhero.subscribers.JUnitSubscriber;
import com.happyprog.pairhero.subscribers.RefactoringSubscriber;
import com.happyprog.pairhero.time.TimeFormatter;
import com.happyprog.pairhero.time.Timer;

public class MainView extends ViewPart {

	public static final String ID = "com.happyprog.pairhero.views.MainView";

	private Label timerLabel;
	private Label scoreLabel;
	private Label messageLabel;
	private int messageDelayCounter;

	private Programmer leftProgrammer;
	private Programmer rightProgrammer;

	private Composite parent;

	private StartAction startButton;

	private Game game;

	private StopAction stopButton;

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		parent.setLayout(createLayout());
		createStartButton();
		leftProgrammer = new Programmer(parent);
		rightProgrammer = new Programmer(parent);
		createScoreboard(parent);
		createMessageArea(parent);

		parent.layout();
	}

	private void createMessageArea(Composite parent) {
		Composite group = new Composite(parent, SWT.NONE);
		group.setLayout(new GridLayout());

		messageLabel = new Label(group, SWT.NONE);
		messageLabel.setImage(Activator.getDefault().getImageFromKey("blank"));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		messageLabel.setLayoutData(gridData);
	}

	private void createScoreboard(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);

		new Label(group, SWT.NONE).setText("Score:");
		scoreLabel = new Label(group, SWT.NONE);
		scoreLabel.setText("0   ");

		new Label(group, SWT.NONE).setText("Time left:");
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

		startButton = new StartAction(this);
		stopButton = new StopAction(this);
		stopButton.setEnabled(false);

		toolbarManager.add(startButton);
		toolbarManager.add(stopButton);
	}

	public void onStart() {
		if (ableToCreatePlayers()) {
			startGame();
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}
		parent.layout();
	}

	private void startGame() {
		game = new Game(this, new Timer(), leftProgrammer, rightProgrammer, new JUnitSubscriber(),
				new RefactoringSubscriber());
		game.start();
	}

	private boolean ableToCreatePlayers() {
		StartDialog dialog = new StartDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();

		if (dialog.getReturnCode() == Dialog.OK) {
			leftProgrammer.resetStats();
			rightProgrammer.resetStats();
			leftProgrammer.setName(dialog.getPlayerOneName());
			rightProgrammer.setName(dialog.getPlayerTwoName());
			leftProgrammer.setAvatar(dialog.getPlayerOneAvatar());
			rightProgrammer.setAvatar(dialog.getPlayerTwoAvatar());

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
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
	}

	public void onTimeChange(int timeInSeconds) {
		updateScore(timerLabel, TimeFormatter.formatTime(timeInSeconds));
		updateMessageToDefault();
	}

	private void updateScore(final Label label, final String text) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				label.setText(text);
			}
		});
	}

	public void onStop() {
		boolean response = MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				"Pair Hero", "Are you sure you want to stop this session?");

		if (response) {
			game.stop();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
		}
	}

	public void onSwitchRole(int score, int multiplier) {
		showMessageAndUpdateScore(getSwitchRoleImage(multiplier), score);
	}

	public void onRefactoring(int score) {
		showMessageAndUpdateScore("refactoring", score);
	}

	public void onGreenTest(int score) {
		showMessageAndUpdateScore("green", score);
	}

	private void showMessageAndUpdateScore(String imageKey, int score) {
		updateMessage(messageLabel, Activator.getDefault().getImageFromKey(imageKey));
		updateScore(score);
		messageDelayCounter = 3;
	}

	private void updateScore(int score) {
		updateScore(scoreLabel, String.format("%d", score));
	}

	private void updateMessageToDefault() {
		if (messageDelayCounter < 0) {
			updateMessage(messageLabel, Activator.getDefault().getImageFromKey("blank"));
		}
		messageDelayCounter--;
	}

	private void updateMessage(final Label label, final Image image) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				label.setImage(image);
			}
		});
	}

	private String getSwitchRoleImage(int multiplier) {
		if (multiplier == Game._4X_MULTIPLIER) {
			return "switch-4x";
		} else if (multiplier == Game._2_MULTIPLIER) {
			return "switch-2x";
		} else {
			return "switch";
		}
	}
}