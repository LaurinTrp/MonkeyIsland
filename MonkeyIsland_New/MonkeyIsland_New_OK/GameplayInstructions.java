package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class GameplayInstructions {

	private final String[] textSplit = "KEYS: /n W: Move to the back; /n A: Move to the left; /n S: Move to the front; /n D: Move to the right; /n Q: Open the skill selector; /n E: Open the inventory; /n Escape: Open the pause menu"
			.split("/n");
	private int width, height, instructionsWidth, instructionsHeight;
	private final Font font = new Font("Blackadder ITC", Font.PLAIN, 50);
	private FontMetrics fm;

	public GameplayInstructions(int width, int height) {
		this.width = width;
		this.height = height;
		instructionsWidth = (int) (this.width * .4f);
		instructionsHeight = (int) (this.height * .7f);

	}

	public void draw(Graphics2D g2d) {
		g2d.setFont(font);
		fm = g2d.getFontMetrics();
//		g2d.setColor(Color.RED);
//		g2d.fillRect(width / 2 - instructionsWidth / 2, height / 2 - instructionsHeight / 2, instructionsWidth,
//				instructionsHeight);
		for (int i = 0; i < textSplit.length; i++) {
			g2d.setColor(Color.WHITE);
			g2d.drawString(textSplit[i], width / 2 - instructionsWidth / 2 + (instructionsWidth/2-fm.stringWidth(textSplit[i])/2),
					height / 2 - instructionsHeight / 2 + 100 + fm.getHeight() * i);
		}
	}

}
