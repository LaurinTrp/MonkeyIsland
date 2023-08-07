package MonkeyIsland_New;

import java.awt.image.BufferedImage;

public class Player {

	BufferedImage playerStandLeft;
	BufferedImage playerStandRight;
	BufferedImage[] playerImagesLeft = new BufferedImage[6];
	BufferedImage[] playerImagesRight = new BufferedImage[6];
	BufferedImage[] skills = new BufferedImage[9];
	
	int width, height;
	float velX;
	final float vel_y = 1.5f;
	final int imageSwitch = 15;
	
	public Player() {
		
		playerStandLeft = ImageLoader.getImageByFilename("Movement/Guybrush_Left.png");
		playerStandRight = ImageLoader.getImageByFilename("Movement/Guybrush_Right.png");
		
		for (int i = 0; i < playerImagesLeft.length; i++) {
			playerImagesLeft[i] = ImageLoader.getImageByFilename("Movement/Guybrush_Left_" + i +".png");
		}
		for (int i = 0; i < playerImagesRight.length; i++) {
			playerImagesRight[i] = ImageLoader.getImageByFilename("Movement/Guybrush_Right_" + i +".png");
		}
		//___________________
		for (int i = 0; i < playerImagesRight.length; i++) {
			skills[i] = ImageLoader.getImageByFilename("Movement/Guybrush_Right_" + i +".png");
		}
	}
	
	public void setFrameSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public float getVelX() {
		if(width > 1200) {
			this.velX = width * (10f/1920f);
		}else {
			this.velX = width * (5f/1920f);
		}
		return velX;
	}
	
}
