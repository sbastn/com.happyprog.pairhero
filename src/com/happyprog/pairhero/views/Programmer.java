package com.happyprog.pairhero.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class Programmer {

	private Label name;
	private Label role;

	public Programmer(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(createLayout());

		name = new Label(group, SWT.NONE);
		name.setText("Press start to add Player");

		role = new Label(group, SWT.NONE);
		role.setText("Current role will be filled in whenever you press start!");
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
		role.setText("driving");
	}

	public void observe() {
		role.setText("observing");
	}

	public void setName(String playerName) {
		name.setText(playerName);
	}

	public void switchRole() {
	}

}
