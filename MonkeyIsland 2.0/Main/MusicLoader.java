package Main;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicLoader {
	
	private static File sound;
	private static Clip clip;
	
	
	public MusicLoader(String fileName) {
		sound = new File(Constants.FileConstants.PACKAGEPATH + "/SoundFiles/" + fileName);
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playN() {
		clip.loop(0);
	}
	
	public static void play(String fileName, float volume, int loop) {
		sound = new File(Constants.FileConstants.PACKAGEPATH + "/SoundFiles/" + fileName);
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			
			clip.loop(loop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}