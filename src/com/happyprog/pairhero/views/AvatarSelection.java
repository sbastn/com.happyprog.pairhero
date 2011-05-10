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
	private Button monster;

	public AvatarSelection(Composite composite) {
		Composite group = new Composite(composite, SWT.NONE);
		// layout.type = SWT.HORIZONTAL;
		group.setLayout(new RowLayout());

		explorator = new Button(group, SWT.RADIO);
		explorator.setImage(Activator.getDefault().getImageFromKey("explorator"));
		// explorator.setSize(500, 500);

		king = new Button(group, SWT.RADIO);
		king.setImage(Activator.getDefault().getImageFromKey("king"));

		robin = new Button(group, SWT.RADIO);
		robin.setImage(Activator.getDefault().getImageFromKey("robin"));

		monster = new Button(group, SWT.RADIO);
		monster.setImage(Activator.getDefault().getImageFromKey("monster"));
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

		if (monster.getSelection()) {
			return "monster";
		}

		return "no-avatar";
	}

}
