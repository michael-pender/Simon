package edu.apsu.simon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



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


}


