package MonkeyIsland_New_OK.InventoryClasses;

import MonkeyIsland_New_OK.ImageLoader;

public class Key extends Inventory_Object{

	public Key(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("Key");
		setDescription("This key opens a lock to a very special surpise...");
		setImage(ImageLoader.getImageByFilename("TestSachen/Schlüssel.png"));
		setImageInventory(getImage());
	}
	
}
