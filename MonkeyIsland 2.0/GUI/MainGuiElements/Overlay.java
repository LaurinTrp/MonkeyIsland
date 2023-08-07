package GUI.MainGuiElements;

import javax.swing.JLabel;

public abstract class Overlay extends JLabel {

	private Panel parent;
	public static int count;

	public Overlay(int width, int height) {
		setSize(width, height);
	}

	public Overlay(Panel parent, int width, int height) {
		setSize(width, height);
		this.parent = parent;
	}

	public void setParent(Panel parent) {
		this.parent = parent;
		addToParent();
	}

	private void addToParent() {
		setLocation(parent.getWidth() / 2 - this.getWidth() / 2, parent.getHeight() / 2 - this.getHeight() / 2);
		parent.add(this);
	}
	
	public void removeFromParent() {
		if(parent != null) {
			parent.remove(this);
		}
	}
}
