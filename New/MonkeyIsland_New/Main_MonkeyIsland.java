package MonkeyIsland_New;


/**
 * 
 * @author laurin.trapp
 * @version 1
 *
 */

public class Main_MonkeyIsland {
	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
		Frame_Keyboard frame_keyboard = null;
		while (startFrame.isActive()) {
			startFrame.repaint();
		}
		switch (startFrame.getMode()) {
		case 'k':
			frame_keyboard = new Frame_Keyboard();
			while (true) {
				frame_keyboard.repaint();
			}
		default:
			break;
		}
	}
}
