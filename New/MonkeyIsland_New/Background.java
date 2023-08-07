package MonkeyIsland_New;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background {
	private Player_Keyboard player_Keyboard;
	private Player_Controller player_Controller;
	
	private ArrayList<Rectangle> rectangles = new ArrayList<>();

	private BufferedImage backgroundImage, barrolsImage, ladderImage;
	private double posX = 0, posY = 0, vel_x = 0.00005f, width, height, sizeFactor, image_width, image_height;
	private boolean end_left, end_right;

	public Background(Player_Keyboard player, int width, int height) {
		backgroundImage = ImageLoader.getImageByFilename("Backgrounds/Background.jpeg");
		barrolsImage = ImageLoader.getImageByFilename("Backgrounds/Barrols.png");
		ladderImage = ImageLoader.getImageByFilename("Backgrounds/Ladder.png");
		this.player_Keyboard = player;
		this.width = width;
		this.height = height;
		this.sizeFactor = (double) backgroundImage.getHeight() / (double) this.height;
		this.image_width = backgroundImage.getWidth() * sizeFactor;
		this.image_height = backgroundImage.getHeight() * sizeFactor;
		setRectangles();
	}

	public Background(Player_Controller player, int width, int height) {
		backgroundImage = ImageLoader.getImageByFilename("Backgrounds/Background.jpeg");
		barrolsImage = ImageLoader.getImageByFilename("Backgrounds/Barrols.png");
		ladderImage = ImageLoader.getImageByFilename("Backgrounds/Ladder.png");
		this.player_Controller = player;
		this.width = width;
		this.height = height;
		this.image_height = height;
		this.image_width = height * (backgroundImage.getWidth() / backgroundImage.getHeight());
		setRectangles();
	}
	
	private void setRectangles() {
//		Kessel
		rectangles.add(new Rectangle((int)(width * (855d / 1600d)), (int)(height*(730d/950d)), (int)(width * (2d / 1600d)), 86));
		rectangles.add(new Rectangle((int)(width * ((855d + 266d) / 1600d)), (int)(height*(730d/950d)), (int)(width * (2d / 1600d)), 86));
		rectangles.add(new Rectangle((int)(width * (855d / 1600d)), (int)(height*((730d+86d)/950d)), (int)(width * (266d / 1600d)), 2));
		
//		Rechte Grenzen
		rectangles.add(new Rectangle((int)(width * (124d / 1600d)), (int)(height*(850d/950d)), (int)(width * (2d / 1600d)), 100));
		rectangles.add(new Rectangle((int)(width * (1187d / 1600d)), (int)(height*(761d/950d)), 1269-1187, (int)(height * (2d/950d))));
		
//		Linke Grenzen
		rectangles.add(new Rectangle((int)(width * (1374d / 1600d)), (int)(height * (787d/950d)), (int)(width * (2d / 1600d)), 163));
		rectangles.add(new Rectangle((int)(width * (277d / 1600d)), (int)(height*(764d/950d)), (int)(width * (2d / 1600d)), 860-764));
		rectangles.add(new Rectangle((int)(width * (435d / 1600d)), (int)(height*(737d/950d)), (int)(width * (2d / 1600d)), 834-737));
//		rectangles.add(new Rectangle((int)(width * (519d / 1600d)), (int)(height*(737d/950d)), (int)(width * (2d / 1600d)), 788-737));
		rectangles.add(new Rectangle((int)(width * (555d / 1600d)), (int)(height*(730d/950d)), (int)(width * (2d / 1600d)), 791-730));
	}
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, (int) posX, (int) posY, (int)width, (int)height, null);
//		backgroundMove();
	}

	public void drawForeground(Graphics g) {
		g.drawImage(barrolsImage, (int) (width - (width * (barrolsImage.getWidth() / 1600d))),
				(int) (height - (height * (barrolsImage.getHeight() / 950d))),
				(int) (width * (barrolsImage.getWidth() / 1600d)), (int) (height * (barrolsImage.getHeight() / 950d)),
				null);
		g.drawImage(ladderImage, 0,
				(int) (height - (height * (ladderImage.getHeight() / 950d))),
				(int) (width * (ladderImage.getWidth() / 1600d)), (int) (height * (ladderImage.getHeight() / 950d)),
				null);
	}

	private void backgroundMove() {
		if (player_Keyboard != null) {
			if (posX < 0) {
				if (player_Keyboard.getBorder_left()) {
					posX += vel_x;
					end_right = false;
				}
				end_right = true;
			} else if (posX > 0) {
				if (player_Keyboard.getBorder_right()) {
					posX -= vel_x;
					end_left = false;
				}
				end_left = true;
			}
			if (player_Keyboard.getBorder_left()) {
				posX += player_Keyboard.getVel_x();
			}
			if (player_Keyboard.getBorder_right()) {
				posX -= player_Keyboard.getVel_x();
			}
		}
		if (player_Controller != null) {
			if (posX < 0) {
				if (player_Controller.getBorder_left()) {
					posX += vel_x;
					end_right = false;
				}
				end_right = true;
			} else if (posX > 0) {
				if (player_Controller.getBorder_right()) {
					posX -= vel_x;
					end_left = false;
				}
				end_left = true;
			}
			if (player_Controller.getBorder_left() || player_Controller.getBorder_right()) {
				posX -= player_Controller.getVel_x();
			}
		}
	}

	public boolean getEnd_left() {
		return end_left;
	}

	public boolean getEnd_right() {
		return end_right;
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}
}
