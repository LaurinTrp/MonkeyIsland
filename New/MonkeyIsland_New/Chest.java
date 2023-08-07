package MonkeyIsland_New;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MonkeyIsland_New.InventoryClasses.Inventory_Object;
import MonkeyIsland_New.InventoryClasses.LOL;
import MonkeyIsland_New.InventoryClasses.Test;

public class Chest {

	private BufferedImage image;
	private Inventory inventory;
	private Inventory_Object[] objectsInInventory;
	private ArrayList<Inventory_Object> objectsInChest = new ArrayList<>();
	private ArrayList<ParticleEffects> particles = new ArrayList<>();
	private boolean mouseClick;

	public Chest(int width, int height, Inventory inventory) {
		image = ImageLoader.getImage("TestSachen/Truhe.png", width, height);
		this.objectsInInventory = inventory.getObjectsInInventory();
		this.inventory = inventory;
		loadObjects(inventory.getFileName());
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		for (ParticleEffects particleEffects : particles) {
			particleEffects.draw(g2d);
		}
		for (Inventory_Object inventory_Object : objectsInChest) {
			g2d.drawImage(inventory_Object.getImage(), inventory_Object.getPosX(), inventory_Object.getPosY(),
					inventory_Object.getWidth(), inventory_Object.getHeight(), null);
		}
		checkMouseClick();
	}

	public void loadObjects(String fileName) {
		objectsInInventory = Storage_Class.getInventoryObjects(fileName, objectsInInventory.length);
		objectsInChest.add(new Test(100, 100, 60, 60, 11));
		objectsInChest.add(new LOL(20, 500, 60, 60, 12));
		int objectsInWorldSize = objectsInChest.size() - 1;
		for (int i = objectsInWorldSize; i >= 0; i--) {
			for (int j = 0; j < objectsInInventory.length; j++) {
				if (objectsInInventory[j] != null) {
					if (objectsInChest.get(i).getID() == objectsInInventory[j].getID()) {
						objectsInChest.remove(i);
						break;
					}
				}
			}
		}
		for (int i = 0; i < objectsInChest.size(); i++) {
			particles.add(new ParticleEffects(10, objectsInChest.get(i).getPosX() + objectsInChest.get(i).getWidth() / 2,
							objectsInChest.get(i).getPosY() + objectsInChest.get(i).getHeight() / 2, 4, -4, 4, -4, 10,
							0, 0, 100, 6, 3, 211, 128, 211, 128, 211, 128, "rect", true));
		}
	}
	
	public void setMouseClick(boolean mouseClick) {
		this.mouseClick = mouseClick;
	}
	
	private void checkMouseClick() {
		for (int i = 0; i < objectsInChest.size(); i++) {
			if(MouseInfo.getPointerInfo().getLocation().x > objectsInChest.get(i).getPosX() &&
				MouseInfo.getPointerInfo().getLocation().x < objectsInChest.get(i).getPosX() + objectsInChest.get(i).getWidth() &&
				MouseInfo.getPointerInfo().getLocation().y > objectsInChest.get(i).getPosY() &&
				MouseInfo.getPointerInfo().getLocation().y < objectsInChest.get(i).getPosY() + objectsInChest.get(i).getHeight() &&
				mouseClick) {
				for (int j = 0; j < inventory.getLabels().length; j++) {
					if(inventory.getObjectsInInventory()[j] == null) {
						inventory.getObjectsInInventory()[j] = objectsInChest.get(i);
						objectsInChest.remove(i);
						particles.remove(i);
						break;
					}
				}
			}
		}
	}

}
