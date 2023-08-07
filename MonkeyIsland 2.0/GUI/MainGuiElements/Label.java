package GUI.MainGuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;

import FileManagers.FileStorage;
import GUI.Panels.ChestPanel;
import GUI.Panels.FirstStorage;
import GUI.Panels.GamePanel;
//import GUI.Panels.InventoryPanel;
import GUI.Panels.PausePanel;
import GUI.Panels.SettingsPanel;
import GUI.Panels.StartPanel;
import GUI.Panels.Overlays.InventoryOverlay;
import GUI.Panels.Overlays.ShipTrash;
import GUI.Panels.Overlays.SkillsOverlay;
import Main.Constants;
import Main.MusicLoader;
import People.Guybrush;

public class Label extends JLabel {
	
	private Panel startPanel, gamePanel, pausePanel, skillsPanel, settingsPanel, inventoryPanel, chestPanel,
			firstStoragePanel;
	private Overlay skillsOverlay, inventoryOverlay, shipTrash;
	private int mode = 0, prevMode = mode, mainBackground;
	private final float maxPanelsCount = 8;
	private float progress;
	private SplashScreen splash = new SplashScreen();
	MusicLoader l = new MusicLoader("Ping.wav");

	public Label() {
	}

	public Label(Dimension dimension) {
		setSize(dimension);
		setLocation(0, 0);
		setBackground(Color.BLACK);

		splash.setVisible(true);

		initPanels(dimension);
		currentMode();

		splash.dispose();
	}

