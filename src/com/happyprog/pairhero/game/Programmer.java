package com.happyprog.pairhero.game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import com.happyprog.pairhero.Activator;
import com.happyprog.pairhero.time.TimeFormatter;

public class Programmer {

	private Label nameLabel;
	private Label roleLabel;
	private Label timeAtKeyboardLabel;
	private Label avatar;

	private Role currentRole;
	private int timeAtKeyboard;

	enum Role {
		Driving, Observing
	}

	public Programmer(Composite parent) {
		initializeUIControls(parent);
	}

	void initializeUIControls(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER_SOLID);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("Press start to add Player");
		GridData nameGridData = new GridData(GridData.FILL_BOTH);
		nameGridData.horizontalSpan = 2;
		nameLabel.setLayoutData(nameGridData);

		avatar = new Label(composite, SWT.NONE);
		avatar.setImage(Activator.getDefault().getImageFromKey("no-avatar"));
		GridData avatarGridData = new GridData(GridData.FILL_BOTH);
		avatarGridData.horizontalSpan = 2;
		avatar.setLayoutData(avatarGridData);

		roleLabel = new Label(composite, SWT.NONE);
		roleLabel.setImage(Activator.getImageDescriptor("icons/red-keyboard.png").createImage());

		timeAtKeyboardLabel = new Label(composite, SWT.NONE);
		timeAtKeyboardLabel.setText("00:00");
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
				if (role.equals(Role.Driving)) {
					roleLabel.setImage(Activator.getImageDescriptor("icons/green-keyboard.png").createImage());
					nameLabel.setFont(new Font(PlatformUI.getWorkbench().getDisplay(), "Arial", 12, SWT.BOLD));
				} else {
					roleLabel.setImage(Activator.getImageDescriptor("icons/red-keyboard.png").createImage());
					nameLabel.setFont(new Font(PlatformUI.getWorkbench().getDisplay(), "Arial", 12, SWT.NORMAL));
				}
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

	public void setAvatar(final String avatarImage) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				avatar.setImage(Activator.getDefault().getImageFromKey(avatarImage));
			}
		});
	}

}
