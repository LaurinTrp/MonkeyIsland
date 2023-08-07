package MonkeyIsland_New.InventoryClasses;

import java.awt.image.BufferedImage;


public abstract class Inventory_Object {
	private String name = "";
	private String description = "";
	private int posX, posY, width, height, ID;
	private BufferedImage image = null;
	
	public Inventory_Object(int x, int y, int width, int height, int ID) {
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.ID = ID;
	}

	public void setLocation(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public BufferedImage getImage() {
		return image;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getID() {
		return ID;
	}
}
