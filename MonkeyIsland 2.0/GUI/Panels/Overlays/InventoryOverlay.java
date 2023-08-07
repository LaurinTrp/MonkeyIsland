package GUI.Panels.Overlays;

import java.util.ArrayList;

import FileManagers.FileStorage;
import GUI.Buttons.InventoryButton;
import GUI.MainGuiElements.Overlay;
import Handlers.ActionHandler;
import Inventory.InventoryObject;

public class InventoryOverlay extends Overlay{


	private static ArrayList<InventoryObject> inventoryObjectsInventory = new ArrayList<>();
	private final int buttonSize = 150;
	private static InventoryButton[] inventoryButtons = new InventoryButton[9];
	public static boolean usable;
	private int mainBackground;
	public static InventoryObject selectedObject;
	
	public InventoryOverlay(int width, int height) {
		super(width, height);
		initComponents();
		fillInventoryButtons(true);
		refreshButtons();
	}
	
	private void initComponents() {

		for (int i = 0; i < inventoryButtons.length; i++) {
			inventoryButtons[i] = new InventoryButton(buttonSize, buttonSize, "", null, this);
			inventoryButtons[i].centerHorizontal(getSize().width);
			inventoryButtons[i].centerVertical(getSize().height);
			inventoryButtons[i].setLocation(inventoryButtons[i].getX() + ((i % 3) - 1) * buttonSize,
					inventoryButtons[i].getY() + (((int) (i / 3f) - 1) * buttonSize));
			inventoryButtons[i].addActionListener(new ActionHandler(inventoryButtons[i]){
				@Override
				public void buttonAction() {
					super.buttonAction();
					selectedObject = ((InventoryButton)super.getButton()).getContainingObject();
				}
			});
			add(inventoryButtons[i]);
		}
	}
	
	public static void refreshButtons() {
		for (int i = 0; i < inventoryButtons.length; i++) {
			inventoryButtons[i].createFullButton();
		}
	}
	
	public InventoryObject getSelectedObject() {
		return selectedObject;
	}

	public static void fillInventoryButtons(boolean startup) {
		if(!startup) {
			removeDuplicates();
		}
		for (InventoryObject inventoryObject : inventoryObjectsInventory) {
			for (int i = 0; i < inventoryButtons.length; i++) {
				if (inventoryButtons[i].getContainingObject() == null) {
					inventoryButtons[i].setContainingObject(inventoryObject);
					break;
				}
			}
		}
	}

	public void saveInventory(String filePath) {
		ArrayList<String> ids = new ArrayList<>();
		for (int i = 0; i < inventoryButtons.length; i++) {
			if(inventoryButtons[i].getContainingObject() != null) {
				ids.add(""  +inventoryButtons[i].getContainingObject().getPersonalId());
			}
		}
		String[] idsArray = new String[ids.size()];
		for (int i = 0; i < idsArray.length; i++) {
			idsArray[i] = ids.get(i);
		}
		FileStorage.writeStorage(filePath, idsArray);
	}
	
	private static void removeDuplicates() {
		for (InventoryButton inventoryButton : inventoryButtons) {
			inventoryButton.createEmptyButton();
		}
		for (int i = inventoryObjectsInventory.size()-1; i >= 0; i--) {
			for (int j = inventoryObjectsInventory.size()-1; j >= 0; j--) {
				if(i != j && inventoryObjectsInventory.get(i).getId()== inventoryObjectsInventory.get(j).getId()) {
					inventoryObjectsInventory.remove(j);
				}
			}
		}
	}
	
	public static ArrayList<InventoryObject> getInventoryObjectsInventory() {
		return inventoryObjectsInventory;
	}
	
	public void setSelectedObject(InventoryObject selectedObject) {
		this.selectedObject = selectedObject;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
	private InventoryObject getHoveredObject() {
		for (InventoryButton inventoryButton : inventoryButtons) {
			if(inventoryButton.isHovered()) {
				return inventoryButton.getContainingObject();
			}
		}
		return null;
	}

}
