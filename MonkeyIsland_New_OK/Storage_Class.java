package MonkeyIsland_New_OK;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import MonkeyIsland_New_OK.InventoryClasses.Inventory_Object;
import MonkeyIsland_New_OK.InventoryClasses.Key;
import MonkeyIsland_New_OK.InventoryClasses.Present;
import MonkeyIsland_New_OK.InventoryClasses.Banana;
import MonkeyIsland_New_OK.InventoryClasses.Beer;

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
			file = new File("New_OK/" + Storage_Class.class.getPackageName() + "/Storage/" + name).getAbsoluteFile();
		} else if (type == 't') {
			file = new File("New_OK/" + Storage_Class.class.getPackageName() + "/Texts/" + name).getAbsoluteFile();
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

	public static void save(String fileName, double playerPosX, double playerPosY, double image_width,
			double image_height, char direction, String skill, boolean schrankOffen,
			Inventory_Object[] inventoryObjects) {
		file = new File("New_OK/" + Storage_Class.class.getPackageName() + "/Storage/" + fileName).getAbsoluteFile();
		fileInventory = new File(
				"New_OK/" + Storage_Class.class.getPackageName() + "/Storage/Inventory/" + fileName + "_Inventory")
						.getAbsoluteFile();
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(playerPosX + "\n" + playerPosY + "\n" + image_width + "\n" + image_height + "\n" + direction
					+ "\n" + skill + "\n" + schrankOffen);
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
	
	public static void writeFirstInventory(File inventoryFile) {
		try {
			writer = new BufferedWriter(new FileWriter(inventoryFile));
			writer.write("null\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Inventory_Object[] getInventoryObjects(String fileName, int maxInventorySize) {
		fileInventory = new File(
				"New_OK/" + Storage_Class.class.getPackageName() + "/Storage/Inventory/" + fileName + "_Inventory")
						.getAbsoluteFile();
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
		String[] lineStrings = new String[6];
		for (int i = 0; i < inventoryObjectsString.length; i++) {
			try {
				lineStrings = inventoryObjectsString[i].split(" ");
				if (!lineStrings[0].equals("null")) {
					inventoryObjects[i] = checkObjects(lineStrings[0], Integer.parseInt(lineStrings[1]),
							Integer.parseInt(lineStrings[2]), Integer.parseInt(lineStrings[3]),
							Integer.parseInt(lineStrings[4]), Integer.parseInt(lineStrings[5]));
				}
			} catch (NullPointerException e) {
			}
		}
		return inventoryObjects;
	}

	private static Inventory_Object checkObjects(String name, int posX, int posY, int width, int height, int ID) {
		Inventory_Object object = null;
		if (name.equals(new String("Banana"))) {
			object = new Banana(posX, posY, width, height, ID);
		}
		if (name.equals(new String("Beer"))) {
			object = new Beer(posX, posY, width, height, ID);
		}
		if (name.equals(new String("Key"))) {
			object = new Key(posX, posY, width, height, ID);
		}
		if (name.equals(new String("Present"))) {
			object = new Present(posX, posY, width, height, ID);
		}
		return object;
	}
}
