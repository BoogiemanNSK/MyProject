package com.example.anonymous.myproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Action extends AppCompatActivity {

    Context context;
    LinearLayout actionLL;
    LinearLayout scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        context = getApplicationContext();

        actionLL = (LinearLayout) findViewById(R.id.actionLL);
        scrollView = (LinearLayout) findViewById(R.id.scrollActionLayout);

        switch (getIntent().getStringExtra("action_type")) {
            case "magnus_tower":
                actionMagnusTower();
                break;
            case "bandits_ambush":
                actionBanditsAmbush();
                break;
            case "tavern":
                actionTavern();
                break;
            case "cave":
                actionCave();
                break;
            default:
                break;
        }
    }

    private void actionMagnusTower() {
        actionLL.setBackgroundResource(R.mipmap.magnus);

        final TextView tv1 = new TextView(context);
        tv1.setText("Маг: Что... Что ты здесь делаешь!? Пошёл вон! Убирайся, пока ничего здесь не сломал!");
        scrollView.addView(tv1);

        final Button btn1 = new Button(context);
        btn1.setText("Спокойно, я не создам никаких проблем [Интеллект " + Path.king.intelligence + "/6]");
        scrollView.addView(btn1);

        final Button btn2 = new Button(context);
        btn2.setText("Извините, кажется я заблудился [Уйти]");
        scrollView.addView(btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setClickable(false);
                btn2.setClickable(false);
                final TextView tv2 = new TextView(context);
                scrollView.addView(tv2);
                if (Path.king.intelligence >= 6)
                {
                    tv2.setText("Обойдя магическую руну на полу, вы подходите к магу.\nМаг: Ну надо же, какой смышлёный. Послушай, мне сейчас не до тебя, хочешь," +
                            " возьми этот дурацкий посох, мне вообще плевать, только не возвращайся сюда.\n[Посох Самосожжения добавлен в инвентарь]");
                    Path.king.inventory.add(new GameClasses.Weapon("Посох самоможжения", 10, 0.3));

                    final Button btn3 = new Button(context);
                    btn3.setText("Спасибо, ну, бывай. [Уйти]");
                    scrollView.addView(btn3);

                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            GameClasses.World.map[Path.king.y][Path.king.x] = GameClasses.World.map[Path.king.y - 1][Path.king.x];
                            finish();
                        }
                    });
                } else {
                    tv2.setText("Целеустремлённо направившись к магу Вы наступаете прямо на Руну призыва Огненного Дождя, таким образом призывая этот самый дождь." +
                            "Из метеоритов. Огненных. Коротко говоря, от башни и мокрого места не осталось. Впрочем, как и от Вас.");

                    final Button btn3 = new Button(context);
                    btn3.setText("Досадно.");
                    scrollView.addView(btn3);

                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Path.onDeath(context);
                            finish();
                        }
                    });
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void actionBanditsAmbush() {

    }

    private void actionTavern() {

    }

    private void actionCave() {

    }
}