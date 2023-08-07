package GUI.MainGuiElements;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {

	private BufferedImage defaultImage, hoverImage;

	public Button(int width, int height, String text, BufferedImage defaultImage, BufferedImage hoverImage, boolean transparent) {
		this.defaultImage = defaultImage;
		this.hoverImage = hoverImage;
		
		
		if (defaultImage != null) {
			setIcon(new ImageIcon(defaultImage));
		}

		setSize(width, height);
		setText(text);

		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFocusable(false);
		setBorder(null);
		setFont(new Font("Blackadder ITC", Font.PLAIN, getHeight()));
		if(transparent) {
			setBackground(new Color(0, 0, 0, 0));
		}
	}

	public void buttonAction() {
	}
	
	public void centerHorizontal(int screenWidth) {
		setLocation(screenWidth / 2 - getWidth() / 2, getY());
	}

	public void centerVertical(int screenHeight) {
		setLocation(getX(), screenHeight / 2 - getHeight() / 2);
	}

	protected void setHoverIcon(BufferedImage image) {
		hoverImage = image;
	}

	protected BufferedImage getDefaultImage() {
		return defaultImage;
	}

	protected void setDefaultImage(BufferedImage image) {
		this.defaultImage = image;
	}

	protected BufferedImage getHoverImage() {
		return hoverImage;
	}

	protected void setHoverImage(BufferedImage image) {
		this.hoverImage = image;
	}

	protected BufferedImage getImage() {
		Image imageIcon = ((ImageIcon) getIcon()).getImage();
		BufferedImage image = (BufferedImage) imageIcon;
		return image;
	}

	protected void setImage(BufferedImage image) {
		setIcon(new ImageIcon(image));
	}

	public void setHovered(boolean hovered) {
		if (hovered) {
			if (hoverImage != null) {
				setIcon(new ImageIcon(hoverImage));
			}
			setBackground(new Color(100, 100, 100));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
			if (defaultImage != null) {
				setIcon(new ImageIcon(defaultImage));
			}
			setBackground(Color.BLACK);
			setCursor(Cursor.getDefaultCursor());
		}
	}

}
