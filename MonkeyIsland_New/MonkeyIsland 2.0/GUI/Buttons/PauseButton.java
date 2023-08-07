package GUI.Buttons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Constants.Constants;
import FileManagers.FileGet;
import FileManagers.ImageLoader;
import GUI.MainGuiElements.Button;
import GUI.MainGuiElements.Label;
import Handlers.MouseHandler;

public class PauseButton extends Button {

	private int buttonAction, counter = 0, currentImageIndex = 0;
	private final int refreshRate = 10;
	private ArrayList<BufferedImage> images = new ArrayList<>();
	private boolean animationStart;

	public PauseButton(int width, int height, String text, int action, String folder) {
		super(width, height, text, null, null, false);
		this.buttonAction = action;

		setText("");
		setSize(width, height);

		addMouseListener(new MouseHandler(this));

		for (int i = 0; i < FileGet.getFolderCount(folder); i++) {
			images.add(ImageLoader.getImageByFullPath(FileGet.getContainingFiles(folder)[i].getAbsolutePath()));
			images.set(i, ImageLoader.resizeImage(images.get(i), width, height));
		}
		if (images.size() == 0) {
			setText(text);
		} else {
			setIcon(new ImageIcon(images.get(0)));
		}
	}

	public void refreshCurrentImage() {
		if (counter == refreshRate) {
			if (currentImageIndex < images.size() - 1) {
				currentImageIndex++;
			} else {
				if (images.size() > 1) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (buttonAction == Constants.GameConstants.STARTPANEL) {
					System.out.println("ok");
					((Label) getParent().getParent()).setMode(Constants.GameConstants.SAVE);
				}
				((Label) getParent().getParent()).setMode(buttonAction);
				animationStart = false;
				currentImageIndex = 0;
			}
			counter %= refreshRate;
		}
		counter += 1;
		setIcon(new ImageIcon(images.get(currentImageIndex)));

	}

	public int getButtonAction() {
		return buttonAction;
	}

	public void setAnimationStart(boolean animationStart) {
		this.animationStart = animationStart;
	}

	public boolean getAnimationStart() {
		return animationStart;
	}

	public void setCurrentImageIndex(int currentImageIndex) {
		this.currentImageIndex = currentImageIndex;
	}

}
