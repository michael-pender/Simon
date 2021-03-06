package edu.apsu.simon;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.net.Uri;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


public class MainActivity extends AppCompatActivity {



    //------------Media state class with four instances."NOT_READY", "PLAYING", "PAUSED" and "STOPPED"

    enum MediaState {NOT_READY, PLAYING, PAUSED, STOPPED}


    private MediaPlayer mediaPlayer;
    private MediaState mediaState;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);




//----------Getting the sound file prepared into memory and getting it prepared to play

        if (mediaPlayer == null){
            mediaState = mediaState.NOT_READY;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu);
            mediaPlayer.setLooping(true);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    mediaState = mediaState.PLAYING;

                }

            });

        }else if(mediaState == MediaState.PAUSED){
            mediaPlayer.start();
            mediaState = mediaState.PLAYING;
        }else if(mediaState == MediaState.STOPPED){
            mediaPlayer.prepareAsync();               //------this will reset and reload the media file again.
        }

        //---------------Methods used for buttons------------------------




        final Button game1Button = findViewById(R.id.play_button);
        game1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.5, 30);
                myAnim.setInterpolator(interpolator);
                game1Button.startAnimation(myAnim);
                mediaPlayer.stop();
                game1();

            }
        });

        final Button game2Button = findViewById(R.id.play2_button);
        game2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.5, 30);
                myAnim.setInterpolator(interpolator);
                game2Button.startAnimation(myAnim);
                mediaPlayer.stop();
                game2();

            }
        });



        final Button aboutButton = findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.5, 30);
                myAnim.setInterpolator(interpolator);
                aboutButton.startAnimation(myAnim);
                about();
            }
        });

 /*     final Button snapchat = findViewById(R.id.snapchat);
        snapchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.5, 30);
                myAnim.setInterpolator(interpolator);
                snapchat.startAnimation(myAnim);
                final String URL = "https://www.snapchat.com/";
                Uri uri = Uri.parse(URL);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);

            }
        });*/



        final Button instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.5, 30);
                myAnim.setInterpolator(interpolator);
                instagram.startAnimation(myAnim);
                final String URL = "https://www.instagram.com/";
                Uri uri = Uri.parse(URL);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);

            }
        });






    }






//----------------Functions created to allow buttons to work and show additional pages---------

    private void game1(){
        Intent intent = new Intent(getApplicationContext(),
                Game.class);
        startActivity(intent);

    }

    private void game2(){
        Intent intent = new Intent(getApplicationContext(),
                Game2.class);
        startActivity(intent);

    }

    private void about(){
        Intent intent = new Intent(getApplicationContext(),
                About.class);
        startActivity(intent);

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //super.onBackPressed();
                        //Or used finish();
                        finish();
                        mediaPlayer.stop();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }





}


