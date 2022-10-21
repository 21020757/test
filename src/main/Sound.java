package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        //MUSIC
        soundURL[0] = getClass().getResource("/sound/title screen.wav");
        soundURL[1] = getClass().getResource("/sound/theme.wav");

        //SE
        soundURL[2] = getClass().getResource("/sound/bomb plant.wav");
        soundURL[3] = getClass().getResource("/sound/explode.wav");
        soundURL[4] = getClass().getResource("/sound/start.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
