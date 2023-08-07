package MonkeyIsland_Alt;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	Variablen var = new Variablen();
	BufferedImage bg_Bild;
	BufferedImage bl_Bild;
	
	Background(){
		try {
			//Die Bilder werden geladen...
			bg_Bild = ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_Background));
			bl_Bild = ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_ButtonLeiste));
		} catch (IOException e) {
			System.out.println("Bild konnte nicht geladen werden");
		}
	}
	//...und in einer Methode widergegeben
	public BufferedImage background() {
		return bg_Bild;
	}
	public BufferedImage ButtonLeiste() {
		return bl_Bild;
	}
}
