package com.example.anonymous.myproject.prepare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.myproject.Path;
import com.example.anonymous.myproject.R;

public class AbilitiesChoice extends AppCompatActivity implements View.OnClickListener {

    private int points, strength = 0, perception = 0, endurance = 0, charisma = 0, intelligence = 0, agility = 0, luck = 0;
    TextView tv_name, strength_tv, perception_tv, endurance_tv, charisma_tv, intelligence_tv, agility_tv, luck_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities_choice);

        tv_name = (TextView) findViewById(R.id.abilities_choice);
        strength_tv = (TextView) findViewById(R.id.strength_tv);
        perception_tv = (TextView) findViewById(R.id.perception_tv);
        endurance_tv = (TextView) findViewById(R.id.endurance_tv);
        charisma_tv = (TextView) findViewById(R.id.charisma_tv);
        intelligence_tv = (TextView) findViewById(R.id.intelligence_tv);
        agility_tv = (TextView) findViewById(R.id.agility_tv);
        luck_tv = (TextView) findViewById(R.id.luck_tv);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.strength_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (strength == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    strength++;
                    strength_tv.setText("[" + strength + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.strength_minus:
                if (strength == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 силы", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    strength--;
                    strength_tv.setText("[" + strength + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.perception_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (perception == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    perception++;
                    perception_tv.setText("[" + perception + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.perception_minus:
                if (perception == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 восприятия", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    perception--;
                    perception_tv.setText("[" + perception + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.endurance_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (endurance == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    endurance++;
                    endurance_tv.setText("[" + endurance + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.endurance_minus:
                if (endurance == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 выносливости", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    endurance--;
                    endurance_tv.setText("[" + endurance + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.charisma_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (charisma == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    charisma++;
                    charisma_tv.setText("[" + charisma + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.charisma_minus:
                if (charisma == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 харизмы", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    charisma--;
                    charisma_tv.setText("[" + charisma + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.intelligence_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (intelligence == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    intelligence++;
                    intelligence_tv.setText("[" + intelligence + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.intelligence_minus:
                if (intelligence == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 интеллекта", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    intelligence--;
                    intelligence_tv.setText("[" + intelligence + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.agility_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (agility == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    agility++;
                    agility_tv.setText("[" + agility + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.agility_minus:
                if (agility == 0)
                    Toast.makeText(getApplicationContext(), "У вас 0 ловкости", Toast.LENGTH_LONG).show();
                else
                {
                    points++;
                    agility--;
                    agility_tv.setText("[" + agility + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.luck_plus:
                if (points == 0)
                    Toast.makeText(getApplicationContext(), "У вас закончились очки", Toast.LENGTH_LONG).show();
                else if (luck == 10)
                    Toast.makeText(getApplicationContext(), "Нельзя вложить более 10 очков в навык", Toast.LENGTH_LONG).show();
                else
                {
                    points--;
                    luck++;
                    luck_tv.setText("[" + luck + "]");
                    tv_name.setText(getString(R.string.abilities_choice, points));
                }
                break;
            case R.id.luck_minus:
                if (luck == 0)
                    Toast.makeText(getApplicationContext(), "У вас уже 0 удачи", Toast.LENGTH_LONG).show();
                else
                {
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
                }
                else
                    Toast.makeText(getApplicationContext(), "У вас остались нераспределённые очки.", Toast.LENGTH_LONG).show();
        }
    }
}