package com.happyprog.pairhero.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class StartDialog extends Dialog {

	private String playerOneName;
	private String playerTwoName;
	private Text playerTwoText;
	private Text playerOneText;

	protected StartDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.FILL_BOTH);

		Label player1Label = new Label(composite, SWT.NONE);
		player1Label.setText("Player 1:");
		player1Label.setLayoutData(data);

		playerOneText = new Text(composite, SWT.BORDER);
		playerOneText.setLayoutData(data);

		Label player2Label = new Label(composite, SWT.NONE);
		player2Label.setText("Player 2:");

		playerTwoText = new Text(composite, SWT.BORDER);
		playerTwoText.setLayoutData(data);

		return composite;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			playerOneName = playerOneText.getText();
			playerTwoName = playerTwoText.getText();
		}

		super.buttonPressed(buttonId);
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

}
