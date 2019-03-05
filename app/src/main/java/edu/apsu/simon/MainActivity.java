package edu.apsu.simon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu);
            mediaPlayer.setLooping(true);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        }















        //---------------Methods used for buttons------------------------

        Button game1Button = findViewById(R.id.game_1);
        game1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                game1();

            }
        });


        Button game2Button = findViewById(R.id.game_2);
        game2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                game2();

            }
        });


        Button game3Button = findViewById(R.id.game_3);
        game3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game3();
            }
        });



        Button aboutButton = findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about();
            }
        });






    }
    //------------------End of Methods------------------------------------------


//----------------Functions created to allow buttons to work and show additional pages---------

    private void game1(){
        Intent intent = new Intent(getApplicationContext(),
                Game1.class);
        startActivity(intent);

    }

    private void game2(){
        Intent intent = new Intent(getApplicationContext(),
                Game2.class);
        startActivity(intent);

    }

    private void game3(){
        Intent intent = new Intent(getApplicationContext(),
                Game3.class);
        startActivity(intent);

    }


    private void about(){
        Intent intent = new Intent(getApplicationContext(),
                About.class);
        startActivity(intent);

    }



}


