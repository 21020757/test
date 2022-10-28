package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip[] clip = new Clip[7];
    URL[] soundURL = new URL[7];

    public Sound() {
        //MUSIC
        soundURL[0] = getClass().getResource("/sound/title screen.wav");
        soundURL[1] = getClass().getResource("/sound/theme.wav");

        //SE
        soundURL[2] = getClass().getResource("/sound/bomb plant.wav");
        soundURL[3] = getClass().getResource("/sound/explode.wav");
        soundURL[4] = getClass().getResource("/sound/start.wav");
        soundURL[5] = getClass().getResource("/sound/die.wav");
        soundURL[6] = getClass().getResource("/sound/level complete.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip[i] = AudioSystem.getClip();
            clip[i].open(ais);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(int i) {
        clip[i].start();
    }

    public void loop(int i) {
        clip[i].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        for(Clip clips: clip) {
            if(clips != null) {
                if(clips.isActive()) {
                    clips.stop();
                }
            }
        }
    }
}
