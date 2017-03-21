package com.example.anonymous.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Path extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Button forward, back, left, right;
    ImageButton map;

    final String LOG_TAG = "logcat_tag";

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

        tv = (TextView) findViewById(R.id.tv);
        right = (Button) findViewById(R.id.right);
        forward = (Button) findViewById(R.id.forward);
        back = (Button) findViewById(R.id.back);
        left = (Button) findViewById(R.id.left);
        map = (ImageButton) findViewById(R.id.map);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        forward.setOnClickListener(this);
        back.setOnClickListener(this);
        map.setOnClickListener(this);

        switch (getIntent().getIntExtra("class", 0)) {
            case 1:
                king = new GameClasses.Warrior();
                break;
            case 2:
                king = new GameClasses.Mage();
                break;
            default:
                Log.d(LOG_TAG, "--- Error in getting Intent Integer Extra ---");
                break;
        }
        king.name = getIntent().getStringExtra("name");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forward: {
                if (king.x != 1) {
                    king.x--;
                    GameClasses.event(tv, myWorld.map, king);
                } else {
                    tv.setText(king.name + ", " + "Вы явно заблудились. Попробуйте другой маршрут.");
                }
                break;
            }
            case R.id.back: {
                if (king.x != 30) {
                    king.x++;
                    GameClasses.event(tv, myWorld.map, king);
                } else {
                    tv.setText(king.name + ", " + "Вы явно заблудились. Попробуйте другой маршрут.");
                }
                break;
            }

            case R.id.left: {
                if (king.y != 1) {
                    king.y--;
                    GameClasses.event(tv, myWorld.map, king);
                } else {
                    tv.setText(king.name + ", " + "Вы явно заблудились. Попробуйте другой маршрут.");
                }
                break;
            }

            case R.id.right: {
                if (king.y != 30) {
                    king.y++;
                    GameClasses.event(tv, myWorld.map, king);
                } else {
                    tv.setText(king.name + ", " + "Вы явно заблудились. Попробуйте другой маршрут.");
                }
                break;
            }
            case R.id.map: {
                Intent maps = new Intent(this, Map.class);
                startActivity(maps);
                break;
            }
            default:
                break;
        }
    }
}