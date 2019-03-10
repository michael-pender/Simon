package edu.apsu.simon;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game2 extends AppCompatActivity implements ColorFragment.PushListener{

    private ColorFragment red;
    private ColorFragment green;
    private ColorFragment blue;
    private ColorFragment yellow;

    private int sequenceIndex = 0;
    private ArrayList<ColorFragment> sequence;

    private ColorFragment[] colors;

    private TextView indicator;

    private boolean challenging;
    private int challengeIndex = 0;

    private int score = 0;

    private SoundPlayer sound;
    private SoundPool soundPool;
    private HashSet<Integer> soundsLoaded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game1_main);


        sound = new SoundPlayer(this);

        indicator = findViewById(R.id.indicator);

        colors = new ColorFragment[4];
        colors[0] = findViewById(R.id.topleft);
        colors[1] = findViewById(R.id.topright);
        colors[2] = findViewById(R.id.bottomleft);
        colors[3] = findViewById(R.id.bottomright);

        colors[0].setPushListener(this);
        colors[1].setPushListener(this);
        colors[2].setPushListener(this);
        colors[3].setPushListener(this);

        soundsLoaded = new HashSet<Integer>();

        initSequence();
        doSequence();
    }

    private void initSequence() {
        sequence = new ArrayList<ColorFragment>();
        incSequence();
    }

    private void doSequence() {

        indicator.setText("" + (sequenceIndex + 1));

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                sequence.get(sequenceIndex).on();

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sequence.get(sequenceIndex).off();

                        Log.i("app","sequence " + sequenceIndex);
                        sequenceIndex++;
                        if(sequenceIndex < sequence.size()) {
                            doSequence();
                        } else {
                            Log.i("app","challenge ?");
                            indicator.setText("?");
                            sequenceIndex = 0;
                            challenging = true;
                        }
                    }
                }, 300);
            }
        }, 1000);
    }


    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(4);
        soundPool = spBuilder.build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) { // success
                    soundsLoaded.add(sampleId);
                    Log.i("SOUND", "Sound loaded " + sampleId);
                } else {
                    Log.i("SOUND", "Error cannot load sound status = " + status);
                }
            }
        });
    }



    private void incSequence() {
        indicator.setText("0");
        sequence.add(colors[(int) (Math.random() * colors.length)]);
        doSequence();
    }

    @Override
    public void onPush(View v) {
        if (challenging) {
            if (v == sequence.get(challengeIndex)) {
                indicator.setText("" + (challengeIndex + 1));
                Log.i("app", "good " + challengeIndex);
                challengeIndex++;

                if (challengeIndex >= sequence.size()) {
                    challenging = false;
                    challengeIndex = 0;
                    indicator.setText("\u2714"); // CHECK MARK

                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            score++; //added
                            incSequence();
                        }
                    }, 1000);
                }
            } else {
                challenging = false;
                challengeIndex = 0;
                sound.gameOverSound();

                //Show Result
                Intent intent = new Intent(getApplicationContext(), result.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;

            soundsLoaded.clear();
        }
    }

    private void playSound(int soundId) {
        if (soundsLoaded.contains(soundId)) {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    private void getSound(View view){
        if(view.getId()== R.id.topleft){
            sound.playTopLeftSound();
        }else if(view.getId()== R.id.topright) {
            sound.playTopRightSound();
        }else if(view.getId() == R.id.bottomleft){
            sound.playBottomLeftSound();
        }else if(view.getId() == R.id.bottomright){
            sound.playBottomRightSound();
        }
    }




    class AboutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String message = "<html>" +
                    "<h2>About Space</h2>" +
                    "<p>Music</p>" +
                    "<p><b>Source:</b> Oribital Collasus<br>" +
                    "<b>Creator:</b> mudkip_dreams<br>" +
                    "<b>Link: </b> <a href='http://opengameart.org/content/space-boss-battle-theme'>source website</a><br>" +
                    "<b>License: </b> CC-BY 3.0" +
                    "</p></html>";
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage(Html.fromHtml(message));
            builder.setPositiveButton("Ok", null);

            AlertDialog dialog = builder.create();
            dialog.show();

            // must be done after the call to show();
            // allows anchor tags to work
            TextView tv = (TextView) dialog.findViewById(android.R.id.message);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}



