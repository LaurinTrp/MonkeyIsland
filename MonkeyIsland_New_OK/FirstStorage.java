package MonkeyIsland_New_OK;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FirstStorage extends JFrame{
	private JLabel label = new JLabel("Add your first storage");
	private JTextField textField = new JTextField();
	private JButton saveButton = new JButton("Save");
	private boolean finished;

	private FontMetrics fm;
	public FirstStorage(int width, int height, char mode) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("First Storage");
		setLayout(null);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		label.setFont(new Font("Blackadder ITC", Font.PLAIN, 50));
		fm = label.getFontMetrics(label.getFont());
		label.setSize(fm.stringWidth(label.getText()) + 10, fm.getHeight());
		label.setLocation(0, 0);
		add(label);
		
		textField.setFont(label.getFont().deriveFont(30));
		textField.setSize(label.getWidth(), label.getHeight());
		textField.setText("MyFirstStorage");
		textField.setLocation(0, label.getHeight());
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
		});
		add(textField);
		
		if(mode == 'c') saveButton.setText("Save (R2)");
		saveButton.setFont(label.getFont().deriveFont(30));
		saveButton.setSize(textField.getWidth(), textField.getHeight());
		saveButton.setLocation(0, textField.getY() + textField.getHeight());
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File packageFiles = new File("New_OK/MonkeyIsland_New_OK/Storage").getAbsoluteFile();
				File newFile = new File(packageFiles + "/" + textField.getText().trim()).getAbsoluteFile();
				File inventoryFile = new File(packageFiles + "/Inventory/" + textField.getText().trim() + "_Inventory");
				try {
					newFile.createNewFile();
					double image_width = 99.88683319091797;
					double image_height = height*0.6d;
					double playerPosX = width * (128d/1600d);
					double playerPosY = height - image_height;
					boolean schrankOffen = false;
					Storage_Class.save(newFile.getName(), playerPosX, playerPosY, image_width, image_height, 'r', "Take", schrankOffen, Storage_Class.getInventoryObjects(newFile.getName(), 9));
				
					inventoryFile.createNewFile();
					Storage_Class.writeFirstInventory(inventoryFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finished = true;
				dispose();
			}
		});
		add(saveButton);
		if(mode == 'k') {
			setSize(label.getWidth(), label.getHeight() + textField.getHeight() + saveButton.getHeight());
		}
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}
	
	@Override
	public void repaint() {
		super.repaint();
		if(textField.getText().trim().equals("")) {
			saveButton.setEnabled(false);
		}else {
			saveButton.setEnabled(true);
		}
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
}
