package Inventory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class InventoryObject {

	private String name, description;
	private BufferedImage image;
	private Rectangle rectangle;
	private static int id;
	private int personalId;

	public InventoryObject(Point location, Dimension dimension) {
		rectangle = new Rectangle(location, dimension);
		id++;
		personalId = id;
		System.out.println(personalId);
	}
	
	public static int getId() {
		return id;
	}
	public int getPersonalId() {
		return personalId;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void draw(Graphics g) {
		g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
	}

}
