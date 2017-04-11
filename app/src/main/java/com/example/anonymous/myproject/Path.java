package com.example.anonymous.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Path extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mainLL;
    TextView tv;
    Button forward, back, left, right;
    ImageButton map, inventory;

    static GameClasses.World myWorld = new GameClasses.World();
    static GameClasses.Hero king;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mainLL = (LinearLayout) findViewById(R.id.mainLL);
        tv = (TextView) findViewById(R.id.tv);
        right = (Button) findViewById(R.id.right);
        forward = (Button) findViewById(R.id.forward);
        back = (Button) findViewById(R.id.back);
        left = (Button) findViewById(R.id.left);
        map = (ImageButton) findViewById(R.id.map);
        inventory = (ImageButton) findViewById(R.id.inv);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        forward.setOnClickListener(this);
        back.setOnClickListener(this);
        map.setOnClickListener(this);
        inventory.setOnClickListener(this);

        switch (getIntent().getIntExtra("class", 0)) {
            case 1:
                king = new GameClasses.Warrior();
                break;
            case 2:
                king = new GameClasses.Mage();
                break;
            default:
                break;
        }

        king.name = getIntent().getStringExtra("name");
        king.strength = getIntent().getIntExtra("strength", 0);
        king.perception = getIntent().getIntExtra("perception", 0);
        king.endurance = getIntent().getIntExtra("endurance", 0);
        king.charisma = getIntent().getIntExtra("charisma", 0);
        king.intelligence = getIntent().getIntExtra("intelligence", 0);
        king.agility = getIntent().getIntExtra("agility", 0);
        king.luck = getIntent().getIntExtra("luck", 0);
        king.inventory = new ArrayList();

        king.inventory.add(new GameClasses.Weapon("Деревянная палка", 2, 0.05));
        king.inventory.add(new GameClasses.Armor("Рваный балахон", 1));
        king.inventory.add(new GameClasses.Weapon("Деревянный меч", 4, 0.1));
        king.inventory.add(new GameClasses.Armor("Костюм-двойка", 3));
        king.inventory.add(new GameClasses.Drink("hp", "Бутылка воды", 10));
        king.inventory.add(new GameClasses.Drink("mana", "Лимонад Гаврош", 15));

        myWorld.difficult = getIntent().getIntExtra("difficult", 0);

        tv.setText("Добро пожаловать в Overthrown! Для того чтобы узнать ваше местоположение, используйте карту, а чтобы менять оружие, броню, пить напитики" +
                " или узнать свои хар-ки, зайдите в инвентарь.\nДля перемещения по миру нажимайте на соответственные кнопки внизу.");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forward: {
                if (king.x != 1) {
                    king.x--;
                    GameClasses.event(mainLL, tv, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }
            case R.id.back: {
                if (king.x != 30) {
                    king.x++;
                    GameClasses.event(mainLL, tv, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }

            case R.id.left: {
                if (king.y != 1) {
                    king.y--;
                    GameClasses.event(mainLL, tv, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }

            case R.id.right: {
                if (king.y != 30) {
                    king.y++;
                    GameClasses.event(mainLL, tv, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }
            case R.id.map: {
                Intent maps = new Intent(this, Map.class);
                startActivity(maps);
                break;
            }
            case R.id.inv: {
                Intent invs = new Intent(this, Inventory.class);
                startActivity(invs);
                break;
            }
            default:
                break;
        }
    }
}