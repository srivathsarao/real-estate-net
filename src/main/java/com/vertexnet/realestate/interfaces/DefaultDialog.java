package com.vertexnet.realestate.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;

public abstract class DefaultDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final javax.swing.KeyStroke escapeStroke = javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
	public final String dispatchWindowClosingActionMapKey = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

	public DefaultDialog() {
		installEscapeCloseOperation(this);
	}

	public void installEscapeCloseOperation(final JDialog dialog) {
		Action dispatchClosing = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {
				dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
			}
		};
		JRootPane root = dialog.getRootPane();
		root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke, dispatchWindowClosingActionMapKey);
		root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing);
	}
	
	public abstract void setupDialog();
}
