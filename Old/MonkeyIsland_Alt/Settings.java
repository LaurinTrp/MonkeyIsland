package MonkeyIsland_Alt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Settings{
	Variablen var = new Variablen();
	KeyHandler keys = new KeyHandler();
	BufferedImage bild;
	BufferedImage bild_CursorSword;
	BufferedImage bild_CursorBanana;
	BufferedImage bild_CursorHook;
	BufferedImage bild_CursorBeer;
	String title = "Settings";


	Font fontTitle = new Font("Blackadder ITC", Font.BOLD, 150);
	Font font = new Font("Blackadder ITC", Font.BOLD, 50);
	private FontMetrics fm;
	
	Settings(){
		//Die benötigten Bilder werden geladen
		try {
			bild = ImageIO.read(getClass().getClassLoader().getResource(var.bild_Start));
			bild_CursorSword = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorSword));
			bild_CursorBanana = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorBanana));
			bild_CursorHook = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorHook));
			bild_CursorBeer = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorBeer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, int rectPosX, final int rectPosY ,int rect2PosX, final int rect2PosY, String auswahl) {
	//Die Oberfläche wird gezeichnet
		
		//Hintergrundbild wird gezeichnet
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(bild, 0, 0, null); 

		//Die Überschrift wird gezeichnet
		g.setFont(fontTitle);
		fm = g.getFontMetrics();
		int titlePosX=var.frameSizeX/2-fm.stringWidth(title)/2;
		g.drawString(title, titlePosX, 200);
		
		//Rechteck um die verschiedenen Cursor wird gezeichnet (Fett wenn die Cursor ausgewählt werden)
		g.setColor(Color.BLACK);
		if(auswahl=="Cursor") {
			g2d.setStroke(new BasicStroke(5));
		}
		else {g2d.setStroke(new BasicStroke(1));}
		
		g.drawRect(rectPosX, rectPosY, 60, 60);

		//Die Reihe der Cursor wird gezeichnet (mit 4 Bildern für die verschiedenen Cursor)
		g.setFont(font);
		fm = g.getFontMetrics();
		g.drawString("Select your cursor", 50, 300);
		g.drawImage(bild_CursorSword, 500, 250, 50, 50, null);
		g.drawImage(bild_CursorBanana, 600, 250, 50, 50, null);
		g.drawImage(bild_CursorHook, 700, 250, 50, 50, null);
		g.drawImage(bild_CursorBeer, 800, 250, 50, 50, null);
		
		//Rechteck um die verschiedenen Strings wird gezeichnet (Fett wenn die Strings ausgewählt werden)
		if(auswahl=="Redo") {
			g2d.setStroke(new BasicStroke(5));
		}
		else {g2d.setStroke(new BasicStroke(1));}
		
		if(rect2PosX==495) {
			g.drawRect(rect2PosX, rect2PosY, fm.stringWidth("Backspace")+10, 70);
		}
		if(rect2PosX==754) {
			g.drawRect(rect2PosX, rect2PosY, fm.stringWidth("Escape")+10, 70);
		}
		if(rect2PosX==1013) {
			g.drawRect(rect2PosX, rect2PosY, fm.stringWidth("E")+10, 70);
		}		
		
		//Die Reihe des Redo Buttons wird gezeichnet (3 Strings für die verschiedenen Auswahlmöglichkeiten)
		g.drawString("Select the redo key", 50, 400);
		g.drawString("Backspace", 500, 400);
		g.drawString("Escape", 759, 400);
		g.drawString("E", 1018, 400);
	}
}
