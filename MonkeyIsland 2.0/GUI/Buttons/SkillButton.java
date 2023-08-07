package GUI.Buttons;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import GUI.MainGuiElements.Button;
import Handlers.MouseHandler;

public class SkillButton extends Button {

	private int index;

	public SkillButton(int width, int height, String text, BufferedImage image, int index) {
		super(width, height, text, null, null, false);

		this.index = index;

		setIcon(new ImageIcon(image));

		addMouseListener(new MouseHandler(this));
	}

	public int getIndex() {
		return index;
	}

}
