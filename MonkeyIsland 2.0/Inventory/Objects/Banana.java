package Inventory.Objects;

import java.awt.Dimension;
import java.awt.Point;

import FileManagers.ImageLoader;
import Inventory.InventoryObject;

public class Banana extends InventoryObject{

	public Banana(Point location, Dimension dimension) {
		super(location, dimension);
		setName("Banana");
		setImage(ImageLoader.getImageByFilename("Cursors/Cursor_Banana.png"));
		setDescription("LOL");
	}

}
