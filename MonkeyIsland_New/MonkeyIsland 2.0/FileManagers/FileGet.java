package FileManagers;

import java.io.File;

public class FileGet {
	public static File[] getContainingFiles(String folder) {
		return new File(folder).listFiles();
	}

	public static int getFolderCount(String folder) {
		return new File(folder).listFiles().length;
	}
}
