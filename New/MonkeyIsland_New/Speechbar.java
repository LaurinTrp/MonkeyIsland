package MonkeyIsland_New;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;

public class Speechbar {
	private BufferedImage image;
	private FontMetrics fm;
	private int width, height;
	private int imageWidth, imageHeight;
	private boolean done = false;
	private static boolean active;
	private static String person = "", text = "";
	private char mode;
	public Speechbar(int width, int height, char mode) {
		image = ImageLoader.getImageByFilename("SpeechBar.png");

		this.width = width;
		this.height = height;
		this.mode = mode;
		imageWidth = (int) (width * 0.6f);
		imageHeight = (int) (height * 0.2f);
	}

	public void draw(Graphics2D g) {
//		System.out.println(active);
		if (!done && !person.equals("") && !text.equals("")) {
			g.drawImage(image, width / 2 - imageWidth / 2, height - imageHeight, imageWidth, imageHeight, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Blackadder ITC", Font.PLAIN, 25));
			fm = g.getFontMetrics();
			g.drawString(person + ":", width / 2 - fm.stringWidth(person + ":") / 2,
					height - imageHeight + fm.getHeight());
			g.drawString(text, width / 2 - fm.stringWidth(text) / 2,
					height - imageHeight + imageHeight / 2 + fm.getHeight() / 2);
			if(mode == 'k') {
				g.drawString("Click to continue", width / 2 - fm.stringWidth("Click to continue") / 2,
						height - imageHeight + imageHeight / 2 + fm.getHeight()*2);
			}
			if(mode == 'c') {
				g.drawString("Press       to continue", width / 2 - fm.stringWidth("Press       to continue") / 2,
						height - imageHeight + imageHeight / 2 + fm.getHeight()*2);
				g.setStroke(new BasicStroke(2));
				g.drawRect(width / 2 - fm.stringWidth("Press       to continue") / 2 + fm.stringWidth("Press  "), height - imageHeight + imageHeight / 2 + fm.getHeight()*2 - fm.getHeight()/2, fm.getHeight()/2, fm.getHeight()/2);
			}
			active = true;
		}else {
			active = false;
		}
	}
	
	public boolean hoverSpeechbar(int frameX, int frameY) {
		float mouseX = MouseInfo.getPointerInfo().getLocation().x - frameX;
		float mouseY = MouseInfo.getPointerInfo().getLocation().y - frameY;
		return mouseX > width/2-imageWidth/2 && mouseX < width/2+imageWidth/2 && mouseY > height - imageHeight && mouseY < height;
	}
	
	public static void setText(String speaker, String content) {
		person = speaker;
		text = content;
	}
	
	public static boolean getActive() {
		return active;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean getDone() {
		return done;
	}
}
