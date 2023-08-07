package GUI.Panels.Overlays;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import FileManagers.ImageLoader;
import GUI.MainGuiElements.Overlay;
import GUI.MainGuiElements.Panel;

public class ShipTrash extends Overlay{
	
	private BufferedImage image = ImageLoader.getImageByFilename("Ship.png");
	public static boolean run;
	
	public ShipTrash(int width, int height) {
		super(width, height);
		
		Label label = new Label(getSize(), image);
		add(label);
	}
	
	@Override
	public void setParent(Panel parent) {
		super.setParent(parent);
		setLocation(getWidth(), getHeight());
	}
	
	private class Label extends JLabel{
		private BufferedImage image;
		public Label(Dimension d, BufferedImage image) {
			setSize(d);
			setLocation(0, 0);
			
			this.image = image;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}

}
