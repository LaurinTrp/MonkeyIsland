package MonkeyIsland_New.InventoryClasses;

import MonkeyIsland_New.ImageLoader;

public class Test extends Inventory_Object {


	public Test(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		setName("Test");
		setDescription(
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		setImage(ImageLoader.getImageByFilename("Cursor_Beer.png"));
	}

}
