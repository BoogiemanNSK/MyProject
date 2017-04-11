package com.example.anonymous.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Action extends AppCompatActivity {

    LinearLayout actionLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        actionLL = (LinearLayout) findViewById(R.id.actionLL);
    }
}
