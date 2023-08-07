package MonkeyIsland_New;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MonkeyIsland_New.InventoryClasses.Inventory_Object;

public class Player_Controller extends Player{

	private int width, height, image_counter, frame_counter;
	private double posX, posY, posZ, image_width, image_height;
	private boolean border_left, border_right, end_left, end_right;
	private char direction = 'r';
	private int skill;

	private boolean[] button_Bools;
	private float[] axis_Floats;
	
	private Pause pause;
	
	private ArrayList<String> storageContent = new ArrayList<>();
	private int inventory;

	public Player_Controller(int width, int height, Pause pause) {
		this.width = width;
		this.height = height;
		this.pause = pause;
		this.posX = width / 2;
		if(width > 1200) {
			super.velX = width * (10f/1920f);
		}else {
			super.velX = width * (5f/1920f);
		}
		this.image_height = height * 0.3f;
		this.image_width = this.image_height * (208f / 618f);
	}

	private BufferedImage currentImage() {
		BufferedImage image = null;
		BufferedImage[] siteImages = null;
		if(Speechbar.getActive()) {
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
		if (axis_Floats[0] != 0) {
			if(axis_Floats[0] < 0) siteImages = super.playerImagesLeft;
			if(axis_Floats[0] > 0) siteImages = super.playerImagesRight;
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
				break;
			}
		}
		return image;
	}

	public void draw(Graphics g) {
		g.drawImage(currentImage(), (int) posX, (int) posY, (int) image_width, (int) image_height, null);

		speak();

		this.image_width = this.image_height * (86f / 240f);
		if(!Speechbar.getActive()) {
			movement();
		}
	}
	
	public void load() {
		try {
			posX = Float.parseFloat(storageContent.get(0).split(" ")[2]);
			image_width = Float.parseFloat(storageContent.get(1));
			image_height = Float.parseFloat(storageContent.get(2));
			direction = storageContent.get(3).charAt(0);			
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	public void save(String fileName, Inventory_Object[] inventoryObjects) {
		Storage_Class.save(fileName, posX, posY, image_width, image_height, direction, inventoryObjects);
	}

	
	private void speak() {
		if(pause.getExplosion()) {
			Speechbar.setText("Guybrush", Storage_Class.getFileContent("Guybrush", 't').get(0));
		}
	}

	private void movement() {
		if (posX > width * 0.15f || end_left) {
			border_left = false;
		} else {
			border_left = true;
		}
		if (border_left && axis_Floats[0] == 0) {
			posX += 1f;
		}
		if (posX + image_width < width - width * 0.15f || end_right) {
			border_right = false;
		} else {
			border_right = true;
		}
		if (border_right && axis_Floats[0] == 0) {
			posX -= 1f;
		}
		if (axis_Floats[0] != 0 && !border_left && !border_right && posX > 0 && posX + image_width < width) {
			posX += axis_Floats[0] * super.velX - (posZ * 0.000001f);
			if (axis_Floats[0] > 0)
				direction = 'r';
			if (axis_Floats[0] < 0)
				direction = 'l';
		}
		if (axis_Floats[1] < 0 && this.image_height > height * 0.15f) {
			this.image_height += vel_y * axis_Floats[1];
			posZ--;
		}
		if (axis_Floats[1] > 0 && this.image_height < height * 0.6f) {
			this.image_height += vel_y * axis_Floats[1];
			posZ++;
		}

		if (posX + image_width >= width)
			posX = width - image_width - 1;
		if (posX <= 0)
			posX = 1;
	}

	public void update(boolean[] button_Bools, float[] axis_Floats, boolean end_left, boolean end_right) {
		this.button_Bools = button_Bools;
		this.axis_Floats = axis_Floats;
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
		return super.velX * axis_Floats[0];
	}
	
	public void setSkill(int skill) {
		this.skill = skill;
	}
	
	public void setStorageContent(ArrayList<String> storageContent) {
		this.storageContent = storageContent;
	}

	public void setInventory(int selected) {
		this.inventory = selected;
	}
}
