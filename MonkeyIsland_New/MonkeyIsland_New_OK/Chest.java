package MonkeyIsland_New_OK;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MonkeyIsland_New_OK.InventoryClasses.Inventory_Object;
import MonkeyIsland_New_OK.InventoryClasses.Key;
import MonkeyIsland_New_OK.InventoryClasses.Present;
import MonkeyIsland_New_OK.InventoryClasses.Banana;
import MonkeyIsland_New_OK.InventoryClasses.Beer;

public class Chest {

	private BufferedImage hintergrund, image, chestCloseImage, chestOpenImage;
	private Inventory inventory;
	private Inventory_Object[] objectsInInventory;
	private Inventory_Object useObject;
	private ArrayList<Inventory_Object> objectsInChest = new ArrayList<>();
	private ArrayList<ParticleEffects> particles = new ArrayList<>();
	private boolean mouseClick, chestOpen;
	private String currentSkill;

	private int width, height;

	public Chest(int width, int height, Inventory inventory) {
		hintergrund = ImageLoader.getImage("Chest/Truhen_Hintergrund.png", width, height);
		chestCloseImage = ImageLoader.getImage("Chest/Truhe_Geschlossen.png", width, height);
		chestOpenImage = ImageLoader.getImage("Chest/Truhe.png", width, height);
		image = chestCloseImage;
		this.objectsInInventory = inventory.getObjectsInInventory();
		this.inventory = inventory;
		this.width = width;
		this.height = height;
		loadObjects(inventory.getFileName());

	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(hintergrund, 0, 0, hintergrund.getWidth(), hintergrund.getHeight(), null);
		g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		if (chestOpen) {
			for (ParticleEffects particleEffects : particles) {
				particleEffects.draw(g2d);
			}
			for (Inventory_Object inventory_Object : objectsInChest) {
				g2d.drawImage(inventory_Object.getImage(), inventory_Object.getPosX(), inventory_Object.getPosY(),
						inventory_Object.getWidth(), inventory_Object.getHeight(), null);
			}
		}
		checkMouseClick();

	}

	public void loadObjects(String fileName) {
		objectsInInventory = Storage_Class.getInventoryObjects(fileName, objectsInInventory.length);
//		objectsInChest.add(new Beer(100, 100, 60, 60, 11));
//		objectsInChest.add(new Banana(20, 500, 60, 60, 12));
		objectsInChest.add(new Present((int) (width * (713d / 1600d)), (int) (height * (557d / 950d)),
				(int) (width * ((1015d - 713d) / 1600d)), (int) (height * ((778d - 557d) / 950d)), 13));
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
			if (objectsInChest.get(i) instanceof Present) {
				particles.add(
						new ParticleEffects(10, objectsInChest.get(i).getPosX() + objectsInChest.get(i).getWidth() / 2,
								objectsInChest.get(i).getPosY() + objectsInChest.get(i).getHeight() / 2, 12, -12, 12, -12,
								10, 0, 0, 100, 3, 1, 211, 128, 211, 128, 211, 128, "rect", true));
			} else {
				particles.add(
						new ParticleEffects(10, objectsInChest.get(i).getPosX() + objectsInChest.get(i).getWidth() / 2,
								objectsInChest.get(i).getPosY() + objectsInChest.get(i).getHeight() / 2, 4, -4, 4, -4,
								10, 0, 0, 100, 6, 3, 211, 128, 211, 128, 211, 128, "rect", true));
			}
		}
	}

	public void setMouseClick(boolean mouseClick) {
		this.mouseClick = mouseClick;
	}

	private void checkMouseClick() {
		if (chestOpen) {
			for (int i = 0; i < objectsInChest.size(); i++) {
				if (MouseInfo.getPointerInfo().getLocation().x > objectsInChest.get(i).getPosX()
						&& MouseInfo.getPointerInfo().getLocation().x < objectsInChest.get(i).getPosX()
								+ objectsInChest.get(i).getWidth()
						&& MouseInfo.getPointerInfo().getLocation().y > objectsInChest.get(i).getPosY()
						&& MouseInfo.getPointerInfo().getLocation().y < objectsInChest.get(i).getPosY()
								+ objectsInChest.get(i).getHeight()
						&& mouseClick && currentSkill.equals("Take")) {
					for (int j = 0; j < inventory.getLabels().length; j++) {
						if (inventory.getObjectsInInventory()[j] == null) {
							inventory.getObjectsInInventory()[j] = objectsInChest.get(i);
							objectsInChest.remove(i);
							particles.remove(i);
							break;
						}
					}
				}
			}
		}
		if (useObject instanceof Key && currentSkill.equals("Use")) {
			image = chestOpenImage;
			chestOpen = true;
		}
	}

	public void setUseObject(Inventory_Object useObject) {
		this.useObject = useObject;
	}

	public void setCurrentSkill(String currentSkill) {
		this.currentSkill = currentSkill;
	}

}
