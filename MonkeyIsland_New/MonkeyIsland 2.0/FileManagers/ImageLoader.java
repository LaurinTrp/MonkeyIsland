package FileManagers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Constants.Constants;

public class ImageLoader {
	public static BufferedImage getImageByFilename(String fileName) {
		try {
			return ImageIO.read(new File(Constants.FileConstants.PACKAGEPATH + "Pictures\\" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage getImageByFullPath(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}
