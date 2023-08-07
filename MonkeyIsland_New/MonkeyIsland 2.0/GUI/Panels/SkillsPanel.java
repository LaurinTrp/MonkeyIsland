package GUI.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;

import Constants.Constants;
import FileManagers.FileGet;
import FileManagers.ImageLoader;
import GUI.Buttons.SkillButton;
import GUI.MainGuiElements.Panel;

public class SkillsPanel extends Panel {

	private static int currentSkill = 0;
	private final static int buttonSize = 150;
	private static Dimension dimension;
	private static SkillButton[] skillButtons = new SkillButton[FileGet
			.getFolderCount(Constants.FileConstants.PACKAGEPATH + "Pictures\\Skills")];

	private static final String[] skills = "Close Open Speak Give Take Spectate Pull Push Use".split(" ");

	public SkillsPanel(Dimension dimension) {
		super(dimension);
		SkillsPanel.dimension = dimension;

		initComponents();
	}

	public void initComponents() {

		for (int i = 0; i < skillButtons.length; i++) {
			skillButtons[i] = new SkillButton(buttonSize, buttonSize, "",
					ImageLoader.resizeImage(ImageLoader.getImageByFullPath(
							FileGet.getContainingFiles(Constants.FileConstants.PACKAGEPATH + "Pictures\\Skills")[i]
									.getAbsolutePath()),
							buttonSize, buttonSize),
					i);
			skillButtons[i].centerHorizontal(dimension.width);
			skillButtons[i].centerVertical(dimension.height);
			skillButtons[i].setLocation(skillButtons[i].getX() + ((i % 3) - 1) * buttonSize,
					skillButtons[i].getY() + (((int) (i / 3f) - 1) * buttonSize));
			add(skillButtons[i]);
		}

		skillButtons[currentSkill].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		resetColors();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), null);
	}
	
	public void resetColors() {
		for (int i = 0; i < skillButtons.length; i++) {
			skillButtons[i].setBackground(Color.BLACK);
		}
	}

	public static int getCurrentSkill() {
		return currentSkill;
	}
	
	public static String getCurrentSkillString() {
		return skills[currentSkill];
	}

	public static void setCurrentSkill(int currentSkill) {
		SkillsPanel.currentSkill = currentSkill;
		for (int i = 0; i < skillButtons.length; i++) {
			skillButtons[i].setBorder(null);
		}
		skillButtons[currentSkill].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	}

}
