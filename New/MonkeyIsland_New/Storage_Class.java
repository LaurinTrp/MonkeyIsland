package MonkeyIsland_New;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import MonkeyIsland_New.InventoryClasses.Inventory_Object;
import MonkeyIsland_New.InventoryClasses.LOL;
import MonkeyIsland_New.InventoryClasses.Test;


public class Storage_Class {

	private static File file;
	private static File fileInventory;
	private static BufferedWriter writer;
	private static BufferedReader reader;
	private static FileReader fileReader;
	private static ArrayList<String> fileContent = new ArrayList<>();

	public static ArrayList<String> getFileContent(String name, char type) {
		int fileContentSize = fileContent.size();
		for (int i = 0; i < fileContentSize; i++) {
			fileContent.remove(0);
		}
		if (type == 's') {
			file = new File("New/" + Storage_Class.class.getPackageName() + "/Storage/" + name);
		} else if (type == 't') {
			file = new File("New/" + Storage_Class.class.getPackageName() + "/Texts/" + name);
		}

		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reader = new BufferedReader(fileReader);

		try {
			String line = reader.readLine();
			while (line != null) {
				fileContent.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	public static void save(String fileName, double playerPosX, double playerPosY, double image_width, double image_height, char direction,
			Inventory_Object[] inventoryObjects) {
		file = new File("New/" + Storage_Class.class.getPackageName() + "/Storage/" + fileName);
		fileInventory = new File("New/" + Storage_Class.class.getPackageName() + "/Storage/Inventory/" + fileName + "_Inventory");
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(playerPosX + "\n" + playerPosY + "\n" + image_width + "\n" + image_height + "\n" + direction);
			if (!fileInventory.exists()) {
				fileInventory.createNewFile();
			}
			writer.flush();
			writer = new BufferedWriter(new FileWriter(fileInventory));
			for (int i = 0; i < inventoryObjects.length; i++) {
				try {
					writer.write(inventoryObjects[i].getName() + " " + inventoryObjects[i].getPosX() + " "
							+ inventoryObjects[i].getPosY() + " " + inventoryObjects[i].getWidth() + " "
							+ inventoryObjects[i].getHeight() + " " + inventoryObjects[i].getID() + "\n");
				} catch (NullPointerException e) {
					writer.write("null\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Inventory_Object[] getInventoryObjects(String fileName, int maxInventorySize) {
		fileInventory = new File("New/" + Storage_Class.class.getPackageName() + "/Storage/Inventory/" + fileName + "_Inventory");
		String[] inventoryObjectsString = new String[maxInventorySize];
		Inventory_Object[] inventoryObjects = new Inventory_Object[maxInventorySize];
		int counter = 0;
		String line;
		try {
			if (fileInventory.exists()) {
				reader = new BufferedReader(new FileReader(fileInventory));
				line = reader.readLine();
				while (line != null) {
					inventoryObjectsString[counter] = line;
					counter++;
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] linesStrings = null;
		for (int i = 0; i < inventoryObjects.length; i++) {
			try {
				linesStrings = inventoryObjectsString[i].split(" ");
				try {
					inventoryObjects[i] = checkObjects(linesStrings[0], Integer.parseInt(linesStrings[1]),
							Integer.parseInt(linesStrings[2]), Integer.parseInt(linesStrings[3]),
							Integer.parseInt(linesStrings[4]), Integer.parseInt(linesStrings[5]));
				} catch (IndexOutOfBoundsException e) {
				}
			} catch (NullPointerException e) {
				try {
					writer = new BufferedWriter(new FileWriter(fileInventory));
				} catch (IOException e2) {
					e.printStackTrace();
				}
				for (int j = 0; j < maxInventorySize; j++) {
					try {
						writer.write("null");
					} catch (IOException e2) {
						e.printStackTrace();
					}
				}
			}
		}
		return inventoryObjects;
	}

	private static Inventory_Object checkObjects(String name, int posX, int posY, int width, int height, int ID) {
		Inventory_Object object = null;
		if (name.equals(new String("LOL"))) {
			object = new LOL(posX, posY, width, height, ID);
		}
		if (name.equals(new String("Test"))) {
			object = new Test(posX, posY, width, height, ID);
		}
		return object;
	}
}
