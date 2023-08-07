package MonkeyIsland_New;

import java.awt.Color;

import javax.swing.JButton;

public class VirtualKeyboard {
	private char[] alphabet = "abcdefghijklmnopqrstuvwxyz.-".toCharArray();
	private JButton[] buttons = new JButton[alphabet.length];
	private int buttonSize = 50;
	private int x, y;
	private int current = 0, prevCurrent;
	public VirtualKeyboard(int startX, int startY, int width) {
		buttonSize = width/7;
		x = startX;
		y = startY;
		for (int i = 0; i < buttons.length; i++) {
			x = (i%7) * buttonSize;
			if(i!=0 && i%7==0) {
				y += buttonSize;
			}
			buttons[i] = new JButton(Character.toString(alphabet[i]));
			buttons[i].setSize(buttonSize, buttonSize);
			buttons[i].setLocation(x, y);
		}
		
		buttons[0].grabFocus();
		
		for (int i = 0; i < buttons.length; i++) {
			if(buttons[i].hasFocus()) {
				System.out.println(i);
			}
		}
	}
	
	public void repaint() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setBackground(Color.WHITE);				
		}
		try {
			buttons[current].setBackground(Color.YELLOW);
			prevCurrent = current;
		} catch (Exception e) {
			buttons[prevCurrent].setBackground(Color.YELLOW);
		}
	}
	
	public JButton[] getButtons() {
		return buttons;
	}
	
	public int getCurrent() {
		if(current >= 0 && current < buttons.length) {
			return current;
		}else return prevCurrent;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
}
