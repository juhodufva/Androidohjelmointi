package com.example.androidohjelmointi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    private Button testBtn;
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testBtn = findViewById(R.id.button_test);
        helloText = findViewById(R.id.textView2);
        testBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                helloText.setText(R.string.new_welcome_text);
                Log.e(TAG, "Button clicked");
                // Code here executes on main thread after user presses button
            }
        });
        helloText.findViewById(R.id.textView2);
    }


}