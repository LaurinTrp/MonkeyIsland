package GUI.MainGuiElements;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Constants.Constants;
import Handlers.KeyHandler;
import Handlers.MouseHandler;

public class Frame extends JFrame {
	private Label label;
	private KeyHandler keyhandler;

	public Frame() {

		setAlwaysOnTop(true);
		setAlwaysOnTop(false);
		
		keyhandler = new KeyHandler();
		addKeyListener(keyhandler);

		addMouseListener(new MouseHandler());

		Toolkit tk = Toolkit.getDefaultToolkit();
		setSize(tk.getScreenSize());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);

		label = new Label(getSize());

		setVisible(true);

		add(label);
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public void repaint() {
		super.repaint();
		label.repaint();

		if (label.getMode() == Constants.GameConstants.GAMEPANEL) {
			label.getGamePanel().setKey(keyhandler.getKey());
		}
	}

}
