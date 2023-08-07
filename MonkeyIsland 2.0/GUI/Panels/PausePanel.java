package GUI.Panels;

import java.awt.Dimension;
import java.awt.Graphics;

import FileManagers.ImageLoader;
import GUI.Buttons.PauseButton;
import GUI.MainGuiElements.Panel;
import Main.Constants;

public class PausePanel extends Panel {

	private PauseButton[] buttons = new PauseButton[3];
	private final int buttonSize = 200;

	public PausePanel(Dimension dimension) {
		super(dimension);
		setBackgroundImage(ImageLoader.getImageByFilename("Backgrounds/Background-Schrank_geschlossen.jpg"));

		initComponents();
	}

	private void initComponents() {
		PauseButton buttonHome = new PauseButton(buttonSize, buttonSize, "Home", Constants.GameConstants.STARTPANEL,
				Constants.FileConstants.PictureConstants.HOMEPACKAGE);
		buttonHome.centerHorizontal(getWidth());
		buttonHome.centerVertical(getHeight());
		buttonHome.setLocation(buttonHome.getX() - 250, buttonHome.getY());
		add(buttonHome);

		PauseButton buttonSave = new PauseButton(buttonSize, buttonSize, "Save", Constants.GameConstants.SAVE,
				Constants.FileConstants.PictureConstants.SAVEPACKAGE);
		buttonSave.centerHorizontal(getWidth());
		buttonSave.centerVertical(getHeight());
		add(buttonSave);

		PauseButton buttonContinue = new PauseButton(buttonSize, buttonSize, "Continue",
				Constants.GameConstants.GAMEPANEL, Constants.FileConstants.PictureConstants.PLAYPACKAGE);
		buttonContinue.centerHorizontal(getWidth());
		buttonContinue.centerVertical(getHeight());
		buttonContinue.setLocation(buttonContinue.getX() + 250, buttonContinue.getY());
		add(buttonContinue);

		buttons[0] = buttonHome;
		buttons[1] = buttonSave;
		buttons[2] = buttonContinue;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getAnimationStart())
				buttons[i].refreshCurrentImage();
		}
	}
}
