package GUI.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import FileManagers.FileGet;
import FileManagers.FileStorage;
import FileManagers.ImageLoader;
import GUI.MainGuiElements.Button;
import GUI.MainGuiElements.Label;
import GUI.MainGuiElements.Panel;
import Handlers.ActionHandler;
import Main.Constants;

public class SettingsPanel extends Panel {
	private Label label;
	private JTree treeStorage;
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel root;
	private JTextField textField;

	public SettingsPanel(Label label, Dimension dimension) {
		super(dimension);
		this.label = label;

		setBackgroundImage(ImageLoader.getImageByFilename("Backgrounds/TurningWheel2.png"));
		initComponents();
	}

	private void initComponents() {
		rootNode = new DefaultMutableTreeNode("Storage");
		root = new DefaultTreeModel(rootNode);
		addItems("", "");
		treeStorage = new JTree();
		treeStorage.setModel(root);
		treeStorage.setSize(800, 600);
		treeStorage.setLocation(100, 100);
		treeStorage.setBackground(new Color(0, 0, 0, 0));
		treeStorage.setFont(Constants.Fonts.getFont(100));
		treeStorage.setForeground(Color.WHITE);
		for (int i = 0; i < root.getChildCount(rootNode); i++) {
			treeStorage.expandRow(i);
		}
		TreeCellRenderer renderer = new TreeCellRenderer();
		treeStorage.setCellRenderer(renderer);
		treeStorage.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				for (int i = 0; i < rootNode.getChildCount(); i++) {
					if (e.getPath().getLastPathComponent() == rootNode.getChildAt(i)) {
						renderer.setSelectedRow(i);
					}
				}
				
			}
		});
		treeStorage.setRootVisible(false);
//		add(treeStorage);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(getWidth() / 2, 200);
		scrollPane.setLocation(getWidth() / 2 - scrollPane.getWidth() / 2,
				getHeight() / 2 - scrollPane.getHeight() / 2);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 2, true));
		scrollPane.add(treeStorage);
		scrollPane.getViewport().add(treeStorage);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				super.configureScrollBarColors();
				this.thumbColor = Color.WHITE;
				this.trackColor = (new Color(0, 0, 0, 50));
			}
		});
		add(scrollPane);
		
		textField = new JTextField();
		textField.setSize(getWidth() / 2, 100);
		textField.setLocation(getWidth() / 2 - textField.getWidth() / 2,
				scrollPane.getY() - textField.getHeight() - 100);
		textField.setFont(Constants.Fonts.getFont(textField.getHeight() + 30));
		textField.addCaretListener(new CaretHandler());
		add(textField);
		
		Button buttonAdd = new Button(getWidth() / 2, 100, "Add", null, null, true);
		buttonAdd.centerHorizontal(getWidth());
		buttonAdd.setLocation(buttonAdd.getX(), getHeight() - buttonAdd.getHeight() - 200);
		buttonAdd.setFont(Constants.Fonts.getFont(buttonAdd.getHeight() + 50));
		buttonAdd.setBackground(new Color(0, 0, 0, 0));
		buttonAdd.addActionListener(new ActionHandler(textField) {
			@Override
			public void buttonAction() {
				if (!textField.getText().equals("")) {
					FileStorage.writeStorage(
							Constants.FileConstants.StorageConstants.STORAGEPATH + textField.getText() + ".txt", "");
					addItems("", textField.getText());
					textField.setText("");
				}
			}
		});
		add(buttonAdd);

		Button buttonRemove = new Button(getWidth() / 2, 100, "Remove", null, null, true);
		buttonRemove.centerHorizontal(getWidth());
		buttonRemove.setLocation(buttonRemove.getX(), getHeight() - buttonRemove.getHeight() - 100);
		buttonRemove.setFont(Constants.Fonts.getFont(buttonRemove.getHeight() + 50));
		buttonRemove.addActionListener(new ActionHandler() {
			@Override
			public void buttonAction() {
				super.buttonAction();
				removeItem();
			}
		});
		add(buttonRemove);
	}


	public void addItems(String filter, String newFile) {
		File[] files = FileGet.getContainingFiles(Constants.FileConstants.StorageConstants.STORAGEPATH);
		rootNode.removeAllChildren();
		for (int i = 0; i < files.length; i++) {
			if (filter.equals("")
					|| files[i].getName().replaceAll(".txt", "").toLowerCase().contains(filter.toLowerCase())) {
				if(!files[i].getName().equals("Inventory")) {
					rootNode.add(new DefaultMutableTreeNode(files[i].getName().replaceAll(".txt", "")));
				}
			}
		}
		if (!newFile.equals("")) {
			for (int i = 0; i < rootNode.getChildCount(); i++) {
				if (rootNode.getChildAt(i).toString().toLowerCase().equals(newFile.toLowerCase())) {
					((TreeCellRenderer) treeStorage.getCellRenderer()).setSelectedRow(i);
					break;
				}
			}
		}

		root.reload();
	}

	public void removeItem() {
		File[] files = FileGet.getContainingFiles(Constants.FileConstants.StorageConstants.STORAGEPATH);
		ArrayList<File> filesArrayList = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			filesArrayList.add(files[i]);
		}
		for (File file : filesArrayList) {
			if(file.getName().equals("Inventory")) {
				filesArrayList.remove(file);
				file.delete();
				break;
			}
		}
		File inventoryFile = new File(Constants.FileConstants.StorageConstants.INVENTORYPATH + files[((TreeCellRenderer) treeStorage.getCellRenderer()).getSelectedRow()].getName());
		inventoryFile.delete();
		filesArrayList.get(((TreeCellRenderer) treeStorage.getCellRenderer()).getSelectedRow()).delete();
		addItems("", "");
	}

	private class CaretHandler implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent e) {
			addItems(textField.getText().strip(), "");
		}

	}

	private class TreeCellRenderer extends DefaultTreeCellRenderer {
		private int selectedRow;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean exp, boolean leaf,
				int row, boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
			setForeground(new Color(0, 0, 0));
			setBackground(new Color(0, 0, 0, 75));
			setOpaque(true);
			if (row == selectedRow) {
				setForeground(Color.RED);
			}
			setSize(100, 100);
			setIcon(new ImageIcon(ImageLoader.getImageByFilename("Cursors/Cursor_Banana.png")));

			return this;
		}

		public void setSelectedRow(int row) {
			this.selectedRow = row;
		}

		public int getSelectedRow() {
			return selectedRow;
		}
	}

	public int getNodeCount() {
		return rootNode.getChildCount();
	}

	public String getSelectedNode() {
		try {
			return rootNode.getChildAt(((TreeCellRenderer) treeStorage.getCellRenderer()).getSelectedRow()).toString();
		}catch(ArrayIndexOutOfBoundsException e) {
			label.setMode(Constants.GameConstants.FIRSTSTORAGEPANEL);
			return "";
		}
	}
}
