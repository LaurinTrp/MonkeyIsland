package Main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Currency;

public class Constants {
	public static class GameConstants {

		public static final int STARTPANEL = 0;
		public static final int GAMEPANEL = 1;
		public static final int SETTINGSPANEL = 2;
		public static final int EXITGAME = 3;
		public static final int PAUSEMENU = 4;
		public static final int SKILLSPANEL = 6;
		public static final int INVENTORYPANEL = 7;
		public static final int CHESTPANEL = 8;
		public static final int SKILLSCHESTPANEL = 9;
		public static final int FIRSTSTORAGEPANEL = 10;
		public static final int INVENTORYCHESTPANEL = 11;

		public static final int SAVE = 5;

		public static class GuybrushConstants {

			public static final int LEFT = 0;
			public static final int RIGHT = 1;
			public static final int UP = 2;
			public static final int DOWN = 3;
		}

		public static class SkillConstants {
			public static final int CLOSE = 0;
			public static final int OPEN = 1;
			public static final int SPEAK = 2;
			public static final int GIVE = 3;
			public static final int TAKE = 4;
			public static final int SPECTATE = 5;
			public static final int PULL = 6;
			public static final int PUSH = 7;
			public static final int USE = 8;
		}

	}

	public static class Fonts {

		public static Font getFont(int height) {
			Font font = null;
			try {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				if(OperatingSystem.currentSystem == OperatingSystem.linux) {
					height -= 80;
				}
				font = Font.createFont(Font.TRUETYPE_FONT, new File(FileConstants.FONTS_FOLDER + "BITCBLKAD.ttf")).deriveFont((float) height);
				ge.registerFont(font);
			} catch (IOException | FontFormatException e) {
				e.printStackTrace();
			}
			return font;
		}
	}

	public static class OperatingSystem {
		public static byte currentSystem;

		public final static byte linux = 0;
		public final static byte windows = 1;
	}

	public static class FileConstants {
		public final static String PACKAGEPATH = System.getenv("FilePath" + OperatingSystem.currentSystem);// "D:/Programmieren/Java/Wörkbensch/MonkeyIsland_New/MonkeyIsland//
																											// 2.0/";

		public final static String FONTS_FOLDER = PACKAGEPATH + "Fonts" + File.separator;

		public static class PictureConstants {
			public final static String PICTURESPATH = PACKAGEPATH + "Pictures" + File.separator;

			private final static String PAUSEANIMATIONS = PACKAGEPATH + "Pictures" + File.separator + "PauseAnimation"
					+ File.separator;
			public final static String HOMEPACKAGE = PAUSEANIMATIONS + "HomeButton" + File.separator;
			public final static String SAVEPACKAGE = PAUSEANIMATIONS + "SaveButton" + File.separator;
			public final static String PLAYPACKAGE = PAUSEANIMATIONS + "PlayButton" + File.separator;

			public final static String INVENTORY = PACKAGEPATH + "Pictures" + File.separator + "Inventory"
					+ File.separator;
			public final static String INVENTORYOBJECTS = PACKAGEPATH + "Pictures" + File.separator + "Inventory"
					+ File.separator + "Objects" + File.separator;
		}

		public static class StorageConstants {
			public final static String STORAGEPATH = PACKAGEPATH + "Storages" + File.separator;
			public final static String INVENTORYPATH = STORAGEPATH + "Inventory" + File.separator;

			public static String[] STARTSTORAGE = {};
		}
	}

	public static class KeyConstants {
		public static final int NO_KEY = -1;
		public static final int KEY_W = 0;
		public static final int KEY_A = 1;
		public static final int KEY_S = 2;
		public static final int KEY_D = 3;
	}
}
