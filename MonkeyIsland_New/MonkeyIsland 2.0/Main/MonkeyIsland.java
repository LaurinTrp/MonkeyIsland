package Main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import Constants.Constants;
import GUI.MainGuiElements.Frame;

public class MonkeyIsland {
	
    static ServerSocket socket;
    
	public static void main(String[] args) {
		try {
			socket = new ServerSocket(6500, 10, InetAddress.getLocalHost());
		} catch (IOException e1) {
			JOptionPane.showOptionDialog(null, "Das Programm wurde bereits gestartet!", "Error", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
			System.exit(0);
			e1.printStackTrace();
		}
		
		Frame frame = new Frame();
		
		while (true) {
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
}
