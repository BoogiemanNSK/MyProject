package com.example.anonymous.myproject.prepare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anonymous.myproject.R;
import com.example.anonymous.myproject.prepare.ScrollingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, dev;

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
        dev = (Button) findViewById(R.id.dev);

        start.setOnClickListener(this);
        dev.setOnClickListener(this);
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
            case R.id.dev: {
                Intent devs = new Intent(this, AboutDevs.class);
                startActivity(devs);
                break;
            }
        }
    }
}