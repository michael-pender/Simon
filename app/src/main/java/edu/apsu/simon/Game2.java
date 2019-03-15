package edu.apsu.simon;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game2 extends AppCompatActivity {

    View button_one, button_two, button_three, button_four, button_five, button_six, button_seven,
    button_eight;
    TextView textView;
    final int MAX_LENGTH = 1000;
    int array_of_moves[] = new int[MAX_LENGTH];
    public int numberOfElmentsInMovesArray = 0, k = 0, numberOfClicksEachStage = 0, x, sadMusic, highScore = 0, hardness;
    public SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    Random r = new Random();
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main2);
        sadMusic = sp.load(this, R.raw.sad, 1);

        button_one = findViewById(R.id.button_one);
        button_two = findViewById(R.id.button_two);
        button_three = findViewById(R.id.button_three);
        button_four = findViewById(R.id.button_four);
        button_five = findViewById(R.id.button_five);
        button_six = findViewById(R.id.button_six);
        button_seven = findViewById(R.id.button_seven);
        button_eight = findViewById(R.id.button_eight);

        textView = (TextView) findViewById(R.id.textView2);
        findViewById(R.id.about_button).setOnClickListener(new Game2.AboutListener());

        button_one.setOnTouchListener(onTouch);
        button_two.setOnTouchListener(onTouch);
        button_three.setOnTouchListener(onTouch);
        button_four.setOnTouchListener(onTouch);
        button_five.setOnTouchListener(onTouch);
        button_six.setOnTouchListener(onTouch);
        button_seven.setOnTouchListener(onTouch);
        button_eight.setOnTouchListener(onTouch);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String a[] = {"Game1 (Easy)", " Game2 (Medium)", "Game3 (Hard)", "Game4 (Geek)"};
        builder.setTitle("SELECT YOUR LEVEL GAME!")

                .setItems(a, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        hardness = which;
                        textView.setText("Current score: " + numberOfElmentsInMovesArray + "           High score: " + highScore);
                        Toast.makeText(Game2.this, " WELCOME TO SIMON " , Toast.LENGTH_LONG).show();
                        //on initial start, click the playGame function after delay
                        final Runnable r = new Runnable() {
                            public void run() {
                                playGame();
                            }
                        };
                        handler.postDelayed(r, 3000);
                    }
                });
        AlertDialog myDialog = builder.create();
        myDialog.show();
    }

    View.OnTouchListener onTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                switch (v.getId()) {
                    case R.id.button_one:
                        x = 1;
                        break;
                    case R.id.button_two:
                        x = 2;
                        break;
                    case R.id.button_three:
                        x = 4;
                        break;
                    case R.id.button_four:
                        x = 3;
                        break;
                    case R.id.button_five:
                        x = 5;
                        break;
                    case R.id.button_six:
                        x = 6;
                        break;
                    case R.id.button_seven:
                        x = 7;
                        break;
                    case R.id.button_eight:
                        x = 8;
                        break;
                }

                if (array_of_moves[numberOfClicksEachStage] != x) { // on wrong click
                    sp.play(sadMusic, 1, 1, 1, 0, 1f);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game2.this);
                    alertDialogBuilder.setMessage("Game over, you reached level: " + (numberOfElmentsInMovesArray - 1) + ", Do you want to play again?");
                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    //Start over
                                    clear();
                                    textView.setText("Current score: " + numberOfElmentsInMovesArray + "           High score: " + highScore);
                                    playGame();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    return true;
                }

                //on success
                playSound(v.getId());
                xorMyColor(v);
                numberOfClicksEachStage++;
                if (numberOfElmentsInMovesArray == numberOfClicksEachStage) { //if 4 boxes shown, then activate  function
                    //playGame only after 4 clicks have been made by the user

                    numberOfClicksEachStage = 0;
                    if (numberOfElmentsInMovesArray > highScore) {
                        highScore = numberOfElmentsInMovesArray;
                    }
                    textView.setText("Current score: " + numberOfElmentsInMovesArray + "           High score: " + highScore);
                    final Runnable r = new Runnable() {
                        public void run() {
                            playGame();
                        }
                    };
                    handler.postDelayed(r, 2000 - 500 * hardness);
                }
            }
            return true;
        }
    };

    private void playSound(int id) {
        //function that play sound according to sound ID
        int audioRes = 0;
        switch (id) {
            case R.id.button_one:
                audioRes = R.raw.doo;
                break;
            case R.id.button_two:
                audioRes = R.raw.re;
                break;
            case R.id.button_three:
                audioRes = R.raw.mi;
                break;
            case R.id.button_four:
                audioRes = R.raw.fa;
                break;
            case R.id.button_five:
                audioRes = R.raw.doo;
                break;
            case R.id.button_six:
                audioRes = R.raw.re;
                break;
            case R.id.button_seven:
                audioRes = R.raw.mi;
                break;
            case R.id.button_eight:
                audioRes = R.raw.fa;
        }

        MediaPlayer p = MediaPlayer.create(this, audioRes);
        p.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        p.start();
    }

    class AboutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String message = "<html>" +
                    "<h2>About Game</h2>" +
                    "<p>SIMON</p>" +
                    "<p><b>Creator</b> Ralph H. Baer and Howard J. Morrison<br>" +
                    "<b>Distribution:</b> Milton Bradley and then Hasbro<br>" +
                    "<b>Link: </b><a href='https://en.wikipedia.org/wiki/Simon_(game)'>source website</a><br>" +
                    "<b>This SIMON </b> Michael and Elvis" +
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

    private void xorMyColor(final View v) {
        //function that changes the background color and get it back after 500 milliseconds
        v.getBackground().setAlpha(51);
        final Runnable r = new Runnable() {
            public void run() {
                v.getBackground().setAlpha(255);
            }
        };
        handler.postDelayed(r, 300);
    }

    public void playGame() {
        appendValueToArray();
        numberOfElmentsInMovesArray++;
        for (k = 0; k < numberOfElmentsInMovesArray; k++) {
            click(k);
        }
    }

    public void click(final int click_index) {
        //Function that clicks one place randomally on the view
        final Runnable r = new Runnable() {
            public void run() {
                if (array_of_moves[click_index] == 1) {
                    playSound(R.id.button_one);
                    xorMyColor(button_one);
                } else if (array_of_moves[click_index] == 2) {
                    playSound(R.id.button_two);
                    xorMyColor(button_two);
                } else if (array_of_moves[click_index] == 3) {
                    playSound(R.id.button_three);
                    xorMyColor(button_three);
                } else if (array_of_moves[click_index] == 4) {
                    playSound(R.id.button_four);
                    xorMyColor(button_four);
                } else if (array_of_moves[click_index] == 5) {
                    playSound(R.id.button_five);
                    xorMyColor(button_five);
                } else if (array_of_moves[click_index] == 6) {
                    playSound(R.id.button_six);
                    xorMyColor(button_six);
                } else if (array_of_moves[click_index] == 7) {
                    playSound(R.id.button_seven);
                    xorMyColor(button_seven);
                } else {
                    playSound(R.id.button_eight);
                    xorMyColor(button_eight);
                }
            }
        };

        handler.postDelayed(r, (2000 - 500 * hardness) * click_index);
    }


    private int generateRandomNumber() {
        return r.nextInt(4) + 1; // generate random number between 1 and 4
    }

    private void appendValueToArray() {  // add random number to the first free position in the array
        for (int i = 0; i < MAX_LENGTH; i++) {
            if (array_of_moves[i] == 0) {
                array_of_moves[i] = generateRandomNumber();
                break;
            }
        }
    }

    private void clear() {//reset the game to initial state
        for (int i = 0; i < MAX_LENGTH; i++) {
            array_of_moves[i] = 0;
        }
        numberOfClicksEachStage = 0;
        numberOfElmentsInMovesArray = 0;
    }


    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //super.onBackPressed();
                        //Or used finish();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}


