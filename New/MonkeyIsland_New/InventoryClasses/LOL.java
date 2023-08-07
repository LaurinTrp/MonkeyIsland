package MonkeyIsland_New.InventoryClasses;

import MonkeyIsland_New.ImageLoader;

public class LOL extends Inventory_Object{


	public LOL(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("LOL");
		setDescription("LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL");
		setImage(ImageLoader.getImageByFilename("Cursor_Banana.png"));
	}
	
}
