package MonkeyIsland_New_OK.InventoryClasses;

import MonkeyIsland_New_OK.ImageLoader;

public class Banana extends Inventory_Object{


	public Banana(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("Banana");
		setDescription("This is a banana... Yummy");
		setImage(ImageLoader.getImageByFilename("Cursor_Banana.png"));
		setImageInventory(getImage());
	}
	
}
