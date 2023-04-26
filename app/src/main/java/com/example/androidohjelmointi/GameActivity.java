package com.example.androidohjelmointi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameActivity extends AppCompatActivity {


    private ImageButton Button1;
    private ImageButton Button2;
    private ImageButton Button3;
    private ImageButton Button4;
    private TextView textView;
    private FloatingActionButton floatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Button1 = findViewById(R.id.imageButton1);
        Button2 = findViewById(R.id.imageButton1);
        Button3 = findViewById(R.id.imageButton1);
        Button4 = findViewById(R.id.imageButton1);
        floatButton = findViewById(R.id.floatingActionButton);

        Button1.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick (View view) {
            Button1.setVisibility(View.GONE);
            Button1.setImageResource(R.mipmap.ic_launcher);
            Button1.setBackgroundColor(Color.WHITE);
            Button1.setVisibility(View.VISIBLE);

        }

        });

        floatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
            finish();

            startActivity(getIntent());


            };

        });
    }
}
