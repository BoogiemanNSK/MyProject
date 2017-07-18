package com.example.anonymous.myproject.prepare;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.myproject.Path;
import com.example.anonymous.myproject.R;

public class AbilitiesChoice extends AppCompatActivity implements View.OnClickListener {

    View view;
    private final int S = 1,
                      P = 2,
                      E = 3,
                      C = 4,
                      I = 5,
                      A = 6,
                      L = 7;
    private int points, strength = 5, perception = 5, endurance = 5, charisma = 5, intelligence = 5, agility = 5, luck = 5;
    TextView tv_name, tv_dsc, strength_tv, perception_tv, endurance_tv, charisma_tv, intelligence_tv, agility_tv, luck_tv;
    TextView strength_tv_dsc, perception_tv_dsc, endurance_tv_dsc, charisma_tv_dsc, intelligence_tv_dsc, agility_tv_dsc, luck_tv_dsc;

    @Override
    public void onBackPressed() {}

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AbilitiesChoice.this);
        switch (id) {
            case S:
                builder.setTitle(getString(R.string.strength_abilities));
                break;
            case P:
                builder.setTitle(getString(R.string.perception_abilities));
                break;
            case E:
                builder.setTitle(getString(R.string.endurance_abilities));
                break;
            case C:
                builder.setTitle(getString(R.string.charisma_abilities));
                break;
            case I:
                builder.setTitle(getString(R.string.intelligence_abilities));
                break;
            case A:
                builder.setTitle(getString(R.string.agility_abilities));
                break;
            case L:
                builder.setTitle(getString(R.string.luck_abilities));
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
            case S:
                tv_dsc.setText(getString(R.string.dsc_strength));
                break;
            case P:
                tv_dsc.setText(getString(R.string.dsc_perception));
                break;
            case E:
                tv_dsc.setText(getString(R.string.dsc_endurance));
                break;
            case C:
                tv_dsc.setText(getString(R.string.dsc_charisma));
                break;
            case I:
                tv_dsc.setText(getString(R.string.dsc_intelligence));
                break;
            case A:
                tv_dsc.setText(getString(R.string.dsc_agility));
                break;
            case L:
                tv_dsc.setText(getString(R.string.dsc_luck));
                break;
            default:
                break;
        }
    }

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
        setContentView(R.layout.activity_abilities_choice);

        tv_name = (TextView) findViewById(R.id.abilities_choice);

        strength_tv = (TextView) findViewById(R.id.strength_tv);
        perception_tv = (TextView) findViewById(R.id.perception_tv);
        endurance_tv = (TextView) findViewById(R.id.endurance_tv);
        charisma_tv = (TextView) findViewById(R.id.charisma_tv);
        intelligence_tv = (TextView) findViewById(R.id.intelligence_tv);
        agility_tv = (TextView) findViewById(R.id.agility_tv);
        luck_tv = (TextView) findViewById(R.id.luck_tv);

        strength_tv_dsc = (TextView) findViewById(R.id.strength_tv_dsc);
        perception_tv_dsc = (TextView) findViewById(R.id.perception__tv_dsc);
        endurance_tv_dsc = (TextView) findViewById(R.id.endurance_tv_dsc);
        charisma_tv_dsc = (TextView) findViewById(R.id.charisma_tv_dsc);
        intelligence_tv_dsc = (TextView) findViewById(R.id.intelligence_tv_dsc);
        agility_tv_dsc = (TextView) findViewById(R.id.agility_tv_dsc);
        luck_tv_dsc = (TextView) findViewById(R.id.luck_tv_dsc);

        findViewById(R.id.strength_plus).setOnClickListener(this);
        findViewById(R.id.strength_minus).setOnClickListener(this);
        findViewById(R.id.perception_plus).setOnClickListener(this);
        findViewById(R.id.perception_minus).setOnClickListener(this);
        findViewById(R.id.endurance_plus).setOnClickListener(this);
        findViewById(R.id.endurance_minus).setOnClickListener(this);
        findViewById(R.id.charisma_plus).setOnClickListener(this);
        findViewById(R.id.charisma_minus).setOnClickListener(this);
        findViewById(R.id.intelligence_plus).setOnClickListener(this);
        findViewById(R.id.intelligence_minus).setOnClickListener(this);
        findViewById(R.id.agility_plus).setOnClickListener(this);
        findViewById(R.id.agility_minus).setOnClickListener(this);
        findViewById(R.id.luck_plus).setOnClickListener(this);
        findViewById(R.id.luck_minus).setOnClickListener(this);

        findViewById(R.id.finish_abilities_choice).setOnClickListener(this);

        points = getIntent().getIntExtra("points", 0);
        tv_name.setText(getString(R.string.abilities_choice, points));

        strength_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(S);
                return false;
            }
        });

        perception_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(P);
                return false;
            }
        });

        endurance_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(E);
                return false;
            }
        });

        charisma_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(C);
                return false;
            }
        });

        intelligence_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(I);
                return false;
            }
        });

        agility_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(A);
                return false;
            }
        });

        luck_tv_dsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(L);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strength_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (strength == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    strength++;
                    strength_tv.setText("[" + strength + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.strength_minus:
                if (strength == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_strength_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    strength--;
                    strength_tv.setText("[" + strength + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.perception_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (perception == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    perception++;
                    perception_tv.setText("[" + perception + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.perception_minus:
                if (perception == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_perception_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    perception--;
                    perception_tv.setText("[" + perception + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.endurance_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (endurance == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    endurance++;
                    endurance_tv.setText("[" + endurance + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.endurance_minus:
                if (endurance == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_endurance_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    endurance--;
                    endurance_tv.setText("[" + endurance + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.charisma_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (charisma == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    charisma++;
                    charisma_tv.setText("[" + charisma + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.charisma_minus:
                if (charisma == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_charisma_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    charisma--;
                    charisma_tv.setText("[" + charisma + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.intelligence_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (intelligence == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    intelligence++;
                    intelligence_tv.setText("[" + intelligence + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.intelligence_minus:
                if (intelligence == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_intelligence_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    intelligence--;
                    intelligence_tv.setText("[" + intelligence + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.agility_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (agility == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    agility++;
                    agility_tv.setText("[" + agility + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.agility_minus:
                if (agility == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_agility_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    agility--;
                    agility_tv.setText("[" + agility + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.luck_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_points_string), Toast.LENGTH_SHORT).show();
                else if (luck == 10)
                    Toast.makeText(getApplicationContext(), getString(R.string.already_ten_string), Toast.LENGTH_SHORT).show();
                else {
                    points--;
                    luck++;
                    luck_tv.setText("[" + luck + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.luck_minus:
                if (luck == 0)
                    Toast.makeText(getApplicationContext(), getString(R.string.lack_of_luck_string), Toast.LENGTH_SHORT).show();
                else {
                    points++;
                    luck--;
                    luck_tv.setText("[" + luck + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.finish_abilities_choice:
                if (points == 0) {
                    Intent pathIntent = new Intent(AbilitiesChoice.this, Path.class);

                    pathIntent.putExtra("name", getIntent().getStringExtra("name"));
                    pathIntent.putExtra("class", getIntent().getIntExtra("class", 0));
                    pathIntent.putExtra("difficult", getIntent().getIntExtra("difficult", 0));
                    pathIntent.putExtra("strength", strength);
                    pathIntent.putExtra("perception", perception);
                    pathIntent.putExtra("endurance", endurance);
                    pathIntent.putExtra("charisma", charisma);
                    pathIntent.putExtra("intelligence", intelligence);
                    pathIntent.putExtra("agility", agility);
                    pathIntent.putExtra("luck", luck);

                    startActivity(pathIntent);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), getString(R.string.points_left_string), Toast.LENGTH_SHORT).show();
        }
    }
}