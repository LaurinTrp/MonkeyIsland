package MonkeyIsland_New_OK.InventoryClasses;

import MonkeyIsland_New_OK.ImageLoader;

public class Present extends Inventory_Object{

	public Present(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("Present");
		setDescription("Was hat das im Spiel zu suchen? Das soll doch Paul haben.");
		setImage(ImageLoader.getImageByFilename("Chest/Present.jpeg"));
		setImageInventory(ImageLoader.getImageByFilename("Chest/Present.png"));
	}
	
}
