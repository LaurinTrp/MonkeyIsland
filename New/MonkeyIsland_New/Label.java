package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class Label extends JLabel {
//	private StartMenu startMenu;
	private Background background;
	private Pause pause;
	private Settings settings;
	private Speechbar speechbar;
	private Skills skills;
	private Chest chest;

	private Player_Keyboard player_Keyboard;
	private Player_Controller player_Controller;

	private Inventory inventory;

	private Start_Settings_Buttons start_settings_buttons;

	private FontMetrics fm;

	private int currentMode, width, height;
	private char mode;

	Label(Pause pause, Settings settings, int width, int height, char mode) {
		this.width = width;
		this.height = height;
		this.mode = mode;
		this.pause = pause;
		this.settings = settings;
		speechbar = new Speechbar(width, height, mode);
		skills = new Skills(width, height);
		inventory = new Inventory(width, height, mode, settings.getStorage());
		start_settings_buttons = new Start_Settings_Buttons(width, height, 0, mode);
		if (mode == 'k') {
			player_Keyboard = new Player_Keyboard(width, height, pause);
			background = new Background(player_Keyboard, width, height);
			for (int i = 0; i < background.getRectangles().size(); i++) {
				player_Keyboard.getCollisionRects().add(background.getRectangles().get(i));
			}
		}
		if (mode == 'c') {
			player_Controller = new Player_Controller(width, height, pause);
			background = new Background(player_Controller, width, height);
		}
		chest = new Chest(width, height, inventory);
		setBackground(Color.BLACK);
		setOpaque(true);
		setSize(width, height);
		setLocation(0, 0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < skills.getButtons().length; i++) {
			remove(skills.getButtons()[i]);
		}
		switch (currentMode) {
		case 0:
			start_settings_buttons.addLabels(this);
			start_settings_buttons.addTitle(this);
			start_settings_buttons.setModus(0);
			start_settings_buttons.repaint();
			g2d.drawImage(start_settings_buttons.getImage(), 0, 0, width, height, null);
			break;
		case 1:
			start_settings_buttons.removeLabels(this);
			inventory.removeFromLabel(this);
			background.drawBackground(g2d);
			for (int i = 0; i < background.getRectangles().size(); i++) {
				g2d.fillRect(background.getRectangles().get(i).x, background.getRectangles().get(i).y, background.getRectangles().get(i).width, background.getRectangles().get(i).height);
			}
			if (mode == 'k') {
				player_Keyboard.draw(g2d);
			} else if (mode == 'c') {
				player_Controller.draw(g2d);
			}
			background.drawForeground(g2d);
			for (int i = 0; i < inventory.getObjectsInWorld().size(); i++) {
				inventory.getParticleEffects().get(i).draw(g2d);
				g2d.drawImage(inventory.getObjectsInWorld().get(i).getImage(),
						inventory.getObjectsInWorld().get(i).getPosX(), inventory.getObjectsInWorld().get(i).getPosY(),
						inventory.getObjectsInWorld().get(i).getWidth(),
						inventory.getObjectsInWorld().get(i).getHeight(), null);
			}
			inventory.repaintObjectsInWorld();
			g2d.setFont(new Font("Blackadder ITC", Font.PLAIN, (int) (height * 0.075)));
			fm = g2d.getFontMetrics();
			g2d.setColor(new Color(173, 23, 23));
			g2d.drawString("Current skill: " + skills.getButtons()[skills.getCurrentButton()].getText(),
					getWidth()
							- fm.stringWidth(
									"Current skill: " + skills.getButtons()[skills.getCurrentButton()].getText())
							- (int) (width * 0.05),
					height - (int) (height * 0.05));
			speechbar.draw(g2d);

			
			break;
		case 2:
			start_settings_buttons.addLabels(this);
			start_settings_buttons.removeTitle(this);
			g2d.drawImage(start_settings_buttons.getImage(), 0, 0, width, height, null);
			start_settings_buttons.setModus(1);
			start_settings_buttons.repaint();
			break;
		case 3:
			background.drawBackground(g2d);
			pause.draw(g2d);
			break;
		case 4:
			background.drawBackground(g2d);
			for (int i = 0; i < skills.getButtons().length; i++) {
				add(skills.getButtons()[i]);
			}
			if (mode == 'c') {
				skills.update();
			}
			break;
		case 5:
			background.drawBackground(g2d);
			inventory.addToLabel(this);
			inventory.repaintImages();
			break;
		case 6:
			chest.draw(g2d);
			break;
		default:
			break;
		}
	}

	public Player_Keyboard getPlayer_Keyboard() {
		if (player_Keyboard != null)
			return player_Keyboard;
		else
			return null;
	}

	public Player_Controller getPlayer_Controller() {
		if (player_Controller != null)
			return player_Controller;
		else
			return null;
	}

	public Background getBackgroundClass() {
		return background;
	}

	public Speechbar getSpeechbar() {
		return speechbar;
	}

	public Skills getSkills() {
		return skills;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Start_Settings_Buttons getStart_settings_buttons() {
		return start_settings_buttons;
	}

	public Chest getChest() {
		return chest;
	}

	public void setCurrentMode(int currentMode) {
		this.currentMode = currentMode;
	}
}
