package GUI.Buttons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import Constants.Constants;
import FileManagers.ImageLoader;
import GUI.MainGuiElements.Button;
import Handlers.MouseHandler;
import Inventory.InventoryObject;

public class InventoryButton extends Button {
	Image tmp;
	BufferedImage dimg;
	Graphics2D g2d;
	private InventoryObject containingObject;

	public InventoryButton(int width, int height, String text, InventoryObject containingObject) {
		super(width, height, text,
				ImageLoader.resizeImage(
						ImageLoader.getImageByFullPath(
								Constants.FileConstants.PictureConstants.INVENTORY + "Inventory_Empty.png"),
						width, height),
				ImageLoader.resizeImage(
						ImageLoader.getImageByFullPath(
								Constants.FileConstants.PictureConstants.INVENTORY + "Inventory_Empty_Hover.png"),
						width, height), false);

		addMouseListener(new MouseHandler(this));

	}
	
	public void createEmptyButton() {
		setImage(getDefaultImage());
	}

	public void createFullButton() {
		if (containingObject != null && (getImage() == getDefaultImage() || getImage() == getHoverImage())) {
			tmp = containingObject.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			dimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = dimg.createGraphics();
			g2d.drawImage(getDefaultImage(), 0, 0, null);
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();

			setDefaultImage(dimg);

			dimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = dimg.createGraphics();
			g2d.drawImage(getHoverImage(), 0, 0, null);
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();

			setHoverImage(dimg);

			setImage(getDefaultImage());
		}
	}

	public InventoryObject getContainingObject() {
		return containingObject;
	}

	public void setContainingObject(InventoryObject containingObject) {
		this.containingObject = containingObject;
	}

}
