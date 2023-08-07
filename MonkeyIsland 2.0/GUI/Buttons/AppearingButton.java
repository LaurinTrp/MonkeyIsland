package GUI.Buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Handlers.MouseHandler;
import Main.Constants;

public class AppearingButton extends JLabel {

	private int counter = 0, alpha = 0, gameConstantIndex;
	private final int refreshRate = 1;
	private boolean a;

	public AppearingButton(int height, String text, int gameConstantIndex) {
		this.gameConstantIndex = gameConstantIndex;

		setBackground(new Color(0, 0, 0, 0));
		setForeground(new Color(255, 255, 255, 0));
		setText(text);
		setFont(Constants.Fonts.getFont(height));
		FontMetrics fm = getFontMetrics(getFont());
		setSize(fm.stringWidth(text) + 50, height);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBorder(null);
		setFocusable(false);

	}

	public AppearingButton(int height, String text, int gameConstantIndex, boolean a) {
		this.gameConstantIndex = gameConstantIndex;

		setBackground(new Color(0, 0, 0, 0));
		setForeground(new Color(255, 255, 255, 0));
		setText(text);
		setFont(Constants.Fonts.getFont(height));
		FontMetrics fm = getFontMetrics(getFont());
		setSize(fm.stringWidth(text) + 50, height);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBorder(null);
		setFocusable(false);
		
		this.a = a;
	}

	public void centerHorizontal(int screenWidth) {
		setLocation(screenWidth / 2 - getWidth() / 2, getY());
	}

	public void centerVertical(int screenHeight) {
		setLocation(getX(), screenHeight / 2 - getHeight() / 2);
	}

	public void startAnimation() {
		if (counter == refreshRate && alpha <= 252) {
			alpha += 3;
			setForeground(new Color(0, 0, 0, alpha));
			counter %= refreshRate;
		}
		if (alpha == 255) {
			addMouseListener(new MouseHandler(gameConstantIndex, this));
		}
		counter++;
	}

	public void setHovered(boolean underline) {
		if (!a) {
			if (underline) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				setForeground(new Color(0, 0, 0, 150));
			} else {
				setCursor(Cursor.getDefaultCursor());
				setForeground(new Color(0, 0, 0));
			}
		}
	}

	public int getAlpha() {
		return alpha;
	}

}