	public void initPanels(Dimension dimension) {

		startPanel = new StartPanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		settingsPanel = new SettingsPanel(this, dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		gamePanel = new GamePanel(dimension, Constants.FileConstants.StorageConstants.STORAGEPATH
				+ ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt");
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		pausePanel = new PausePanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		chestPanel = new ChestPanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		firstStoragePanel = new FirstStorage(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		skillsOverlay = new SkillsOverlay(SkillsOverlay.buttonSize * 3, SkillsOverlay.buttonSize * 3);

		inventoryOverlay = new InventoryOverlay(getWidth() / 2, getHeight() / 2);
		
		shipTrash = new ShipTrash(getWidth(), getHeight());

		if (((SettingsPanel) settingsPanel).getNodeCount() == 0) {
			mode = Constants.GameConstants.FIRSTSTORAGEPANEL;
		}
	}

	public float getProgress() {
		return progress;
	}

	private void currentMode() {
		removeAll();
		try {
			skillsOverlay.removeFromParent();
			inventoryOverlay.removeFromParent();
		} catch (NullPointerException e) {
		}
		((SkillsOverlay) skillsOverlay).resetColors();
		switch (mode) {
		case Constants.GameConstants.STARTPANEL: {
			add(startPanel);
			((SettingsPanel) settingsPanel).addItems("", "");
			if (FileStorage.loadStorage(Constants.FileConstants.StorageConstants.STORAGEPATH
					+ ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt") != null) {
				((GamePanel) gamePanel).getGuybrush()
						.loadPlayer(FileStorage.loadStorage(Constants.FileConstants.StorageConstants.STORAGEPATH
								+ ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt"));
			}
			mainBackground = Constants.GameConstants.STARTPANEL;
			break;
		}
		case Constants.GameConstants.INVENTORYPANEL: {
			inventoryOverlay.setParent(gamePanel);
		}
		case Constants.GameConstants.SKILLSPANEL: {
			skillsOverlay.setParent(gamePanel);
		}
		case Constants.GameConstants.GAMEPANEL: {
			((StartPanel) startPanel).resetColors();
			mainBackground = Constants.GameConstants.GAMEPANEL;
			shipTrash.setParent(gamePanel);
			add(gamePanel);
			break;
		}
		case Constants.GameConstants.SETTINGSPANEL: {
			mainBackground = Constants.GameConstants.SETTINGSPANEL;
			((StartPanel) startPanel).resetColors();
			add(settingsPanel);
			break;
		}
		case Constants.GameConstants.EXITGAME: {
			System.exit(0);
			break;
		}
		case Constants.GameConstants.SAVE: {
			if (((SettingsPanel) settingsPanel).getNodeCount() != 0) {
				((GamePanel) gamePanel).getGuybrush().savePlayer(Constants.FileConstants.StorageConstants.STORAGEPATH
						+ ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt");
				((InventoryOverlay) inventoryOverlay).saveInventory(Constants.FileConstants.StorageConstants.STORAGEPATH
						+ "Inventory/" + ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt");
			}
			mode = Constants.GameConstants.PAUSEMENU;
			currentMode();
			break;
		}
		case Constants.GameConstants.PAUSEMENU: {
			((PausePanel) pausePanel).setBackgroundImage(((GamePanel) gamePanel).getBackgroundImage());
			add(pausePanel);
			break;
		}
		case Constants.GameConstants.INVENTORYCHESTPANEL: {
			inventoryOverlay.setParent(chestPanel);
		}
		case Constants.GameConstants.SKILLSCHESTPANEL: {
			skillsOverlay.setParent(chestPanel);
		}
		case Constants.GameConstants.CHESTPANEL: {
			mainBackground = Constants.GameConstants.CHESTPANEL;
			add(chestPanel);
			break;
		}
		case Constants.GameConstants.FIRSTSTORAGEPANEL: {
			add(firstStoragePanel);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	@Override
	public void repaint() {
		super.repaint();
		Toolkit.getDefaultToolkit().sync();
		try {
			if (prevMode != mode) {
				currentMode();
				prevMode = mode;
			}
		} catch (Exception e) {
		}
		if (mode == Constants.GameConstants.FIRSTSTORAGEPANEL) {
			if (((FirstStorage) firstStoragePanel).getSubmit()) {
				((GamePanel) gamePanel).getGuybrush().savePlayer(Constants.FileConstants.StorageConstants.STORAGEPATH
						+ ((FirstStorage) firstStoragePanel).getTextFieldText() + ".txt");
				mode = Constants.GameConstants.STARTPANEL;
			}
		}
		if (SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.USE) {
			switch (mode) {
			case Constants.GameConstants.SKILLSPANEL: {
				((InventoryOverlay)inventoryOverlay).setSelectedObject(null);
				setMode(Constants.GameConstants.INVENTORYPANEL);
				break;
			}
			case Constants.GameConstants.SKILLSCHESTPANEL: {
				((InventoryOverlay)inventoryOverlay).setSelectedObject(null);
				setMode(Constants.GameConstants.INVENTORYCHESTPANEL);
				break;
			}
			case Constants.GameConstants.INVENTORYPANEL: {
				((InventoryOverlay)inventoryOverlay).setUsable(true);
				if(((InventoryOverlay) inventoryOverlay).getSelectedObject() != null) {
					setMode(Constants.GameConstants.GAMEPANEL);
					break;
				}
				break;
			}
			case Constants.GameConstants.INVENTORYCHESTPANEL: {
				((InventoryOverlay)inventoryOverlay).setUsable(true);
				if(((InventoryOverlay) inventoryOverlay).getSelectedObject() != null) {
					setMode(Constants.GameConstants.CHESTPANEL);
					break;
				}
				break;
			}
			default:
			}
		}
		
		if(mode == Constants.GameConstants.GAMEPANEL && !Guybrush.yeet && ShipTrash.run) {
			shipTrash.setLocation(shipTrash.getX() - 1, 0);
			if(shipTrash.getLocation().x <= Guybrush.position.x + Guybrush.imageWidth) {
				l.playN();
				Guybrush.yeet = true;
			}
		}

	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}

	public GamePanel getGamePanel() {
		return (GamePanel) gamePanel;
	}

	public InventoryOverlay getInventoryPanel() {
		return (InventoryOverlay) inventoryOverlay;
	}

	public ChestPanel getChestPanel() {
		return (ChestPanel) chestPanel;
	}

}
