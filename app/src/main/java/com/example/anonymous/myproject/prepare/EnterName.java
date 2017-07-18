package com.example.anonymous.myproject.prepare;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anonymous.myproject.R;

public class EnterName extends AppCompatActivity {

    final int EASY = 1, NORMAL = 2, HARD = 3;

    private int difficult, class_num;
    private String name;

    View view;
    TextView tv2, tv_name, tv_empty, tv_dsc;
    EditText et;
    Button enter;

    @Override
    public void onBackPressed() {}

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

        et.setText("Dude");

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    tv2.setText(getString(R.string.enter_name_string));
                } else {
                    name = et.getText().toString();

                    enter.setVisibility(View.GONE);
                    et.setVisibility(View.GONE);

                    difficultChoose();
                }
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EnterName.this);
        switch (id) {
            case EASY:
                builder.setTitle(getString(R.string.word_easy));
                break;
            case NORMAL:
                builder.setTitle(getString(R.string.word_normal));
                break;
            case HARD:
                builder.setTitle(getString(R.string.word_hard));
                break;
            default:
                break;
        }
        view = getLayoutInflater().inflate(R.layout.dialog, null);
        builder.setView(view);
        tv_dsc = (TextView) view.findViewById(R.id.description);
        return builder.create();
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case EASY:
                tv_dsc.setText(getString(R.string.dsc_easy));
                break;
            case NORMAL:
                tv_dsc.setText(getString(R.string.dsc_normal));
                break;
            case HARD:
                tv_dsc.setText(getString(R.string.dsc_hard));
                break;
            default:
                break;
        }
    }

    public void difficultChoose() {
        tv_name.setText(getString(R.string.choose_difficult_string));
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
                difficult = EASY;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficult = NORMAL;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficult = HARD;

                easy.setVisibility(View.GONE);
                hard.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);

                classChoose();
            }
        });

        easy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(EASY);
                return false;
            }
        });

        normal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(NORMAL);
                return false;
            }
        });

        hard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(HARD);
                return false;
            }
        });
    }

    public void classChoose() {
        tv_name.setText(getString(R.string.choose_class_string));

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

                Intent abilityIntent = new Intent(EnterName.this, AbilitiesChoice.class);

                abilityIntent.putExtra("name", name);
                abilityIntent.putExtra("class", class_num);
                abilityIntent.putExtra("difficult", difficult);
                abilityIntent.putExtra("points", (4 - difficult) * 5);
                startActivity(abilityIntent);
                finish();
            }
        });

        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class_num = 2;

                class1.setVisibility(View.GONE);
                class2.setVisibility(View.GONE);

                Intent abilityIntent = new Intent(EnterName.this, AbilitiesChoice.class);

                abilityIntent.putExtra("name", name);
                abilityIntent.putExtra("class", class_num);
                abilityIntent.putExtra("difficult", difficult);
                abilityIntent.putExtra("points", (4 - difficult) * 5);
                startActivity(abilityIntent);
                finish();
            }
        });
    }
}