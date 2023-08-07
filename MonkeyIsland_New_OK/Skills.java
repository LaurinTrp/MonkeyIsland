package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Skills {
	private JButton[] buttons = new JButton[9];
	private String[] buttonStrings = {"Take", "Give", "Speak", "Pull", "Push", "Open", "Close", "Use", "Spectate"};
	private boolean[] buttonBools = {false, false, false, false, false, false, false, false, false};
	private int buttonSize = 150;
	private int x, y;
	private int currentButton = 0, clickedButton = -1;
	public Skills(int width, int height) {
		x = width/2-buttonSize/2-buttonSize;
		y = height/2-buttonSize/2-buttonSize;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(buttonStrings[i]);
			if(i%3 == 0 && i!= 0) {
				y+=buttonSize;
			}
			buttons[i].setBounds(x+(i%3)*buttonSize, y, buttonSize, buttonSize);
			buttons[i].setFont(new Font("Blackadder ITC", Font.PLAIN, 20));
			buttons[i].setFocusable(false);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setIcon(new ImageIcon(ImageLoader.getImage("TestSachen/Etimes/"+buttonStrings[i] + ".png", buttonSize, buttonSize)));
			buttons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					for (int j = 0; j < buttons.length; j++) {
						if(e.getSource() == buttons[j]) {
							currentButton = j;
							buttonBools[j] = true;
							clickedButton = j;
						}
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					for (int j = 0; j < buttons.length; j++) {
						if(e.getSource() == buttons[j]) {
							buttonBools[j] = false;
							clickedButton = -1;
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
	public int getClickedButton() {
		return clickedButton;
	}
	public void setClickedButton(int clickedButton) {
		this.clickedButton = clickedButton;
	}
	public void setCurrentSkill(String currentSkill) {
		for (int i = 0; i < buttonStrings.length; i++) {
			if(buttonStrings[i].equals(currentSkill)) {
				currentButton = i;
				break;
			}
		}		
	}
}
