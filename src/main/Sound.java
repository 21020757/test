package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        //MUSIC
        soundURL[0] = getClass().getResource("/sound/Bomberman-_NES_-Music-Stage-Theme.wav");
        soundURL[1] = getClass().getResource("/sound/Bomberman-_NES_-Music-Level-Start.wav");
        soundURL[2] = getClass().getResource("/sound/Bomberman-_NES_-Music-Level-Complete.wav");
        soundURL[3] = getClass().getResource("/sound/Bomberman-_NES_-Music-Title-Screen.wav");
        soundURL[4] = getClass().getResource("/sound/Bomberman-_NES_-Music-Just-Died.wav");
        //SE
        soundURL[5] = getClass().getResource("/sound/Bomberman SFX (1).wav");
        soundURL[6] = getClass().getResource("/sound/Bomberman SFX (2).wav");
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
