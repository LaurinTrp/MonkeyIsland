package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import MonkeyIsland_New_OK.InventoryClasses.Inventory_Object;
import MonkeyIsland_New_OK.InventoryClasses.Key;
import MonkeyIsland_New_OK.InventoryClasses.Present;
import MonkeyIsland_New_OK.InventoryClasses.Banana;
import MonkeyIsland_New_OK.InventoryClasses.Beer;

public class Inventory {
	private JLabel[] labels = new JLabel[9];
	private Inventory_Object[] objectsInInventory = new Inventory_Object[labels.length];
	private Inventory_Object useObject;
	private ArrayList<Inventory_Object> objectsInWorld = new ArrayList<>();
	private ObjectInfo info;
	private ArrayList<ParticleEffects> particleEffects = new ArrayList<>();
	private int width = 200, height = 200, startX, startY, rows = 3, frameWidth, frameHeight;
	private boolean added, mouseClicked, schrankMouseClicked, schrankOffen;
	private int selected = 0;
	private char mode;
	private String fileName;

	public Inventory(int frameWidth, int frameHeight, char mode, String fileName) {
		this.mode = mode;
		this.fileName = fileName;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
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
						for (int j = 0; j < labels.length; j++) {
							if (e.getSource() == labels[j]) {
								useObject = objectsInInventory[j];
								setMouseClicked(true);
								break;
							}
						}
					}
				});
			}
		}
		loadObjects(fileName);
	}

	public void drawObjects(Graphics2D g2d) {
		for (int i = 0; i < objectsInWorld.size(); i++) {
			if (objectsInWorld.get(i) instanceof Key == false) {
				particleEffects.get(i).draw(g2d);
				g2d.drawImage(objectsInWorld.get(i).getImage(), objectsInWorld.get(i).getPosX(),
						objectsInWorld.get(i).getPosY(), objectsInWorld.get(i).getWidth(),
						objectsInWorld.get(i).getHeight(), null);
			} else if (objectsInWorld.get(i) instanceof Key && schrankOffen) {
				particleEffects.get(i).draw(g2d);
				g2d.drawImage(objectsInWorld.get(i).getImage(), objectsInWorld.get(i).getPosX(),
						objectsInWorld.get(i).getPosY(), objectsInWorld.get(i).getWidth(),
						objectsInWorld.get(i).getHeight(), null);
			}
		}
		repaintObjectsInWorld();
	}

	public void loadObjects(String fileName) {
		objectsInInventory = Storage_Class.getInventoryObjects(fileName, objectsInInventory.length);
		objectsInWorld.add(new Beer((int) (frameWidth * (1204d / 1600d)), (int) (frameHeight * (405d / 950d)), 45, 45, 1));
		objectsInWorld.add(new Banana((int) (frameWidth * (693d / 1600d)), (int) (frameHeight * (710d / 950d)), 35, 35, 2));

		objectsInWorld.add(new Key((int) (frameWidth * (360d / 1600d)), (int) (frameHeight * (537d / 950d)),
				(int) (frameWidth * ((423d - 360d) / 1600d)), (int) (frameHeight * ((575d - 537d) / 950d)), 3));

		int objectsInWorldSize = objectsInWorld.size() - 1;
		for (int i = objectsInWorldSize; i >= 0; i--) {
			for (int j = 0; j < objectsInInventory.length; j++) {
				if (objectsInInventory[j] != null) {
					if (objectsInWorld.get(i).getID() == objectsInInventory[j].getID()) {
						objectsInWorld.remove(i);
						break;
					}
				}
			}
		}
		for (int i = 0; i < objectsInWorld.size(); i++) {
			particleEffects
					.add(new ParticleEffects(10, objectsInWorld.get(i).getPosX() + objectsInWorld.get(i).getWidth() / 2,
							objectsInWorld.get(i).getPosY() + objectsInWorld.get(i).getHeight() / 2, 4, -4, 4, -4, 10,
							0, 0, 70, 9, 6, 211, 128, 211, 128, 211, 128, "rect", true));
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
			label.add(info);
		}
		added = true;
	}

	public void removeFromLabel(JLabel label) {
		if (added) {
			for (int i = 0; i < labels.length; i++) {
				label.remove(labels[i]);
			}
//			--------------VERSION 1.1--------------
			label.remove(info);
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
	
	public void resetInventoryImages() {
		for (int i = 0; i < labels.length; i++) {
			ImageIcon labelImage = (ImageIcon) labels[i].getIcon();
			Image image = labelImage.getImage();
			BufferedImage buffered = (BufferedImage) image;

			Graphics2D g2d = buffered.createGraphics();
			g2d.drawImage(ImageLoader.getImage("Inventory/Inventory_Empty.png", width, height), 0, 0, null);
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
					g2d.drawImage(objectsInInventory[i].getImageInventory(),
							(int) (labels[i].getWidth() / 2 - labels[i].getWidth() * (3f / 4f) / 2),
							(int) (labels[i].getHeight() / 2 - labels[i].getHeight() * (3f / 4f) / 2),
							(int) (labels[i].getWidth() * (3f / 4f)), (int) (labels[i].getHeight() * (3f / 4f)), null);
					
				}
			} catch (NullPointerException e) {
			}
			g2d.dispose();
			info.repaintText();
			labels[i].setIcon(new ImageIcon(buffered));
		}
	}

	public void repaintObjectsInWorld() {
		int mouseX = MouseInfo.getPointerInfo().getLocation().x;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;

		for (int i = 0; i < objectsInWorld.size(); i++) {
			if (mouseX > objectsInWorld.get(i).getPosX()
					&& mouseX < objectsInWorld.get(i).getPosX() + objectsInWorld.get(i).getWidth()
					&& mouseY > objectsInWorld.get(i).getPosY()
					&& mouseY < objectsInWorld.get(i).getPosY() + objectsInWorld.get(i).getHeight()
					&& (mouseClicked || schrankMouseClicked)) {
				for (int j = 0; j < labels.length; j++) {
					if (objectsInInventory[j] == null) {
						objectsInInventory[j] = objectsInWorld.get(i);
						objectsInWorld.remove(i);
						particleEffects.remove(i);
						break;
					}
				}

			}
		}
		removeDoubles();
	}

	public void removeDoubles() {
		for (int i = 0; i < objectsInInventory.length; i++) {
			for (int j = i + 1; j < objectsInInventory.length; j++) {
				if (objectsInInventory[i] != null && objectsInInventory[j] != null) {
					if (objectsInInventory[i].getID() == objectsInInventory[j].getID()) {
						objectsInInventory[j] = null;
					}
				}
			}
		}
		repaintInventory();
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
		private BufferedImage description = ImageLoader.getImageByFilename("Inventory/Inventarbeschreibung.png");
		private Graphics2D g2d;
		private AffineTransform transform;

		public ObjectInfo(int frameWidth, int frameHeight) {
			setSize((int) (startX), (int) (frameHeight * .7f));
			setLocation(startX / 2 - getWidth() / 2, frameHeight / 2 - getHeight() / 2);

			textArea.setFont(new Font("Blackadder ITC", Font.PLAIN, 40));
			textArea.setWrapStyleWord(true);
			textArea.setSize((int) (getWidth()/2.2), getHeight() / 2);
			textArea.setLocation(60, 450);
			textArea.setForeground(Color.WHITE);
			textArea.setLineWrap(true);
			textArea.setOpaque(false);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			add(textArea);

			transform = new AffineTransform();
			
		}

		public void repaintText() {
//			--------------VERSION 1.1--------------
			try {
				textArea.setText(objectsInInventory[selected].getDescription());
			} catch (Exception e) {
				textArea.setText("");
			}
		}
		private int imageX, imageY, imageSize = 100;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g2d = (Graphics2D) g;
			fm = g.getFontMetrics();
			try {
				g2d.drawImage(description, 0, -25, getWidth(), getHeight() + 50, null);
				g2d.setColor(Color.WHITE);
				transform.setToRotation(Math.toRadians(-30), 60, 460);
				g2d.setTransform(transform);
				g2d.setFont(new Font("Blackadder ITC", Font.PLAIN, (int) (description.getHeight() / 20f)));
				fm = g2d.getFontMetrics();

				g2d.drawString(objectsInInventory[selected].getName(), 60, 480);

				imageX = 390;
				imageY = 430;
				transform.setToRotation(Math.toRadians(10), imageX+imageSize/2, imageY+imageSize/2);
				g2d.setTransform(transform);
				g2d.drawImage(objectsInInventory[selected].getImageInventory(), imageX, imageY, imageSize, imageSize, null);

				transform.setToRotation(Math.toRadians(-30), 200, 480);
				g2d.setTransform(transform);
				
			} catch (NullPointerException e) {
			}

			repaintText();
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

	public boolean getMouseClicked() {
		return mouseClicked;
	}

	public Inventory_Object getUseObject() {
		return useObject;
	}

	public void setUseObject(Inventory_Object useObject) {
		this.useObject = useObject;
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

	public ArrayList<ParticleEffects> getParticleEffects1() {
		return particleEffects;
	}

	public JLabel[] getLabels() {
		return labels;
	}

	public void setSchrankOffen(boolean schrankOffen) {
		this.schrankOffen = schrankOffen;
	}

	public void setSchrankMouseClicked(boolean schrankMouseClicked) {
		this.schrankMouseClicked = schrankMouseClicked;
	}

}
