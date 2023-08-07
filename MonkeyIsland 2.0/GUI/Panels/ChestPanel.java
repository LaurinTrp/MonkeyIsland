package GUI.Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import FileManagers.ImageLoader;
import GUI.MainGuiElements.Panel;

public class ChestPanel extends Panel {

	public static boolean chestOpen = false;

	public ChestPanel(Dimension dimension) {
		super(dimension);

		refreshBackgroundImage(dimension, false);
	}

	private void refreshBackgroundImage(Dimension dimension, boolean open) {
		BufferedImage chestBackground = ImageLoader.getImageByFilename("Backgrounds/Chest/ChestBackground.png");
		BufferedImage chestImage = open ? ImageLoader.getImageByFilename("Backgrounds/Chest/ChestOpend.png")
				: ImageLoader.getImageByFilename("Backgrounds/Chest/ChestClosed.png");
		setBackgroundImage(chestImage);

		BufferedImage backImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = backImage.createGraphics();
		g2d.drawImage(chestBackground, 0, 0, dimension.width, dimension.height, null);
		g2d.drawImage(getBackgroundImage(), 0, 0, dimension.width, dimension.height, null);

		setBackgroundImage(backImage);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), null);
	}

	public void setChestOpen(boolean chestOpen) {
		this.chestOpen = chestOpen;
		refreshBackgroundImage(getSize(), chestOpen);
	}

	public boolean getChestOpen() {
		return chestOpen;
	}

}
