package edu.apsu.simon;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;

//import edu.apsu.simon.R; not needed

public class SoundPlayer extends AppCompatActivity {

    private static SoundPool soundPool;
    private static int topRightSound;
    private static int topLeftSound;
    private static int bottomRightSound;
    private static int bottomLeftSound;
    private static int gameOver;


    public SoundPlayer(Context context) {

        //SoundPool (int maxStreams, int streamType, int srcQuality
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        topLeftSound = soundPool.load(context,R.raw.topleft , 1);
        topRightSound = soundPool.load(context, R.raw.topright, 1);
        bottomLeftSound = soundPool.load(context, R.raw.bottomleft, 1);
        bottomRightSound = soundPool.load(context, R.raw.bottomright, 1);
        gameOver = soundPool.load(context, R.raw.gameover, 1);
    }

    final public SoundPlayer playTopRightSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(topRightSound, 1.0f, 1.0f, 1, 0, 1.0f);
        return null;
    }

    final public SoundPlayer playTopLeftSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(topLeftSound, 1.0f, 1.0f, 1, 0, 1.0f);
        return null;
    }

    final public SoundPlayer playBottomRightSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(bottomRightSound, 1.0f, 1.0f, 1, 0, 1.0f);
        return null;
    }

    final public SoundPlayer playBottomLeftSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(bottomLeftSound, 1.0f, 1.0f, 1, 0, 1.0f);
        return null;
    }

   final public void gameOverSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(gameOver, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}