package MonkeyIsland_New_OK;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import MonkeyIsland_New_OK.InventoryClasses.Inventory_Object;

public class Label extends JLabel {
//	private StartMenu startMenu;
	private Background background;
	private Pause pause;
	private Settings settings;
	private Speechbar speechbar;
	private Skills skills;
	private Chest chest;
	private GameplayInstructions gameplayInstructions;

	private Player_Keyboard player_Keyboard;

	private Inventory inventory;

	private Start_Settings_Buttons start_settings_buttons;

	private FontMetrics fm;

	private int currentMode, width, height;
	private char mode;
	private boolean inventoryChoose;

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
		chest = new Chest(width, height, inventory);
		gameplayInstructions = new GameplayInstructions(width, height);
		player_Keyboard.setStorageContent(Storage_Class.getFileContent(settings.getStorage(), 's'));
		background.setSchrankOffen(player_Keyboard.getSchrankOffen());
		
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
		inventory.removeDoubles();
		background.removeSchrankLabel(this);
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
			inventory.drawObjects(g2d);
			player_Keyboard.draw(g2d);
			background.drawForeground(g2d);
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
			background.addSchrankLabel(this);
			inventory.setSchrankOffen(background.getSchrankOffen());
			inventory.setSchrankMouseClicked(background.getMouseClickedSchrank());
			break;
		case 2:
			start_settings_buttons.addLabels(this);
			start_settings_buttons.removeTitle(this);
			g2d.drawImage(start_settings_buttons.getSettingsImage(), 0, 0, width, height, null);
			start_settings_buttons.setModus(1);
			start_settings_buttons.repaint();
			start_settings_buttons.setCurrentMode(currentMode);
			break;
		case 3:
			background.drawBackground(g2d);
			pause.draw(g2d);
			player_Keyboard.setSchrankOffen(background.getSchrankOffen());
			break;
		case 4:
			background.drawBackground(g2d);
			for (int i = 0; i < skills.getButtons().length; i++) {
				add(skills.getButtons()[i]);
			}
			break;
		case 5:
			background.drawBackground(g2d);
			inventory.addToLabel(this);
			inventory.repaintImages();
			break;
		case 8:
			for (int i = 0; i < skills.getButtons().length; i++) {
				add(skills.getButtons()[i]);
			}
		case 6:
			chest.draw(g2d);
			chest.setCurrentSkill(skills.getButtons()[skills.getCurrentButton()].getText());
			break;
		case 7:
			g2d.drawImage(start_settings_buttons.getSettingsImage(), 0, 0, width, height, null);			
			gameplayInstructions.draw(g2d);
			start_settings_buttons.setCurrentMode(currentMode);
			break;
		default:
			break;
		}
		if(skills.getButtonBools()[7] && inventory.getUseObject() == null) {
			inventoryChoose = true;
			inventory.addToLabel(this);
		}
		if(inventory.getUseObject() != null) {
			inventoryChoose = false;
		}
		if(currentMode == 4 && inventory.getUseObject() != null && skills.getButtonBools()[7]) {
			inventory.setUseObject(null);
			skills.getButtonBools()[7] = false;
		}
		chest.setUseObject(inventory.getUseObject());
		background.setSkillInt(skills.getCurrentButton());
		
	}

	public Player_Keyboard getPlayer_Keyboard() {
		if (player_Keyboard != null)
			return player_Keyboard;
		else
			return null;
	}

	public Background getBackgroundClass() {
		return background;
	}
	
	public boolean getInventoryChoose() {
		return inventoryChoose;
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
