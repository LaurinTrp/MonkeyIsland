package Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import GUI.MainGuiElements.Button;

public class ActionHandler implements ActionListener {
	private JTextField textField;
	private Button button;

	public ActionHandler(JTextField textField) {
		this.textField = textField;
	}
	public ActionHandler(Button button) {
		this.button = button;
	}
	public ActionHandler() {
	}
	
	public void buttonAction() {
		
	}
	
	public Button getButton() {
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonAction();
	}

}