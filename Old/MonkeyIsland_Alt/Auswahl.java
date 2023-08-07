package MonkeyIsland_Alt;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Auswahl{
	private Variablen var = new Variablen();
	private BufferedImage startMenu;
	private BufferedImage[] button;
	private String startButton="Press Enter to start the game";
	private String settingsButton="Press Space to enter the settings";
	private String exitButton="Press Zero to exit the game";
	private String title = "Monkey Island";

	private FontMetrics fm;
	Font font = new Font("Blackadder ITC", Font.BOLD, 50);
	Font fontTitle = new Font("Blackadder ITC", Font.BOLD, 150);
	
	public Auswahl() {
		
		//Die Bilder, die für das Menü benötigt werden, werden gezeichnet
		button = new BufferedImage[3];
		try {
			startMenu=ImageIO.read(getClass().getClassLoader().getResource(var.bild_Start));
			for(int i = 0; i<button.length;i++) {
				button[i]=ImageIO.read(getClass().getClassLoader().getResource(var.bild_Button));
			}
		} catch (IOException e) {
			System.out.println("Bild wurde nicht geunden");
		}
	}
	public void draw(Graphics g) {
	//Auswahl Menü wird gezeichnet
		int buttonPosX=var.frameSizeX/2-button[0].getWidth()/2;
		int posY=100;
		
		//Das Hintergrundbild wird gezeichnet
		g.drawImage(startMenu, 0, 0, null);
		
		//Die Überschrift wird gezeichnet
		g.setColor(Color.BLACK);

		g.setFont(fontTitle);
		fm = g.getFontMetrics();
		int titlePosX=var.frameSizeX/2-fm.stringWidth(title)/2;
		g.drawString(title, titlePosX, posY+100);
		
		//Die drei Buttons mit den entsprechenden Strings werden gezeichnet
		for(int i =0; i<button.length;i++) {
			posY+=200;
			g.drawImage(button[i], buttonPosX, posY, null);
		}
		g.setFont(font);
		fm = g.getFontMetrics();
		g.drawString(startButton, buttonPosX+button[0].getWidth()/2-fm.stringWidth(startButton)/2, 300+button[0].getHeight()/2+font.getSize()/2);
		g.drawString(settingsButton, buttonPosX+button[0].getWidth()/2-fm.stringWidth(settingsButton)/2, 500+button[0].getHeight()/2+font.getSize()/2);
		g.drawString(exitButton, buttonPosX+button[0].getWidth()/2-fm.stringWidth(exitButton)/2, 700+button[0].getHeight()/2+font.getSize()/2);
	}

}
