package com.example.anonymous.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, settings, dev, exit, contin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        exit = (Button) findViewById(R.id.exit);
        settings = (Button) findViewById(R.id.settings);
        dev = (Button) findViewById(R.id.dev);
        contin = (Button) findViewById(R.id.contin);

        start.setOnClickListener(this);
        exit.setOnClickListener(this);
        settings.setOnClickListener(this);
        dev.setOnClickListener(this);
        contin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start: {
                Intent start = new Intent(this, ScrollingActivity.class);
                startActivity(start);
                super.onStop();
                break;
            }
            case R.id.contin: {
                Intent cont = new Intent(this, Path.class);
                startActivity(cont);
                finish();
            }
            case R.id.settings: {
                break;
            }
            case R.id.dev: {
                break;
            }
            case R.id.exit: {
                finish();
                break;
            }
        }
    }
}