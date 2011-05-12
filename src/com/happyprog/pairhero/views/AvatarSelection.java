package com.happyprog.pairhero.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.happyprog.pairhero.Activator;

public class AvatarSelection {

	private Button explorator;
	private Button king;
	private Button robin;
	private Button wizard;

	public AvatarSelection(Composite composite) {
		buildUI(composite);
	}

	protected void buildUI(Composite composite) {
		Composite group = new Composite(composite, SWT.NONE);
		group.setLayout(new RowLayout());

		explorator = new Button(group, SWT.RADIO);
		explorator.setImage(Activator.getDefault().getImageFromKey("explorator"));

		king = new Button(group, SWT.RADIO);
		king.setImage(Activator.getDefault().getImageFromKey("king"));

		robin = new Button(group, SWT.RADIO);
		robin.setImage(Activator.getDefault().getImageFromKey("robin"));

		wizard = new Button(group, SWT.RADIO);
		wizard.setImage(Activator.getDefault().getImageFromKey("wizard"));
	}

	public String getSelection() {
		if (explorator.getSelection()) {
			return "explorator";
		}

		if (king.getSelection()) {
			return "king";
		}

		if (robin.getSelection()) {
			return "robin";
		}

		if (wizard.getSelection()) {
			return "wizard";
		}

		return "no-avatar";
	}

}
