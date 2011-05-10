package com.happyprog.pairhero.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.happyprog.pairhero.Activator;

public class StartDialog extends Dialog {

	private String playerOneName;
	private String playerTwoName;
	private String playerOneAvatar;
	private String playerTwoAvatar;

	private Text playerTwoText;
	private Text playerOneText;

	private AvatarSelection playerOneAvatarSelection;
	private AvatarSelection playerTwoAvatarSelection;

	private static RowData textDataLayout = new RowData(200, 15);

	protected StartDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		RowLayout layout = new RowLayout();
		layout.type = SWT.VERTICAL;
		composite.setLayout(layout);

		showLogo(composite);

		showPlayerOneControls(composite);

		showBarSeparation(composite);

		showPlayerTwoControls(composite);

		return composite;
	}

	private void showLogo(Composite composite) {
		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("logo"));
	}

	private void showPlayerOneControls(Composite composite) {
		Label player1Label = new Label(composite, SWT.NONE);
		player1Label.setText("Player 1:");

		playerOneText = new Text(composite, SWT.BORDER);
		playerOneText.setLayoutData(textDataLayout);

		playerOneAvatarSelection = new AvatarSelection(composite);
	}

	private void showPlayerTwoControls(Composite composite) {
		Label player2Label = new Label(composite, SWT.NONE);
		player2Label.setText("Player 2:");

		playerTwoText = new Text(composite, SWT.BORDER);
		playerTwoText.setLayoutData(textDataLayout);

		playerTwoAvatarSelection = new AvatarSelection(composite);
	}

	private void showBarSeparation(Composite composite) {
		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("div-bar"));
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			playerOneName = playerOneText.getText();
			playerTwoName = playerTwoText.getText();
			playerOneAvatar = playerOneAvatarSelection.getSelection();
			playerTwoAvatar = playerTwoAvatarSelection.getSelection();
		}

		super.buttonPressed(buttonId);
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

	public String getPlayerOneAvatar() {
		return playerOneAvatar;
	}

	public String getPlayerTwoAvatar() {
		return playerTwoAvatar;
	}

}
