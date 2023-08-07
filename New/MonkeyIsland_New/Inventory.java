package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import MonkeyIsland_New.InventoryClasses.Inventory_Object;
import MonkeyIsland_New.InventoryClasses.LOL;
import MonkeyIsland_New.InventoryClasses.Test;

public class Inventory {
	private JLabel[] labels = new JLabel[9];
	private Inventory_Object[] objectsInInventory = new Inventory_Object[labels.length];
	private ArrayList<Inventory_Object> objectsInWorld = new ArrayList<>();
	private ObjectInfo info;
	private ArrayList<ParticleEffects> particleEffects = new ArrayList<>();
	private int width = 200, height = 200, startX, startY, rows = 3;
	private boolean added, mouseClicked;
	private int selected = 0;
	private char mode;
	private String fileName;

	public Inventory(int frameWidth, int frameHeight, char mode, String fileName) {
		this.mode = mode;
		this.fileName = fileName;
		startX = frameWidth / 2 - (getWidth() * getRows()) / 2;
		startY = frameHeight / 2 - (getHeight() * (getLabelsSize() / getRows())) / 2;
		
		info = new ObjectInfo(frameWidth, frameHeight);
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
			labels[i].setSize(width, height);
			labels[i].setIcon(new ImageIcon(ImageLoader.getImage("Inventory/Inventory_Empty.png", width, height)));
			labels[i].setLocation(startX + labels[i].getWidth() * (i % rows), startY);
			if ((i + 1) % rows == 0)
				startY += labels[i].getHeight();
			if (mode == 'k') {
				labels[i].addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						for (int j = 0; j < labels.length; j++) {
							if (e.getSource() == labels[j]) {
								labels[j].setIcon(new ImageIcon(
										ImageLoader.getImage("Inventory/Inventory_Empty.png", width, height)));
								repaintInventory();
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						for (int j = 0; j < labels.length; j++) {
							if (e.getSource() == labels[j]) {
								labels[j].setIcon(new ImageIcon(
										ImageLoader.getImage("Inventory/Inventory_Empty_Hover.png", width, height)));
								selected = j;
								repaintInventory();
							}
						}
					}

					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
			}
		}
		loadObjects(fileName);
	}
	
	
	public void loadObjects(String fileName) {
		objectsInInventory = Storage_Class.getInventoryObjects(fileName, objectsInInventory.length);
		objectsInWorld.add(new Test(100, 100, 60, 60, 1));
		objectsInWorld.add(new LOL(200, 100, 60, 60, 2));
		int objectsInWorldSize = objectsInWorld.size()-1;
		for (int i = objectsInWorldSize; i >= 0; i--) {
			for (int j = 0; j < objectsInInventory.length; j++) {
				if(objectsInInventory[j] != null) {
					if(objectsInWorld.get(i).getID() == objectsInInventory[j].getID()) {
						objectsInWorld.remove(i);
						break;
					}
				}
			}
		}
		for (int i = 0; i < objectsInWorld.size(); i++) {
			particleEffects.add(new ParticleEffects(10, objectsInWorld.get(i).getPosX() + objectsInWorld.get(i).getWidth()/2,
					objectsInWorld.get(i).getPosY()+ objectsInWorld.get(i).getHeight()/2, 4, -4, 4, -4, 10, 0, 0, 100, 6, 3, 211, 128, 211, 128,
					211, 128, "rect", true));
		}
	}
	
	public void addToInventory(Inventory_Object object, int pos) {
		objectsInInventory[pos] = object;
	}

	public void addToLabel(JLabel label) {
		if (!added) {
			for (int i = 0; i < labels.length; i++) {
				label.add(labels[i]);
			}
			repaintInventory();

//			--------------VERSION 1.1--------------
//			label.add(info);
		}
		added = true;
	}

	public void removeFromLabel(JLabel label) {
		if (added) {
			for (int i = 0; i < labels.length; i++) {
				label.remove(labels[i]);
			}
//			--------------VERSION 1.1--------------
//			label.remove(info);
		}
		added = false;
	}

	public void repaintImages() {
		if (mode == 'c') {
			for (int i = 0; i < labels.length; i++) {
				labels[i].setIcon(new ImageIcon(ImageLoader.getImage("Inventory/Inventory_Empty.png", width, height)));
			}
			labels[selected]
					.setIcon(new ImageIcon(ImageLoader.getImage("Inventory/Inventory_Empty_Hover.png", width, height)));
		}
	}

