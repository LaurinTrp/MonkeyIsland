package GUI.MainGuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Taskbar;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import FileManagers.ImageLoader;

public class SplashScreen extends JFrame {
	private SplashLabel label;
	private float progress;
	private JProgressBar progressBar;
	private Taskbar taskBar;

	public SplashScreen() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setSize(getWidth(), 50);
		progressBar.setLocation(0, getHeight() - progressBar.getHeight());
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Blackadder ITC", Font.PLAIN, progressBar.getHeight()));
		progressBar.setFocusable(false);
		progressBar.setBackground(new Color(0, 0, 100));
		progressBar.setForeground(new Color(0, 0, 200));
		progressBar.setBorder(null);
		add(progressBar);

		label = new SplashLabel(new Dimension(getWidth(), getHeight() - progressBar.getHeight()));
		add(label);

		taskBar = Taskbar.getTaskbar();
	}

	private class SplashLabel extends JLabel {
		private BufferedImage image;

		public SplashLabel(Dimension d) {
			setSize(d);
			setLocation(0, 0);

			image = ImageLoader.getImageByFilename("Backgrounds\\Splash.png");
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}

	public void setProgress(float progress) {
		this.progress = progress;
		progressBar.setValue((int) (progress * 100));
		taskBar.setWindowProgressValue(this, progressBar.getValue());
		repaint();
	}
}