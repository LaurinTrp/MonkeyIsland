package MonkeyIsland_Alt;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameMain {
	GameMain(){
		
	}
	public static void main(String[] args) {
		//Frame wird gezeichnet als Vollbild
		Frame fr = new Frame();
		fr.setUndecorated(true);
		fr.setResizable(false);
		fr.setSize(1600, 900);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		DisplayMode dm = new DisplayMode(1600, 900, 32, 60);
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		
		device.setFullScreenWindow(fr);
		device.setDisplayMode(dm);

		fr.makeStrat();
		fr.draw();
		
		//Endlosschleife führt die Zeichnungen aus
		while(true) {
			fr.bewegung();
			fr.draw();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
}