import java.io.File;
import java.util.Objects;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
public class MusicPlayer {
    private Clip clip;
    private FloatControl gainControl;
    Clip explosionSound = loadSoundEffect("explosion.wav");
    Clip landingSound = loadSoundEffect("landing.wav");
    Clip gameOverSound = loadSoundEffect("game-over.wav");
    Clip victorySound = loadSoundEffect("victory.wav");
    Clip newLevelSound = loadSoundEffect("new-level.wav");

    public void playMusic(String filePath) {
        File musicPath= new File(filePath);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public Clip loadSoundEffect(String filePath) {
        File musicPath= new File(filePath);
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public void playSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
