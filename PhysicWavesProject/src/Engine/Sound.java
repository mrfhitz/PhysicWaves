package Engine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    int loop;

    public Sound(File f, int loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if (loop > 1) {
                this.loop = loop;
            } else {
                this.loop = -1;
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound. " + ex.getMessage() + ".");
        }
    }

    public Sound(File f) {
        this(f, -1);
    }

    public void playSound() {
            clip.stop();
        
        if(loop == -1)
        clip.start();
        else{
            clip.loop(loop);
        }
    }

    public void stopSound() {
        clip.stop();
    }

    public void closeSoundFile() {
        clip.close();
    }
    
    public boolean isRunning(){
        return clip.isRunning();
    }
}//end class
