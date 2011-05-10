package com.happyprog.pairhero.game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
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

	private static final Color DRIVER_COLOR = new Color(PlatformUI.getWorkbench().getDisplay(), 58, 170, 53);
	private static final Color OBSERVER_COLOR = new Color(PlatformUI.getWorkbench().getDisplay(), 218, 218, 218);
	private Composite composite;

	enum Role {
		Driving, Observing
	}

	public Programmer(Composite parent) {
		initializeUIControls(parent);
	}

	void initializeUIControls(Composite parent) {
		composite = new Composite(parent, SWT.BORDER_SOLID);
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
					composite.setBackground(DRIVER_COLOR);
				} else {
					roleLabel.setImage(Activator.getImageDescriptor("icons/red-keyboard.png").createImage());
					composite.setBackground(OBSERVER_COLOR);
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
