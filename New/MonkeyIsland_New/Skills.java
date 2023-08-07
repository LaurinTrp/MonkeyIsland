package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Skills {
	private JButton[] buttons = new JButton[9];
	private String[] buttonStrings = {"Take", "Give", "Speech", "Pull", "Push", "Open", "Close", "Use", "Spectate"};
	private boolean[] buttonBools = {false, false, false, false, false, false, false, false, false};
	private int buttonSize = 150;
	private int x, y;
	private int currentButton = 0;
	public Skills(int width, int height) {
		x = width/2-buttonSize/2-buttonSize;
		y = height/2-buttonSize/2-buttonSize;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonStrings[i]);
			if(i%3 == 0 && i!= 0) {
				y+=buttonSize;
			}
			buttons[i].setBounds(x+(i%3)*buttonSize, y, buttonSize, buttonSize);
			buttons[i].setFont(new Font("Blackadder ITC", Font.PLAIN, 20));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < buttons.length; j++) {
						if(e.getSource() == buttons[j]) {
							currentButton = j;
						}
					}
				}
			});
		}
	}
	
	public void update() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setBackground(Color.WHITE);
		}
		if(currentButton >= 0) {
			buttons[currentButton].setBackground(Color.YELLOW);
		}
	}
	
	public void setCurrentButton(int currentButton) {
		this.currentButton = currentButton;
	}
	public int getCurrentButton() {
		return currentButton;
	}
	public String[] getButtonStrings() {
		return buttonStrings;
	}
	
	public boolean[] getButtonBools() {
		return buttonBools;
	}
	public JButton[] getButtons() {
		return buttons;
	}
}
