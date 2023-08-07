package GUI.Panels.Overlays;

import java.awt.Color;

import javax.swing.BorderFactory;

import FileManagers.FileGet;
import FileManagers.ImageLoader;
import GUI.Buttons.SkillButton;
import GUI.MainGuiElements.Overlay;
import Main.Constants;

public class SkillsOverlay extends Overlay{
	private static int currentSkill = 0;
	private static SkillButton[] skillButtons = new SkillButton[FileGet
	                                                			.getFolderCount(Constants.FileConstants.PACKAGEPATH + "Pictures/Skills")];
	public final static int buttonSize = 150;
	private static final String[] skills = "Close Open Speak Give Take Spectate Pull Push Use".split(" ");

	public SkillsOverlay(int width, int height) {
		super(width, height);
		
		initComponents();
	}


	public void initComponents() {

		for (int i = 0; i < skillButtons.length; i++) {
			skillButtons[i] = new SkillButton(buttonSize, buttonSize, "",
					ImageLoader.resizeImage(ImageLoader.getImageByFullPath(
							FileGet.getContainingFiles(Constants.FileConstants.PACKAGEPATH + "Pictures/Skills")[i]
									.getAbsolutePath()),
							buttonSize, buttonSize),
					i);
			skillButtons[i].centerHorizontal(getWidth());
			skillButtons[i].centerVertical(getHeight());
			skillButtons[i].setLocation(skillButtons[i].getX() + ((i % 3) - 1) * buttonSize,
					skillButtons[i].getY() + (((int) (i / 3f) - 1) * buttonSize));
			add(skillButtons[i]);
		}

		skillButtons[currentSkill].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		resetColors();
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
		SkillsOverlay.currentSkill = currentSkill;
		for (int i = 0; i < skillButtons.length; i++) {
			skillButtons[i].setBorder(null);
		}
		skillButtons[currentSkill].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	}
	
}
