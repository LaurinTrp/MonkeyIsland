package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.text.BadLocationException;

public class Settings {
	private JTextField textField = new JTextField();
	private JLabel messageLabel = new JLabel();

	private FontMetrics fm;

	private JComboBox<String> comboBox = new JComboBox<>();

	private ArrayList<String> currentStorageContent = new ArrayList<>();

	private boolean back,firstStorage, added;
	private char mode;

	private int width, height;

	private File packageFile = new File("New_OK\\MonkeyIsland_New_OK\\Storage").getAbsoluteFile();
	private File inventoryFile = new File("New_OK\\MonkeyIsland_New_OK\\Storage\\Inventory").getAbsoluteFile();
	public Settings(int width, int height, char mode) {
		if (packageFile.listFiles().length == 1 && packageFile.listFiles()[0].getName().equals("Inventory")) {
			firstStorage = true;
		}
		
		this.mode = mode;
		this.width = width;
		this.height = height;

		init();
		checkFiles();
		
	}

	private void message(String message) {
		messageLabel.setText(message);
		messageLabel.setFont(new Font("Blackadder ITC", Font.PLAIN, 50));
		fm = messageLabel.getFontMetrics(messageLabel.getFont());
		messageLabel.setSize(fm.stringWidth(messageLabel.getText()), fm.getHeight());
		messageLabel.setLocation(width / 2 - messageLabel.getWidth() / 2, height - messageLabel.getHeight());
		messageLabel.setForeground(Color.RED);
	}

	public void checkFiles() {
		for (int i = currentStorageContent.size() - 1; i >= 0; i--) {
			currentStorageContent.remove(i);
		}
		for (String file : packageFile.list()) {
			if(!file.equals(new String("Inventory"))) {
				currentStorageContent.add(file);
			}
		}
		fillComboBox();
	}
	public void checkFiles(File selectedFile) {
		for (int i = currentStorageContent.size() - 1; i >= 0; i--) {
			currentStorageContent.remove(i);
		}
		for (String file : packageFile.list()) {
			if(!file.equals(new String("Inventory"))) {
				currentStorageContent.add(file);
			}
		}
		fillComboBox(selectedFile);
	}

	private void fillComboBox() {
		comboBox.removeAllItems();
		for (String string : currentStorageContent) {
			comboBox.addItem(string);
		}
	}
	private void fillComboBox(File selectedFile) {
		comboBox.removeAllItems();
		for (String string : currentStorageContent) {
			comboBox.addItem(string);
		}
		comboBox.setSelectedItem(selectedFile.getName());
	}

	public void repaint() {
		if (textField != null) {
			try {
				
				
				
			} catch (NullPointerException e) {
			}
		}
		if (comboBox.getItemCount() == 0) {
			message("The game cannot be saved. You have not selected a storage!");
		}
	}


	private void init() {
		textField.setFont(new Font("Blackadder ITC", Font.PLAIN, 30));
		textField.setSize((int) (width / 10f * 4), (int) (height / 10f));
		textField.setLocation(width / 2 - textField.getWidth() / 2, (int) (height * 0.2f));
		textField.setBackground(Color.GRAY);
		textField.setForeground(Color.WHITE);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
		});

		comboBox.setSize(textField.getWidth(), textField.getHeight());
		comboBox.setLocation((int) (width * 0.5f - comboBox.getWidth() * 0.5f),
				textField.getY() + textField.getHeight());
		comboBox.setFocusable(false);
		comboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
		UIManager.put("ComboBox.squareButton", Boolean.FALSE);
		comboBox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton b = new JButton();
				b.setVisible(false);
				return b;
			}
		});
		comboBox.setFont(new Font("Blackadder ITC", Font.PLAIN, 20));
		comboBox.setFocusable(false);

		
	}
	
	public void addNewStorage() {
		textField.setFocusable(false);
		String fileName = null;
		try {
			fileName = textField.getText().toUpperCase().charAt(0)
					+ textField.getText(1, textField.getText().length() - 1);
		} catch (BadLocationException e2) {
			e2.printStackTrace();
		}
		File file = new File(packageFile + "\\" + fileName).getAbsoluteFile();
		if (file.exists()) {
			message(fileName + " already exists!");
		} else {
			checkFiles();
			if (currentStorageContent.size() < 10) {
				try {
					file.createNewFile();
					double image_width = 99.88683319091797;
					double image_height = height*0.6d;
					double playerPosX = width * (128d/1600d);
					double playerPosY = height - image_height;
					boolean schrankOffen = false;
					Storage_Class.save(fileName, playerPosX, playerPosY, image_width, image_height, 'r', "Take", schrankOffen, Storage_Class.getInventoryObjects(fileName, 9));
					textField.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				checkFiles(file);
				message("");
			} else {
				message("Storage is full!");
			}
		}
				
	}
	
	public void deleteCurrentStorage() {
		File[] file = inventoryFile.listFiles();
		for (int i = 0; i < file.length; i++) {
			if(file[i].getName().equals(comboBox.getSelectedItem() + "_Inventory")) {
				file[i].delete();
				break;
			}
		}
		file = packageFile.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].getName().equals(comboBox.getSelectedItem())) {
				file[i].delete();
				checkFiles();
				break;
			}
		}
	}

	public void addObjects(JLabel label) {
		if (!added) {
			label.add(textField);
			label.add(comboBox);
			label.add(messageLabel);
			added = true;
		}
	}

	public void removeObjects(JLabel label) {
		if (added) {
			label.remove(textField);
			label.remove(comboBox);
			label.remove(messageLabel);
			added = false;
		}
	}

	public boolean getBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
	}

	public JTextField getTextField() {
		return textField;
	}

	public String getStorage() {
		return (String) comboBox.getSelectedItem();

	}

	public boolean getFirstStorage() {
		return firstStorage;
	}

}
