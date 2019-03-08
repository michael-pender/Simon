package edu.apsu.simon;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

//import edu.apsu.simon.R; not needed

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int topRightSound;
    private static int topLeftSound;
    private static int bottomRightSound;
    private static int bottomLeftSound;


    public SoundPlayer(Context context) {

        //SoundPool (int maxStreams, int streamType, int srcQuality
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        topLeftSound = soundPool.load(context, R.raw.topLeft, 1);
        topRightSound = soundPool.load(context, R.raw.topRight, 1);
        bottomLeftSound = soundPool.load(context, R.raw.bottomLeft, 1);
        bottomRightSound = soundPool.load(context, R.raw.bottomRight, 1);
    }

    public void playTopRightSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(topRightSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playTopLeftSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(topLeftSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playbottomRightSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(bottomRightSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playbottomLeftSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(bottomLeftSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}