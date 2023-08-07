package MonkeyIsland_New_OK.InventoryClasses;

import MonkeyIsland_New_OK.ImageLoader;

public class Beer extends Inventory_Object {


	public Beer(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("Beer");
		setDescription("CHEERS! A good beer saves the day");
		setImage(ImageLoader.getImageByFilename("Cursor_Beer.png"));
		setImageInventory(getImage());
	}

}
