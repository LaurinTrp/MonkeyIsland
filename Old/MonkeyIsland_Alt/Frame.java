package MonkeyIsland_Alt;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Frame extends JFrame{
	BufferStrategy strategy;
	Label label;
	Auswahl auswahl;
	KeyHandler keys;
	Variablen var;
	Figuren figuren;
	Settings settings;
	Background bg;

	private BufferedImage cursor;
	private BufferedImage iCursor;
	
	Frame(){
		auswahl = new Auswahl();
		keys = new KeyHandler();
		var = new Variablen();
		figuren = new Figuren();
		label = new Label();
		settings = new Settings();
		bg = new Background();
		addKeyListener(keys);
		addMouseListener(keys);
		add(label);
		


		try {
			iCursor = ImageIO.read(getClass().getClassLoader().getResource(var.bild_InvisibleCursor));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cursors();
		
	}
	//Bewegung des Spielers bzw. des Hintergrunds
	public void bewegung() {
		//Maximale/Minimale Position des Hintergrundbildes
		int bgMin=var.frameSizeX-bg.bg_Bild.getWidth()+20;
		int bgMax=0;
		
		if(getRight()==true && label.posX>1240 && label.bg_posX>=+bgMin) {label.bg_posX-=20;}
		else if(getRight()==true) {label.posX+=20;}
		if(getLeft()==true && label.posX<350 && label.bg_posX<bgMax) {label.bg_posX+=20;}
		else if(getLeft()==true) {label.posX-=20;}
		
		
		if(label.posX+figuren.bild_Rechts.getWidth()>var.frameSizeX) {label.posX=var.frameSizeX-figuren.bild_Rechts.getWidth();}
		if(label.posX<0) {label.posX=0;}
		
		
	}
	public boolean getLeft() {
		return keys.key_A; //Gibt die Variable aus der KeyHandler Klasse wieder
	}
	public boolean getRight() {
		return keys.key_D; //Gibt die Variable aus der KeyHandler Klasse wieder
	}
	public void draw() {
		Graphics g = strategy.getDrawGraphics();
		keys.getMouseOnButton();
		repaintLabel(g);
		g.dispose();
		strategy.show();
	}
	public void repaintLabel(Graphics g) {
		//Es wird geguckt welche Oberfläche gezeichnet werden soll
		int modus=keys.modus;
		switch (modus) {
		case 0:
			auswahl.draw(g);
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(iCursor, new Point(0,0), "Unsichtbarer Cursor"));
			break;
		case 1:
			label.draw(g, (double)keys.sizeX, (double)keys.sizeY,keys.posY, keys.Richtung);
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursors(), new Point(0,0), "cursor"));
			break;
		case 2:
			settings.draw(g, keys.rectPosX, keys.rectPosY, keys.rect2PosX, keys.rect2PosY, keys.auswahl);
			break;
		default:
			break;
		}
	}
	public void makeStrat() {
		createBufferStrategy(2);
		strategy = getBufferStrategy();	
	}
	public BufferedImage cursors() {
		//Es wird geguckt welcher Cursor gewählt werden soll und entsprechendes Bild wird geladen
		try {
			if(keys.cursor=="Sword") {
				cursor = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorSword));
				
			}
			if(keys.cursor=="Banana") {
				cursor = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorBanana));
				
			}
			if(keys.cursor=="Hook") {
				cursor = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorHook));
				
			}if(keys.cursor=="Beer") {
				cursor = ImageIO.read(getClass().getClassLoader().getResource(var.bild_CursorBeer));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cursor;
		
	}
}
