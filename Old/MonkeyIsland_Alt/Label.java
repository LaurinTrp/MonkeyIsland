package MonkeyIsland_Alt;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Label extends JLabel {
	Variablen var = new Variablen();
	Figuren figuren = new Figuren();
	Background bg = new Background();
	ButtonLeiste bl = new ButtonLeiste();

	int startPosX = var.frameSizeX / 2 - figuren.bild_Rechts.getWidth() / 2;
	float posX = var.frameSizeX / 2 - figuren.bild_Rechts.getWidth() / 2;
	float posY = 400;

	int bg_posX = 0;

	public void draw(Graphics g, double sizeX, double sizeY, float posY, byte Richtung) {
		// Die Spieloberfl�che wird gezeichnet
		g.drawImage(bg.background(), bg_posX, 0, null);
		g.drawImage(bg.ButtonLeiste(), 0, var.bl_posY, null);

		g.drawImage(figuren.bild(Richtung), (int) posX, (int) posY, (int) sizeX, (int) sizeY, null);

		// Die 10 Buttons in der Button Leiste werden als Images gezeichnet
		for (int i = 0; i < bl.buttons.length; i++) {
			g.drawImage(bl.buttons[i], var.buttons_posX[i], var.button_posY, var.button_Size, var.button_Size, null);
		}

		// Eine art Minimap wird auf einem weissen Rechteck gezeichnet
		g.setColor(Color.WHITE);
		g.fillRect(var.miniMap_posX - 10, var.miniMap_posY - 10, var.miniMap_sizeX + 20, var.miniMap_sizeY + 20);
		g.drawImage(bg.background(), var.miniMap_posX, var.miniMap_posY, 300, 89, null);
	}

}
