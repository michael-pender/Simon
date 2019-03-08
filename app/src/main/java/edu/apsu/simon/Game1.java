package edu.apsu.simon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Game1 extends AppCompatActivity implements ColorFragment.PushListener {
    /** Called when the activity is first created. */

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game1_main);


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
                    Log.i("app", "Yahouuuuuuu");
                    challenging = false;
                    challengeIndex = 0;
                    indicator.setText("\u2714"); // HEAVY CHECK MARK

                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            score++; //added
                            incSequence();
                        }
                    }, 1000);
                }
            } else {
                Log.i("app", "Bouhouhou");
                challenging = false;
                challengeIndex = 0;

                //Show Result
                Intent intent = new Intent(getApplicationContext(), result.class);
                intent.putExtra("SCORE", score); //having problems getting score
                startActivity(intent);



 /*               indicator.setText("\u2718");// HEAVY BALLOT X

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initSequence();
                    }
                }, 3000);   */
            }
        }
    }
}
