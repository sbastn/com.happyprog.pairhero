package com.happyprog.pairhero.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.happyprog.pairhero.Activator;

public class StartDialog extends Dialog {

	private String playerOneName;
	private String playerTwoName;
	private Text playerTwoText;
	private Text playerOneText;
	private Button[] playerOneFaces;

	protected StartDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		GridData data = new GridData(GridData.FILL_BOTH);

		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("logo"));

		Label player1Label = new Label(composite, SWT.NONE);
		player1Label.setText("Player 1:");
		player1Label.setLayoutData(data);

		playerOneText = new Text(composite, SWT.BORDER);
		playerOneText.setLayoutData(data);

		// playerOneFaces = new Button[4];
		// playerOneFaces[0] = new Button(composite, SWT.RADIO);
		// playerOneFaces[0].setImage(Activator.getImageDescriptor("icons/punk-girl.png").createImage());
		// playerOneFaces[0].setLayoutData(data);
		//
		// playerOneFaces[1] = new Button(composite, SWT.RADIO);
		// playerOneFaces[1].setImage(Activator.getImageDescriptor("icons/hippy-dude.png").createImage());
		// playerOneFaces[1].setLayoutData(data);
		//
		// playerOneFaces[2] = new Button(composite, SWT.RADIO);
		// playerOneFaces[2].setImage(Activator.getImageDescriptor("icons/goth.png").createImage());
		// playerOneFaces[2].setLayoutData(data);
		//
		// playerOneFaces[3] = new Button(composite, SWT.RADIO);
		// playerOneFaces[3].setImage(Activator.getImageDescriptor("icons/logo.png").createImage());
		// playerOneFaces[3].setLayoutData(data);

		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("div-bar"));

		Label player2Label = new Label(composite, SWT.NONE);
		player2Label.setText("Player 2:");

		playerTwoText = new Text(composite, SWT.BORDER);
		playerTwoText.setLayoutData(data);

		composite.setSize(600, 600);

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
