package GUI.MainGuiElements;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Handlers.KeyHandler;
import Handlers.MouseHandler;
import Main.Constants;

public class Frame extends JFrame {
	private Label label;
	private KeyHandler keyhandler;

	public static int FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT;
	
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
		setTitle("Monkey Island");

		label = new Label(getSize());
		
		setVisible(true);

		add(label);
		
		FRAME_X = getX();
		FRAME_Y = getY();
		FRAME_WIDTH = getWidth();
		FRAME_HEIGHT = getHeight();
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public void repaint() {
		super.repaint();
		Toolkit.getDefaultToolkit().sync();
		label.repaint();

		if (label.getMode() == Constants.GameConstants.GAMEPANEL) {
			label.getGamePanel().setKey(keyhandler.getKey());
		}
	}

}
