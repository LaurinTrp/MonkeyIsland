package MonkeyIsland_New_OK;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Currency;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.text.DefaultEditorKit.CutAction;

import MonkeyIsland_New_OK.InventoryClasses.Banana;

public class Frame_Keyboard extends JFrame {
	private Label label;
	private Settings settings;
	private Pause pause;
	private boolean keyW, keyA, keyS, keyD, buttonPlay;
	private int currentMode = 0, currentChoice;
	private float mouseX, mouseY;

	private KeyMouseHandler keyMouseHandler;

	public Frame_Keyboard() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setSize(768, 432);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		keyMouseHandler = new KeyMouseHandler();

		addKeyListener(keyMouseHandler);
		addMouseListener(keyMouseHandler);
		setLayout(null);

		pause = new Pause(getWidth(), getHeight(), 'k');
		settings = new Settings(getWidth(), getHeight(), 'k');
		settings.checkFiles();
		if (settings.getFirstStorage()) {
			FirstStorage firstStorage = new FirstStorage(getWidth(), getHeight(), 'k');
			while (!firstStorage.getFinished()) {
				firstStorage.repaint();
			}
		}
		settings.checkFiles();

		label = new Label(pause, settings, getWidth(), getHeight(), 'k');
		add(label);

		repaint();
	}

	@Override
	public void repaint() {
		super.repaint();

		label.setCurrentMode(currentMode);
		mouseIcon(label.getSkills().getCurrentButton());
		if (currentMode == 0) {
			label.getStart_settings_buttons().addTitle(label);
			switch (label.getStart_settings_buttons().getPressedButton()) {
			case -1:
				break;
			case 0:
				label.getPlayer_Keyboard().setStorageContent(Storage_Class.getFileContent(settings.getStorage(), 's'));
				label.getBackgroundClass().setSchrankOffen(label.getPlayer_Keyboard().getSchrankOffen());
				label.getInventory().loadObjects(settings.getStorage());
				label.getInventory().resetInventoryImages();
				label.getSkills().setCurrentSkill(label.getPlayer_Keyboard().getCurrentSkill());
				currentMode = 1;
				break;
			case 1:
				label.getStart_settings_buttons().removeTitle(label);
				currentMode = 2;
				label.getStart_settings_buttons().setPressedButton(-1);
				settings.addObjects(label);
				label.getStart_settings_buttons().addMiniMapLabel(label);
				break;
			case 2:
				System.exit(0);
			default:
				break;
			}
		}
		if (currentMode == 1) {
			label.getPlayer_Keyboard().update(keyW, keyA, keyS, keyD, label.getBackgroundClass().getEnd_left(),
					label.getBackgroundClass().getEnd_right());
			label.getStart_settings_buttons().resetColors();
		}
		if (currentMode == 2) {
			settings.repaint();

			switch (label.getStart_settings_buttons().getPressedButton()) {
			case -1:
				break;
			case 0:
				settings.removeObjects(label);
				currentMode = 7;
				label.getStart_settings_buttons().removeLabels(label);
				label.getStart_settings_buttons().miniMapLabelText("Back");
				label.getStart_settings_buttons().setModus(2);
				requestFocus();
				break;
			case 1:
				if (!settings.getTextField().getText().strip().equals(new String(""))) {
					settings.addNewStorage();
				}
				break;
			case 2:
				settings.deleteCurrentStorage();
				break;
			case 3:
				settings.removeObjects(label);
				currentMode = 0;
				label.getStart_settings_buttons().removeMiniMapLabel(label);
				requestFocus();
				break;
			default:
				break;
			}
			label.getStart_settings_buttons().setPressedButton(-1);
		}
		if (currentMode == 3) {
			pause.update(getX(), getY());
			if(buttonPlay) {
				if(pause.getCurrentImage() < 8) {
					pause.setCurrentImage(pause.getCurrentImage() + 1);
					label.repaint();
					if(pause.getCurrentImage() != 8) {
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}else {
					currentMode = 1;
					buttonPlay = false;
					pause.setCurrentImage(0);
					if (pause.getExploded()) {
						pause.setExplosionDone(true);
					}
				}
			}
		}

		if (currentMode == 4) {
			if (label.getInventoryChoose() || label.getSkills().getClickedButton() == -1) {
				currentMode = 1;
			}
		}
		
		
		if (currentMode == 8 && !label.getInventoryChoose()) {
			label.getInventory().removeFromLabel(label);
			if (label.getInventory().getUseObject() != null) {
				currentMode = 6;
			}
		}

		if (currentMode == 7) {
			if (label.getStart_settings_buttons().getPressedButton() == 4) {
				currentMode = 2;
				label.getStart_settings_buttons().setModus(1);
				label.getStart_settings_buttons().addMiniMapLabel(label);
				label.getStart_settings_buttons().miniMapLabelText("");
				settings.addObjects(label);
			}
		}
		if (settings.getBack()) {
			currentMode = 0;
			settings.removeObjects(label);
			this.requestFocus();
			settings.setBack(false);
		}
	}

	private void mouseIcon(int skill) {
		if (skill >= 0) {

		}
	}

	private class KeyMouseHandler implements KeyListener, MouseListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (currentMode) {
			case 0:
				break;
			case 1:
				if (e.getKeyCode() == KeyEvent.VK_W) {
					keyW = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					keyA = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					keyS = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					keyD = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					currentMode = 3;
					pause.randomExplosionButton();
				}
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					label.getSkills().setClickedButton(20);
					currentMode = 4;
				}
				if (e.getKeyCode() == KeyEvent.VK_E) {
					currentMode = 5;
				}
				break;
			case 2:
				System.out.println("fuck");
				break;
			case 3:
				break;
			case 4:
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					currentMode = 1;
				}
				break;
			case 5:
				if (e.getKeyCode() == KeyEvent.VK_E) {
					currentMode = 1;
				}
				break;
			case 6:
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					currentMode = 1;
				}
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					label.getInventory().setUseObject(null);
					currentMode = 8;
				}
				break;
			case 8:
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					currentMode = 6;
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + currentMode);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				keyW = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				keyA = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				keyS = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				keyD = false;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseX = MouseInfo.getPointerInfo().getLocation().x;
				mouseY = MouseInfo.getPointerInfo().getLocation().y;
				if (currentMode == 1) {
					if (label.getSpeechbar().hoverSpeechbar(getX(), getY())) {
						label.getSpeechbar().setDone(true);
					}
					if (mouseX > getWidth() * (400d / getWidth()) && mouseX < getWidth() * (506d / getWidth())
							&& mouseY > getHeight() * (670d / getHeight())
							&& mouseY < getHeight() * (750d / getHeight())
							&& label.getSkills().getButtonStrings()[label.getSkills().getCurrentButton()]
									.equals(new String("Spectate"))) {
						currentMode = 6;
					}
				}
				if (currentMode == 3) {
					if (pause.buttons()[0]) {
						label.getPlayer_Keyboard().save(settings.getStorage(),
								label.getSkills().getButtons()[label.getSkills().getCurrentButton()].getText(),
								label.getInventory().getObjectsInInventory());
						label.getStart_settings_buttons().setPressedButton(-1);
						label.getStart_settings_buttons().resetPositions(0);
						currentMode = 0;
					}
					if (pause.buttons()[2]) {
						label.getPlayer_Keyboard().save(settings.getStorage(),
								label.getSkills().getButtons()[label.getSkills().getCurrentButton()].getText(),
								label.getInventory().getObjectsInInventory());
					}
					if (pause.buttons()[3]) {
						pause.setExplosion(true);
					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (currentMode == 1 && label.getSkills().getCurrentButton() == 0) {
					label.getInventory().setMouseClicked(true);
				}
				if (currentMode == 6) {
					label.getChest().setMouseClick(true);
				}
				if(currentMode == 3) {
					if(pause.buttons()[1]) {
						buttonPlay = true;
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (currentMode == 1) {
					label.getInventory().setMouseClicked(false);
				}
				if (currentMode == 6) {
					label.getChest().setMouseClick(false);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
