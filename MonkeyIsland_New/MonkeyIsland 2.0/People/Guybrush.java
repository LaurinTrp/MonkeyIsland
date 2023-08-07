package People;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Constants.Constants;
import FileManagers.FileStorage;
import FileManagers.ImageLoader;

public class Guybrush {

	private BufferedImage left, right, currentImage;
	private ArrayList<BufferedImage> leftMovement = new ArrayList<>(), rightMovement = new ArrayList<>();
	private static Point position = new Point();
	private int key, lastKey, counter = 0, currentImageIndex, imageWidth, imageHeight;
	private byte heightChange, positionChange;
	private final int refreshRate = 10, heightChangeValue = 2, positionChangeValue = 10;
	private float siteRatio;
	private Dimension dimension;

	public Guybrush(Dimension dimension) {

		this.dimension = dimension;

		left = ImageLoader.getImageByFilename("Guybrush\\Movement\\Guybrush_Left.png");
		right = ImageLoader.getImageByFilename("Guybrush\\Movement\\Guybrush_Right.png");

		siteRatio = 576f / left.getHeight();

		for (int i = 0; i < 6; i++) {
			leftMovement.add(ImageLoader.getImageByFilename("Guybrush\\Movement\\Guybrush_Left_" + (i) + ".png"));
			rightMovement.add(ImageLoader.getImageByFilename("Guybrush\\Movement\\Guybrush_Right_" + (i) + ".png"));
		}

		currentImage = right;

		position.setLocation(position.x, dimension.height - left.getHeight() * siteRatio);
		imageWidth = (int) (currentImage.getWidth() * siteRatio);
		imageHeight = (int) (currentImage.getHeight() * siteRatio);
	}

	public void draw(Graphics g) {

		movement(key);
		changeImage();
		changeHeight(heightChange);
		changePosition(positionChange);
		g.drawImage(currentImage, position.x, position.y, imageWidth, imageHeight, null);
	}

	private void changeHeight(int value) {
		if (imageHeight + value < (left.getHeight() * siteRatio)
				&& imageHeight + value > (dimension.height * (877f / 1080f) - dimension.height * (360f / 1080f))) {
			imageHeight += value;
			imageWidth = (int) ((float) imageHeight * ((float) left.getWidth() / (float) left.getHeight()));
		}
	}

	private void changePosition(int value) {
		position.setLocation(position.x + value, position.y);
	}

	private void movement(int direction) {
		switch (direction) {
		case Constants.KeyConstants.NO_KEY: {
			heightChange = 0;
			positionChange = 0;
			break;
		}
		case Constants.KeyConstants.KEY_W: {
			heightChange = -heightChangeValue;
			break;
		}
		case Constants.KeyConstants.KEY_A: {
			positionChange = -positionChangeValue;
			break;
		}
		case Constants.KeyConstants.KEY_S: {
			heightChange = heightChangeValue;
			break;
		}
		case Constants.KeyConstants.KEY_D: {
			positionChange = positionChangeValue;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}

		if (position.x < 200) {
			position.setLocation(200, position.y);
		}
		if (position.x + left.getWidth() > (dimension.width * (1650f / 1920f))) {
			position.setLocation((dimension.width * (1650f / 1920f) - left.getWidth()), position.y);
		}
	}

	private void changeImage() {
		if (key != Constants.KeyConstants.NO_KEY && key != Constants.KeyConstants.KEY_W
				&& key != Constants.KeyConstants.KEY_S) {
			ArrayList<BufferedImage> images = null;
			if (key == Constants.KeyConstants.KEY_A) {
				images = leftMovement;
			} else if (key == Constants.KeyConstants.KEY_D) {
				images = rightMovement;
			}

			if (counter == refreshRate) {
				if (currentImageIndex < images.size() - 1) {
					currentImageIndex++;
				}
				counter %= refreshRate;
			}
			counter += 1;
			currentImageIndex %= images.size() - 1;

			currentImage = images.get(currentImageIndex);
			lastKey = key;
		} else {
			switch (lastKey) {
			case Constants.KeyConstants.KEY_A: {
				currentImage = left;
				break;
			}
			case Constants.KeyConstants.KEY_D: {
				currentImage = right;
				break;
			}
			case Constants.KeyConstants.KEY_W: {
				break;
			}
			case Constants.KeyConstants.KEY_S: {
				break;
			}
			default:
				break;
			}
		}
	}

	public void savePlayer(String storagePath) {
		FileStorage.writeStorage(storagePath, "" + position.x, "" + imageHeight, "" + lastKey);
	}

	public void loadPlayer(String... values) {
		try {
			position.setLocation(Integer.parseInt(values[0]), position.y);
			imageHeight = Integer.parseInt(values[1]);
			lastKey = Integer.parseInt(values[2]);
		} catch (Exception e) {
		}
	}

	public void setKey(int key) {
		this.key = key;
	}

}
