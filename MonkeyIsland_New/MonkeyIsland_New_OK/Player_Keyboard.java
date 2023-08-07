package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MonkeyIsland_New_OK.InventoryClasses.Inventory_Object;

public class Player_Keyboard extends Player {
	private Rectangle feetRect = new Rectangle();
	private ArrayList<Rectangle> collisionRects = new ArrayList<>();
	private int width, height, image_counter, frame_counter;
	private float posX, posY, posZ, image_width, image_height;
	private boolean keyW, keyA, keyS, keyD, border_left, border_right, end_left, end_right, schrankOffen;
	private char direction = 'r';
	private String currentSkill = "";
	private Pause pause;

	private ArrayList<String> storageContent = new ArrayList<>();

	public Player_Keyboard(int width, int height, Pause pause) {
		this.width = width;
		this.height = height;
		this.pause = pause;
		this.posX = width / 2;
		this.image_height = height * 0.3f;
		this.image_width = this.image_height * (208f / 618f);

		super.setFrameSize(width, height);

	}

	private BufferedImage currentImage() {
		BufferedImage image = null;
		BufferedImage[] siteImages = null;
		if (Speechbar.getActive()) {
			switch (direction) {
			case 'l':
				image = super.playerStandLeft;
				break;
			case 'r':
				image = super.playerStandRight;
				break;

			default:
				break;
			}
			return image;
		}
		if (keyA && keyD) {
			image = super.playerStandLeft;
		} else if (keyA || keyD) {
			if (keyA)
				siteImages = super.playerImagesLeft;
			if (keyD)
				siteImages = super.playerImagesRight;
			frame_counter++;
			image_counter %= siteImages.length;
			if (frame_counter % super.imageSwitch / (width / 1000f) == 0) {
				image_counter++;
				frame_counter = 0;
			}
			image = siteImages[image_counter % siteImages.length];
		} else {
			switch (direction) {
			case 'l':
				image = super.playerStandLeft;
				break;
			case 'r':
				image = super.playerStandRight;
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + direction);
			}
		}
		return image;
	}

	private void load(ArrayList<String> storageContent) {
		posX = Float.parseFloat(storageContent.get(0));
		posY = Float.parseFloat(storageContent.get(1));
		image_width = Float.parseFloat(storageContent.get(2));
		image_height = Float.parseFloat(storageContent.get(3));
		direction = storageContent.get(4).charAt(0);
		currentSkill = storageContent.get(5);
		if(currentSkill.equals("Use")) {
			currentSkill = "Take";
		}
		schrankOffen = Boolean.parseBoolean(storageContent.get(6));
	}

	public void save(String fileName, String skill, Inventory_Object[] inventoryObjects) {
		Storage_Class.save(fileName, posX, posY, image_width, image_height, direction, skill, schrankOffen, inventoryObjects);
	}

	public void draw(Graphics g) {
		g.drawImage(currentImage(), (int) posX, (int) posY, (int) image_width, (int) image_height, null);
		speak();
		this.image_width = this.image_height * (237f / 615f);
		if (!Speechbar.getActive()) {
			movement(g);
		}
		feetRect.setBounds((int) posX, (int) (posY + image_height * (580d / 618d)), (int) image_width,
				(int) (image_height - (image_height * (580d / 618d))));
	}

	private void speak() {
		if (pause.getExplosion()) {
			Speechbar.setText("Guybrush", Storage_Class.getFileContent("Guybrush", 't').get(0));
		}
	}

	private void movement(Graphics g) {
		Rectangle closestRectangle = new Rectangle();
		int closestRectangleMiddle = 1000;
		int currentRectangleMiddle = 0;
		for (Rectangle rectangle : collisionRects) {
			currentRectangleMiddle = (int) Math.sqrt(Math.pow(rectangle.getCenterX() - feetRect.getCenterX(), 2)
					+ Math.pow(rectangle.getCenterY() - feetRect.getCenterY(), 2));
			if (currentRectangleMiddle < closestRectangleMiddle) {
				closestRectangle = rectangle;
				closestRectangleMiddle = currentRectangleMiddle;
			}
		}
		Rectangle futureFeetRect = new Rectangle(feetRect.x, feetRect.y, feetRect.width, feetRect.height);
		if(keyW) {
			futureFeetRect.setLocation(feetRect.x, feetRect.y-10);
		}
		if(keyA) {
			futureFeetRect.setLocation(feetRect.x - 10, feetRect.y);
		}
		if(keyS) {
			futureFeetRect.setLocation(feetRect.x, feetRect.y + 10);
		}
		if(keyD) {
			futureFeetRect.setLocation(feetRect.x + 20, feetRect.y);
		}
		
//		g.setColor(Color.GREEN);
//		g.fillRect(futureFeetRect.x, futureFeetRect.y, futureFeetRect.width, futureFeetRect.height);
//		g.setColor(Color.ORANGE);
//		g.fillRect(closestRectangle.x, closestRectangle.y, closestRectangle.width, closestRectangle.height);
		
		if (!futureFeetRect.intersects(closestRectangle)) {

//		if (posX > width * 0.15f || end_left) {
			if (keyA && posX > 0)
				posX -= super.getVelX() - posZ * 0.000001f;
//			border_left = false;
//		} else {
//			border_left = true;
//		}
			if (border_left && !keyA) {
				posX += 1f;
			}
//		if (posX + image_width < width - width * 0.15f || end_right) {
			if (keyD && posX + image_width < width)
				posX += super.getVelX() - posZ * 0.000001f;
//			border_right = false;
//		} else {
//			border_right = true;
//		}
			if (border_right && !keyD) {
				posX -= 1f;
			}

			if (this.keyW && this.image_height > height * (450d / 1080d)) {
				this.image_height -= vel_y;
				posZ++;
			}
			if (this.keyS && this.image_height < height * 0.6f) {
				this.image_height += vel_y;
				posZ--;
			}
			if (keyA)
				direction = 'l';
			if (keyD)
				direction = 'r';
		}
	}

	public void update(boolean keyW, boolean keyA, boolean keyS, boolean keyD, boolean end_left, boolean end_right) {
		this.keyW = keyW;
		this.keyA = keyA;
		this.keyS = keyS;
		this.keyD = keyD;
		this.end_left = end_left;
		this.end_right = end_right;
	}

	public boolean getBorder_left() {
		return border_left;
	}

	public boolean getBorder_right() {
		return border_right;
	}

	public float getVel_x() {
		return super.getVelX();
	}

	public BufferedImage[] getSkillsImages() {
		return super.skills;
	}

	public Rectangle getFeetRect() {
		return feetRect;
	}
	
	public String getCurrentSkill() {
		return currentSkill;
	}

	public void setStorageContent(ArrayList<String> storageContent) {
		this.storageContent = storageContent;
		load(this.storageContent);
	}

	public ArrayList<Rectangle> getCollisionRects() {
		return collisionRects;
	}
	
	public void setSchrankOffen(boolean schrankOffen) {
		this.schrankOffen = schrankOffen;
	}
	public boolean getSchrankOffen() {
		return schrankOffen;
	}

}
