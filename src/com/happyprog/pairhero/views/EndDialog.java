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

public class EndDialog extends Dialog {

	private final String message;

	public EndDialog(Shell parentShell, String message) {
		super(parentShell);
		this.message = message;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		RowLayout layout = new RowLayout();
		layout.type = SWT.VERTICAL;
		composite.setLayout(layout);

		createLogo(composite);
		createLabel(composite);
		createButton(composite);

		return composite;
	}

	private void createLogo(Composite composite) {
		new Label(composite, SWT.NONE).setImage(Activator.getDefault().getImageFromKey("times-up"));
	}

	private void createLabel(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(message);
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
