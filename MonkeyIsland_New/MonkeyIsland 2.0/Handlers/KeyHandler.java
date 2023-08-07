package Handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Constants.Constants;
import GUI.MainGuiElements.Frame;
import GUI.MainGuiElements.Label;

public class KeyHandler implements KeyListener {

	private int key = Constants.KeyConstants.NO_KEY;
	private boolean escapeReady = true, skillsReady = true, inventoryReady = true;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		Frame frame = (Frame) e.getSource();
		Label label = frame.getLabel();
		int mode = label.getMode();
		if (e.getKeyCode() == KeyEvent.VK_W)
			key = Constants.KeyConstants.KEY_W;
		if (e.getKeyCode() == KeyEvent.VK_A)
			key = Constants.KeyConstants.KEY_A;
		if (e.getKeyCode() == KeyEvent.VK_S)
			key = Constants.KeyConstants.KEY_S;
		if (e.getKeyCode() == KeyEvent.VK_D)
			key = Constants.KeyConstants.KEY_D;

		if (mode == Constants.GameConstants.GAMEPANEL) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && escapeReady)
				label.setMode(Constants.GameConstants.PAUSEMENU);
			escapeReady = false;
			if (e.getKeyCode() == KeyEvent.VK_E && skillsReady)
				label.setMode(Constants.GameConstants.SKILLSPANEL);
			skillsReady = false;

			if (e.getKeyCode() == KeyEvent.VK_Q && inventoryReady)
				label.setMode(Constants.GameConstants.INVENTORYPANEL);
			inventoryReady = false;

		} else if (mode == Constants.GameConstants.PAUSEMENU) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && escapeReady)
				label.setMode(Constants.GameConstants.GAMEPANEL);
			escapeReady = false;
		} else if (mode == Constants.GameConstants.SKILLSPANEL) {
			if (e.getKeyCode() == KeyEvent.VK_E && skillsReady)
				label.setMode(Constants.GameConstants.GAMEPANEL);
			skillsReady = false;
		} else if (mode == Constants.GameConstants.INVENTORYPANEL) {
			if (e.getKeyCode() == KeyEvent.VK_Q && inventoryReady)
				label.setMode(Constants.GameConstants.GAMEPANEL);
			inventoryReady = false;
		} else if (mode == Constants.GameConstants.CHESTPANEL) {
			if (e.getKeyCode() == KeyEvent.VK_E) {
				label.setMode(Constants.GameConstants.SKILLSCHESTPANEL);
			}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				label.setMode(Constants.GameConstants.GAMEPANEL);
			}
		} else if (mode == Constants.GameConstants.SKILLSCHESTPANEL) {
			if(e.getKeyCode() == KeyEvent.VK_E) {
				label.setMode(Constants.GameConstants.CHESTPANEL);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key = Constants.KeyConstants.NO_KEY;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			escapeReady = true;
		if (e.getKeyCode() == KeyEvent.VK_E)
			skillsReady = true;
		if (e.getKeyCode() == KeyEvent.VK_Q)
			inventoryReady = true;
	}

	public int getKey() {
		return key;
	}

}
