package Main;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import GUI.MainGuiElements.Frame;

public class MonkeyIsland {
	
    static ServerSocket socket;
    
	public static void main(String[] args) {
		
		
		multipleWindowPrevention();
		detectOS();
		
		Frame frame = new Frame();
		
		while (true) {
			Toolkit.getDefaultToolkit().sync();
			if (frame.getLabel().getMode() == Constants.GameConstants.STARTPANEL) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			frame.repaint();
		}
	}
	
	private static void detectOS() {
		String os = System.getProperty("os.name");
		if(os.startsWith("Linux")) {
			Constants.OperatingSystem.currentSystem = Constants.OperatingSystem.linux;
		}
		if(os.startsWith("Windows")) {
			Constants.OperatingSystem.currentSystem = Constants.OperatingSystem.windows;
		}
	}
	
	private static void multipleWindowPrevention() {
		try {
			socket = new ServerSocket(6500, 10, InetAddress.getLocalHost());
		} catch (IOException e1) {
			JOptionPane.showOptionDialog(null, "Das Programm wurde bereits gestartet!", "Error", JOptionPane.DEFAULT_OPTION,JOptionPane.OK_OPTION, null, new Object[]{}, null);
			System.exit(0);
			e1.printStackTrace();
		}
	}
}
