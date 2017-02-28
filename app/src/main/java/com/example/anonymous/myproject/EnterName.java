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

public class EnterName extends AppCompatActivity implements View.OnClickListener {
    int difficult;
    String name;
    TextView tv2, tvname;
    EditText et;
    Button enter, easy, normal, hard;
    static GameClasses.Hero king = new GameClasses.Hero();

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
        tvname = (TextView) findViewById(R.id.tvname);

        enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter: {
                if (et.getText().toString().equals("")) {
                    tv2.setText("Введите имя");
                } else {
                    name = et.getText().toString();

                    enter.setVisibility(View.GONE);
                    et.setVisibility(View.GONE);

                    tvname.setText("Выберите уровень сложности");
                    tv2.setText("");

                    final Button easy = new Button(this);
                    easy.setText(getString(R.string.word_easy));

                    final Button normal = new Button(this);
                    normal.setText(getString(R.string.word_normal));

                    final Button hard = new Button(this);
                    hard.setText(getString(R.string.word_hard));

                    ((LinearLayout) findViewById(R.id.LL)).addView(easy);
                    ((LinearLayout) findViewById(R.id.LL)).addView(normal);
                    ((LinearLayout) findViewById(R.id.LL)).addView(hard);

                    easy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvname.setText("Выберите класс");

                            difficult = 1;

                            easy.setVisibility(View.GONE);
                            hard.setVisibility(View.GONE);
                            normal.setVisibility(View.GONE);

                            final Button class1 = new Button(EnterName.this);
                            class1.setText(getString(R.string.word_class1));

                            final Button class2 = new Button(EnterName.this);
                            class2.setText(getString(R.string.word_class2));

                            ((LinearLayout) findViewById(R.id.LL)).addView(class1);
                            ((LinearLayout) findViewById(R.id.LL)).addView(class2);

                            class1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });

                            class2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });
                        }
                    });

                    normal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvname.setText("Выберите класс");

                            difficult = 2;

                            easy.setVisibility(View.GONE);
                            hard.setVisibility(View.GONE);
                            normal.setVisibility(View.GONE);

                            final Button class1 = new Button(EnterName.this);
                            class1.setText(getString(R.string.word_class1));

                            final Button class2 = new Button(EnterName.this);
                            class2.setText(getString(R.string.word_class2));

                            ((LinearLayout) findViewById(R.id.LL)).addView(class1);
                            ((LinearLayout) findViewById(R.id.LL)).addView(class2);

                            class1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });

                            class2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });
                        }
                    });

                    hard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvname.setText("Выберите класс");

                            difficult = 3;

                            easy.setVisibility(View.GONE);
                            hard.setVisibility(View.GONE);
                            normal.setVisibility(View.GONE);

                            final Button class1 = new Button(EnterName.this);
                            class1.setText(getString(R.string.word_class1));

                            final Button class2 = new Button(EnterName.this);
                            class2.setText(getString(R.string.word_class2));

                            ((LinearLayout) findViewById(R.id.LL)).addView(class1);
                            ((LinearLayout) findViewById(R.id.LL)).addView(class2);

                            class1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });

                            class2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GameClasses.Hero.name = name;
                                    Intent path = new Intent(EnterName.this, Path.class);
                                    startActivity(path);
                                    finish();
                                }
                            });
                        }
                    });
                    break;
                }
            }
        }
    }
}