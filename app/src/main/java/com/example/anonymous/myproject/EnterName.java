package com.example.anonymous.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EnterName extends AppCompatActivity implements View.OnClickListener {

    int points, difficult, class_num, strength = 0, perception = 0, endurance = 0, charisma = 0, intelligence = 0, agility = 0, luck = 0;
    String name;
    TextView tv2, tv_name, tv_empty, strength_tv,  perception_tv,  endurance_tv, charisma_tv, intelligence_tv, agility_tv, luck_tv;
    EditText et;
    Button enter;

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
        setContentView(R.layout.activity_enter_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enter = (Button) findViewById(R.id.enter);
        et = (EditText) findViewById(R.id.name);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv_name = (TextView) findViewById(R.id.tvname);
        tv_empty = (TextView) findViewById(R.id.tv_empty);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    tv2.setText("Введите имя");
                } else {
                    name = et.getText().toString();

                    enter.setVisibility(View.GONE);
                    et.setVisibility(View.GONE);

                    difficultChoose();
                }
            }
        });
    }

    public void difficultChoose() {
        tv_name.setText("Выберите уровень сложности");
        tv2.setText("");

        final Button easy = new Button(this);
        final Button normal = new Button(this);
        final Button hard = new Button(this);

        easy.setText(getString(R.string.word_easy));
        normal.setText(getString(R.string.word_normal));
        hard.setText(getString(R.string.word_hard));

        ((LinearLayout) findViewById(R.id.LL)).addView(easy);
        ((LinearLayout) findViewById(R.id.LL)).addView(normal);
        ((LinearLayout) findViewById(R.id.LL)).addView(hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficult = 1;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficult = 2;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficult = 3;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });
    }

    public void classChoose() {
        tv_name.setText("Выберите класс");

        final Button class1 = new Button(EnterName.this);
        class1.setText(getString(R.string.word_class1));

        final Button class2 = new Button(EnterName.this);
        class2.setText(getString(R.string.word_class2));

        ((LinearLayout) findViewById(R.id.LL)).addView(class1);
        ((LinearLayout) findViewById(R.id.LL)).addView(class2);

        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class_num = 1;

                class1.setVisibility(View.GONE);
                class2.setVisibility(View.GONE);

                abilitiesChoose();
            }
        });

        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class_num = 2;

                class1.setVisibility(View.GONE);
                class2.setVisibility(View.GONE);

                abilitiesChoose();
            }
        });
    }

    public void abilitiesChoose() {
        points = 50 - difficult * 5;
        tv_name.setText("Распределите очки навыков [" + points + "]");

        final LinearLayout strength_layout = new LinearLayout(this);
        final LinearLayout perception_layout = new LinearLayout(this);
        final LinearLayout endurance_layout = new LinearLayout(this);
        final LinearLayout charisma_layout = new LinearLayout(this);
        final LinearLayout intelligence_layout = new LinearLayout(this);
        final LinearLayout agility_layout = new LinearLayout(this);
        final LinearLayout luck_layout = new LinearLayout(this);
        final Button enter = new Button(this);

        strength_layout.setOrientation(LinearLayout.HORIZONTAL);
        perception_layout.setOrientation(LinearLayout.HORIZONTAL);
        endurance_layout.setOrientation(LinearLayout.HORIZONTAL);
        charisma_layout.setOrientation(LinearLayout.HORIZONTAL);
        intelligence_layout.setOrientation(LinearLayout.HORIZONTAL);
        agility_layout.setOrientation(LinearLayout.HORIZONTAL);
        luck_layout.setOrientation(LinearLayout.HORIZONTAL);

        ((LinearLayout) findViewById(R.id.LL)).addView(strength_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(perception_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(endurance_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(charisma_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(intelligence_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(agility_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(luck_layout);
        ((LinearLayout) findViewById(R.id.LL)).addView(enter);

        strength_tv = new TextView(this);
        perception_tv = new TextView(this);
        endurance_tv = new TextView(this);
        charisma_tv = new TextView(this);
        intelligence_tv = new TextView(this);
        agility_tv = new TextView(this);
        luck_tv = new TextView(this);
        final Button strength_plus = new Button(this);
        final Button perception_plus = new Button(this);
        final Button endurance_plus = new Button(this);
        final Button charisma_plus = new Button(this);
        final Button intelligence_plus = new Button(this);
        final Button agility_plus = new Button(this);
        final Button luck_plus = new Button(this);
        final Button strength_minus = new Button(this);
        final Button perception_minus = new Button(this);
        final Button endurance_minus = new Button(this);
        final Button charisma_minus = new Button(this);
        final Button intelligence_minus = new Button(this);
        final Button agility_minus = new Button(this);
        final Button luck_minus = new Button(this);

        enter.setText(R.string.enter_word);
        strength_tv.setText(R.string.strength_abilities);
        perception_tv.setText(R.string.perception_abilities);
        endurance_tv.setText(R.string.endurance_abilities);
        charisma_tv.setText(R.string.charisma_abilities);
        intelligence_tv.setText(R.string.intelligence_abilities);
        agility_tv.setText(R.string.agility_abilities);
        luck_tv.setText(R.string.luck_abilities);
        strength_plus.setText("+");
        perception_plus.setText("+");
        endurance_plus.setText("+");
        charisma_plus.setText("+");
        intelligence_plus.setText("+");
        agility_plus.setText("+");
        luck_plus.setText("+");
        strength_minus.setText("-");
        perception_minus.setText("-");
        endurance_minus.setText("-");
        charisma_minus.setText("-");
        intelligence_minus.setText("-");
        agility_minus.setText("-");
        luck_minus.setText("-");

        strength_layout.addView(strength_tv);
        perception_layout.addView(perception_tv);
        endurance_layout.addView(endurance_tv);
        charisma_layout.addView(charisma_tv);
        intelligence_layout.addView(intelligence_tv);
        agility_layout.addView(agility_tv);
        luck_layout.addView(luck_tv);

        strength_layout.addView(strength_plus);
        strength_layout.addView(strength_minus);
        perception_layout.addView(perception_plus);
        perception_layout.addView(perception_minus);
        endurance_layout.addView(endurance_plus);
        endurance_layout.addView(endurance_minus);
        charisma_layout.addView(charisma_plus);
        charisma_layout.addView(charisma_minus);
        intelligence_layout.addView(intelligence_plus);
        intelligence_layout.addView(intelligence_minus);
        agility_layout.addView(agility_plus);
        agility_layout.addView(agility_minus);
        luck_layout.addView(luck_plus);
        luck_layout.addView(luck_minus);

        strength_plus.setId(R.id.strength_plus);
        strength_minus.setId(R.id.strength_minus);
        perception_plus.setId(R.id.perception_plus);
        perception_minus.setId(R.id.perception_minus);
        endurance_plus.setId(R.id.endurance_plus);
        endurance_minus.setId(R.id.endurance_minus);
        charisma_plus.setId(R.id.charisma_plus);
        charisma_minus.setId(R.id.charisma_minus);
        intelligence_plus.setId(R.id.intelligence_plus);
        intelligence_minus.setId(R.id.intelligence_minus);
        agility_plus.setId(R.id.agility_plus);
        agility_minus.setId(R.id.agility_minus);
        luck_plus.setId(R.id.luck_plus);
        luck_minus.setId(R.id.luck_minus);

        strength_plus.setOnClickListener(this);
        strength_minus.setOnClickListener(this);
        perception_plus.setOnClickListener(this);
        perception_minus.setOnClickListener(this);
        endurance_plus.setOnClickListener(this);
        endurance_minus.setOnClickListener(this);
        charisma_plus.setOnClickListener(this);
        charisma_minus.setOnClickListener(this);
        intelligence_plus.setOnClickListener(this);
        intelligence_minus.setOnClickListener(this);
        agility_plus.setOnClickListener(this);
        agility_minus.setOnClickListener(this);
        luck_plus.setOnClickListener(this);
        luck_minus.setOnClickListener(this);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (points == 0)
                    newIntent();
                else
                    Toast.makeText(getApplicationContext(), "У вас остались нераспределённые очки.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void newIntent() {
        Intent path = new Intent(EnterName.this, Path.class);

        path.putExtra("name", name);
        path.putExtra("class", class_num);
        path.putExtra("difficult", difficult);
        path.putExtra("strength", strength);
        path.putExtra("perception", perception);
        path.putExtra("endurance", endurance);
        path.putExtra("charisma", charisma);
        path.putExtra("intelligence", intelligence);
        path.putExtra("agility", agility);
        path.putExtra("luck", luck);

        startActivity(path);

        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.strength_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    strength++;
                    strength_tv.setText("Strength [" + strength + "]       ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.strength_minus:
                if (strength == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 силы", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    strength--;
                    strength_tv.setText("Strength [" + strength + "]       ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.perception_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    perception++;
                    perception_tv.setText("Perception [" + perception + "]   ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.perception_minus:
                if (perception == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 восприятия", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    perception--;
                    perception_tv.setText("Perception [" + perception + "]   ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.endurance_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    endurance++;
                    endurance_tv.setText("Endurance [" + endurance + "]    ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.endurance_minus:
                if (endurance == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 выносливости", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    endurance--;
                    endurance_tv.setText("Endurance [" + endurance + "]    ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.charisma_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    charisma++;
                    charisma_tv.setText("Charisma [" + charisma + "]     ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.charisma_minus:
                if (charisma == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 харизмы", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    charisma--;
                    charisma_tv.setText("Charisma [" + charisma + "]     ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.intelligence_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    intelligence++;
                    intelligence_tv.setText("Intelligence [" + intelligence + "]     ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.intelligence_minus:
                if (intelligence == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 интеллекта", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    intelligence--;
                    intelligence_tv.setText("Intelligence [" + intelligence + "]     ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.agility_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    agility++;
                    agility_tv.setText("Agility [" + agility + "]         ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.agility_minus:
                if (agility == 0)
                    Toast.makeText(getApplicationContext(), "У вас 0 ловкости", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    agility--;
                    agility_tv.setText("Agility [" + agility + "]         ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.luck_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    luck++;
                    luck_tv.setText("Luck [" + luck + "]            ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
            case R.id.luck_minus:
                if (luck == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 удачи", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    luck--;
                    luck_tv.setText("Luck [" + luck + "]            ");
                    tv_name.setText("Распределите очки навыков [" + points + "]");
                }
                break;
        }
    }
}