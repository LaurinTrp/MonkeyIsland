package FileManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage {

	public static void writeStorage(String filePath, String... inputs) {

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			for (int i = 0; i < inputs.length; i++) {
				writer.write(inputs[i] + ";");
			}
			writer.flush();

			writer.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String[] loadStorage(String filePath) {
		String[] output = null;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader);
				String line = reader.readLine();
				try {
					output = line.split(";");
				} catch (Exception e) {
					FileStorage.writeStorage(file.getAbsolutePath(), "");
				}
				reader.close();
				fileReader.close();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;

	}

}
