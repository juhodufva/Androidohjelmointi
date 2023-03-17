package com.example.androidohjelmointi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameActivity extends AppCompatActivity {


    private ImageButton Button1;
    private ImageButton Button2;
    private ImageButton Button3;
    private ImageButton Button4;
    private TextView textView;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Button1 = findViewById(R.id.imageButton1);
        Button2 = findViewById(R.id.imageButton1);
        Button3 = findViewById(R.id.imageButton1);
        Button4 = findViewById(R.id.imageButton1);
        Button1.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick (View view) {
            Button1.setVisibility(View.GONE);
            Button1.setImageResource(R.mipmap.ic_launcher);
            Button1.setBackgroundColor(Color.WHITE);
            Button1.setVisibility(View.VISIBLE);


        }
        });

    }
}


