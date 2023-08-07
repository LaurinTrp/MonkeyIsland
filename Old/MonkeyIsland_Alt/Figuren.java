package MonkeyIsland_Alt;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Figuren {
	Variablen var = new Variablen();
	
	BufferedImage bild_Rechts;
	BufferedImage bild_Links;
	
	BufferedImage[] rechtsBewegung=new BufferedImage[3];
	byte counter=0;
	Figuren(){
		try {
			//Die Bilder f�r die Laufanimation werden geladen
			bild_Rechts = ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_Guybrush_Rechts));
			bild_Links = ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_Guybrush_Links));
			BufferedImage bild_1=ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_Background));
			BufferedImage bild_2=ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_CursorBanana));
			BufferedImage bild_3=ImageIO.read(getClass().getClassLoader().getResourceAsStream(var.bild_Button));
			rechtsBewegung[0]=bild_1;
			rechtsBewegung[1]=bild_2;
			rechtsBewegung[2]=bild_3;
			
		} catch (IOException e) {
			System.out.println("Bild konnte nicht geladen werden");
		}
	}
	public BufferedImage bild(byte Richtung) {
		if(Richtung==0) {
			//Die Laufanimation nach rechts wird durchgef�hrt, indem die Variable der For-Schleife durch ein Araay geht und das dementsprechende Bild zur�ckgibt
			
			for(byte i=counter; i<=rechtsBewegung.length;) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(counter>=2)counter=-1;
				else if(counter<2) {
					counter++;
					return rechtsBewegung[i];
				}
			}
			
		}
		if(Richtung==1) {
			//Die Laufanimation nach links wird durchgef�hrt, indem die Variable der For-Schleife durch ein Araay geht und das dementsprechende Bild zur�ckgibt
			for(byte i=counter; i<=rechtsBewegung.length;) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(counter>=2)counter=-1;
				else if(counter<2) {
					counter++;
					return rechtsBewegung[i];
				}
			}
			
		}
		if(Richtung==3) {
			//Dieses Bild wird zur�ckgegeben, wenn der Spieler sich nach rechts bewegt hat und jetzt stehengeblieben ist
			return bild_Rechts;
		}
		if(Richtung==4) {
			//Dieses Bild wird zur�ckgegeben, wenn der Spieler sich nach links bewegt hat und jetzt stehengeblieben ist
			return bild_Links;
		}
		return null;
	}
}
