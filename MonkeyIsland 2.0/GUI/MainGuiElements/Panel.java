package GUI.MainGuiElements;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class Panel extends JPanel {

	private BufferedImage backgroundImage;
	private static BufferedImage gameBackgroundImage;
	public static float panelsCounter;

	public Panel(Dimension dimension) {
		setSize(dimension);
		setLocation(0, 0);
		setLayout(null);

		panelsCounter++;
	}

	protected static void setGameBackgroundImage(BufferedImage gameBackgroundImage) {
		Panel.gameBackgroundImage = gameBackgroundImage;
	}

	protected static BufferedImage getGameBackgroundImage() {
		return gameBackgroundImage;
	}

	protected void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}

}
