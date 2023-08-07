package Handlers;

import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Constants.Constants;
import GUI.Buttons.AppearingButton;
import GUI.Buttons.PauseButton;
import GUI.Buttons.SkillButton;
import GUI.MainGuiElements.Button;
import GUI.MainGuiElements.Frame;
import GUI.MainGuiElements.Label;
import GUI.Panels.GamePanel;
import GUI.Panels.InventoryPanel;
import GUI.Panels.SkillsPanel;
import Inventory.InventoryObject;

public class MouseHandler implements MouseListener {

	private int mode;
	private AppearingButton appearingButton;
	private Button button;
	private boolean mouseReady = true;

	public MouseHandler(int mode, AppearingButton appearingButton) {
		this.mode = mode;
		this.appearingButton = appearingButton;
	}

	public MouseHandler(Button pauseButton) {
		this.button = pauseButton;
	}

	public MouseHandler() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (appearingButton != null) {
			if (e.getSource() == appearingButton) {
				((Label) appearingButton.getParent().getParent()).setMode(mode);
			}
		} else if (button != null) {
			if (button instanceof PauseButton) {
				((PauseButton) button).setAnimationStart(true);
			}
			if (button instanceof SkillButton) {
				SkillsPanel.setCurrentSkill(((SkillButton) button).getIndex());
			}
		} else {
			if (e.getSource() instanceof Frame) {
				Frame frame = (Frame) e.getSource();
				frame.requestFocus();
			}
		}
		if (e.getSource() instanceof Frame && e.getButton() == MouseEvent.BUTTON1) {
			Frame frame = (Frame) e.getSource();
			int mode = frame.getLabel().getMode();
			if (mode == Constants.GameConstants.GAMEPANEL) {
				if (SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.OPEN) {
					Rectangle rectCloset = new Rectangle((int) (frame.getWidth() * (1031f / 5906f)),
							(int) (frame.getHeight() * (1272f / 3508f)),
							(int) (frame.getWidth() * ((1636f - 1031f) / 5906f)),
							(int) (frame.getHeight() * ((2206f - 1272f) / 508f)));

					if (rectCloset.contains(MouseInfo.getPointerInfo().getLocation())) {
						if (GamePanel.getBackgroundIndex() == 0) {
							GamePanel.setBackgroundIndex(1);
						}
						if (GamePanel.getBackgroundIndex() == 1
								&& SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.CLOSE) {
							GamePanel.setBackgroundIndex(0);
						}
					}
					if (rectCloset.contains(MouseInfo.getPointerInfo().getLocation())) {
						if (GamePanel.getBackgroundIndex() == 0
								&& SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.OPEN) {
							GamePanel.setBackgroundIndex(1);
						}
						if (GamePanel.getBackgroundIndex() == 1
								&& SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.CLOSE) {
							GamePanel.setBackgroundIndex(0);
						}
					}
				}
				if (SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.SPECTATE) {
					Rectangle rectChest = new Rectangle((int) (frame.getWidth() * (422f / 1600f)),
							(int) (frame.getHeight() * (746f / 950f)),
							(int) (frame.getWidth() * ((525f - 422f) / 1600f)),
							(int) (frame.getHeight() * ((823f - 746f) / 950f)));
					if (rectChest.contains(MouseInfo.getPointerInfo().getLocation())) {
						frame.getLabel().setMode(Constants.GameConstants.CHESTPANEL);
					}
				}
			}
			if (mode == Constants.GameConstants.SETTINGSPANEL) {
				Rectangle rectMap = new Rectangle((int) (frame.getWidth() * (262f / 2322f)),
						(int) (frame.getHeight() * (156f / 1625f)), (int) (frame.getWidth() * ((596f - 262f) / 2322f)),
						(int) (frame.getHeight() * ((507f - 156f) / 1625f)));
				if (rectMap.contains(MouseInfo.getPointerInfo().getLocation())) {
					frame.getLabel().setMode(Constants.GameConstants.STARTPANEL);
				}
			}
			if (mode == Constants.GameConstants.CHESTPANEL) {
				if (mouseReady) {
					frame.getLabel().getChestPanel().setChestOpen(!frame.getLabel().getChestPanel().getChestOpen());
				}
			}

			ArrayList<InventoryObject> inventoryObjectsWorld = frame.getLabel().getGamePanel()
					.getInventoryObjectsWorld();
			ArrayList<InventoryObject> inventoryObjectsInventory = frame.getLabel().getInventoryPanel()
					.getInventoryObjectsInventory();

			if (mode == Constants.GameConstants.GAMEPANEL
					&& SkillsPanel.getCurrentSkill() == Constants.GameConstants.SkillConstants.TAKE) {
				for (int i = 0; i < inventoryObjectsWorld.size(); i++) {
					if (inventoryObjectsWorld.get(i).getRectangle()
							.contains(MouseInfo.getPointerInfo().getLocation())) {
						inventoryObjectsInventory.add(inventoryObjectsWorld.get(i));
						inventoryObjectsWorld.removeAll(inventoryObjectsInventory);
						InventoryPanel.fillInventoryButtons(false);
						InventoryPanel.refreshButtons();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseReady = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseReady = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (appearingButton != null) {
			appearingButton.setHovered(true);
		}
		if (button != null) {
			button.setHovered(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (appearingButton != null) {
			appearingButton.setHovered(false);
		}
		if (button != null) {
			button.setHovered(false);
		}
	}

}