	private void repaintInventory() {
		for (int i = 0; i < labels.length; i++) {
			ImageIcon labelImage = (ImageIcon) labels[i].getIcon();
			Image image = labelImage.getImage();
			BufferedImage buffered = (BufferedImage) image;

			Graphics2D g2d = buffered.createGraphics();
			try {
				if (objectsInInventory[i].getImage() != null) {
					g2d.drawImage(objectsInInventory[i].getImage(),
							(int) (labels[i].getWidth() / 2 - labels[i].getWidth() * (3f / 4f) / 2),
							(int) (labels[i].getHeight() / 2 - labels[i].getHeight() * (3f / 4f) / 2),
							(int) (labels[i].getWidth() * (3f / 4f)), (int) (labels[i].getHeight() * (3f / 4f)), null);
				}
			} catch (NullPointerException e) {}
			g2d.dispose();
			info.repaintText();
			labels[i].setIcon(new ImageIcon(buffered));
		}
	}
	public void repaintObjectsInWorld() {
		for (int i = 0; i < objectsInWorld.size(); i++) {
			if(MouseInfo.getPointerInfo().getLocation().x > objectsInWorld.get(i).getPosX() &&
				MouseInfo.getPointerInfo().getLocation().x < objectsInWorld.get(i).getPosX() + objectsInWorld.get(i).getWidth() &&
				MouseInfo.getPointerInfo().getLocation().y > objectsInWorld.get(i).getPosY() &&
				MouseInfo.getPointerInfo().getLocation().y < objectsInWorld.get(i).getPosY() + objectsInWorld.get(i).getHeight() &&
				mouseClicked) {
				for (int j = 0; j < labels.length; j++) {
					if(objectsInInventory[j] == null) {
						objectsInInventory[j] = objectsInWorld.get(i);
						objectsInWorld.remove(i);
						particleEffects.remove(i);
						break;
					}
				}
				repaintInventory();
			}
		}

	}
	
	public ArrayList<ParticleEffects> getParticleEffects() {
		return particleEffects;
	}

	private class ObjectInfo extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3846418742470888954L;
		private JTextArea textArea = new JTextArea();
		private FontMetrics fm;

		public ObjectInfo(int frameWidth, int frameHeight) {
			setSize((int) (startX * 0.8f), frameHeight / 2);
			setLocation(startX / 2 - getWidth() / 2, frameHeight / 2 - getHeight() / 2);
			setBackground(Color.RED);
			setOpaque(true);
			textArea.setFont(new Font("Blackadder ITC", Font.PLAIN, 25));
			textArea.setSize(getWidth(), getHeight() / 2);
			textArea.setLocation(0, getHeight() / 2 - textArea.getHeight() / 2);
			textArea.setLineWrap(true);
			textArea.setOpaque(false);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			add(textArea);
		}

		public void repaintText() {
//			--------------VERSION 1.1--------------
//			try {
//				textArea.setText(objects[selected].getDescription());				
//			} catch (Exception e) {
//				
//			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Blackadder ITC", Font.PLAIN, 50));
			fm = g.getFontMetrics();
			g.drawString(objectsInInventory[selected].getName(), getWidth() / 2 - fm.stringWidth(objectsInInventory[selected].getName()) / 2,
					fm.getHeight());
			g.drawImage(objectsInInventory[selected].getImage(),
					getWidth() / 2 - (getHeight() - (textArea.getY() + textArea.getHeight())) / 2,
					textArea.getY() + textArea.getHeight(), getHeight() - (textArea.getY() + textArea.getHeight()),
					getHeight() - (textArea.getY() + textArea.getHeight()), null);
		}
	}

	public int getLabelLength() {
		return labels.length;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return rows;
	}
	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}
	public int getLabelsSize() {
		return labels.length;
	}
	public String getFileName() {
		return fileName;
	}
	public Inventory_Object[] getObjectsInInventory() {
		return objectsInInventory;
	}
	public ArrayList<Inventory_Object> getObjectsInWorld() {
		return objectsInWorld;
	}
	public ArrayList<ParticleEffects> getParticleEffects1(){
		return particleEffects; 
	}
	public JLabel[] getLabels() {
		return labels;
	}
	
}
