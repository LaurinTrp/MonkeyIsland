package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class Start_Settings_Buttons {
	private JLabel title = new JLabel("Monkey Island");
	private JLabel[] labels = new JLabel[3];
	private String[] start_texts = { "Play", "Settings", "Exit" };
	private String[] setting_texts = { "Back", "Add", "Remove" };
	private Point[] targetPosStart = new Point[labels.length];
	private Point[] targetPosSettings = new Point[labels.length];
	private FontMetrics fm;
	private BufferedImage currentImage, startImage = ImageLoader.getImageByFilename("StartMenu2.1.png"),
			settingsImage = ImageLoader.getImageByFilename("Backgrounds/TurningWheel2.png");
	private BufferedImage[] animationImages = new BufferedImage[30-1];

	private boolean added, titleAdded;
	private int width, height, modus, pressedButton = -1, titleSize = 100;
	private float titleAddition = 1.2f, counter;
	private int currentChoice;
	private char mode;

	public Start_Settings_Buttons(int width, int height, int modus, char mode) {
		this.width = width;
		this.height = height;
		this.modus = modus;
		this.mode = mode;
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(start_texts[i]);
			labels[i].setFont(new Font("Blackadder ITC", Font.PLAIN, (int) (height * 0.1f)));
			fm = labels[i].getFontMetrics(labels[i].getFont());
			labels[i].setSize(fm.stringWidth(labels[i].getText()) + 20, fm.getHeight());
			labels[i].setLocation((int) (width * 0.5f - fm.stringWidth(labels[i].getText()) * 0.5f),
					(int) (height * 0.5f - fm.getHeight() + (fm.getHeight() * 1.5f) * i) - fm.getHeight() / 2);
			targetPosStart[i] = new Point(labels[i].getX(), labels[i].getY());
			labels[i].setFocusable(false);
			labels[i].setForeground(Color.BLACK);
			labels[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					for (int j = 0; j < labels.length; j++) {
						if (e.getSource() == labels[j]) {
							pressedButton = j;
						}
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					for (int j = 0; j < labels.length; j++) {
						if (e.getSource() == labels[j]) {
							labels[j].setForeground(Color.BLACK);
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					for (int j = 0; j < labels.length; j++) {
						if (e.getSource() == labels[j]) {
							labels[j].setForeground(Color.RED);
						}
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});

		}
		for (int i = 0; i < labels.length; i++) {
			targetPosSettings[i] = new Point();
		}
		for (int i = 0; i < animationImages.length-1; i++) {
			animationImages[i] = ImageLoader.getImageByFilename("StartAnimation/StartMenuAnimation" + (i+1) +".png");
		}
	}

	public void resetColors() {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setForeground(Color.BLACK);
		}
	}

	public void repaint() {
		title.setFont(new Font("Blackadder ITC", Font.PLAIN, titleSize));
		fm = title.getFontMetrics(title.getFont());
		title.setSize(fm.stringWidth(title.getText()), fm.getHeight());
		title.setLocation(width / 2 - title.getWidth() / 2, targetPosStart[0].y/2 - title.getHeight()/2);
		title.setForeground(Color.BLACK);
		titleSize += titleAddition;
		if (titleSize >= 150 || titleSize <= 50) {
			titleAddition = -titleAddition;
		}

		for (int i = 0; i < labels.length; i++) {
			fm = labels[i].getFontMetrics(labels[i].getFont());
			labels[i].setSize(fm.stringWidth(labels[i].getText()) + 20, fm.getHeight());
		}
		if(mode == 'c') {
			targetPosSettings[0].setLocation(-labels[0].getWidth(), -labels[0].getHeight());
		}else {
			targetPosSettings[0].setLocation(width*.1f, height*.1f);			
		}
		targetPosSettings[1].setLocation(width - labels[1].getWidth(), height / 2 - labels[1].getHeight());
		targetPosSettings[2].setLocation(width - labels[2].getWidth(), height / 2);
		if (modus == 0) {
			modus0();
		}
		if(modus == 1) {
			modus1();
		}
		if(mode == 'c') {
			for (int i = 0; i < labels.length; i++) {
				labels[i].setForeground(Color.BLACK);
			}
			labels[currentChoice].setForeground(Color.RED);
		}
	}

	private void modus0() {
		counter+= .2;
		counter %= animationImages.length;
		currentImage = animationImages[(int)counter];
		for (int i = 0; i < labels.length; i++) {
			if (labels[i].getX() < targetPosStart[i].x) {
				labels[i].setLocation(labels[i].getX() + 10, labels[i].getY());
			}
			if (labels[i].getX() > targetPosStart[i].x) {
				labels[i].setLocation(labels[i].getX() - 10, labels[i].getY());
			}
			if (labels[i].getY() < targetPosStart[i].y) {
				labels[i].setLocation(labels[i].getX(), labels[i].getY() + 10);
			}
			if (labels[i].getY() > targetPosStart[i].y) {
				labels[i].setLocation(labels[i].getX(), labels[i].getY() - 10);
			}
			labels[i].setText(start_texts[i]);
		}
	}
	
	private void modus1() {
		currentImage = settingsImage;
		for (int i = 0; i < labels.length; i++) {
			if (labels[i].getX() < targetPosSettings[i].x) {
				labels[i].setLocation(labels[i].getX() + 10, labels[i].getY());
			}
			if (labels[i].getX() > targetPosSettings[i].x) {
				labels[i].setLocation(labels[i].getX() - 10, labels[i].getY());
			}
			if (labels[i].getY() < targetPosSettings[i].y) {
				labels[i].setLocation(labels[i].getX(), labels[i].getY() + 10);
			}
			if (labels[i].getY() > targetPosSettings[i].y) {
				labels[i].setLocation(labels[i].getX(), labels[i].getY() - 10);
			}
			labels[i].setText(setting_texts[i]);
		}
	}

	public void addLabels(JLabel label) {
		if (!added) {
			addTitle(label);
			for (int i = 0; i < labels.length; i++) {
				label.add(labels[i]);
			}
			added = true;
		}
	}

	public void removeLabels(JLabel label) {
		if (added) {
			removeTitle(label);
			for (int i = 0; i < labels.length; i++) {
				label.remove(labels[i]);
			}
			added = false;
		}
	}
	public void addTitle(JLabel label) {
		if(!titleAdded) {
			label.add(title);
			titleAdded = true;
		}
	}

	
	public void removeTitle(JLabel label) {
		if(titleAdded) {
			label.remove(title);
			titleAdded = false;
		}
	}

	public BufferedImage getImage() {
		return currentImage;
	}

	public void setModus(int modus) {
		this.modus = modus;
	}

	public int getPressedButton() {
		return pressedButton;
	}

	public void setPressedButton(int pressedButton) {
		this.pressedButton = pressedButton;
	}

	public void setCurrentChoice(int currentChoice) {
		this.currentChoice = currentChoice;
	}
}
