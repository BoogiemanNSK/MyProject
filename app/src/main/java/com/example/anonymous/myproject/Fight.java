package com.example.anonymous.myproject;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Fight extends AppCompatActivity implements View.OnClickListener {

    class Enemy {
        String name;
        int dmg, hp, armor;

        Enemy(String name, int dmg, int hp, int armor) {
            this.name = name;
            this.dmg = dmg;
            this.hp = hp;
            this.armor = armor;
        }
    }

    View view;
    ProgressTextView heroHP, heroArmor, heroMana, enemyHP, enemyArmor;
    TextView battleLog, heroName, enemyName, tv_dsc;
    ImageButton attack, inventory;
    ScrollView fightScrollView;
    Button exit;
    List<Enemy> enemyList;
    Enemy enemy;
    int heroTempArmor = Path.king.armor.armor, enemyNum;
    final private int ATTACK = 1, INVENTORY = 2;

    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        exit = (Button) findViewById(R.id.fightExitButton);
        heroHP = (ProgressTextView) findViewById(R.id.fightHeroHP);
        heroArmor = (ProgressTextView) findViewById(R.id.fightHeroArmor);
        heroMana = (ProgressTextView) findViewById(R.id.fightHeroMana);
        enemyHP = (ProgressTextView) findViewById(R.id.fightEnemyHP);
        enemyArmor = (ProgressTextView) findViewById(R.id.fightEnemyArmor);
        battleLog = (TextView) findViewById(R.id.fightLog);
        attack = (ImageButton) findViewById(R.id.fightAttack);
        inventory = (ImageButton) findViewById(R.id.fightInventory);
        heroName = (TextView) findViewById(R.id.fightHeroName);
        enemyName = (TextView) findViewById(R.id.fightEnemyName);
        fightScrollView = (ScrollView) findViewById(R.id.fightScrollView);

        enemyNum = getIntent().getIntExtra("enemyNum", 0);
        enemyList = new ArrayList<>();

        for (int i = 0; i < enemyNum; i++) {
            enemyList.add(new Enemy(getIntent().getStringExtra("enemyName" + i), getIntent().getIntExtra("enemyDmg" + i, 0),
                    getIntent().getIntExtra("enemyHP" + i, 0), getIntent().getIntExtra("enemyArmor" + i, 0)));
        }

        exit.setVisibility(View.INVISIBLE);
        exit.setClickable(false);

        heroName.setText(Path.king.name);

        heroHP.setMax(Path.king.hp_max);
        heroMana.setMax(Path.king.mana_max);
        heroArmor.setMax(Path.king.armor.armor);

        heroHP.setProgress("HP: " + Path.king.hp + "/" + heroHP.getMax(), Path.king.hp);
        heroMana.setProgress("Mana: " + Path.king.mana + "/" + heroMana.getMax(), Path.king.mana);
        heroArmor.setProgress("Armor: " + heroTempArmor + "/" + heroArmor.getMax(), heroTempArmor);

        setNewEnemy(0);

        attack.setOnClickListener(this);
        inventory.setOnClickListener(this);

        fightScrollView.setSmoothScrollingEnabled(true);

        attack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(ATTACK);
                return false;
            }
        });

        inventory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(INVENTORY);
                return false;
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Fight.this);
        switch (id) {
            case ATTACK:
                builder.setTitle(getString(R.string.attack_word));
                break;
            case INVENTORY:
                builder.setTitle(getString(R.string.inv_word));
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
            case ATTACK:
                tv_dsc.setText(getString(R.string.dsc_attack));
                break;
            case INVENTORY:
                tv_dsc.setText(getString(R.string.dsc_inventory));
                break;
            default:
                break;
        }
    }

    void setNewEnemy(int i) {
        enemy = enemyList.get(i);

        enemyName.setText(enemy.name);
        enemyHP.setMax(enemy.hp);
        enemyArmor.setMax(enemy.armor);

        enemyHP.setProgress("HP: " + enemy.hp + "/" + enemyHP.getMax(), enemy.hp);
        enemyArmor.setProgress("Armor: " + enemy.armor + "/" + enemyArmor.getMax(), enemy.armor);

        battleLog.setText(" " + enemy.name + " готовится атаковать...\n\n");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fightAttack:
                Logic.isFightEnded = false;
                double critChance = Math.random();
                int heroDMG = Path.king.weapon.damage, enemyDMG = enemy.dmg;
                String logMsg = " Своим ударом Вы обескровили противника на " + heroDMG + " HP!\n";

                if (critChance <= Path.king.weapon.critical) {
                    heroDMG = (int) (heroDMG * Path.king.critical_multipler);
                    logMsg = " Кританув немножечко, Вы наносите " + heroDMG + " урона!\n";
                }

                battleLog.setText(battleLog.getText() + logMsg);

                if (enemy.armor > 0) {
                    if (heroDMG >= enemy.armor) {
                        logMsg = " Противник остался без армора!\n";
                        heroDMG -= enemy.armor;
                        enemy.armor = 0;
                        battleLog.setText(battleLog.getText() + logMsg);
                    } else {
                        enemy.armor -= heroDMG;
                        heroDMG = 0;
                    }
                    enemyArmor.setProgress("Armor: " + enemy.armor + "/" + enemyArmor.getMax(), enemy.armor);
                }

                if (enemy.armor == 0) {
                    if (enemy.hp <= heroDMG) {
                        logMsg = enemy.name + " пал замертво!\n";
                        enemy.hp = 0;
                        battleLog.setText(battleLog.getText() + logMsg);
                        enemyHP.setProgress("HP: " + enemy.hp + "/" + enemyHP.getMax(), enemy.hp);
                        enemyList.remove(0);

                        Logic.isFightEnded = true;

                        exit.setVisibility(View.VISIBLE);
                        exit.setClickable(true);

                        attack.setClickable(false);
                        inventory.setClickable(false);

                        if (enemyList.isEmpty()) {
                            Logic.isLastFightWin = true;

                            attack.setOnClickListener(null);
                            inventory.setOnClickListener(null);

                            exit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                        } else {
                            exit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    exit.setVisibility(View.INVISIBLE);
                                    exit.setClickable(false);
                                    exit.setOnClickListener(null);

                                    attack.setClickable(true);
                                    inventory.setClickable(true);

                                    setNewEnemy(0);
                                }
                            });
                        }
                    } else {
                        enemy.hp -= heroDMG;
                        enemyHP.setProgress("HP: " + enemy.hp + "/" + enemyHP.getMax(), enemy.hp);
                    }
                }

                if (!Logic.isFightEnded) {
                    logMsg = "\n " + enemy.name + " нападает!\n";
                    battleLog.setText(battleLog.getText() + logMsg);

                    logMsg = " Противник наносит вам " + enemyDMG + " урона.\n";
                    battleLog.setText(battleLog.getText() + logMsg);

                    if (heroTempArmor > 0) {
                        if (enemyDMG >= heroTempArmor) {
                            logMsg = " Похоже, Вы остались без защиты!\n";
                            enemyDMG -= heroTempArmor;
                            heroTempArmor = 0;
                            battleLog.setText(battleLog.getText() + logMsg);
                        } else {
                            heroTempArmor -= enemyDMG;
                            enemyDMG = 0;
                        }
                        heroArmor.setProgress("Armor: " + heroTempArmor + "/" + heroArmor.getMax(), heroTempArmor);
                    }

                    if (heroTempArmor == 0) {
                        if (Path.king.hp <= enemyDMG) {
                            logMsg = " Силы вас покидают, Вы падаете на землю, глаза закрываются, это конец...\n";
                            Path.king.hp = 0;
                            battleLog.setText(battleLog.getText() + logMsg);
                            heroHP.setProgress("HP: " + Path.king.hp + "/" + heroHP.getMax(), Path.king.hp);

                            Logic.isFightEnded = true;
                            Logic.isLastFightWin = false;

                            attack.setOnClickListener(null);
                            inventory.setOnClickListener(null);

                            exit.setVisibility(View.VISIBLE);
                            exit.setClickable(true);

                            exit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                        } else if (!Logic.isFightEnded) {
                            Path.king.hp -= enemyDMG;
                            heroHP.setProgress("HP: " + Path.king.hp + "/" + heroHP.getMax(), Path.king.hp);
                        }
                    }

                    logMsg = "\n Ваш черёд действовать.\n\n";
                    battleLog.setText(battleLog.getText() + logMsg);
                }

                fightScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        fightScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });

                break;
            case R.id.fightInventory:
                Logic.isFightEnded = false;
                Intent invs = new Intent(this, Inventory.class);
                startActivity(invs);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgress();
    }

    void updateProgress() {
        heroHP.setProgress("HP: " + Path.king.hp + "/" + heroHP.getMax(), Path.king.hp);
        heroMana.setProgress("Mana: " + Path.king.mana + "/" + heroMana.getMax(), Path.king.mana);
    }
}