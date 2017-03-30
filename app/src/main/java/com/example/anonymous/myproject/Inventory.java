package com.example.anonymous.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Inventory extends AppCompatActivity{

    TextView hp, mana, strength, perception, endurance, charisma, intelligence, agility, luck;

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
        setContentView(R.layout.activity_inventory);

        hp = (TextView) findViewById(R.id.hp);
        mana = (TextView) findViewById(R.id.mana);
        strength = (TextView) findViewById(R.id.strength);
        perception = (TextView) findViewById(R.id.perception);
        endurance = (TextView) findViewById(R.id.endurance);
        charisma = (TextView) findViewById(R.id.charisma);
        intelligence = (TextView) findViewById(R.id.intelligence);
        agility = (TextView) findViewById(R.id.agility);
        luck = (TextView) findViewById(R.id.luck);

        hp.setText(String.valueOf(Path.king.hp));
        mana.setText(String.valueOf(Path.king.mana));
        strength.setText(String.valueOf(Path.king.strength));
        perception.setText(String.valueOf(Path.king.perception));
        endurance.setText(String.valueOf(Path.king.endurance));
        charisma.setText(String.valueOf(Path.king.charisma));
        intelligence.setText(String.valueOf(Path.king.intelligence));
        agility.setText(String.valueOf(Path.king.agility));
        luck.setText(String.valueOf(Path.king.luck));
    }

}