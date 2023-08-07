package GUI.Panels;

import java.awt.Dimension;

import javax.swing.JTextField;

import FileManagers.ImageLoader;
import GUI.MainGuiElements.Button;
import GUI.MainGuiElements.Panel;
import Handlers.ActionHandler;
import Main.Constants;

public class FirstStorage extends Panel{
	private boolean submit;
	private JTextField textField;
	public FirstStorage(Dimension dimension) {
		super(dimension);
		setSize(dimension);
		setLocation(0, 0);
		setBackgroundImage(ImageLoader.getImageByFilename("Backgrounds/Splash.png"));
		
		textField = new JTextField();
		textField.setSize(500, 100);
		textField.setLocation(getWidth()/2-textField.getWidth()/2, getHeight()/2-textField.getHeight()/2);
		textField.setFont(Constants.Fonts.getFont(textField.getHeight()));
		add(textField);
		
		Button button = new Button(500, 100, "Submit", null, null, false);
		button.setLocation(getWidth()/2-textField.getWidth()/2, getHeight()/2-textField.getHeight()/2 + textField.getHeight());
		button.setFont(Constants.Fonts.getFont(textField.getHeight()));
		button.addActionListener(new ActionHandler() {
			@Override
			public void buttonAction() {
				super.buttonAction();
				submit = true;
			}
		});
		add(button);
		
	}
	
	public boolean getSubmit() {
		return submit;
	}
	public String getTextFieldText() {
		return textField.getText();
	}
	
}
