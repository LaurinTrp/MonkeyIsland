package Constants;

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

	public static class FileConstants {
		public final static String PACKAGEPATH = "D:\\Programmieren\\Java\\Git\\MonkeyIsland\\MonkeyIsland_New\\";

		public static class PictureConstants {

			private final static String PAUSEANIMATIONS = PACKAGEPATH + "Pictures\\PauseAnimation\\";
			public final static String HOMEPACKAGE = PAUSEANIMATIONS + "HomeButton\\";
			public final static String SAVEPACKAGE = PAUSEANIMATIONS + "SaveButton\\";
			public final static String PLAYPACKAGE = PAUSEANIMATIONS + "PlayButton\\";

			public final static String INVENTORY = PACKAGEPATH + "Pictures\\Inventory\\";
			public final static String INVENTORYOBJECTS = PACKAGEPATH + "Pictures\\Inventory\\Objects\\";
		}

		public static class StorageConstants {
			public final static String STORAGEPATH = PACKAGEPATH + "Storages\\";
			public final static String INVENTORYPATH = STORAGEPATH + "Inventory\\";
			

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
