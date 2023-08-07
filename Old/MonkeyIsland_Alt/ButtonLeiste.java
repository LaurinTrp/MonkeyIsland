package MonkeyIsland_Alt;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ButtonLeiste {
	Variablen var = new Variablen();
	BufferedImage buttons[] = new BufferedImage[10];
	public ButtonLeiste() {
		try {
			for(int i=0; i<buttons.length;i++) {
				buttons[i]=ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bilder_Auswahl[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
