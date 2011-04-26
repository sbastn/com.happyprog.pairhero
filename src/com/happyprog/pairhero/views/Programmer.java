package com.happyprog.pairhero.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class Programmer {

	private Label name;
	private Label roleLabel;

	private Role currentRole;

	enum Role {
		Driving, Observing
	}

	public Programmer(Composite parent) {
		initializeUIControls(parent);
	}

	void initializeUIControls(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());

		name = new Label(group, SWT.NONE);
		name.setText("Press start to add Player");

		roleLabel = new Label(group, SWT.NONE);
		roleLabel.setText("Current role will be filled in whenever you press start!");
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
		name.setText(playerName);
	}

	public void switchRole() {
		if (currentRole.equals(Role.Driving)) {
			observe();
		} else {
			drive();
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

}
