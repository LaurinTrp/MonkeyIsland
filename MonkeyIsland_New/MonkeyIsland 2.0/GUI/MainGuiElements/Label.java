package GUI.MainGuiElements;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import Constants.Constants;
import FileManagers.FileStorage;
import GUI.Panels.ChestPanel;
import GUI.Panels.FirstStorage;
import GUI.Panels.GamePanel;
import GUI.Panels.InventoryPanel;
import GUI.Panels.PausePanel;
import GUI.Panels.SettingsPanel;
import GUI.Panels.SkillsPanel;
import GUI.Panels.StartPanel;

public class Label extends JLabel {
	private Panel startPanel, gamePanel, pausePanel, skillsPanel, settingsPanel, inventoryPanel, chestPanel,
			firstStoragePanel;
	private int mode = 0, prevMode = mode;
	private final float maxPanelsCount = 8;
	private float progress;
	private SplashScreen splash = new SplashScreen();

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

		gamePanel = new GamePanel(dimension, Constants.FileConstants.StorageConstants.STORAGEPATH + "Inventory\\" + ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt");
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		pausePanel = new PausePanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		skillsPanel = new SkillsPanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		inventoryPanel = new InventoryPanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		chestPanel = new ChestPanel(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		firstStoragePanel = new FirstStorage(dimension);
		progress = Panel.panelsCounter / maxPanelsCount;
		splash.setProgress(progress);

		if (((SettingsPanel) settingsPanel).getNodeCount() == 0) {
			mode = Constants.GameConstants.FIRSTSTORAGEPANEL;
		}
	}

	public float getProgress() {
		return progress;
	}

	private void currentMode() {
		removeAll();
		((SkillsPanel) skillsPanel).resetColors();
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
			break;
		}
		case Constants.GameConstants.GAMEPANEL: {
			((StartPanel) startPanel).resetColors();
			add(gamePanel);
			break;
		}
		case Constants.GameConstants.SETTINGSPANEL: {
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
				((InventoryPanel) inventoryPanel).saveInventory(Constants.FileConstants.StorageConstants.STORAGEPATH
						+ "Inventory\\" + ((SettingsPanel) settingsPanel).getSelectedNode() + ".txt");
			}
			mode = Constants.GameConstants.PAUSEMENU;
			currentMode();
			break;
		}
		case Constants.GameConstants.SKILLSPANEL:
			skillsPanel.setBackgroundImage(gamePanel.getBackgroundImage());
			add(skillsPanel);
			break;
		case Constants.GameConstants.INVENTORYPANEL:
			add(inventoryPanel);
			break;
		case Constants.GameConstants.PAUSEMENU: {
			((PausePanel) pausePanel).setBackgroundImage(((GamePanel) gamePanel).getBackgroundImage());
			add(pausePanel);
			break;
		}
		case Constants.GameConstants.CHESTPANEL: {
			add(chestPanel);
			chestPanel.remove(skillsPanel);
			break;
		}
		case Constants.GameConstants.SKILLSCHESTPANEL: {
			add(chestPanel, 0);
			skillsPanel.setBackgroundImage(chestPanel.getBackgroundImage());
			chestPanel.add(skillsPanel);
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
		if(mode == Constants.GameConstants.SKILLSPANEL) {
			if(SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.USE) {
				((InventoryPanel)inventoryPanel).setSelection(true);
				setMode(Constants.GameConstants.INVENTORYPANEL);
				SkillsPanel.setCurrentSkill(0);
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

	public InventoryPanel getInventoryPanel() {
		return (InventoryPanel) inventoryPanel;
	}

	public ChestPanel getChestPanel() {
		return (ChestPanel) chestPanel;
	}

}
