package com.happyprog.pairhero.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class Programmer {

	private Label nameLabel;
	private Label roleLabel;
	private Label timeAtKeyboardLabel;

	private Role currentRole;
	private int timeAtKeyboard;

	enum Role {
		Driving, Observing
	}

	public Programmer(Composite parent) {
		initializeUIControls(parent);
	}

	void initializeUIControls(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());

		nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText("Press start to add Player");

		roleLabel = new Label(group, SWT.NONE);
		roleLabel.setText("Current role will be filled in whenever you press start!");

		timeAtKeyboardLabel = new Label(group, SWT.NONE);
		timeAtKeyboardLabel.setText("00:00");
	}

	private RowLayout createLayout() {
		RowLayout layout = new RowLayout();
		layout.wrap = true;
		layout.pack = true;
		layout.justify = false;
		layout.type = SWT.VERTICAL;
		return layout;
	}

	public void drive() {
		currentRole = Role.Driving;
		updateRole(currentRole);
	}

	public void observe() {
		currentRole = Role.Observing;
		updateRole(currentRole);
	}

	public void setName(String playerName) {
		nameLabel.setText(playerName);
	}

	public void switchRole() {
		if (currentRole.equals(Role.Driving)) {
			observe();
		} else {
			drive();
		}
	}

	public void onTimeChange() {
		if (Role.Driving.equals(currentRole)) {
			timeAtKeyboard++;
			updateTimeAtKeyboard(timeAtKeyboard);
		}
	}

	void updateRole(final Role role) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				roleLabel.setText(role.name());
			}
		});
	}

	void updateTimeAtKeyboard(final int seconds) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				timeAtKeyboardLabel.setText(TimeFormatter.formatTime(seconds));
			}
		});
	}

	public void resetStats() {
		timeAtKeyboard = 0;
		updateTimeAtKeyboard(timeAtKeyboard);
	}

}
