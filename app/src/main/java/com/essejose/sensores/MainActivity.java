package com.essejose.sensores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

    }

    public void acelerometro(View v){
        Intent intent = new Intent(MainActivity.this,
                Acelerometro.class);
        startActivity(intent);

    }

    public void shake(View v){
        Intent intent = new Intent(MainActivity.this,
                Shake.class);
        startActivity(intent);

    }

    public void tts(View v){
        Intent intent = new Intent(MainActivity.this,
                Tts.class);
        startActivity(intent);

    }
    public void stt(View v){
        Intent intent = new Intent(MainActivity.this,
                Stt.class);
        startActivity(intent);

    }
    public void todos(View v){
        Intent intent = new Intent(MainActivity.this,
                todos.class);
        startActivity(intent);

    }



}
