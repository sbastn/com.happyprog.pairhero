package com.happyprog.pairhero.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.happyprog.pairhero.Activator;
import com.happyprog.pairhero.game.Scoreboard;

public class EndDialog extends Dialog {

	private final Scoreboard scoreboard;

	public EndDialog(Shell parentShell, Scoreboard scoreboard) {
		super(parentShell);
		this.scoreboard = scoreboard;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		RowLayout layout = new RowLayout();
		layout.type = SWT.VERTICAL;
		composite.setLayout(layout);

		createLogo(composite);
		createStats(composite);
		createButton(composite);

		return composite;
	}

	private void createLogo(Composite composite) {
		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("times-up"));
	}

	private void createStats(Composite composite) {
		Composite group = new Composite(composite, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.type = SWT.VERTICAL;
		group.setLayout(layout);

		new Label(group, SWT.NONE).setText(String.format("Your score = %d", scoreboard.getScore()));

		new Label(group, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("div-bar"));

		new Label(group, SWT.NONE).setText(String.format("Passed Tests = %d", scoreboard.getNumberOfGreenTests()));
		new Label(group, SWT.NONE).setText(String.format("Refactorings = %d", scoreboard.getNumberOfRefactorings()));

		new Label(group, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("div-bar"));

		new Label(group, SWT.NONE).setText(String.format("4x Multipliers = %d", scoreboard.getNumberOf4xMultipliers()));
		new Label(group, SWT.NONE).setText(String.format("2x Multipliers = %d", scoreboard.getNumberOf2xMultipliers()));
		new Label(group, SWT.NONE).setText(String.format("1x Multipliers = %d", scoreboard.getNumberOf1xMultipliers()));
	}

	private void createButton(Composite composite) {
		Button doneButton = new Button(composite, SWT.PUSH);
		doneButton.setText("Well done!");
		doneButton.setLayoutData(new RowData(245, 40));

		doneButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				close();
			}
		});
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		// overwrite so we can add our own buttons instead of OK, Cancel
		return null;
	}
}
