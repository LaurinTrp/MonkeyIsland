package Inventory.Objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import FileManagers.ImageLoader;
import Inventory.InventoryObject;
import Main.Constants;

public class Key extends InventoryObject {

	private final String name = "Key";
	private final String description = "";
	private final BufferedImage image = ImageLoader
			.getImageByFullPath(Constants.FileConstants.PictureConstants.INVENTORYOBJECTS + "Key.png");

	public Key(Point loaction, Dimension dimension) {
		super(loaction, dimension);
		setName(name);
		setDescription(description);
		setImage(image);
	}
}
