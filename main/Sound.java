package main;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class Sound {
    public static void playSound(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.start();
            }
            else {
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
