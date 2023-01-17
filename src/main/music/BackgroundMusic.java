package music;

import java.applet.AudioClip;

public class BackgroundMusic {
    AudioClip clip = null;

    public AudioClip getClip() {
        return clip;

    }

    public void setClip(AudioClip clip) {
        this.clip = clip;
    }

    //EFFECT: play background music
    public void play() {
        if (getClip() != null) {
            getClip().play();
        }
    }

    //EFFECT: loop background music
    public void loop() {
        if (getClip() != null) {
            getClip().loop();
        }
    }

    //EFFECT: stop background music
    public void stop() {
        if (getClip() != null) {
            getClip().stop();
        }
    }
}

