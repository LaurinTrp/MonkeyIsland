package MonkeyIsland_New_OK;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	static BufferedImage image = null;
	
	public static BufferedImage getImageByPath(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getImageByFilename(String fileName) {
		try {
			image = ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(ImageLoader.class.getPackageName() + "/Pictures/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static BufferedImage getImage(String fileName, int width, int height) {
		try {
			image = ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(ImageLoader.class.getPackageName() + "/Pictures/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = image.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
		return image;
	}
	
	public static BufferedImage changeImageSize(BufferedImage image, int width, int height) {
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = tempImage.createGraphics();
		g2d.drawImage(image, 0, 0, width, height, null);
		return tempImage;
	}
}
