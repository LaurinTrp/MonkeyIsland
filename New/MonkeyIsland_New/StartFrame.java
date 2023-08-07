package MonkeyIsland_New;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class StartFrame extends JFrame {
	private JButton keyboard, controller, exit;
	private JLabel label;
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 5, true);
	private Border borderRed = BorderFactory.createLineBorder(Color.RED, 5, true);
	private Border borderGreen = BorderFactory.createLineBorder(Color.GREEN, 5, true);
	private char mode;
	private FontMetrics fm;
	public StartFrame() {
		setUndecorated(true);
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		getRootPane().setBorder(border);
		requestFocus();
		
		label = new JLabel("Select the game mode");
		label.setFont(new Font("Blackadder ITC", Font.PLAIN, 50));
		fm = label.getFontMetrics(label.getFont());
		label.setSize(fm.stringWidth(label.getText()) + 15, fm.getHeight());
		label.setLocation(getWidth()/2-label.getSize().width/2, 0);
		label.setForeground(Color.BLACK);
		add(label);
		
		keyboard = new JButton("Keyboard");
		keyboard.setIcon(new ImageIcon(ImageLoader.getImageByFilename("Keyboard.png")));
		keyboard.setSize(getWidth()/2, keyboard.getIcon().getIconHeight());
		keyboard.setLocation(-5, getHeight()/2-keyboard.getHeight()/2);
		keyboard.setBackground(Color.WHITE);
		keyboard.setBorder(border);
		keyboard.addActionListener(new ActionHandler());
		keyboard.addMouseListener(new MouseHandler());
		add(keyboard);
		
		controller = new JButton("Controller");
		controller.setIcon(new ImageIcon(ImageLoader.getImageByFilename("Controller.png")));
		controller.setSize(getWidth()/2, controller.getIcon().getIconHeight());
		controller.setLocation(getWidth()/2-5, getHeight()/2-controller.getHeight()/2);
		controller.setBackground(Color.WHITE);
		controller.setBorder(border);
		controller.addActionListener(new ActionHandler());
		controller.addMouseListener(new MouseHandler());
		add(controller);

		exit = new JButton("Exit");
		exit.setSize(100, 50);
		exit.setLocation(getWidth() / 2 - exit.getWidth() / 2, getHeight() - exit.getHeight());
		exit.setBackground(Color.WHITE);
		exit.setBorder(border);
		exit.setFocusable(false);
		exit.addActionListener(new ActionHandler());
		exit.addMouseListener(new MouseHandler());
		add(exit);
		repaint();
	}

	@Override
	public void repaint() {
		super.repaint();
		
	}

	private void startGame(char mode) {
		this.mode = mode;
		this.dispose();
	}

	public char getMode() {
		return mode;
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == keyboard) {
				startGame('k');
			}
			if(e.getSource() == controller) {
				startGame('c');
			}
			if(e.getSource() == exit) {
				System.exit(0);
			}
		}
		
	}
	
	private class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == keyboard) {
				keyboard.setBorder(borderGreen);
			}
			if(e.getSource() == controller) {
				controller.setBorder(borderGreen);
			}
			if(e.getSource() == exit) {
				exit.setBorder(borderRed);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == keyboard) {
				keyboard.setBorder(border);
			}
			if(e.getSource() == controller) {
				controller.setBorder(border);
			}
			if(e.getSource() == exit) {
				exit.setBorder(border);
			}			
		}
		
	}
}
