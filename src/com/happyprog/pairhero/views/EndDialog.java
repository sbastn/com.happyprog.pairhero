package com.happyprog.pairhero.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class EndDialog extends Dialog {

	private final String message;

	protected EndDialog(Shell parentShell, String message) {
		super(parentShell);
		this.message = message;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		createLabel(composite);
		createButton(composite);

		return composite;
	}

	private void createLabel(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(message);
	}

	private void createButton(Composite composite) {
		Button doneButton = new Button(composite, SWT.PUSH);
		doneButton.setText("Done");

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
