import java.io.File;
import java.util.Objects;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
public class MusicPlayer {
    private Clip clip;
    private FloatControl gainControl;
    Clip explosionSound = loadSoundEffect("explosion.wav");
    Clip landingSound = loadSoundEffect("landing.wav");

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

    public void playExplosionSound() {
        if (explosionSound != null) {
            explosionSound.setFramePosition(0); // Reset the clip to the beginning
            explosionSound.start();
        }
    }

    public void playLandingSound() {
        if (landingSound != null) {
            landingSound.setFramePosition(0); // Reset the clip to the beginning
            landingSound.start();
        }
    }
}
