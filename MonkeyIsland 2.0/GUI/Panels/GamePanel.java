package GUI.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import FileManagers.FileStorage;
import FileManagers.ImageLoader;
import GUI.MainGuiElements.Panel;
import GUI.Panels.Overlays.InventoryOverlay;
import GUI.Panels.Overlays.SkillsOverlay;
import Inventory.InventoryObject;
import Inventory.Objects.Banana;
import Inventory.Objects.Key;
import Main.Constants;
import People.Guybrush;

public class GamePanel extends Panel {
	private BufferedImage barrols, ladder;
	private Guybrush guybrush;
	private BufferedImage[] backgroundImages = new BufferedImage[2];
	private static int backgroundIndex = 0;

	private Font font;
	private FontMetrics fm;

	private ArrayList<InventoryObject> inventoryObjectsWorld = new ArrayList<>();
	private InventoryObject key, banana;

	public GamePanel(Dimension dimension, String filePath) {
		super(dimension);
		setSize(dimension);
		setLocation(0, 0);

		backgroundImages[0] = ImageLoader.getImageByFilename("Backgrounds/Background-Schrank_geschlossen.jpg");
		backgroundImages[1] = ImageLoader.getImageByFilename("Backgrounds/Background-Schrank_geöffnet.jpg");

		barrols = ImageLoader.getImageByFilename("Backgrounds/Barrols.png");
		ladder = ImageLoader.getImageByFilename("Backgrounds/Ladder.png");
		guybrush = new Guybrush(dimension);
		
		addObjects(filePath);
		
		font = Constants.Fonts.getFont(120);
	}
	
	
	public void addObjects(String filePath) {
		key = new Key(new Point(100, 100), new Dimension(50, 50));
		inventoryObjectsWorld.add(key);
		banana = new Banana(new Point(200, 100), new Dimension(50, 50));
		inventoryObjectsWorld.add(banana);

		
		try {
			String[] ids = FileStorage.loadStorage(filePath);
			for (int i = 0; i < ids.length; i++) {
				for (int j = 0; j < inventoryObjectsWorld.size(); j++) {
					if(Integer.parseInt(ids[i]) == inventoryObjectsWorld.get(j).getPersonalId()) {
						InventoryOverlay.getInventoryObjectsInventory().add(inventoryObjectsWorld.get(j));
					}
				}
			}
			inventoryObjectsWorld.removeAll(InventoryOverlay.getInventoryObjectsInventory());
		} catch (NullPointerException e) {
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackgroundImage(backgroundImages[backgroundIndex]);
		setGameBackgroundImage(getBackgroundImage());

		guybrush.draw(g);
		
		g.drawImage(barrols, (int) (getWidth() - (getWidth() * (barrols.getWidth() / 1600d))),
				(int) (getHeight() - (getHeight() * (barrols.getHeight() / 950d))),
				(int) (getWidth() * (barrols.getWidth() / 1600d)), (int) (getHeight() * (barrols.getHeight() / 950d)),
				null);

		g.drawImage(ladder, 0, (int) (getHeight() - (getHeight() * (ladder.getHeight() / 950d))),
				(int) (getWidth() * (ladder.getWidth() / 1600d)), (int) (getHeight() * (ladder.getHeight() / 950d)),
				null);

		g.setColor(new Color(200, 0, 0));
		g.setFont(font);
		fm = g.getFontMetrics();
		String string = "Current Skill: " + SkillsOverlay.getCurrentSkillString();
		g.drawString(string, getWidth() - fm.stringWidth(string), getHeight() - 10);

		drawObjects(g);
		
	}

	private void drawObjects(Graphics g) {
		for (InventoryObject inventoryObject : inventoryObjectsWorld) {
			if (!(inventoryObject instanceof Key)) {
				inventoryObject.draw(g);
			} else {
				if (backgroundIndex == 1) {
					inventoryObject.draw(g);
				}
			}
		}
	}

	public void setKey(int key) {
		guybrush.setKey(key);
	}

	public static void setBackgroundIndex(int backgroundIndex) {
		GamePanel.backgroundIndex = backgroundIndex;
	}

	public static int getBackgroundIndex() {
		return backgroundIndex;
	}

	public Guybrush getGuybrush() {
		return guybrush;
	}

	public ArrayList<InventoryObject> getInventoryObjectsWorld() {
		return inventoryObjectsWorld;
	}

}
