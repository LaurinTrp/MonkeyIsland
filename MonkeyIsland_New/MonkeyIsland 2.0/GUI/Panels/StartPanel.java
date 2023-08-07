package GUI.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Constants.Constants;
import FileManagers.FileGet;
import FileManagers.ImageLoader;
import GUI.Buttons.AppearingButton;
import GUI.MainGuiElements.Panel;

public class StartPanel extends Panel {

	private ArrayList<BufferedImage> images = new ArrayList<>();
	private BufferedImage currentImage;
	private int currentImageIndex = 0, counter = 0;
	private final int refreshRate = 1;

	private AppearingButton buttonLabelTitle, buttonStart, buttonSettings, buttonExit;
	private Dimension dimension;

	public StartPanel(Dimension dimension) {
		super(dimension);
		this.dimension = dimension;

		setSize(dimension);
		setLocation(0, 0);

		String fileName = Constants.FileConstants.PACKAGEPATH + "Pictures\\StartAnimation";
		for (int i = 0; i < FileGet.getFolderCount(fileName); i++) {
			images.add(ImageLoader.getImageByFullPath(fileName + "\\StartMenuAnimation" + (i + 1) + ".png"));
		}
		initComponents();
	}

	private void initComponents() {

		buttonLabelTitle = new AppearingButton(dimension.height / 6, "MONKEY ISLAND", Constants.GameConstants.STARTPANEL, true);
		buttonLabelTitle.centerHorizontal(dimension.width);
		buttonLabelTitle.setLocation(buttonLabelTitle.getX(), 10);
		buttonLabelTitle.setSize(buttonLabelTitle.getWidth(), buttonLabelTitle.getHeight() + 20);
//		buttonLabelTitle.setEnabled(false);
		add(buttonLabelTitle);
		
		buttonStart = new AppearingButton(dimension.height / 6, "Start", Constants.GameConstants.GAMEPANEL);
		buttonStart.centerHorizontal(dimension.width);
		buttonStart.setLocation(buttonStart.getX(), dimension.height / 2 - buttonStart.getHeight() / 2 - 150);
		add(buttonStart);

		buttonSettings = new AppearingButton(dimension.height / 6, "Settings", Constants.GameConstants.SETTINGSPANEL);
		buttonSettings.centerHorizontal(dimension.width);
		buttonSettings.setLocation(buttonSettings.getX(), dimension.height / 2 - buttonSettings.getHeight() / 2);
		add(buttonSettings);

		buttonExit = new AppearingButton(dimension.height / 6, "Exit", Constants.GameConstants.EXITGAME);
		buttonExit.centerHorizontal(dimension.width);
		buttonExit.setLocation(buttonExit.getX(), dimension.height / 2 - buttonExit.getHeight() / 2 + 150);
		add(buttonExit);
	}

	public void resetColors() {
		if (buttonStart.getAlpha() == 255) {
			buttonLabelTitle.setForeground(Color.BLACK);
			buttonStart.setForeground(Color.BLACK);
			buttonSettings.setForeground(Color.BLACK);
			buttonExit.setForeground(Color.BLACK);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (currentImageIndex < images.size() - 1) {
			refreshCurrentImage();
		} else {
			buttonLabelTitle.startAnimation();
			buttonStart.startAnimation();
			buttonSettings.startAnimation();
			buttonExit.startAnimation();
		}
	}

	private void refreshCurrentImage() {
		if (counter == refreshRate) {
			if (currentImageIndex < images.size() - 1) {
				currentImageIndex++;
			}
			counter %= refreshRate;
		}
		counter += 1;
		setBackgroundImage(images.get(currentImageIndex));
	}
}
