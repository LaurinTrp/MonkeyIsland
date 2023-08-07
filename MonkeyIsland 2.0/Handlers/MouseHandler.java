package Handlers;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import GUI.Buttons.AppearingButton;
import GUI.Buttons.PauseButton;
import GUI.Buttons.SkillButton;
import GUI.MainGuiElements.Button;
import GUI.MainGuiElements.Frame;
import GUI.MainGuiElements.Label;
import GUI.Panels.GamePanel;
import GUI.Panels.Overlays.InventoryOverlay;
import GUI.Panels.Overlays.SkillsOverlay;
import Inventory.InventoryObject;
import Inventory.Objects.Key;
import Main.Constants;
import Main.MusicLoader;

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
				SkillsOverlay.setCurrentSkill(((SkillButton) button).getIndex());
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
				if (SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.OPEN
						|| SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.CLOSE) {
					Rectangle rectCloset = new Rectangle((int) (Frame.FRAME_X + frame.getWidth() * (1031f / 5906f)),
							(int) (Frame.FRAME_Y + frame.getHeight() * (1272f / 3508f)),
							(int) (frame.getWidth() * ((1636f - 1031f) / 5906f)),
							(int) (frame.getHeight() * ((2206f - 1272f) / 508f)));

					if (rectCloset.contains(MouseInfo.getPointerInfo().getLocation())) {
						if (GamePanel.getBackgroundIndex() == 0
								&& SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.OPEN) {
							GamePanel.setBackgroundIndex(1);
						}
						if (GamePanel.getBackgroundIndex() == 1
								&& SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.CLOSE) {
							GamePanel.setBackgroundIndex(0);
						}
					}
				}
				if (SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.SPECTATE) {
					Rectangle rectChest = new Rectangle((int) (Frame.FRAME_X + frame.getWidth() * (422f / 1600f)),
							(int) (Frame.FRAME_Y + frame.getHeight() * (746f / 950f)),
							(int) (frame.getWidth() * ((525f - 422f) / 1600f)),
							(int) (frame.getHeight() * ((823f - 746f) / 950f)));
					if (rectChest.contains(MouseInfo.getPointerInfo().getLocation())) {
						frame.getLabel().setMode(Constants.GameConstants.CHESTPANEL);
					}
				}
			}
			if (mode == Constants.GameConstants.SETTINGSPANEL) {
				Rectangle rectMap = new Rectangle((int) (frame.getX() + frame.getWidth() * (262f / 2322f)),
						(int) (frame.getY() +  frame.getHeight() * (156f / 1625f)), (int) (frame.getWidth() * ((596f - 262f) / 2322f)),
						(int) (frame.getHeight() * ((507f - 156f) / 1625f)));
				if (rectMap.contains(MouseInfo.getPointerInfo().getLocation())) {
					frame.getLabel().setMode(Constants.GameConstants.STARTPANEL);
				}
			}

			ArrayList<InventoryObject> inventoryObjectsWorld = frame.getLabel().getGamePanel()
					.getInventoryObjectsWorld();
			frame.getLabel().getInventoryPanel();
			ArrayList<InventoryObject> inventoryObjectsInventory = InventoryOverlay.getInventoryObjectsInventory();

			if (mode == Constants.GameConstants.GAMEPANEL
					&& SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.TAKE) {
				for (int i = 0; i < inventoryObjectsWorld.size(); i++) {
					if (inventoryObjectsWorld.get(i).getRectangle()
							.contains(new Point(MouseInfo.getPointerInfo().getLocation().x - Frame.FRAME_X,
									MouseInfo.getPointerInfo().getLocation().y - Frame.FRAME_Y))) {
						MusicLoader.play("Coin.wav", 0, 0);
						inventoryObjectsInventory.add(inventoryObjectsWorld.get(i));
						inventoryObjectsWorld.removeAll(inventoryObjectsInventory);
						InventoryOverlay.fillInventoryButtons(false);
						InventoryOverlay.refreshButtons();
					}
				}
			}
			if (mode == Constants.GameConstants.CHESTPANEL) {
				if (!frame.getLabel().getChestPanel().getChestOpen() && InventoryOverlay.usable && InventoryOverlay.selectedObject instanceof Key) {
					frame.getLabel().getChestPanel().setChestOpen(!frame.getLabel().getChestPanel().getChestOpen());
				}
				if(SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.CLOSE) {
					frame.getLabel().getChestPanel().setChestOpen(false);
				}
				if(SkillsOverlay.getCurrentSkill() == Constants.GameConstants.SkillConstants.OPEN) {
					frame.getLabel().getChestPanel().setChestOpen(true);
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
